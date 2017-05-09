package org.ocpsoft.prettytime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by Azimkhan Yerzhan on 5/8/2017
 */
public class PrettyTimeI18n_kk_Test {
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private Locale locale;

    @Before
    public void setUp() throws Exception
    {
        locale = new Locale("kk");
        Locale.setDefault(locale);
    }

    @Test
    public void testPrettyTime()
    {
        PrettyTime p = new PrettyTime(locale);
        assertEquals("дәл қазір", p.format(new Date()));
    }

    @Test
    public void testPrettyTimeCenturies()
    {
        PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("3 ғасыр бұрын", p.format(new Date(0)));

        p = new PrettyTime(new Date(0), locale);
        assertEquals("3 ғасырдан кейін", p.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testCeilingInterval() throws Exception
    {
        Date then = format.parse("20/5/2009");
        Date ref = format.parse("17/6/2009");
        PrettyTime t = new PrettyTime(ref, locale);
        assertEquals("1 ай бұрын", t.format(then));
    }

    @Test
    public void testNullDate() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        Date date = null;
        assertEquals("дәл қазір", t.format(date));
    }

    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime(locale);
        assertEquals("дәл қазір", t.format(new Date()));
    }

    @Test
    public void testRightNowVariance() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("дәл қазір", t.format(new Date(600)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("12 минуттан кейін", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 сағаттан кейін", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 күннен кейін", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 аптадан кейін", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 айдан кейін", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 жылдан кейін", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 онжылдықтан кейін", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 ғасырдан кейін", t.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testMillenniaFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 мыңжылдықтан кейін", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3)));
    }

    /*
     * Past
     */
    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000), locale);
        assertEquals("жана ғана", t.format(new Date(0)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
        assertEquals("12 минут бұрын", t.format(new Date(0)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
        assertEquals("3 сағат бұрын", t.format(new Date(0)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
        assertEquals("3 күн бұрын", t.format(new Date(0)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
        assertEquals("3 апта бұрын", t.format(new Date(0)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
        assertEquals("3 ай бұрын", t.format(new Date(0)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
        assertEquals("3 жыл бұрын", t.format(new Date(0)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
        assertEquals("3 онжылдық бұрын", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("3 ғасыр бұрын", t.format(new Date(0)));
    }

    @Test
    public void testMillenniaAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 10 * 3L), locale);
        // 3 millennia ago
        assertEquals("3 мыңжылдық бұрын", t.format(new Date(0)));
    }

    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }
}
