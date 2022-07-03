<img src="https://www.yegor256.com/images/books/elegant-objects/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/eo-files)](http://www.rultor.com/p/objectionary/eo-files)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Hits-of-Code](https://hitsofcode.com/github/objectionary/eo-files)](https://hitsofcode.com/view/github/objectionary/eo-files)
![Lines of code](https://img.shields.io/tokei/lines/github/objectionary/eo-files)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/objectionary/eo-files/blob/master/LICENSE.txt)

[EO](https://www.eolang.org) objects for date/time manipulations.

To get current time:

```
QQ.dt.now > t
```

To make time from absolute value in **nano-seconds** after Epoch:

```
QQ.dt.time > t
  1656855443000000
```

To make short time interval:

```
QQ.dt.hour.mul 5 > five-hours
QQ.dt.ms.mul 100 > hundred-milliseconds
QQ.dt.month.mul 2 > two-months
```

To print it in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) format
using [strftime](https://man7.org/linux/man-pages/man3/strftime.3.html):

```
QQ.io.stdout
  QQ.txt.sprintf
    "Current time is %s"
    QQ.dt.strftime
      "%Y-%m-%dT%H:%M:%S%z"
      t
```

To parse [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) back to time
using [strptime](https://man7.org/linux/man-pages/man3/strptime.3.html):

```
QQ.dt.strptime > t
  "%Y-%m-%dT%H:%M:%S%z"
  "2022-07-03T20:26:09+0300"
```

The object `QQ.dt.time` has the following attributes:

  * `plus` adds two times
  * `minus` subtracts
  * `eq` compares
  * `gt` is TRUE if greater than
  * `gte` if greater than or equal
  * `lt` if less than
  * `lte` if less than or equal

For example, to add give hours and thirty minutes to current time and then compare it with the time of file creation:

```
QQ.io.stdout
  if.
    gt.
      plus.
        QQ.dt.now
        QQ.dt.hour.mul 5
        QQ.dt.minute.mul 30
      utime.
        QQ.fs.file
          "test.txt"
    "The file is too old"
    "The file is fresh"
```

## How to Contribute

Fork repository, make changes, send us a pull request.
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.

