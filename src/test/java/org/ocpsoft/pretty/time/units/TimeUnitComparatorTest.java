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
package org.ocpsoft.pretty.time.units;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.pretty.time.units.Day;
import org.ocpsoft.pretty.time.units.Hour;
import org.ocpsoft.pretty.time.units.TimeUnitComparator;

public class TimeUnitComparatorTest {

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception
    {
        locale = Locale.getDefault();
        Locale.setDefault(Locale.ROOT);
    }

    @Test
    public void testComparingOrder() throws Exception
    {
        TimeUnitComparator comparator = new TimeUnitComparator();
        assertEquals(-1, comparator.compare(new Hour(locale), new Day(locale)));
    }

    // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }

}
