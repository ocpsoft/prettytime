package org.ocpsoft.prettytime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class PrettyTimeI18n_TH_Test
{
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    private Locale locale;

    @Before
    public void setUp() throws Exception
    {
        locale = Locale.getDefault();
    }

    @Test
    public void testFromNow()
    {
        PrettyTime prettyTime = new PrettyTime(locale);
        prettyTime.format(new Date());
        Assert.assertEquals("ต่อจากนี้", prettyTime.format(new Date()));
    }

    @Test
    public void testCeilingInterval() throws Exception
    {
        Date then = format.parse("7/8/2015");
        Date ref = format.parse("8/7/2015");
        PrettyTime t = new PrettyTime(ref);
        Assert.assertEquals("1 เดือนก่อน", t.format(then));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Date date = null;
        Assert.assertEquals("ต่อจากนี้", t.format(date));
    }

    @Test
    public void testPrettyTimeDefault()
    {
        PrettyTime p = new PrettyTime(new Date(0), Locale.ROOT);
        assertEquals("ชั่วขณะต่อจากนี้้", p.format(new Date(1)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("12 นาทีต่อจากนี้", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 ชั่วโมงต่อจากนี้", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 วันต่อจากนี้",
                t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 อาทิตย์ต่อจากนี้",
                t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 เดือนต่อจากนี้", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        Assert.assertEquals("3 ทศวรรษต่อจากนี้",
                t.format(new Date(3155692597470L * 3L)));
    }

    /*
    * Past
    */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000));
        Assert.assertEquals("ชั่วขณะก่อน", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
        Assert.assertEquals("12 นาทีก่อน", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
        Assert.assertEquals("3 ชั่วโมงก่อน", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
        Assert.assertEquals("3 วันก่อน", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
        Assert.assertEquals("3 อาทิตย์ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
        Assert.assertEquals("3 เดือนก่อน", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
        Assert.assertEquals("3 ทศวรรษก่อน", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
        Assert.assertEquals("3 ศตวรรษก่อน", t.format(new Date(0)));
    }

    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }
}
