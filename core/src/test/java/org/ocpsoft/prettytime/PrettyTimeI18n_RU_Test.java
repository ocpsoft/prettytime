package org.ocpsoft.prettytime;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA. User: Tumin Alexander Date: 2012-12-13 Time: 04:47
 */
public class PrettyTimeI18n_RU_Test
{
   private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = new Locale("ru");
      Locale.setDefault(locale);
   }

   @Test
   public void testPrettyTime()
   {
      PrettyTime p = new PrettyTime(locale);
      assertEquals("сейчас", p.format(new Date()));
   }

   @Test
   public void testPrettyTimeCenturies()
   {
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 века назад", p.format(new Date(0)));

      p = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 века", p.format(new Date(3155692597470L * 3L)));
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("20/5/2009");
      Date ref = format.parse("17/6/2009");
      PrettyTime t = new PrettyTime(ref, locale);
      assertEquals("1 месяц назад", t.format(then));
   }

   @Test
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      Date date = null;
      assertEquals("сейчас", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      assertEquals("сейчас", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("сейчас", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 12 минут", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 часа", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 дня", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 недели", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 месяца", t.format(new Date(2629743830L * 3L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 года", t.format(new Date(2629743830L * 12L * 3L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 десятилетия", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 века", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000), locale);
      assertEquals("только что", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60), locale);
      assertEquals("1 минуту назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 минуту назад", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 3), locale);
      assertEquals("3 минуты назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 минуты назад", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
      assertEquals("12 минут назад", t.formatUnrounded(new Date(0)));
      assertEquals("12 минут назад", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60), locale);
      assertEquals("1 час назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 час назад", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
      assertEquals("3 часа назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 часа назад", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 5), locale);
      assertEquals("5 часов назад", t.formatUnrounded(new Date(0)));
      assertEquals("5 часов назад", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24), locale);
      assertEquals("1 день назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 день назад", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
      assertEquals("3 дня назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 дня назад", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 5), locale);
      assertEquals("5 дней назад", t.formatUnrounded(new Date(0)));
      assertEquals("5 дней назад", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7), locale);
      assertEquals("1 неделю назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 неделю назад", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
      assertEquals("3 недели назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 недели назад", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L), locale);
      assertEquals("1 месяц назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 месяц назад", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
      assertEquals("3 месяца назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 месяца назад", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 5L), locale);
      assertEquals("5 месяцев назад", t.formatUnrounded(new Date(0)));
      assertEquals("5 месяцев назад", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L), locale);
      assertEquals("1 год назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 год назад", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
      assertEquals("3 года назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 года назад", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 5L), locale);
      assertEquals("5 лет назад", t.formatUnrounded(new Date(0)));
      assertEquals("5 лет назад", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L), locale);
      assertEquals("1 десятилетие назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 десятилетие назад", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
      assertEquals("3 десятилетия назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 десятилетия назад", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 5L), locale);
      assertEquals("5 десятилетий назад", t.formatUnrounded(new Date(0)));
      assertEquals("5 десятилетий назад", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo1() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L), locale);
      assertEquals("1 век назад", t.formatUnrounded(new Date(0)));
      assertEquals("1 век назад", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo2() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 века назад", t.formatUnrounded(new Date(0)));
      assertEquals("3 века назад", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo3() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 5L), locale);
      assertEquals("5 веков назад", t.formatUnrounded(new Date(0)));
      assertEquals("5 веков назад", t.format(new Date(0)));
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }
}
