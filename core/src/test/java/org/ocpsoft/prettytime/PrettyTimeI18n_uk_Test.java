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
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Ihor Lavrynuk Date: 2013-01-05 Time: 16:57
 */
public class PrettyTimeI18n_uk_Test
{
   private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = new Locale("uk");
      Locale.setDefault(locale);
   }

   @Test
   public void testPrettyTime()
   {
      PrettyTime p = new PrettyTime(locale);
      assertEquals("зараз", p.format(new Date()));
   }

   @Test
   public void testPrettyTimeCenturies()
   {
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 століття тому", p.format(new Date(0)));

      p = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 століття", p.format(new Date(3155692597470L * 3L)));
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("20/5/2009");
      Date ref = format.parse("17/6/2009");
      PrettyTime t = new PrettyTime(ref, locale);
      assertEquals("1 місяць тому", t.format(then));
   }

   @Test
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      Date date = null;
      assertEquals("зараз", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      assertEquals("зараз", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("зараз", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 12 хвилин", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 години", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 дні", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 тижні", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 місяці", t.format(new Date(2629743830L * 3L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 роки", t.format(new Date(2629743830L * 12L * 3L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 десятиліття", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("через 3 століття", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000), locale);
      assertEquals("щойно", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
      assertEquals("12 хвилин тому", t.format(new Date(0)));
   }

   @Test
   public void test1HourAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 1), locale);
      assertEquals("1 годину тому", t.format(new Date(0)));
   }

   @Test
   public void test3HoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
      assertEquals("3 години тому", t.format(new Date(0)));
   }

   @Test
   public void test6HoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 6), locale);
      assertEquals("6 годин тому", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
      assertEquals("3 дні тому", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
      assertEquals("3 тижні тому", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
      assertEquals("3 місяці тому", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
      assertEquals("3 роки тому", t.format(new Date(0)));
   }

   @Test
   public void test8YearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 8L), locale);
      assertEquals("8 років тому", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
      assertEquals("3 десятиліття тому", t.format(new Date(0)));
   }

   @Test
   public void test8DecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 8L), locale);
      assertEquals("8 десятиліть тому", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 століття тому", t.format(new Date(0)));
   }

   @Test
   public void testUnroundedNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("зараз", t.formatUnrounded(new Date(0)));
   }

   @Test
   public void testUnroundedSlightlyAfterNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("зараз", t.formatUnrounded(new Date(600)));
   }

   @Test
   public void testUnroundedFuture() throws Exception
   {
      Object[][] datesAndExpectedResults = {
         {new Date(1000 * 60 * 12), "через 12 хвилин"},
         {new Date(1000 * 60 * 60 * 3), "через 3 години"},
         {new Date(1000 * 60 * 60 * 24 * 3), "через 3 дні"},
         {new Date(1000 * 60 * 60 * 24 * 7 * 3), "через 3 тижні"},
         {new Date(2629743830L * 3L), "через 3 місяці"},
         {new Date(2629743830L * 12L * 3L), "через 3 роки"},
         {new Date(315569259747L * 3L), "через 3 десятиліття"},
         {new Date(3155692597470L * 3L), "через 3 століття"},
      };

      for (Object[] dateAndExpectedResult : datesAndExpectedResults) {
         Date date = (Date) dateAndExpectedResult[0];
         String expectedResult = (String) dateAndExpectedResult[1];

         PrettyTime t = new PrettyTime(new Date(0), locale);
         assertEquals(expectedResult, t.formatUnrounded(date));
      }
   }

   @Test
   public void testUnroundedSlightlyBeforeNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(600), locale);
      assertEquals("щойно", t.formatUnrounded(new Date(0)));
   }

   @Test
   public void testUnroundedPast() throws Exception
   {
      Object[][] referenceDatesAndExpectedResults = {
         {new Date(1000 * 60 * 12), "12 хвилин тому"},
         {new Date(1000 * 60 * 60 * 1), "1 годину тому"},
         {new Date(1000 * 60 * 60 * 3), "3 години тому"},
         {new Date(1000 * 60 * 60 * 6), "6 годин тому"},
         {new Date(1000 * 60 * 60 * 24 * 3), "3 дні тому"},
         {new Date(1000 * 60 * 60 * 24 * 7 * 3), "3 тижні тому"},
         {new Date(2629743830L * 3L), "3 місяці тому"},
         {new Date(2629743830L * 12L * 3L), "3 роки тому"},
         {new Date(2629743830L * 12L * 8L), "8 років тому"},
         {new Date(315569259747L * 3L), "3 десятиліття тому"},
         {new Date(315569259747L * 8L), "8 десятиліть тому"},
         {new Date(3155692597470L * 3L), "3 століття тому"},
      };

      for (Object[] referenceDateAndExpectedResult : referenceDatesAndExpectedResults) {
         Date referenceDate = (Date) referenceDateAndExpectedResult[0];
         String expectedResult = (String) referenceDateAndExpectedResult[1];

         PrettyTime t = new PrettyTime(referenceDate, locale);
         assertEquals(expectedResult, t.formatUnrounded(new Date(0)));
      }
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }
}
