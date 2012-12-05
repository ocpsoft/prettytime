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

import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;

/**
 * @author Bram
 */
public class PrettyTimeI18n_NL_Test
{
   private Locale locale;
   private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

   @Before
   public void setUp() throws Exception
   {
      locale = new Locale("nl");
   }

   @Test
   public void testPrettyTime()
   {
      PrettyTime p = new PrettyTime(locale);
      assertEquals(p.format(new Date()), "op dit moment");
   }

   @Test
   public void testPrettyTimeCenturies()
   {
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 eeuwen geleden", p.format(new Date(0)));

      p = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 eeuwen", p.format(new Date(3155692597470L * 3L)));
   }

   @Test
   public void testCeilingInterval() throws Exception
   {
      Date then = format.parse("20/5/2009");
      Date ref = format.parse("17/6/2009");
      PrettyTime t = new PrettyTime(ref, locale);
      assertEquals("1 maand geleden", t.format(then));
   }

   @Test(expected = IllegalArgumentException.class)
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      Date date = null;
      assertEquals("op dit moment", t.format(date));
   }

   @Test
   public void testRightNow() throws Exception
   {
      PrettyTime t = new PrettyTime(locale);
      assertEquals("op dit moment", t.format(new Date()));
   }

   @Test
   public void testRightNowVariance() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("op dit moment", t.format(new Date(600)));
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 12 minuten", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 uur", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 dagen", t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 weken", t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testMonthsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 maanden", t.format(new Date(2629743830L * 3L)));
   }

   @Test
   public void testYearsFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 jaar", t.format(new Date(2629743830L * 12L * 3L)));
   }

   @Test
   public void testDecadesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 decennia", t.format(new Date(315569259747L * 3L)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0), locale);
      assertEquals("over 3 eeuwen", t.format(new Date(3155692597470L * 3L)));
   }

   /*
    * Past
    */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000), locale);
      assertEquals("een ogenblik geleden", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12), locale);
      assertEquals("12 minuten geleden", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3), locale);
      assertEquals("3 uur geleden", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3), locale);
      assertEquals("3 dagen geleden", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3), locale);
      assertEquals("3 weken geleden", t.format(new Date(0)));
   }

   @Test
   public void testMonthsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 3L), locale);
      assertEquals("3 maanden geleden", t.format(new Date(0)));
   }

   @Test
   public void testYearsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L), locale);
      assertEquals("3 jaar geleden", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L), locale);
      assertEquals("3 decennia geleden", t.format(new Date(0)));
   }

   @Test
   public void testCenturiesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L), locale);
      assertEquals("3 eeuwen geleden", t.format(new Date(0)));
   }
}