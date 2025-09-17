package org.eolang.time;

import org.eolang.Data;
import org.eolang.Dataized;
import org.eolang.PhDefault;
import org.eolang.Phi;
import org.eolang.XmirObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@XmirObject(oname = "datetime")
public class Datetime extends PhDefault {
    
    public datetime(final Phi sigma) {
        super(sigma);
        this.add("now", new Now());
        this.add("format", new Format());
        this.add("parse", new Parse());
        this.add("current-year", new CurrentYear());
        this.add("current-month", new CurrentMonth());
        this.add("current-day", new CurrentDay());
    }
    
    private static class Now extends PhDefault {
        public Now() {
            super(Phi.Φ);
            this.add("φ", new NowPhi());
        }
        
        private static class NowPhi implements Phi {
            @Override
            public Object copy() {
                return this;
            }
            
            @Override
            public Phi take(String name) {
                return this;
            }
            
            @Override
            public boolean put(int pos, Phi object) {
                return false;
            }
            
            @Override
            public boolean put(String name, Phi object) {
                return false;
            }
            
            @Override
            public String locator() {
                return "";
            }
            
            @Override
            public String forma() {
                return String.valueOf(System.currentTimeMillis());
            }
        }
    }
    
    private static class Format extends PhDefault {
        public Format() {
            super(Phi.Φ);
            this.add("value", new Data.ToPhi(0L));
            this.add("pattern", new Data.ToPhi(""));
            this.add("φ", new FormatPhi());
        }
        
        private static class FormatPhi implements Phi {
            private long millis;
            private String pattern;
            
            @Override
            public Object copy() {
                return this;
            }
            
            @Override
            public Phi take(String name) {
                return this;
            }
            
            @Override
            public boolean put(int pos, Phi object) {
                return false;
            }
            
            @Override
            public boolean put(String name, Phi object) {
                if ("value".equals(name)) {
                    this.millis = Long.parseLong(new Dataized(object).take(String.class));
                } else if ("pattern".equals(name)) {
                    this.pattern = new Dataized(object).take(String.class);
                }
                return true;
            }
            
            @Override
            public String locator() {
                return "";
            }
            
            @Override
            public String forma() {
                try {
                    Instant instant = Instant.ofEpochMilli(this.millis);
                    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern);
                    return dateTime.format(formatter);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to format datetime", e);
                }
            }
        }
    }
    
    private static class Parse extends PhDefault {
        public Parse() {
            super(Phi.Φ);
            this.add("string", new Data.ToPhi(""));
            this.add("pattern", new Data.ToPhi(""));
            this.add("φ", new ParsePhi());
        }
        
        private static class ParsePhi implements Phi {
            private String dateString;
            private String pattern;
            
            @Override
            public Object copy() {
                return this;
            }
            
            @Override
            public Phi take(String name) {
                return this;
            }
            
            @Override
            public boolean put(int pos, Phi object) {
                return false;
            }
            
            @Override
            public boolean put(String name, Phi object) {
                if ("string".equals(name)) {
                    this.dateString = new Dataized(object).take(String.class);
                } else if ("pattern".equals(name)) {
                    this.pattern = new Dataized(object).take(String.class);
                }
                return true;
            }
            
            @Override
            public String locator() {
                return "";
            }
            
            @Override
            public String forma() {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern);
                    LocalDateTime dateTime = LocalDateTime.parse(this.dateString, formatter);
                    Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                    return String.valueOf(instant.toEpochMilli());
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Failed to parse datetime string: " + this.dateString, e);
                }
            }
        }
    }
    
    private static class CurrentYear extends PhDefault {
        public CurrentYear() {
            super(Phi.Φ);
            this.add("φ", new CurrentYearPhi());
        }
        
        private static class CurrentYearPhi implements Phi {
            @Override
            public Object copy() {
                return this;
            }
            
            @Override
            public Phi take(String name) {
                return this;
            }
            
            @Override
            public boolean put(int pos, Phi object) {
                return false;
            }
            
            @Override
            public boolean put(String name, Phi object) {
                return false;
            }
            
            @Override
            public String locator() {
                return "";
            }
            
            @Override
            public String forma() {
                return String.valueOf(LocalDateTime.now().getYear());
            }
        }
    }
    
    private static class CurrentMonth extends PhDefault {
        public CurrentMonth() {
            super(Phi.Φ);
            this.add("φ", new CurrentMonthPhi());
        }
        
        private static class CurrentMonthPhi implements Phi {
            @Override
            public Object copy() {
                return this;
            }
            
            @Override
            public Phi take(String name) {
                return this;
            }
            
            @Override
            public boolean put(int pos, Phi object) {
                return false;
            }
            
            @Override
            public boolean put(String name, Phi object) {
                return false;
            }
            
            @Override
            public String locator() {
                return "";
            }
            
            @Override
            public String forma() {
                return String.valueOf(LocalDateTime.now().getMonthValue());
            }
        }
    }
    
    private static class CurrentDay extends PhDefault {
        public CurrentDay() {
            super(Phi.Φ);
            this.add("φ", new CurrentDayPhi());
        }
        
        private static class CurrentDayPhi implements Phi {
            @Override
            public Object copy() {
                return this;
            }
            
            @Override
            public Phi take(String name) {
                return this;
            }
            
            @Override
            public boolean put(int pos, Phi object) {
                return false;
            }
            
            @Override
            public boolean put(String name, Phi object) {
                return false;
            }
            
            @Override
            public String locator() {
                return "";
            }
            
            @Override
            public String forma() {
                return String.valueOf(LocalDateTime.now().getDayOfMonth());
            }
        }
    }
}
