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
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

public class PrettyTimeI18n_KO_Test
{
   SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

   // Stores current locale so that it can be restored
   private Locale locale;

   // Method setUp() is called automatically before every test method
   @Before
   public void setUp() throws Exception
   {
      locale = Locale.getDefault();
      Locale.setDefault(Locale.KOREA);
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("5/20/2009");
      Date ref = format.parse("6/17/2009");
      PrettyTime t = new PrettyTime(ref, Locale.KOREA);
      assertEquals("1개월 전", t.format(then));
   }

   @Test(expected=IllegalArgumentException.class)
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime();
      Date date = null;
      assertEquals("지금", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime();
      assertEquals("지금", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("지금", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("12분 후", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("3시간 후", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("3일 후", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("3주 후", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("3개월 후", t.format(new Date(2629743830L * 3L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("3년 후", t.format(new Date(2629743830L * 12L * 3L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("30년 후", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("3세기 후", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000));
      assertEquals("방금", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
      assertEquals("12분 전", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
      assertEquals("3시간 전", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
      assertEquals("3일 전", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
      assertEquals("3주 전", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
      assertEquals("3개월 전", t.format(new Date(0)));
   }

   @Test
   public void testCustomFormat() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      TimeUnit unit = new TimeUnit()
      {
         @Override
         public long getMaxQuantity()
         {
            return 0;
         }

         @Override
         public long getMillisPerUnit()
         {
            return 5000;
         }
      };
      t.clearUnits();
      t.registerUnit(unit, new SimpleTimeFormat()
      .setSingularName("tick").setPluralName("ticks")
      .setPattern("%n %u").setRoundingTolerance(20)
      .setFutureSuffix("... RUN!")
      .setFuturePrefix("self destruct in: ").setPastPrefix("self destruct was: ").setPastSuffix(
               " ago..."));

      assertEquals("self destruct in: 5 ticks ... RUN!", t.format(new Date(25000)));
      t.setReference(new Date(25000));
      assertEquals("self destruct was: 5 ticks ago...", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L));
      assertEquals("3년 전", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
      assertEquals("30년 전", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
      assertEquals("3세기 전", t.format(new Date(0)));
   }

   @Test
   public void testWithinTwoHoursRounding() throws Exception
   {
      PrettyTime t = new PrettyTime();
      assertEquals("2시간 전", t.format(new Date(new Date().getTime() - 6543990)));
   }

   @Test
   public void testPreciseInTheFuture() throws Exception
   {
      PrettyTime t = new PrettyTime();
      List<Duration> durations = t.calculatePreciseDuration(new Date(new Date().getTime() + 1000
               * (10 * 60 + 5 * 60 * 60)));
      assertTrue(durations.size() >= 2); // might be more because of milliseconds between date capturing and result
      // calculation
      assertEquals(5, durations.get(0).getQuantity());
      assertEquals(10, durations.get(1).getQuantity());
   }

   @Test
   public void testPreciseInThePast() throws Exception
   {
      PrettyTime t = new PrettyTime();
      List<Duration> durations = t.calculatePreciseDuration(new Date(new Date().getTime() - 1000
               * (10 * 60 + 5 * 60 * 60)));
      assertTrue(durations.size() >= 2); // might be more because of milliseconds between date capturing and result
      // calculation
      assertEquals(-5, durations.get(0).getQuantity());
      assertEquals(-10, durations.get(1).getQuantity());
   }

   @Test
   public void testFormattingDurationListInThePast() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38));
      List<Duration> durations = t.calculatePreciseDuration(new Date(0));
      assertEquals("3일 15시간 38분 전", t.format(durations));
   }

   @Test
   public void testFormattingDurationListInTheFuture() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      List<Duration> durations = t.calculatePreciseDuration(new Date(1000 * 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15
               + 1000 * 60 * 38));
      assertEquals("3일 15시간 38분 후", t.format(durations));
   }

   @Test
   public void testSetLocale() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
      assertEquals("30년 전", t.format(new Date(0)));
      t.setLocale(Locale.GERMAN);
      assertEquals("vor 3 Jahrzehnten", t.format(new Date(0)));
   }

   // Method tearDown() is called automatically after every test method
   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

}
