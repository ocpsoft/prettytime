package org.ocpsoft.prettytime;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrettyTimeI18n_BG_Test {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception
    {
       locale = Locale.getDefault();
       Locale.setDefault(new Locale("bg"));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 века", t.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
       assertEquals("преди 3 века", t.format(new Date(0)));
    }

    @Test
    public void testCenturySingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(3155692597470L));
       assertEquals("преди 1 век", t.format(new Date(0)));
    }
    
    @Test
    public void testDaysFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 дни", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
       assertEquals("преди 3 дни", t.format(new Date(0)));
    }

    @Test
    public void testDaySingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24));
       assertEquals("преди 1 ден", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
       assertEquals("преди 3 десетилетия", t.format(new Date(0)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 десетилетия", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testDecadeSingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 1 десетилетие", t.format(new Date(315569259747L)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 часа", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
       assertEquals("преди 3 часа", t.format(new Date(0)));
    }

    @Test
    public void testHourSingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60));
       assertEquals("преди 1 час", t.format(new Date(0)));
    }

    @Test
    public void testRightNow() throws Exception
    {
       PrettyTime t = new PrettyTime();
       assertEquals("в момента", t.format(new Date()));
    }
    
    @Test
    public void testMomentsAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(6000));
       assertEquals("току що", t.format(new Date(0)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 12 минути", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
       assertEquals("преди 12 минути", t.format(new Date(0)));
    }
    
    @Test
    public void testMonthsFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 месеца", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
       assertEquals("преди 3 месеца", t.format(new Date(0)));
    }

    @Test
    public void testMonthSingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(2629743830L));
       assertEquals("преди 1 месец", t.format(new Date(0)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 седмици", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
       assertEquals("преди 3 седмици", t.format(new Date(0)));
    }

    @Test
    public void testWeekSingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7));
       assertEquals("преди 1 седмица", t.format(new Date(0)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       assertEquals("след 3 години", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L));
       assertEquals("преди 3 години", t.format(new Date(0)));
    }

    @Test
    public void testYearSingular() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(2629743830L * 12L));
       assertEquals("преди 1 година", t.format(new Date(0)));
    }

    @Test
    public void testFormattingDurationListInThePast() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38));
       List<Duration> durations = t.calculatePreciseDuration(new Date(0));
       assertEquals("преди 3 дни 15 часа 38 минути", t.format(durations));
    }

    @Test
    public void testFormattingDurationListInTheFuture() throws Exception
    {
       PrettyTime t = new PrettyTime(new Date(0));
       List<Duration> durations = t.calculatePreciseDuration(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
                + 1000 * 60 * 38));
       assertEquals("след 3 дни 15 часа 38 минути", t.format(durations));
    }

    // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception
    {
       Locale.setDefault(locale);
    }

}
