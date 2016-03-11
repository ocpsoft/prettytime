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
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

public class PrettyTimeI18n_AR_Test
{
   SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = new Locale("ar");
      Locale.setDefault(locale);
   }

   @Test
   public void testFromNow()
   {
      PrettyTime prettyTime = new PrettyTime(locale);
      assertEquals("بعد لحظات", prettyTime.format(new Date()));
   }

   @Test
   public void testNullDate() throws Exception
   {
      PrettyTime t = new PrettyTime();
      Date date = null;
      assertEquals("بعد لحظات", t.format(date));
   }

   @Test
   public void testPrettyTimeDefault()
   {
      PrettyTime p = new PrettyTime(locale);
      assertEquals(p.format(new Date()), "بعد لحظات");
   }

   @Test
   public void testMinutesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("بعد 12 دقائق", t.format(new Date(1000 * 60 * 12)));
   }

   @Test
   public void testHoursFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("بعد 3 ساعات", t.format(new Date(1000 * 60 * 60 * 3)));
   }

   @Test
   public void testDaysFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("بعد 3 ايام",
              t.format(new Date(1000 * 60 * 60 * 24 * 3)));
   }

   @Test
   public void testWeeksFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("بعد 3 أسابيع" ,
              t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
   }

   @Test
   public void testCenturiesFromNow() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(0));
      assertEquals("بعد 3 قرون",
              t.format(new Date(3155692597470L * 3L)));
   }

   /*
   * Past
   */
   @Test
   public void testMomentsAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(6000));
      assertEquals("منذ لحظات", t.format(new Date(0)));
   }

   @Test
   public void testMinutesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
      assertEquals("منذ 12 دقائق", t.format(new Date(0)));
   }

   @Test
   public void testHoursAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
      assertEquals("منذ 3 ساعات", t.format(new Date(0)));
   }

   @Test
   public void testDaysAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
      assertEquals("منذ 3 ايام", t.format(new Date(0)));
   }

   @Test
   public void testWeeksAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
      assertEquals("منذ 3 أسابيع", t.format(new Date(0)));
   }

   @Test
   public void testDecadesAgo() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
      assertEquals("منذ 3 عقود", t.format(new Date(0)));
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

}
