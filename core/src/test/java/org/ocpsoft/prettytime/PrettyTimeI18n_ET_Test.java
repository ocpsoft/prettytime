package org.ocpsoft.prettytime;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.units.JustNow;

public class PrettyTimeI18n_ET_Test {
    private Locale locale;

    @Before
    public void setUp() throws Exception
    {
        locale = new Locale("et");
    }
    
    private PrettyTime newPrettyTimeWOJustNow(Date ref, Locale locale) 
    {
        PrettyTime t = new PrettyTime(ref, locale);
        List<TimeUnit> units = t.getUnits();
        List<TimeFormat> formats = new ArrayList<TimeFormat>();
        for (TimeUnit timeUnit : units) {
            if(!(timeUnit instanceof JustNow)) {
                formats.add(t.getFormat(timeUnit));
            }
        }
        int index = 0;
        t.clearUnits();
        for (TimeUnit timeUnit : units) {
            if(!(timeUnit instanceof JustNow)) {
                t.registerUnit(timeUnit, formats.get(index));
                index++;
            }
        }
        return t;
    }


    @Test
    public void testRightNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("hetke pärast", t.format(new Date(6000)));
    }

    @Test
    public void testMomentsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(6000), locale);
        assertEquals("hetk tagasi", t.format(new Date(0)));
    }
    
    @Test
    public void testMilliSecondsFromNow() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(0), locale);
        assertEquals("13 millisekundi pärast", t.format(new Date(13)));
    }

    @Test
    public void testMilliSecondsAgo() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(13), locale);
        assertEquals("13 millisekundit tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMilliSecondFromNow() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(0), locale);
        assertEquals("millisekundi pärast", t.format(new Date(1)));
    }

    @Test
    public void testMilliSecondAgo() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(1), locale);
        assertEquals("millisekund tagasi", t.format(new Date(0)));
    }
    
    @Test
    public void testSecondsFromNow() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(0), locale);
        assertEquals("13 sekundi pärast", t.format(new Date(1000 * 13)));
    }

    @Test
    public void testSecondsAgo() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(1000 * 13), locale);
        assertEquals("13 sekundit tagasi", t.format(new Date(0)));
    }

    @Test
    public void testSecondFromNow() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(0), locale);
        assertEquals("sekundi pärast", t.format(new Date(1000  * 1)));
    }

    @Test
    public void testSecondAgo() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(1000  * 1), locale);
        assertEquals("sekund tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMinutesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("13 minuti pärast", t.format(new Date(1000 * 60  * 13)));
    }

    @Test
    public void testMinutesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 13), locale);
        assertEquals("13 minutit tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMinuteFromNow() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(0), locale);
        assertEquals("minuti pärast", t.format(new Date(1000 * 60 * 1)));
    }

    @Test
    public void testMinuteAgo() throws Exception
    {
        PrettyTime t = newPrettyTimeWOJustNow(new Date(1000 * 60 * 1), locale);
        assertEquals("minut tagasi", t.format(new Date(0)));
    }

    @Test
    public void testHoursFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 tunni pärast", t.format(new Date(1000 * 60 * 60 * 3)));
    }

    @Test
    public void testHoursAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
        assertEquals("3 tundi tagasi", t.format(new Date(0)));
    }

    @Test
    public void testHoursFromNowSingle() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("tunni pärast", t.format(new Date(1000 * 60 * 60 * 1)));
    }

    @Test
    public void testHoursAgoSingle() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 1), locale);
        assertEquals("tund tagasi", t.format(new Date(0)));
    }

    @Test
    public void testDaysFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 päeva pärast", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
    }

    @Test
    public void testDaysAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
        assertEquals("3 päeva tagasi", t.format(new Date(0)));
    }

    @Test
    public void testDaysFromNowSingle() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("homme", t.format(new Date(1000 * 60 * 60 * 24 * 1)));
    }

    @Test
    public void testDaysAgoSingle() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 1), locale);
        assertEquals("eile", t.format(new Date(0)));
    }

    @Test
    public void testWeeksFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 nädala pärast", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
    }

    @Test
    public void testWeeksAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
        assertEquals("3 nädalat tagasi", t.format(new Date(0)));
    }

    @Test
    public void testWeeksFromNowSingle() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("nädala pärast", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 1)));
    }

    @Test
    public void testWeeksAgoSingle() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 1), locale);
        assertEquals("nädal tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMonthsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 kuu pärast", t.format(new Date(1000L * 60 * 60 * 24 * 30 * 3)));
    }

    @Test
    public void testMonthsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 30 * 3), locale);
        assertEquals("3 kuud tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMonthFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("kuu pärast", t.format(new Date(1000L * 60 * 60 * 24 * 30 * 1)));
    }

    @Test
    public void testMonthAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 30 * 1), locale);
        assertEquals("kuu tagasi", t.format(new Date(0)));
    }

    @Test
    public void testYearsFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 aasta pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 3)));
    }

    @Test
    public void testYearsAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 3), locale);
        assertEquals("3 aastat tagasi", t.format(new Date(0)));
    }

    @Test
    public void testYearFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("aasta pärast", t.format(new Date(1000L * 60 * 60 * 24 * 366 * 1)));
    }

    @Test
    public void testYearAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 366 * 1), locale);
        assertEquals("aasta tagasi", t.format(new Date(0)));
    }

    @Test
    public void testDecadesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 aastakümne pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 10 * 3)));
    }

    @Test
    public void testDecadesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 10 * 3), locale);
        assertEquals("3 aastakümmet tagasi", t.format(new Date(0)));
    }

    @Test
    public void testDecadeFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("aastakümne pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 11 * 1)));
    }

    @Test
    public void testDecadeAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 11), locale);
        assertEquals("aastakümme tagasi", t.format(new Date(0)));
    }

    @Test
    public void testCenturiesFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 sajandi pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 100 * 3)));
    }

    @Test
    public void testCenturiesAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 100 * 3), locale);
        assertEquals("3 sajandit tagasi", t.format(new Date(0)));
    }

    @Test
    public void testCenturyFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("sajandi pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 101)));
    }

    @Test
    public void testCenturyAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 101), locale);
        assertEquals("sajand tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMillenniaFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("3 aastatuhande pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3)));
    }

    @Test
    public void testMillenniaAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 1000 * 3), locale);
        assertEquals("3 aastatuhandet tagasi", t.format(new Date(0)));
    }

    @Test
    public void testMillenniumFromNow() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        assertEquals("aastatuhande pärast", t.format(new Date(1000L * 60 * 60 * 24 * 365 * 1001)));
    }

    @Test
    public void testMillenniumAgo() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000L * 60 * 60 * 24 * 365 * 1001), locale);
        assertEquals("aastatuhat tagasi", t.format(new Date(0)));
    }

    @Test
    public void testFormattingDurationListInThePast() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38), locale);
        List<Duration> durations = t.calculatePreciseDuration(new Date(0));
        assertEquals("3 päeva 15 tundi 38 minutit tagasi", t.format(durations));
    }

    @Test
    public void testFormattingDurationListInTheFuture() throws Exception
    {
        PrettyTime t = new PrettyTime(new Date(0), locale);
        List<Duration> durations = t.calculatePreciseDuration(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
                + 1000 * 60 * 38));
        assertEquals("3 päeva 15 tunni 38 minuti pärast", t.format(durations));
    }


}
