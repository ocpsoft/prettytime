package com.ocpsoft.pretty.time;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class PrettyTimeI18nTest_SE {
	private Locale locale;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception
    {
        locale = new Locale("se");
    }

    @Test
    public void testPrettyTime()
    {
        PrettyTime p = new PrettyTime(locale);
        assertEquals(p.format(new Date()), "om ett par ögonblick från nu");
    }

    @Test
    public void testPrettyTimeCenturies()
    {
        PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("för 3 århundraden sedan", p.format(new Date(0)));

        p = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 århundraden från och med nu", p.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testCeilingInterval() throws Exception
    {
        Date then = format.parse("20/5/2009");
        Date ref = format.parse("17/6/2009");
        PrettyTime t = new PrettyTime(ref, locale);
        assertEquals("för 1 månad sedan", t.format(then));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        Date date = null;
        assertEquals("om ett par ögonblick från nu", t.format(date));
    }

    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        assertEquals("om ett par ögonblick från nu", t.format(new Date()));
    }

    @Test
    public void testRightNowVariance() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om ett par ögonblick från nu", t.format(new Date(600)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 12 minuter från och med nu", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 timmar från och med nu", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 dagar från och med nu", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 veckor från och med nu", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 månader från och med nu", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 år från och med nu", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 årtionden från och med nu", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("om 3 århundraden från och med nu", t.format(new Date(3155692597470L * 3L)));
    }

    /*
     * Past
     */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000), locale);
        assertEquals("för ett par ögonblick sedan", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
        assertEquals("för 12 minuter sedan", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
        assertEquals("för 3 timmar sedan", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
        assertEquals("för 3 dagar sedan", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
        assertEquals("för 3 veckor sedan", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
        assertEquals("för 3 månader sedan", t.format(new Date(0)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
        assertEquals("för 3 år sedan", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
        assertEquals("för 3 årtionden sedan", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("för 3 århundraden sedan", t.format(new Date(0)));
    }
}
