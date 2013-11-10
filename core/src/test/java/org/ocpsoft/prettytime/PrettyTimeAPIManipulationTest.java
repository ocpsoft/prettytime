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

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.ocpsoft.prettytime.units.JustNow;

public class PrettyTimeAPIManipulationTest
{
   Date date = null;
   Duration duration = null;
   List<Duration> list = null;
   PrettyTime t = new PrettyTime();

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse1() throws Exception
   {
      t.approximateDuration(null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse2() throws Exception
   {
      t.calculatePreciseDuration(null);
   }

   @Test
   public void testApiMisuse3() throws Exception
   {
      t.clearUnits();
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse4() throws Exception
   {
      t.format(date);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse5() throws Exception
   {
      t.format(duration);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse6() throws Exception
   {
      t.format(list);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse7() throws Exception
   {
      t.formatUnrounded(date);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse8() throws Exception
   {
      t.formatUnrounded(duration);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse9() throws Exception
   {
      t.getFormat(null);
   }

   @Test
   public void testApiMisuse10() throws Exception
   {
      t.getLocale();
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

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse16() throws Exception
   {
      t.removeUnit((Class<TimeUnit>) null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse17() throws Exception
   {
      t.removeUnit((TimeUnit) null);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse18() throws Exception
   {
      t.getUnit(null);
   }
  
   @Test(expected = IllegalArgumentException.class)
   public void testApiMisuse19() throws Exception
   {
      t.getUnit((Class<TimeUnit>) null);
   }
  
   @Test
   public void testGetUnit() {
       JustNow unit = t.getUnit(JustNow.class);
       assertNotNull(unit);
   }
  
   @Test
   public void testChangeUnit() {
       JustNow unit = t.getUnit(JustNow.class);
       assertEquals(1000L * 60L * 5L, unit.getMaxQuantity());
       unit.setMaxQuantity(1);
       assertEquals(1, t.getUnit(JustNow.class).getMaxQuantity());
   }
}
