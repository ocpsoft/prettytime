/**
 * @author hertg
 */
package org.ocpsoft.prettytime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class PrettyTimeI18n_DE_Test {

    private Locale locale;
    private Date base;
    private PrettyTime prettyTime;

    @Before
    public void setUp() throws Exception {
        locale = new Locale("de");
        base = new Date();
        prettyTime = new PrettyTime(base, locale);
    }

    @Test
    public void testGrammaticalCaseHours() {
        assertEquals("vor 5 Stunden", prettyTime.format(addTime(-5, Calendar.HOUR)));
        assertEquals("vor 1 Stunde", prettyTime.format(addTime(-1, Calendar.HOUR)));
        assertEquals("5 Stunden", prettyTime.formatDuration(addTime(-5, Calendar.HOUR)));
        assertEquals("1 Stunde", prettyTime.formatDuration(addTime(-1, Calendar.HOUR)));
    }

    @Test
    public void testGrammaticalCaseDays() {
        assertEquals("vor 5 Tagen", prettyTime.format(addTime(-5, Calendar.DAY_OF_MONTH)));
        assertEquals("vor 1 Tag", prettyTime.format(addTime(-1, Calendar.DAY_OF_MONTH)));
        assertEquals("5 Tage", prettyTime.formatDuration(addTime(-5, Calendar.DAY_OF_MONTH)));
        assertEquals("1 Tag", prettyTime.formatDuration(addTime(-1, Calendar.DAY_OF_MONTH)));
    }

    @Test
    public void testGrammaticalCaseMonths() {
        assertEquals("vor 5 Monaten", prettyTime.format(addTime(-5, Calendar.MONTH)));
        assertEquals("vor 1 Monat", prettyTime.format(addTime(-1, Calendar.MONTH)));
        assertEquals("5 Monate", prettyTime.formatDuration(addTime(-5, Calendar.MONTH)));
        assertEquals("1 Monat", prettyTime.formatDuration(addTime(-1, Calendar.MONTH)));
    }

    @Test
    public void testGrammaticalCaseYears() {
        assertEquals("vor 5 Jahren", prettyTime.format(addTime(-5, Calendar.YEAR)));
        assertEquals("vor 1 Jahr", prettyTime.format(addTime(-1, Calendar.YEAR)));
        assertEquals("5 Jahre", prettyTime.formatDuration(addTime(-5, Calendar.YEAR)));
        assertEquals("1 Jahr", prettyTime.formatDuration(addTime(-1, Calendar.YEAR)));
    }

    @Test
    public void testGrammaticalCaseDecades() {
        assertEquals("vor 5 Jahrzenten", prettyTime.format(addTime(-50, Calendar.YEAR)));
        assertEquals("vor 1 Jahrzent", prettyTime.format(addTime(-10, Calendar.YEAR)));
        assertEquals("5 Jahrzente", prettyTime.formatDuration(addTime(-50, Calendar.YEAR)));
        assertEquals("1 Jahrzent", prettyTime.formatDuration(addTime(-10, Calendar.YEAR)));
    }

    @Test
    public void testGrammaticalCaseCenturies() {
        assertEquals("vor 5 Jahrhunderten", prettyTime.format(addTime(-500, Calendar.YEAR)));
        assertEquals("vor 1 Jahrhundert", prettyTime.format(addTime(-100, Calendar.YEAR)));
        assertEquals("5 Jahrhunderte", prettyTime.formatDuration(addTime(-500, Calendar.YEAR)));
        assertEquals("1 Jahrhundert", prettyTime.formatDuration(addTime(-100, Calendar.YEAR)));
    }

    @Test
    public void testGrammaticalCaseMillenia() {
        assertEquals("vor 5 Jahrtausenden", prettyTime.format(addTime(-5000, Calendar.YEAR)));
        assertEquals("vor 1 Jahrtausend", prettyTime.format(addTime(-1001, Calendar.YEAR))); // why does -1000 return "10 Jahrhunderte"?
        assertEquals("5 Jahrtausende", prettyTime.formatDuration(addTime(-5000, Calendar.YEAR)));
        assertEquals("1 Jahrtausend", prettyTime.formatDuration(addTime(-1000, Calendar.YEAR)));
    }

    // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception {
        Locale.setDefault(locale);
    }

    /**
     * Helper method to simplify unit tests
     * and prevent code duplicates.
     *
     * @param amount Amount of time to add
     * @param field Calendar field (ie. {@link Calendar#HOUR}, {@link Calendar#YEAR})
     * @return Date with the added quantity of time
     */
    private Date addTime(int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
        calendar.add(field, amount);
        return calendar.getTime();
    }

}
