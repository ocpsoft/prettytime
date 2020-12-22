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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Locale;

public class PrettyTimeOverrideBundleTest
{
   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = Locale.getDefault();
      Locale.setDefault(Locale.ROOT);
   }

   @Test
   public void testHoursAgo() throws Exception
   {
      final LocalDateTime now = LocalDateTime.now();
      PrettyTime t = new PrettyTime(now, Locale.ENGLISH,
              "org.ocpsoft.prettytime.i18n.override.Resources");
      Assert.assertEquals("3 hours ago override", t.format(now.minusHours(3)));
   }

   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }
}
