package org.eolang.time;

import org.eolang.Data;
import org.eolang.Dataized;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatetimeTest {
    
    @Test
    public void testNow() {
        datetime.Now now = new datetime.Now();
        long result = Long.parseLong(new Dataized(now).take(String.class));
        assertTrue(result > 0);
    }
    
    @Test
    public void testFormat() {
        datetime.Format format = new datetime.Format();
        format.put("value", new Data.ToPhi(0L));
        format.put("pattern", new Data.ToPhi("yyyy-MM-dd"));
        
        String result = new Dataized(format).take(String.class);
        assertEquals("1970-01-01", result);
    }
    
    @Test
    public void testParse() {
        datetime.Parse parse = new datetime.Parse();
        parse.put("string", new Data.ToPhi("2025-01-01"));
        parse.put("pattern", new Data.ToPhi("yyyy-MM-dd"));
        
        String result = new Dataized(parse).take(String.class);
        assertNotNull(result);
        assertTrue(Long.parseLong(result) > 0);
    }
    
    @Test
    public void testCurrentYear() {
        datetime.CurrentYear currentYear = new datetime.CurrentYear();
        int year = Integer.parseInt(new Dataized(currentYear).take(String.class));
        assertEquals(java.time.LocalDateTime.now().getYear(), year);
    }
}
