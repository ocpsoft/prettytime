package com.ocpsoft.pretty.time;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicTimeFormatTest {

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception
    {
        locale = Locale.getDefault();
        Locale.setDefault(Locale.ROOT);
    }

    @Test
    public void testRounding() throws Exception
    {
    	PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3 + 1000 * 60 * 45));
        Duration duration = t.approximateDuration(new Date(0));
        TimeFormat format = duration.getUnit().getFormat();
        
        assertEquals("3 hours", format.formatWithoutRounding(duration));
        assertEquals("4 hours", format.formatWithRounding(duration));
        // backward compatibility
        assertEquals("4 hours ago", t.format(duration));
    }
    
    @Test
    public void testDecorating() throws Exception
    {
    	PrettyTime t = new PrettyTime();
        Duration duration = t.approximateDuration(new Date(0));
        TimeFormat format = duration.getUnit().getFormat();
        
        assertEquals("some time from now", format.decorateFuture("some time"));
        assertEquals("some time ago", format.decoratePast("some time"));
    }
    
   // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }

}
