package org.ocpsoft.prettytime;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrettyTimeI18n_PL_Test
{
   private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = new Locale("pl");
      Locale.setDefault(locale);
   }

   @Test
   public void testPrettyTime()
   {
      PrettyTime p = new PrettyTime(locale);
      assertEquals("za chwilę", p.format(new Date()));
   }

   @Test
   public void testPrettyTimeCenturies()
   {
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 wieki temu", p.format(new Date(0)));

      p = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 wieki", p.format(new Date(3155692597470L * 3L)));
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("20/5/2009");
      Date ref = format.parse("17/6/2009");
      PrettyTime t = new PrettyTime(ref, locale);
      assertEquals("1 miesiąc temu", t.format(then));
   }

   @Test
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      Date date = null;
      assertEquals("za chwilę", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      assertEquals("za chwilę", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za chwilę", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 12 minut", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 godziny", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 dni", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 tygodnie", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 miesiące", t.format(new Date(2629743830L * 3L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 lata", t.format(new Date(2629743830L * 12L * 3L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 dekady", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("za 3 wieki", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000), locale);
      assertEquals("przed chwilą", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60), locale);
      assertEquals("1 minutę temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 minutę temu", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 3), locale);
      assertEquals("3 minuty temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 minuty temu", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
      assertEquals("12 minut temu", t.formatUnrounded(new Date(0)));
      assertEquals("12 minut temu", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60), locale);
      assertEquals("1 godzinę temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 godzinę temu", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
      assertEquals("3 godziny temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 godziny temu", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 5), locale);
      assertEquals("5 godzin temu", t.formatUnrounded(new Date(0)));
      assertEquals("5 godzin temu", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24), locale);
      assertEquals("1 dzień temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 dzień temu", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
      assertEquals("3 dni temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 dni temu", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 5), locale);
      assertEquals("5 dni temu", t.formatUnrounded(new Date(0)));
      assertEquals("5 dni temu", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7), locale);
      assertEquals("1 tydzień temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 tydzień temu", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
      assertEquals("3 tygodnie temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 tygodnie temu", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L), locale);
      assertEquals("1 miesiąc temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 miesiąc temu", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
      assertEquals("3 miesiące temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 miesiące temu", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 5L), locale);
      assertEquals("5 miesięcy temu", t.formatUnrounded(new Date(0)));
      assertEquals("5 miesięcy temu", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L), locale);
      assertEquals("1 rok temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 rok temu", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
      assertEquals("3 lata temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 lata temu", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 5L), locale);
      assertEquals("5 lat temu", t.formatUnrounded(new Date(0)));
      assertEquals("5 lat temu", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L), locale);
      assertEquals("1 dekadę temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 dekadę temu", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
      assertEquals("3 dekady temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 dekady temu", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 5L), locale);
      assertEquals("5 dekad temu", t.formatUnrounded(new Date(0)));
      assertEquals("5 dekad temu", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L), locale);
      assertEquals("1 wiek temu", t.formatUnrounded(new Date(0)));
      assertEquals("1 wiek temu", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 wieki temu", t.formatUnrounded(new Date(0)));
      assertEquals("3 wieki temu", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 5L), locale);
      assertEquals("5 wieków temu", t.formatUnrounded(new Date(0)));
      assertEquals("5 wieków temu", t.format(new Date(0)));
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }
}
