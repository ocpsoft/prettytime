package com.ocpsoft.pretty.time;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrettyTimeTest
{
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

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
    public void testCeilingInterval() throws Exception
    {
        Date then = format.parse("5/20/2009");
        Date ref = format.parse("6/17/2009");
        PrettyTime t = new PrettyTime(ref);
        assertEquals("1 month ago", t.format(then));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Date date = null;
        assertEquals("moments from now", t.format(date));
    }

    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime();
        assertEquals("moments from now", t.format(new Date()));
    }

    @Test
    public void testRightNowVariance() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("moments from now", t.format(new Date(600)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("12 minutes from now", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 hours from now", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 days from now", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 weeks from now", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 months from now", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 years from now", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 decades from now", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 centuries from now", t.format(new Date(3155692597470L * 3L)));
    }

    /*
     * Past
     */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000));
        assertEquals("moments ago", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
        assertEquals("12 minutes ago", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
        assertEquals("3 hours ago", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
        assertEquals("3 days ago", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
        assertEquals("3 weeks ago", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
        assertEquals("3 months ago", t.format(new Date(0)));
    }

    @Test
    public void testCustomFormat() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        t.setUnits(new TimeUnit()
        {

            public TimeFormat getFormat()
            {
                return new BasicTimeFormat().setPattern("%n %u").setRoundingTolerance(20).setFutureSuffix("... RUN!")
                        .setFuturePrefix("self destruct in: ").setPastPrefix("self destruct was: ").setPastSuffix(
                                " ago...");
            }

            public long getMaxQuantity()
            {
                return 0;
            }

            public long getMillisPerUnit()
            {
                return 5000;
            }

            public String getName()
            {
                return "tick";
            }

            public String getPluralName()
            {
                return "ticks";
            }
        });
        assertEquals("self destruct in: 5 ticks ... RUN!", t.format(new Date(25000)));
        t.setReference(new Date(25000));
        assertEquals("self destruct was: 5 ticks ago...", t.format(new Date(0)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L));
        assertEquals("3 years ago", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
        assertEquals("3 decades ago", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
        assertEquals("3 centuries ago", t.format(new Date(0)));
    }

    @Test
    public void testWithinTwoHoursRounding() throws Exception
    {
      PrettyTime t = new PrettyTime();
      assertEquals("2 hours ago", t.format(new Date(new Date().getTime() - 6543990)));      
    }

    @Test
    public void testPreciseInTheFuture() throws Exception
    {
      PrettyTime t = new PrettyTime();
      List<Duration> durations = t.calculatePreciseDuration(new Date(new Date().getTime() + 1000*(10*60+5*60*60)));
      assertTrue(durations.size() >= 2); // might be more because of milliseconds between date capturing and result calculation
      assertEquals(5, durations.get(0).getQuantity());
      assertEquals(10, durations.get(1).getQuantity());
    }

    @Test
    public void testPreciseInThePast() throws Exception
    {
      PrettyTime t = new PrettyTime();
      List<Duration> durations = t.calculatePreciseDuration(new Date(new Date().getTime() - 1000*(10*60+5*60*60)));
      assertTrue(durations.size() >= 2); // might be more because of milliseconds between date capturing and result calculation
      assertEquals(-5, durations.get(0).getQuantity());
      assertEquals(-10, durations.get(1).getQuantity());
    }

    // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }

}
