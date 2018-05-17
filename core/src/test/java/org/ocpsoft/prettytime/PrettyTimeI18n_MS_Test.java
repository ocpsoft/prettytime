package org.ocpsoft.prettytime;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by edward_chiang on 13/6/27.
 */
public class PrettyTimeI18n_MS_Test
{
   private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = new Locale("ms");
      Locale.setDefault(new Locale("ms"));
   }

   @Test
   public void testPrettyTime()
   {
      PrettyTime p = new PrettyTime(locale);
      assertEquals("tadi", p.format(new Date()));
   }

   @Test
   public void testPrettyTimeCenturies()
   {
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 abad yang lalu", p.format(new Date(0)));

      p = new PrettyTime(new Date(0), locale);
      assertEquals("3 abad kemudian", p.format(new Date(3155692597470L * 3L)));
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("20/5/2009");
      Date ref = format.parse("17/6/2009");
      PrettyTime t = new PrettyTime(ref, locale);
      assertEquals("1 bulan yang lalu", t.format(then));
   }

   @Test
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      Date date = null;
      assertEquals("tadi", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      assertEquals("tadi", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("tadi", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("12 minit kemudian", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("3 jam kemudian", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("3 hari kemudian", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("3 minggu kemudian", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("3 bulan kemudian", t.format(new Date(2629743830L * 3L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("3 tahun kemudian", t.format(new Date(2629743830L * 12L * 3L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("30 tahun kemudian", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("3 abad kemudian", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000), locale);
      assertEquals("tadi", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
      assertEquals("12 minit yang lalu", t.format(new Date(0)));
   }

   @Test
   public void test1HourAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 1), locale);
      assertEquals("1 jam yang lalu", t.format(new Date(0)));
   }

   @Test
   public void test3HoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
      assertEquals("3 jam yang lalu", t.format(new Date(0)));
   }

   @Test
   public void test6HoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 6), locale);
      assertEquals("6 jam yang lalu", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
      assertEquals("3 hari yang lalu", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
      assertEquals("3 minggu yang lalu", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
      assertEquals("3 bulan yang lalu", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
      assertEquals("3 tahun yang lalu", t.format(new Date(0)));
   }

   @Test
   public void test8YearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 8L), locale);
      assertEquals("8 tahun yang lalu", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
      assertEquals("30 tahun yang lalu", t.format(new Date(0)));
   }

   @Test
   public void test8DecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 8L), locale);
      assertEquals("80 tahun yang lalu", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 abad yang lalu", t.format(new Date(0)));
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }
}
