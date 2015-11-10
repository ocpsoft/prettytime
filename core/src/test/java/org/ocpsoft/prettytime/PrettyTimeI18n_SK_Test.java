/*
 * Copyright 2012 <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ocpsoft.prettytime;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Month;

public class PrettyTimeI18n_SK_Test
{
   SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

   private static Locale locale;

   @BeforeClass
   public static void setUp() throws Exception
   {
      locale = Locale.getDefault();
      Locale.setDefault(new Locale("sk"));
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("5/20/2009");
      Date ref = format.parse("6/17/2009");
      PrettyTime t = new PrettyTime(ref);
      assertEquals("pred 1 mesiacom", t.format(then));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime();
      assertEquals("o chvíľu", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o chvíľu", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      for (TimeUnit u : t.getUnits())
      {
         if (u instanceof JustNow)
         {
            ((JustNow) u).setMaxQuantity(1000L);
         }
      }
      assertEquals("o 1 minútu", t.format(new Date(1000 * 60 * 1)));
      assertEquals("o 3 minúty", t.format(new Date(1000 * 60 * 3)));
      assertEquals("o 12 minút", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {

      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o 1 hodinu", t.format(new Date(1000 * 60 * 60 * 1)));
      assertEquals("o 3 hodiny", t.format(new Date(1000 * 60 * 60 * 3)));
      assertEquals("o 10 hodín", t.format(new Date(1000 * 60 * 60 * 10)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o 1 deň", t.format(new Date(1000 * 60 * 60 * 24 * 1)));
      assertEquals("o 3 dni", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
      assertEquals("o 5 dní", t.format(new Date(1000 * 60 * 60 * 24 * 5)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      for (TimeUnit u : t.getUnits())
      {
         if (u instanceof Month)
         {
            t.removeUnit(u);
         }
      }
      assertEquals("o 1 týždeň", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 1L)));
      assertEquals("o 3 týždne", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3L)));
      assertEquals("o 5 týždňov", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 5L)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o 1 mesiac", t.format(new Date(2629743830L * 1L)));
      assertEquals("o 3 mesiace", t.format(new Date(2629743830L * 3L)));
      assertEquals("o 6 mesiacov", t.format(new Date(2629743830L * 6L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o 1 rok", t.format(new Date(2629743830L * 12L * 1L)));
      assertEquals("o 3 roky", t.format(new Date(2629743830L * 12L * 3L)));
      assertEquals("o 9 rokov", t.format(new Date(2629743830L * 12L * 9L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o 3 desaťročia", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("o 3 storočia", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000));
      assertEquals("pred chvíľou", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
      assertEquals("pred 12 minútami", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo() throws Exception
   {
      Date base = new Date();
      PrettyTime t = new PrettyTime(base);

      assertEquals("pred 1 hodinou", t.format(addTime(base, -1, Calendar.HOUR_OF_DAY)));
      assertEquals("pred 3 hodinami", t.format(addTime(base, -3, Calendar.HOUR_OF_DAY)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      Date base = new Date();
      PrettyTime t = new PrettyTime(base);

      assertEquals("pred 1 dňom", t.format(addTime(base, -1, Calendar.DAY_OF_MONTH)));
      assertEquals("pred 3 dňami", t.format(addTime(base, -3, Calendar.DAY_OF_MONTH)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      Date base = new Date();
      PrettyTime t = new PrettyTime(base);

      assertEquals("pred 1 týždňom", t.format(addTime(base, -1, Calendar.WEEK_OF_MONTH)));
      assertEquals("pred 3 týždňami", t.format(addTime(base, -3, Calendar.WEEK_OF_MONTH)));
   }

   @Test
   public void testMonthsAgo() throws Exception
   {
      Date base = new Date();
      PrettyTime t = new PrettyTime(base);

      assertEquals("pred 1 mesiacom", t.format(addTime(base, -1, Calendar.MONTH)));
      assertEquals("pred 3 mesiacmi", t.format(addTime(base, -3, Calendar.MONTH)));
   }

   @Test
   public void testYearsAgo() throws Exception
   {
      Date base = new Date();
      PrettyTime t = new PrettyTime(base);
      for (TimeUnit u : t.getUnits())
      {
         if (u instanceof Month)
         {
            t.removeUnit(u);
         }
      }
      assertEquals("pred 1 rokom", t.format(addTime(base, -1, Calendar.YEAR)));
      assertEquals("pred 3 rokmi", t.format(addTime(base, -3, Calendar.YEAR)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
      assertEquals("pred 3 desaťročiami", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
      assertEquals("pred 3 storočiami", t.format(new Date(0)));
   }

   @Test
   public void testFormattingDurationListInThePast() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38));
      List<Duration> durations = t.calculatePreciseDuration(new Date(0));
      assertEquals("pred 3 dňami 15 hodinami 38 minútami", t.format(durations));
   }

   @Test
   public void testFormattingDurationListInTheFuture() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      List<Duration> durations = t.calculatePreciseDuration(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
               + 1000 * 60 * 38));
      assertEquals("o 3 dni 15 hodín 38 minút", t.format(durations));
   }

   /**
    * Tests formatApproximateDuration and by proxy, formatDuration.
    * 
    * @throws Exception
    */
   @Test
   public void testFormatApproximateDuration() throws Exception
   {
      long tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10);
      Date tenMinAgo = new Date(System.currentTimeMillis() - tenMinMillis);
      PrettyTime t = new PrettyTime();
      String result = t.formatDuration(tenMinAgo);
      Assert.assertEquals("10 minútami", result);
   }

   @AfterClass
   public static void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

   private Date addTime(Date time, int amount, int field)
   {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(time);
      calendar.add(field, amount);
      return calendar.getTime();
   }

}
