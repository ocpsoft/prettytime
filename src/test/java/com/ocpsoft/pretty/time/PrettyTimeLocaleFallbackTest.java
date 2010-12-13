package com.ocpsoft.pretty.time;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrettyTimeLocaleFallbackTest
{
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception
    {
        locale = Locale.getDefault();
        Locale.setDefault(Locale.JAPAN);
    }

    @Test
    public void testCeilingInterval() throws Exception
    {
        assertEquals(Locale.JAPAN, Locale.getDefault());
        Date then = format.parse("5/20/2009");
        Date ref = format.parse("6/17/2009");
        PrettyTime t = new PrettyTime(ref);
        assertEquals("1 month ago", t.format(then));
    }

    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }

}
