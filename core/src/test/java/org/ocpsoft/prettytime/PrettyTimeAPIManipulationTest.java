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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ocpsoft.prettytime.units.JustNow;

public class PrettyTimeAPIManipulationTest
{
   PrettyTime t = new PrettyTime();

   @Test
   public void testApiMisuse1() throws Exception
   {
      Assert.assertEquals(t.approximateDuration(new Date()), t.approximateDuration(null));
   }

   @Test
   public void testApiMisuse2() throws Exception
   {
      Assert.assertEquals(t.calculatePreciseDuration(new Date()), t.calculatePreciseDuration(null));
   }

   @Test
   public void testApiMisuse3() throws Exception
   {
      t.clearUnits();
   }

   @Test
   public void testApiMisuse4() throws Exception
   {
      Assert.assertEquals(t.format(new Date()), t.format((Date) null));
   }

   @Test
   public void testApiMisuse4_1() throws Exception
   {
      Assert.assertEquals(t.format(new Date()), t.format((Calendar) null));
   }

   @Test
   public void testApiMisuse4_2() throws Exception
   {
      Assert.assertEquals(t.format(new Date()), t.format((Duration) null));
   }

   @Test
   public void testApiMisuse4_3() throws Exception
   {
      Assert.assertEquals(t.format(new Date()), t.format((List<Duration>) null));
   }

   @Test
   public void testApiMisuse5() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(new Date()), t.formatUnrounded((Date) null));
   }

   @Test
   public void testApiMisuse5_1() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(new Date()), t.formatUnrounded((Calendar) null));
   }

   @Test
   public void testApiMisuse5_2() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(new Date()), t.formatUnrounded((Duration) null));
   }

   @Test
   public void testApiMisuse5_3() throws Exception
   {
      Assert.assertEquals(t.formatUnrounded(new Date()), t.formatUnrounded((List<Duration>) null));
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
      t.getUnits();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse13() throws Exception
   {
      t.registerUnit(null, null);
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
      Assert.assertNull(t.getUnit(null));
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
      assertEquals(1000L * 60L * 5L, unit.getMaxQuantity());
      unit.setMaxQuantity(1);
      assertEquals(1, t.getUnit(JustNow.class).getMaxQuantity());
   }
}
