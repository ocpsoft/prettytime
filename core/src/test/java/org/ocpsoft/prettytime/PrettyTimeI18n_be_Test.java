package org.ocpsoft.prettytime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * User: Ihor Lavrynuk Date: 2013-01-05 Time: 16:57
 * reedit to Belarusian with IntelliJ IDEA. User: Siarhiej Bahdaniec Date: 2023-10-01 Time: 11:33 PM
 */
public class PrettyTimeI18n_be_Test
{
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private Locale locale;

    @Before
    public void setUp() throws Exception
    {
        locale = new Locale("be");
        Locale.setDefault(locale);
    }

    @Test
    public void testPrettyTime()
    {
        PrettyTime p = new PrettyTime(locale);
        assertEquals("зараз", p.format(new Date()));
    }

    @Test
    public void testPrettyTimeCenturies()
    {
        PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("3 стагоддзі таму", p.format(new Date(0)));

        p = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 стагоддзі", p.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testCeilingInterval() throws Exception
    {
        Date then = format.parse("20/5/2009");
        Date ref = format.parse("17/6/2009");
        PrettyTime t = new PrettyTime(ref, locale);
        assertEquals("1 месяц таму", t.format(then));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        Date date = null;
        assertEquals("зараз", t.format(date));
    }

    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        assertEquals("зараз", t.format(new Date()));
    }

    @Test
    public void testRightNowVariance() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("зараз", t.format(new Date(600)));
    }

    @Test
    public void test1MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 1 хвіліну", t.format(new Date(1000 * 60)));
    }

    @Test
    public void test2MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 2 хвіліны", t.format(new Date(1000 * 60 * 2)));
    }

    @Test
    public void test5MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 5 хвілін", t.format(new Date(1000 * 60 * 5)));
    }

    @Test
    public void test12MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 12 хвілін", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void test21MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 21 хвіліну", t.format(new Date(1000 * 60 * 21)));
    }

    @Test
    public void test23MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 23 хвіліны", t.format(new Date(1000 * 60 * 23)));
    }

    @Test
    public void test25MinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 25 хвілін", t.format(new Date(1000 * 60 * 25)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 гадзіны", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 дні", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 тыдні", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 месяцы", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 гады", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 дзесяцігоддзі", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("праз 3 стагоддзі", t.format(new Date(3155692597470L * 3L)));
    }

    /*
     * Past
     */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000), locale);
        assertEquals("толькі што", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
        assertEquals("12 хвілін таму", t.format(new Date(0)));
    }

    @Test
    public void test1HourAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 1), locale);
        assertEquals("1 гадзіну таму", t.format(new Date(0)));
    }

    @Test
    public void test3HoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
        assertEquals("3 гадзіны таму", t.format(new Date(0)));
    }

    @Test
    public void test6HoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 6), locale);
        assertEquals("6 гадзін таму", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
        assertEquals("3 дні таму", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
        assertEquals("3 тыдні таму", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
        assertEquals("3 месяцы таму", t.format(new Date(0)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
        assertEquals("3 гады таму", t.format(new Date(0)));
    }

    @Test
    public void test8YearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 8L), locale);
        assertEquals("8 гадоў таму", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
        assertEquals("3 дзесяцігоддзі таму", t.format(new Date(0)));
    }

    @Test
    public void test8DecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 8L), locale);
        assertEquals("8 дзесяцігоддзяў таму", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("3 стагоддзі таму", t.format(new Date(0)));
    }

    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }
}
