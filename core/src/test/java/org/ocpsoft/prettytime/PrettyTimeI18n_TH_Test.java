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
        locale = new Locale("TH");
        Locale.setDefault(locale);
    }

    @Test
    public void testLocaleISOCorrectness()
    {
        assertEquals("th", this.locale.getLanguage());
        assertEquals("ไทย", this.locale.getDisplayLanguage());
    }

    @Test
    public void testFromNow()
    {
        PrettyTime prettyTime = new PrettyTime(locale);
        assertEquals("ชั่วขณะต่อจากนี้้ี้้", prettyTime.format(new Date()));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime();
        Date date = null;
        assertEquals("ชั่วขณะต่อจากนี้้ี้้", t.format(date));
    }

    @Test
    public void testPrettyTimeDefault()
    {
        PrettyTime p = new PrettyTime(locale);
        assertEquals(p.format(new Date()), "ชั่วขณะต่อจากนี้้ี้้");
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("12 นาที ต่อจากนี้ี้", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 ชั่วโมง ต่อจากนี้", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 วัน ต่อจากนี้ี้",
                t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 อาทิตย์ ต่อจากนี้ี้",
                t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 เดือน ต่อจากนี้", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0));
        assertEquals("3 ศตวรรษ ต่อจากนี้",
                t.format(new Date(3155692597470L * 3L)));
    }

    /*
    * Past
    */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000));
        assertEquals("ชั่วขณะก่อน", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
        assertEquals("12 นาที ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
        assertEquals("3 ชั่วโมง ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
        assertEquals("3 วัน ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
        assertEquals("3 อาทิตย์ ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
        assertEquals("3 เดือน ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
        assertEquals("3 ทศวรรษ ก่อน", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
        assertEquals("3 ศตวรรษ ก่อน", t.format(new Date(0)));
    }

    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }
}
