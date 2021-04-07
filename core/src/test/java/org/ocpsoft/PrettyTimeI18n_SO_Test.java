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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat;
import org.ocpsoft.prettytime.units.Minute;

public class PrettyTimeTest
{
   private Locale locale;
   private LocalDateTime now;

   @Before
   public void setUp() throws Exception
   {
      locale = Locale.getDefault();
      Locale.setDefault(new Locale("so","SO"));
      now = LocalDateTime.now();
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      PrettyTime prettyTime = new PrettyTime(LocalDate.of(2009, 6, 17));
      Assert.assertEquals("1 bil kahor", prettyTime.format(LocalDate.of(2009, 5, 20)));
   }

   @Test
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime();
      Date date = null;
      Assert.assertEquals("waxyar kadib", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime();
      Assert.assertEquals("waxyar kadib", t.format(new Date()));
   }

   @Test
   public void testRightNowWithReference() throws Exception
   {
      Date now = new Date();
      PrettyTime t = new PrettyTime();
      Assert.assertEquals("waxyar kadib", t.format(now));
   }

   @Test
   public void testCalculatePreciceDuration() throws Exception
   {
      PrettyTime t = new PrettyTime();
      List<Duration> preciseDuration = t.calculatePreciseDuration(
               new Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000) - (2 * 60 * 1000)));
      Assert.assertEquals("2 saac 2 daqiiqo kahor", t.format(preciseDuration));
      Assert.assertEquals("2 saac 2 daqiiqo", t.formatDuration(preciseDuration));
      Assert.assertEquals("waxyar kahor",
               t.format(t.calculatePreciseDuration(new Date(new Date().getTime() - 10))));

      preciseDuration = t.calculatePreciseDuration(now.minusHours(2).minusMinutes(2));
      Assert.assertEquals("2 saac 2 daqiiqo kahor", t.format(preciseDuration));
      Assert.assertEquals("2 saac 2 daqiiqo", t.formatDuration(preciseDuration));
      Assert.assertEquals("waxyar kahor", t.format(t.calculatePreciseDuration(LocalDateTime.now().plusSeconds(1))));
   }

   @Test
   @SuppressWarnings("Digniin")
   public void testCalculatePreciceDurationMillenia() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2014, 8, 15, 0, 0));
      List<Duration> durations = t.calculatePreciseDuration(new Date(0));
      Assert.assertEquals("1 kun sano 9 qarni 4 toban sanooyin 4 sano 8 bilood 1 todobaad 6 maalmood 20 saac 5 daqiiqo kahor",
               t.format(durations));
      Assert.assertEquals("1 kun sano 9 qarni 4 toban sanooyin 4 sano 8 bilood 1 todobaad 6 maalmood 20 saac 5 daqiiqo",
               t.formatDuration(durations));
   }

   @Test
   public void testCalculatePreciseDurationSingleUnit()
   {
      PrettyTime prettyTime = new PrettyTime();
      Minute minutes = new Minute();
      prettyTime.setUnits(minutes);
      Assert.assertEquals("40 daqiiqo kahor", prettyTime.formatUnrounded(prettyTime
               .calculatePreciseDuration(now.minusSeconds(40).minusMinutes(40))));
   }

   @Test
   public void testCalculatePreciseDurationSingleUnitWithFormat()
   {
      PrettyTime prettyTime = new PrettyTime();
      Minute minutes = new Minute();
      TimeFormat format = new SimpleTimeFormat().setPluralName("daqiiqo").setPattern("%n %u");
      prettyTime.setUnits(format, minutes);
      Assert.assertEquals("40 daqiiqo", prettyTime.formatUnrounded(prettyTime
               .calculatePreciseDuration(now.minusSeconds(40).minusMinutes(40))));
   }

   @Test
   public void testCalculatePreciseDuration2()
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("40 daqiiqo kahor", prettyTime.formatUnrounded(prettyTime
               .calculatePreciseDuration(now.minusSeconds(40).minusMinutes(40))));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("waxyar kadib", t.format(now.plus(600, ChronoUnit.MILLIS)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("12 daqiiqo kadib", t.format(now.plusMinutes(12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 saac kadib", t.format(now.plusHours(3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 maalmood kadib", t.format(now.plusDays(3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 todobaad kadib", t.format(now.plusWeeks(3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 bilood kadib", t.format(now.plusMonths(3)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 sano kadib", t.format(now.plusYears(3)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 toban sanooyin kadib", t.format(now.plus(3, ChronoUnit.DECADES)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 qarni kadib", t.format(now.plus(3, ChronoUnit.CENTURIES)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("waxyar kahor", t.format(now.minusSeconds(6)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("12 daqiiqo kahor", t.format(now.minusMinutes(12)));
   }

   @Test
   public void testMinutesFromNowDefaultReference() throws Exception
   {
      PrettyTime t = new PrettyTime();
      Assert.assertEquals("12 daqiiqo kadib", t.format(now.plusMinutes(12)));
   }

   @Test
   public void testHoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 saac kahor", t.format(now.minusHours(3)));
   }

   @Test
   public void testHoursAgoDefaultReference() throws Exception
   {
      PrettyTime t = new PrettyTime();
      Assert.assertEquals("3 saac kahor", t.format(now.minusHours(3)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 maalmood kahor", t.format(now.minusDays(3)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 todobaad kahor", t.format(now.minusWeeks(3)));
   }

   @Test
   public void testMonthsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 bilood kahor", t.format(now.minusMonths(3)));
   }

   @Test
   public void testCustomFormat() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      TimeUnit unit = new TimeUnit() {
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

         @Override
         public boolean isPrecise()
         {
            return false;
         }
      };
      t.clearUnits();
      t.registerUnit(unit, new SimpleTimeFormat()
               .setSingularName("tick").setPluralName("ilbiriqsi")
               .setPattern("%n %u").setRoundingTolerance(20)
               .setFutureSuffix("... BILOW!")
               .setFuturePrefix("isburburi kadib: ").setPastPrefix("isburburintii waxay ahayd: ").setPastSuffix(
                        " kahor..."));

      final LocalDateTime fiveTicks = now.plusSeconds(25);
      Assert.assertEquals("isburburi kadib: 5 tik ... BILOW!", t.format(fiveTicks));
      t.setReference(fiveTicks);
      Assert.assertEquals("isburburintii waxay ahayd: 5 tik kahor...", t.format(now));
   }

   @Test
   public void testYearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 sank kahor", t.format(now.minusYears(3)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 toban sanooyin kahor", t.format(now.minus(3, ChronoUnit.DECADES)));
   }

   @Test
   public void testCenturiesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("3 qarni kahor", t.format(now.minus(3, ChronoUnit.CENTURIES)));
   }

   @Test
   public void testWithinTwoHoursRounding() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      Assert.assertEquals("2 saac kahor", t.format(now.minusHours(1).minusMinutes(45)));
   }

   @Test
   public void testPreciseInTheFuture() throws Exception
   {
      PrettyTime t = new PrettyTime();
      List<Duration> durations = t.calculatePreciseDuration(now.plusHours(5).plusMinutes(10).plusSeconds(1));
      Assert.assertTrue(durations.size() >= 2);
      Assert.assertEquals(5, durations.get(0).getQuantity());
      Assert.assertEquals(10, durations.get(1).getQuantity());
   }

   @Test
   public void testPreciseInThePast() throws Exception
   {
      PrettyTime t = new PrettyTime();
      List<Duration> durations = t.calculatePreciseDuration(now.minusHours(5).minusMinutes(10).minusSeconds(1));
      Assert.assertTrue(durations.size() >= 2);
      Assert.assertEquals(-5, durations.get(0).getQuantity());
      Assert.assertEquals(-10, durations.get(1).getQuantity());
   }

   @Test
   public void testFormattingDurationListInThePast() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      List<Duration> durations = t.calculatePreciseDuration(now.minusDays(3).minusHours(15).minusMinutes(38));
      Assert.assertEquals("3 maalmood 15 saac 38 daqiiqo kahor", t.format(durations));
   }

   @Test
   public void testFormattingDurationListInTheFuture() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      List<Duration> durations = t.calculatePreciseDuration(now.plusDays(3).plusHours(15).plusMinutes(38));
      Assert.assertEquals("3 maalmood 15 saac 38 daqiiqo kadib", t.format(durations));
   }

   @Test
   public void testSetLocale() throws Exception
   {
      PrettyTime t = new PrettyTime(now);
      final LocalDateTime threeDecadesAgo = now.minus(3, ChronoUnit.DECADES);
      Assert.assertEquals("3 toban sanooyin kadib", t.format(threeDecadesAgo));
      t.setLocale(Locale.GERMAN);
      Assert.assertEquals("vor 3 Jahrzehnten", t.format(threeDecadesAgo));
   }

   /**
    * Since {@link PrettyTime#format(Calendar)} is just delegating to {@link PrettyTime#format(Date)} a single simple
    * test is sufficient.
    * 
    * @throws Exception
    */
   @Test
   @Ignore
   public void testCalendarParameter() throws Exception
   {
      Calendar c = Calendar.getInstance();
      Date r = c.getTime();
      PrettyTime t = new PrettyTime();
      t.setLocale(Locale.ENGLISH);
      t.setReference(r);
      c.add(Calendar.YEAR, -1);
      Assert.assertEquals("1 sano kahor", t.format(c));
   }

   /**
    * Tests formatApproximateDuration and by proxy, formatDuration.
    * 
    * @throws Exception
    */
   @Test
   public void testFormatDuration() throws Exception
   {
      long tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10);
      Date tenMinAgo = new Date(System.currentTimeMillis() - tenMinMillis);
      PrettyTime t = new PrettyTime();
      String result = t.formatDuration(tenMinAgo);
      Assert.assertEquals("10 daqiiqo", result);

      result = t.formatDuration(now.minusMinutes(10));
      Assert.assertEquals("10 daqiiqo", result);
   }

   @Test
   public void testFormatDurationWithRounding() throws Exception
   {
      long tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10) + 1000 * 40;
      Date tenMinAgo = new Date(System.currentTimeMillis() - tenMinMillis);
      PrettyTime t = new PrettyTime();
      String result = t.formatDuration(tenMinAgo);
      Assert.assertEquals("11 daqiiqo", result);

      result = t.formatDuration(now.minusMinutes(10).minusSeconds(40));
      Assert.assertEquals("11 daqiiqo", result);
   }

   @Test
   public void testFormatDurationUnrounded() throws Exception
   {
      long tenMinMillis = java.util.concurrent.TimeUnit.MINUTES.toMillis(10) + 1000 * 40;
      Date tenMinAgo = new Date(System.currentTimeMillis() - tenMinMillis);
      PrettyTime t = new PrettyTime();
      String result = t.formatDurationUnrounded(tenMinAgo);
      Assert.assertEquals("10 daqiiqo", result);

      result = t.formatDurationUnrounded(now.minusMinutes(10).minusSeconds(40));
      Assert.assertEquals("10 daqiiqo", result);
   }

   @Test
   public void testFormatList() throws Exception
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("41 daqiiqo kadib",
               prettyTime.format(prettyTime.calculatePreciseDuration(now.minusMinutes(40).minusSeconds(40))));
   }

   @Test
   public void testFormatListUnrounded() throws Exception
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("40 daqiiqo kahor",
               prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(now.minusMinutes(40).minusSeconds(40))));
   }

   @Test
   public void testFormatDurationList() throws Exception
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("41 daqiiqo",
               prettyTime.formatDuration(prettyTime.calculatePreciseDuration(now.minusMinutes(40).minusSeconds(40))));
   }

   @Test
   public void testFormatDurationListUnrounded() throws Exception
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("40 daqiiqo",
               prettyTime.formatDurationUnrounded(prettyTime.calculatePreciseDuration(
                        now.minusMinutes(40).minusSeconds(40))));
   }

   @Test
   public void testCalculatePreciseDuration()
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("41 daqiiqo kahor",
               prettyTime.format(prettyTime.calculatePreciseDuration(now.minusMinutes(40).minusSeconds(40))));
   }

   @Test
   public void testCalculatePreciseDurationUnrounded()
   {
      PrettyTime prettyTime = new PrettyTime();
      prettyTime.clearUnits();
      Minute minutes = new Minute();
      prettyTime.registerUnit(minutes, new ResourcesTimeFormat(minutes));
      Assert.assertEquals("40 daqiiqo kahor",
               prettyTime.formatUnrounded(prettyTime.calculatePreciseDuration(now.minusMinutes(40).minusSeconds(40))));
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

}
