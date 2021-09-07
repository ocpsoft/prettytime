/*
 * @author hertg
 */
package org.ocpsoft.prettytime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrettyTimeI18n_DE_Test
{

    private LocalDateTime base;
    private PrettyTime prettyTime;

    @Before
    public void setUp() throws Exception
    {
        base = LocalDateTime.parse("2020-09-20T12:00:00");
        prettyTime = new PrettyTime(base);
        prettyTime.setLocale(Locale.GERMAN);
    }

    @Test
    public void testGrammaticalCaseMilliseconds()
    {
        assertEquals("gerade eben", prettyTime.format(base.minus(5, ChronoUnit.MILLIS)));
        assertEquals("gerade eben", prettyTime.format(base.minus(1, ChronoUnit.MILLIS)));
        assertEquals("Jetzt", prettyTime.format(base.plus(5, ChronoUnit.MILLIS)));
        assertEquals("Jetzt", prettyTime.format(base.plus(1, ChronoUnit.MILLIS)));

        // the following tests are commented out, because the formatDuration() method
        // only returns an empty string for durations in the milliseconds
        // assertEquals("5 Millisekunden", prettyTime.formatDuration(base.minus(5, Calendar.MILLISECOND)));
        // assertEquals("1 Millisekunde", prettyTime.formatDuration(base.minus(1, Calendar.MILLISECOND)));
    }

    @Test
    public void testGrammaticalCaseSeconds()
    {
        assertEquals("gerade eben", prettyTime.format(base.minus(5, ChronoUnit.SECONDS)));
        assertEquals("gerade eben", prettyTime.format(base.minus(1, ChronoUnit.SECONDS)));
        assertEquals("Jetzt", prettyTime.format(base.plus(5, ChronoUnit.SECONDS)));
        assertEquals("Jetzt", prettyTime.format(base.plus(1, ChronoUnit.SECONDS)));

        // the following tests are commented out, because the formatDuration() method
        // only returns an empty string for durations in the seconds
        // assertEquals("5 Sekunden", prettyTime.formatDuration(base.minus(5, ChronoUnit.SECONDS)));
        // assertEquals("1 Sekunde", prettyTime.formatDuration(base.minus(1, ChronoUnit.SECONDS)));
    }

    @Test
    public void testGrammaticalCaseMinutes()
    {
        assertEquals("vor 5 Minuten", prettyTime.format(base.minus(5, ChronoUnit.MINUTES)));
        assertEquals("vor 1 Minute", prettyTime.format(base.minus(1, ChronoUnit.MINUTES)));
        assertEquals("in 5 Minuten", prettyTime.format(base.plus(5, ChronoUnit.MINUTES)));
        assertEquals("in 1 Minute", prettyTime.format(base.plus(1, ChronoUnit.MINUTES)));
        assertEquals("5 Minuten", prettyTime.formatDuration(base.minus(5, ChronoUnit.MINUTES)));
        assertEquals("1 Minute", prettyTime.formatDuration(base.minus(1, ChronoUnit.MINUTES)));
    }

    @Test
    public void testGrammaticalCaseHours()
    {
        assertEquals("vor 5 Stunden", prettyTime.format(base.minus(5, ChronoUnit.HOURS)));
        assertEquals("vor 1 Stunde", prettyTime.format(base.minus(1, ChronoUnit.HOURS)));
        assertEquals("in 5 Stunden", prettyTime.format(base.plus(5, ChronoUnit.HOURS)));
        assertEquals("in 1 Stunde", prettyTime.format(base.plus(1, ChronoUnit.HOURS)));
        assertEquals("5 Stunden", prettyTime.formatDuration(base.minus(5, ChronoUnit.HOURS)));
        assertEquals("1 Stunde", prettyTime.formatDuration(base.minus(1, ChronoUnit.HOURS)));
    }

    @Test
    public void testGrammaticalCaseDays()
    {
        assertEquals("vor 5 Tagen", prettyTime.format(base.minus(5, ChronoUnit.DAYS)));
        assertEquals("vor 1 Tag", prettyTime.format(base.minus(1, ChronoUnit.DAYS)));
        assertEquals("in 5 Tagen", prettyTime.format(base.plus(5, ChronoUnit.DAYS)));
        assertEquals("in 1 Tag", prettyTime.format(base.plus(1, ChronoUnit.DAYS)));
        assertEquals("5 Tage", prettyTime.formatDuration(base.minus(5, ChronoUnit.DAYS)));
        assertEquals("1 Tag", prettyTime.formatDuration(base.minus(1, ChronoUnit.DAYS)));
    }

    @Test
    public void testGrammaticalCaseMonths()
    {
        assertEquals("vor 5 Monaten", prettyTime.format(base.minus(5, ChronoUnit.MONTHS)));
        assertEquals("vor 1 Monat", prettyTime.format(base.minus(1, ChronoUnit.MONTHS)));
        assertEquals("in 5 Monaten", prettyTime.format(base.plus(5, ChronoUnit.MONTHS)));
        assertEquals("in 1 Monat", prettyTime.format(base.plus(1, ChronoUnit.MONTHS)));
        assertEquals("5 Monate", prettyTime.formatDuration(base.minus(5, ChronoUnit.MONTHS)));
        assertEquals("1 Monat", prettyTime.formatDuration(base.minus(1, ChronoUnit.MONTHS)));
    }

    @Test
    public void testGrammaticalCaseYears()
    {
        assertEquals("vor 5 Jahren", prettyTime.format(base.minus(5, ChronoUnit.YEARS)));
        assertEquals("vor 1 Jahr", prettyTime.format(base.minus(1, ChronoUnit.YEARS)));
        assertEquals("in 5 Jahren", prettyTime.format(base.plus(5, ChronoUnit.YEARS)));
        assertEquals("in 1 Jahr", prettyTime.format(base.plus(13, ChronoUnit.MONTHS)));
        assertEquals("5 Jahre", prettyTime.formatDuration(base.minus(5, ChronoUnit.YEARS)));
        assertEquals("1 Jahr", prettyTime.formatDuration(base.plus(13, ChronoUnit.MONTHS)));
    }

    @Test
    public void testGrammaticalCaseDecades()
    {
        assertEquals("vor 5 Jahrzehnten", prettyTime.format(base.minus(50, ChronoUnit.YEARS)));
        assertEquals("vor 1 Jahrzehnt", prettyTime.format(base.minus(10, ChronoUnit.YEARS)));
        assertEquals("in 5 Jahrzehnten", prettyTime.format(base.plus(50, ChronoUnit.YEARS)));
        assertEquals("in 1 Jahrzehnt", prettyTime.format(base.plus(11, ChronoUnit.YEARS)));
        assertEquals("5 Jahrzehnte", prettyTime.formatDuration(base.minus(50, ChronoUnit.YEARS)));
        assertEquals("1 Jahrzehnt", prettyTime.formatDuration(base.minus(10, ChronoUnit.YEARS)));
    }

    @Test
    public void testGrammaticalCaseCenturies()
    {
        assertEquals("vor 5 Jahrhunderten", prettyTime.format(base.minus(500, ChronoUnit.YEARS)));
        assertEquals("vor 1 Jahrhundert", prettyTime.format(base.minus(100, ChronoUnit.YEARS)));
        assertEquals("in 5 Jahrhunderten", prettyTime.format(base.plus(500, ChronoUnit.YEARS)));
        assertEquals("in 1 Jahrhundert", prettyTime.format(base.plus(101, ChronoUnit.YEARS)));
        assertEquals("5 Jahrhunderte", prettyTime.formatDuration(base.minus(500, ChronoUnit.YEARS)));
        assertEquals("1 Jahrhundert", prettyTime.formatDuration(base.minus(100, ChronoUnit.YEARS)));
    }

    @Test
    public void testGrammaticalCaseMillenia()
    {
        assertEquals("vor 5 Jahrtausenden", prettyTime.format(base.minus(5000, ChronoUnit.YEARS)));
        assertEquals("vor 1 Jahrtausend", prettyTime.format(base.minus(1000, ChronoUnit.YEARS)));
        assertEquals("in 5 Jahrtausenden", prettyTime.format(base.plus(5000, ChronoUnit.YEARS)));
        assertEquals("in 1 Jahrtausend", prettyTime.format(base.plus(1001, ChronoUnit.YEARS)));
        assertEquals("5 Jahrtausende", prettyTime.formatDuration(base.minus(5000, ChronoUnit.YEARS)));
        assertEquals("1 Jahrtausend", prettyTime.formatDuration(base.minus(1000, ChronoUnit.YEARS)));
    }

    @Test
    public void testGrammaticalCasesForMultipleDurations()
    {
        LocalDateTime then = base.minus(2, ChronoUnit.YEARS)
                .minus(3, ChronoUnit.MONTHS)
                .minus(4, ChronoUnit.DAYS)
                .minus(6, ChronoUnit.HOURS)
                .minus(30, ChronoUnit.MINUTES);
        final List<Duration> durations = prettyTime.calculatePreciseDuration(then);

        // note: days are not accurate due to a contained leap year,
        // hours and minutes are not accurate on the scale of years
        // (a prettytime year has always about 365,242 days)
        assertEquals("vor 2 Jahren 3 Monaten 5 Tagen 11 Stunden 25 Minuten", prettyTime.format(durations));
    }
}
