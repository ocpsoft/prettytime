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
package org.ocpsoft.prettytime.units;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.TimeUnitComparator;

public class TimeUnitComparatorTest {

   @Test
   public void testComparingOrder() throws Exception
   {
      TimeUnitComparator comparator = new TimeUnitComparator();
      assertEquals(-1, comparator.compare(new Hour(), new Day()));
   }

}
