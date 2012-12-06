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

import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

public class SimpleTimeFormatTest
{
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
   public void testRounding() throws Exception
   {
      PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3 + 1000 * 60 * 45));
      Duration duration = t.approximateDuration(new Date(0));

      assertEquals("4 hours ago", t.format(duration));
      assertEquals("3 hours ago", t.formatUnrounded(duration));
   }

   @Test
   public void testDecorating() throws Exception
   {
      PrettyTime t = new PrettyTime();
      TimeFormat format = new SimpleTimeFormat().setFutureSuffix("from now").setPastSuffix("ago");

      Duration duration = t.approximateDuration(new Date(System.currentTimeMillis() + 1000));
      assertEquals("some time from now", format.decorate(duration, "some time"));

      duration = t.approximateDuration(new Date(System.currentTimeMillis() - 10000));
      assertEquals("some time ago", format.decorate(duration, "some time"));
   }

   // Method tearDown() is called automatically after every test method
   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

}
