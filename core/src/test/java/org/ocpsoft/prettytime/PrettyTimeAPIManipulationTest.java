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
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ocpsoft.prettytime.units.JustNow;

public class PrettyTimeAPIManipulationTest
{
   PrettyTime t = new PrettyTime();

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuseSetUnits1() throws Exception
   {
      t.setUnits();
   }

   @Test
   public void testApiMisuse1() throws Exception
   {
      Assert.assertEquals(t.approximateDuration(LocalDateTime.now()),
               t.approximateDuration((LocalDateTime) null));
   }

   @Test
   public void testApiMisuse2() throws Exception
   {
      Assert.assertEquals(t.calculatePreciseDuration(LocalDateTime.now()),
               t.calculatePreciseDuration((LocalDateTime) null));
   }

   @Test
   public void testApiMisuse3() throws Exception
   {
      t.clearUnits();
   }

   @Test
   public void testApiMisuse4() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((Date) null));
   }

   @Test
   public void testApiMisuse4_1() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((Calendar) null));
   }

   @Test
   public void testApiMisuse4_2() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((Duration) null));
   }

   @Test
   public void testApiMisuse4_3() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((List<Duration>) null));
   }

   @Test
   public void testApiMisuse4_4() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((Instant) null));
   }

   @Test
   public void testApiMisuse4_5() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((ZonedDateTime) null));
   }

   @Test
   public void testApiMisuse4_6() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((OffsetDateTime) null));
   }

   @Test
   public void testApiMisuse4_7_1() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((LocalDateTime) null, null));
   }

   @Test(expected = NullPointerException.class)
   public void testApiMisuse4_7_2()
   {
      t.format(LocalDateTime.now(), null);
   }

   @Test
   public void testApiMisuse4_8()
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((LocalDateTime) null));
   }

   @Test
   public void testApiMisuse4_9_1() throws Exception
   {
      Assert.assertEquals(t.format(LocalDateTime.now().plusSeconds(1)), t.format((LocalDate) null, null));
   }

   @Test(expected = NullPointerException.class)
   public void testApiMisuse4_9_2()
   {
      t.format(LocalDate.now(), null);
   }

   @Test
   public void testApiMisuse5() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)), t.formatUnrounded((Date) null));
   }

   @Test
   public void testApiMisuse5_1() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)), t.formatUnrounded((Calendar) null));
   }

   @Test
   public void testApiMisuse5_2() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)), t.formatUnrounded((Duration) null));
   }

   @Test
   public void testApiMisuse5_3() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)),
               t.formatUnrounded((List<Duration>) null));
   }

   @Test
   public void testApiMisuse5_4() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)), t.formatUnrounded((Instant) null));
   }

   @Test
   public void testApiMisuse5_5() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)),
               t.formatUnrounded((ZonedDateTime) null));
   }

   @Test
   public void testApiMisuse5_6() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)),
               t.formatUnrounded((OffsetDateTime) null));
   }

   @Test
   public void testApiMisuse5_7_1() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)),
               t.formatUnrounded((LocalDateTime) null, null));
   }

   @Test(expected = NullPointerException.class)
   public void testApiMisuse5_7_2()
   {
      t.formatUnrounded(LocalDateTime.now(), null);
   }

   @Test
   public void testApiMisuse5_8()
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)),
               t.formatUnrounded((LocalDateTime) null));
   }

   @Test
   public void testApiMisuse5_9_1() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(LocalDateTime.now().plusSeconds(1)),
               t.formatUnrounded((LocalDate) null, null));
   }

   @Test(expected = NullPointerException.class)
   public void testApiMisuse5_9_2()
   {
      t.formatUnrounded(LocalDate.now(), null);
   }

   @Test
   public void testApiMisuse9() throws Exception
   {
      Assert.assertNull(t.getFormat(null));
   }

   @Test
   public void testApiMisuse10() throws Exception
   {
      Assert.assertNotNull(t.getLocale());
   }

   @Test
   public void testApiMisuse11() throws Exception
   {
      t.getReference();
   }

   @Test
   public void testApiMisuse12() throws Exception
   {
      t.getReferenceAsLegacyDate();
   }

   @Test
   public void testApiMisuse13() throws Exception
   {
      t.getUnits();
   }

   @Test(expected = NullPointerException.class)
   public void testApiMisuse14_TimeUnit() throws Exception
   {
      t.registerUnit((TimeUnit) null, null);
   }

   @Test(expected = NullPointerException.class)
   public void testApiMisuse14_ChronoUnit()
   {
      t.registerUnit((ChronoUnit) null, null);
   }

   @Test
   public void testApiMisuse15() throws Exception
   {
      t.toString();
   }

   @Test
   public void testApiMisuse16() throws Exception
   {
      Assert.assertNull(t.removeUnit((Class<TimeUnit>) null));
   }

   @Test
   public void testApiMisuse17() throws Exception
   {
      Assert.assertNull(t.removeUnit((TimeUnit) null));
   }

   @Test
   public void testApiMisuse18() throws Exception
   {
      Assert.assertNull(t.getUnit((Class<TimeUnit>) null));
   }

   @Test
   public void testApiMisuse19() throws Exception
   {
      Assert.assertNull(t.getUnit((Class<TimeUnit>) null));
   }

   @Test
   public void testGetUnit()
   {
      JustNow unit = t.getUnit(JustNow.class);
      assertNotNull(unit);
   }

   @Test
   public void testChangeUnit()
   {
      JustNow unit = t.getUnit(JustNow.class);
      assertEquals(1000L * 60L, unit.getMaxQuantity());
      unit.setMaxQuantity(1);
      assertEquals(1, t.getUnit(JustNow.class).getMaxQuantity());
   }
}
