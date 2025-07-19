package org.ocpsoft.prettytime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat;
import org.ocpsoft.prettytime.units.Minute;

public class PrettyTimeI18n_EL_Test
{
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    private Locale locale;

    @Before
    public void setUp() throws Exception
    {
        locale = Locale.getDefault();
        Locale.setDefault(Locale.forLanguageTag("el"));
    }

    @Test
    public void testCeilingInterval() throws Exception
    {
        Date then = format.parse("5/20/2009");
        Date ref = format.parse("6/17/2009");
        PrettyTime t = new PrettyTime(ref);
        Assert.assertEquals("Πριν από 1 μήνα", t.format(then));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime();
        final String format = t.format((Date) null);
        Assert.assertTrue("πριν από στιγμές".equals(format) || "στιγμές από τώρα".equals(format));
    }

    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Assert.assertEquals("στιγμές από τώρα", t.format(new Date()));
    }

    @Test
    public void testCalculatePreciceDuration() throws Exception
    {
        Date now = new Date();
        PrettyTime t = new PrettyTime(now);
        List<Duration> preciseDuration = t.calculatePreciseDuration(
                    new Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000) - (2 * 60 * 1000)));
        Assert.assertEquals("Πριν από 2 ώρες 2 λεπτά", t.format(preciseDuration));
        Assert.assertEquals("2 ώρες 2 λεπτά", t.formatDuration(preciseDuration));
        Assert.assertEquals("στιγμές από τώρα", t.format(t.calculatePreciseDuration(now)));
    }

    @Test
    public void testCalculatePreciseDuration2()
    {
        PrettyTime prettyTime = new PrettyTime();
        prettyTime.clearUnits();
        Minute minutes = new Minute();
        prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
        Assert.assertEquals("Πριν από 40 λεπτά", prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(
                    new Date(new Date().getTime() - 40 * 60 * 1000 - 40 * 1000))));
    }

    @Test
    public void testRightNowVariance() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("στιγμές από τώρα", t.format(new Date(600)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("12 λεπτά από τώρα", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 ώρες από τώρα", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 ημέρες από τώρα", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 εβδομάδες από τώρα", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 μήνες από τώρα", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 έτη από τώρα", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 δεκαετίες από τώρα", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 αιώνες από τώρα", t.format(new Date(3155692597470L * 3L)));
    }

    /*
     * Past
     */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000));
        Assert.assertEquals("πριν από στιγμές", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
        Assert.assertEquals("Πριν από 12 λεπτά", t.format(new Date(0)));
    }

    @Test
    public void testMinutesFromNowDefaultReference() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Assert.assertEquals("12 λεπτά από τώρα", t.format(new Date(System.currentTimeMillis() + 1000 * 60 * 12)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
        Assert.assertEquals("Πριν από 3 ώρες", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgoDefaultReference() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Assert.assertEquals("Πριν από 3 ώρες", t.format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
        Assert.assertEquals("Πριν από 3 ημέρες", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
        Assert.assertEquals("Πριν από 3 εβδομάδες", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
        Assert.assertEquals("Πριν από 3 μήνες", t.format(new Date(0)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L));
        Assert.assertEquals("Πριν από 3 έτη", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
        Assert.assertEquals("Πριν από 3 δεκαετίες", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
        Assert.assertEquals("Πριν από 3 αιώνες", t.format(new Date(0)));
    }

    @Test
    public void testWithinTwoHoursRounding() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Assert.assertEquals("Πριν από 2 ώρες", t.format(new Date(new Date().getTime() - 6543990)));
    }

    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }
}
