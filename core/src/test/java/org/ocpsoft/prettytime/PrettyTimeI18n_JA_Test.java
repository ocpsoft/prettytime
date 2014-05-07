package org.ocpsoft.prettytime;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Most languages (using the standard %n %u pattern) will render something like: {prefix} {number} {unitName} {suffix}.
 */
public class PrettyTimeI18n_JA_Test {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception
    {
        locale = new Locale("ja");
        Locale.setDefault(Locale.JAPAN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        Date date = null;
        // moments from now
        assertEquals("今からすぐ", t.format(date));
    }

    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        // moments from now
        assertEquals("今からすぐ", t.format(new Date()));
    }

    @Test
    public void testRightNowVariance() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // moments from now
        assertEquals("今からすぐ", t.format(new Date(600)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 12 minutes from now
        assertEquals("12 minutes from now", "今から12分", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 3 hours from now
        assertEquals("3 hours from now", "今から3時間", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 3 days from now
        assertEquals("3 days from now", "今から3日間", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 3 weeks from now
        assertEquals("3 weeks from now", "今から3週間", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 3 months from now
        assertEquals("3 months from now", "今から3ヶ月", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 3 years from now
        assertEquals("3 years from now", "3年後の", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testOneDecadeFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 1 decade from now
        assertEquals("今から1年間", t.format(new Date(315569259747L * 1L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        // 3 centuries from now
        assertEquals("3 centuries from now", "今から3世紀にもわたっ", t.format(new Date(3155692597470L * 3L)));
    }

    /*
     * Past
     */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000), locale);
        // moments ago
        assertEquals("さっき", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
        // 12 minutes ago
        assertEquals("12分前", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
        // 3 hours ago
        assertEquals("3時間前", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
        // 3 days ago
        assertEquals("3日前", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
        // 3 weeks ago
        assertEquals("3週間前", t.format(new Date(0)));
    }

    @Test
    public void testOneMonthAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 1L), locale);
        // 3 months ago
        assertEquals("1 months ago", "1ヶ月前", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
        // 3 months ago
        assertEquals("3 months ago", "3ヶ月前", t.format(new Date(0)));
    }

    @Test
    public void testCustomFormat() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        TimeUnit unit = new TimeUnit()
        {
            @Override
            public long getMaxQuantity()
            {
                return 0;
            }

            @Override
            public long getMillisPerUnit()
            {
                return 5000;
            }
        };
        t.clearUnits();
        t.registerUnit(unit, new SimpleTimeFormat()
                .setSingularName("tick").setPluralName("ticks")
                .setPattern("%n %u").setRoundingTolerance(20)
                .setFutureSuffix("... RUN!")
                .setFuturePrefix("self destruct in: ").setPastPrefix("self destruct was: ").setPastSuffix(
                        " ago..."));

        assertEquals("self destruct in: 5 ticks ... RUN!", t.format(new Date(25000)));
        t.setReference(new Date(25000));
        assertEquals("self destruct was: 5 ticks ago...", t.format(new Date(0)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
        // 3 years ago
        assertEquals("3年前", t.format(new Date(0)));
    }

    @Test
    public void testDecadeAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 1L), locale);
        // 1 decade ago
        assertEquals("1年前", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
        // 3 centuries ago
        assertEquals("3 centuries ago", "3世紀前", t.format(new Date(0)));
    }

    @Test
    public void testWithinTwoHoursRounding() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        // 2 hours ago
        assertEquals("2時間前", t.format(new Date(new Date().getTime() - 6543990)));
    }

    @Test
    public void testPreciseInTheFuture() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        List<Duration> durations = t.calculatePreciseDuration(new Date(new Date().getTime() + 1000
                * (10 * 60 + 5 * 60 * 60)));
        assertTrue(durations.size() >= 2); // might be more because of milliseconds between date capturing and result
        // calculation
        assertEquals(5, durations.get(0).getQuantity());
        assertEquals(10, durations.get(1).getQuantity());
    }

    @Test
    public void testPreciseInThePast() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        List<Duration> durations = t.calculatePreciseDuration(new Date(new Date().getTime() - 1000
                * (10 * 60 + 5 * 60 * 60)));
        assertTrue(durations.size() >= 2); // might be more because of milliseconds between date capturing and result
        // calculation
        assertEquals(-5, durations.get(0).getQuantity());
        assertEquals(-10, durations.get(1).getQuantity());
    }

    @Test
    public void testSetLocale() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 1L), locale);
        // 1 decade ago
        assertEquals("1年前", t.format(new Date(0)));
        t.setLocale(Locale.GERMAN);
        assertEquals("vor 1 Jahrzehnt", t.format(new Date(0)));
    }

    /**
     * Since {@link PrettyTime#format(java.util.Calendar)} is just delegating to {@link PrettyTime#format(Date)} a single simple
     * test is sufficient.
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testCalendarParameter() throws Exception
    {
        Calendar c = Calendar.getInstance();
        Date r = c.getTime();
        PrettyTime t = new PrettyTime();
        t.setLocale(Locale.ENGLISH);
        t.setReference(r);
        c.add(Calendar.YEAR, -1);
        // 1 year ago
        assertEquals("1年前", t.format(c));
    }

    /**
     * Tests formatApproximateDuration and by proxy, formatDuration.
     *
     * @throws Exception
     */
    @Test
    public void testFormatApproximateDuration() throws Exception
    {
        long tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10);
        Date tenMinAgo = new Date(System.currentTimeMillis()-tenMinMillis);
        PrettyTime t = new PrettyTime();
        String result = t.formatApproximateDuration(tenMinAgo);
        // 10 minutes
        assert result.equals("10分");
    }

    // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }
}
