package org.ocpsoft.prettytime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by vincentpaing on 5/17/17.
 */
public class PrettyTimel18n_MM_Test {

   /*
    * A note when you want to use the YourKit profiler: To use the YourKit
    * profiler (http://yourkit.com), run with VM argument for profiling:
    * -agentlib:yjpagent=onexit=snapshot,tracing
    */

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception {
        locale = new Locale("mm");
        Locale.setDefault(locale);
    }

    @Test
    public void testNullDate() throws Exception {
        PrettyTime t = new PrettyTime();
        Date date = null;
        assertEquals("ခေတ္တ မကြာမီ", t.format(date));
    }

    @Test
    public void testRightNow() throws Exception {
        PrettyTime t = new PrettyTime();
        assertEquals("ခေတ္တ မကြာမီ", t.format(new Date()));
    }

    @Test
    public void testMinutes() throws Exception {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
        assertEquals("12 မိနစ် အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 12 မိနစ် အကြာ", t.format(new Date(1000 * 60 * 12)));
    }

    @Test
    public void testHours() throws Exception {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 5), locale);
        assertEquals("5 နာရီ အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 5 နာရီ အကြာ", t.format(new Date(1000 * 60 * 60 * 5)));
    }

    @Test
    public void testDays() throws Exception {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
        assertEquals("3 ရက် အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 3 ရက် အကြာ", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testWeeks() throws Exception {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 2), locale);
        assertEquals("2 ရက်သတ္တပတ် အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 2 ရက်သတ္တပတ် အကြာ", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 2)));
    }

    @Test
    public void testMonths() throws Exception {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
        assertEquals("3 လ အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 3 လ အကြာ", t.format(new Date(2629743830L * 3L)));
    }

    @Test
    public void testYears() throws Exception {
        PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
        assertEquals("3 နှစ် အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 3 နှစ် အကြာ", t.format(new Date(2629743830L * 12L * 3L)));
    }

    @Test
    public void testDecades() throws Exception {
        PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
        assertEquals("ဆယ်စုနှစ် 3 နှစ်အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ ဆယ်စုနှစ် 3 နှစ်အကြာ", t.format(new Date(315569259747L * 3L)));
    }

    @Test
    public void testCenturies() throws Exception {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("3 ရာစု အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 3 ရာစု အကြာ", t.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testCentury() throws Exception {
        PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
        assertEquals("3 ရာစု အကြာက", t.format(new Date(0)));

        t = new PrettyTime(new Date(0));
        assertEquals("ယခုမှ 3 ရာစု အကြာ", t.format(new Date(3155692597470L * 3L)));
    }

    @Test
    public void testFormattingDurationList() throws Exception {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38));
        List<Duration> durations = t.calculatePreciseDuration(new Date(0));
        assertEquals("3 ရက် 15 နာရီ 38 မိနစ် အကြာက", t.format(durations));

        t = new PrettyTime(new Date(0));
        durations = t.calculatePreciseDuration(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
                + 1000 * 60 * 38));
        assertEquals("ယခုမှ 3 ရက် 15 နာရီ 38 မိနစ် အကြာ", t.format(durations));
    }
}
