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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * All the tests for PrettyTime.
 * 
 * @author Thomas Weitzel <tweitzel@synformation.com>
 */
public class PrettyTimeI18n_Test
{

   /*
    * A note when you want to use the YourKit profiler: To use the YourKit
    * profiler (http://yourkit.com), run with VM argument for profiling:
    * -agentlib:yjpagent=onexit=snapshot,tracing
    */

   // Stores current locale so that it can be restored
   private Locale locale;

   // Method setUp() is called automatically before every test method
   @Before
   public void setUp() throws Exception
   {
      locale = Locale.getDefault();
   }

   @Test
   public void testPrettyTimeDefault()
   {
      // The default resource bundle should be used
      PrettyTime p = new PrettyTime(new Date(0), Locale.ROOT);
      assertEquals("moments from now", p.format(new Date(1)));
   }

   @Test
   public void testPrettyTimeGerman()
   {
      // The German resource bundle should be used
      PrettyTime p = new PrettyTime(Locale.GERMAN);
      p.setReference(new Date(0));
      assertEquals("Jetzt", p.format(new Date(1)));
   }

   @Test
   public void testPrettyTimeSpanish()
   {
      // The Spanish resource bundle should be used
      PrettyTime p = new PrettyTime(new Locale("es"));
      assertEquals("hace instantes", p.format(LocalDateTime.now().minusSeconds(1)));
   }

   @Test
   public void testPrettyTimeDefaultCenturies()
   {
      // The default resource bundle should be used
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), Locale.ROOT);
      assertEquals("3 centuries ago", p.format(new Date(0)));
   }

   @Test
   public void testPrettyTimeGermanCenturies()
   {
      // The default resource bundle should be used
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L), Locale.GERMAN);
      assertEquals(p.format(new Date(0)), "vor 3 Jahrhunderten");
   }

   @Test
   public void testPrettyTimeViaDefaultLocaleDefault()
   {
      // The default resource bundle should be used
      Locale.setDefault(Locale.ROOT);
      PrettyTime p = new PrettyTime(new Date(0));
      assertEquals(p.format(new Date(1)), "moments from now");
   }

   @Test
   public void testPrettyTimeViaDefaultLocaleGerman()
   {
      // The German resource bundle should be used
      Locale.setDefault(Locale.GERMAN);
      PrettyTime p = new PrettyTime(new Date(0));
      assertEquals(p.format(new Date(1)), "Jetzt");
   }

   @Test
   public void testPrettyTimeViaDefaultLocaleDefaultCenturies()
   {
      // The default resource bundle should be used
      Locale.setDefault(Locale.ROOT);
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L));
      assertEquals(p.format(new Date(0)), "3 centuries ago");
   }

   @Test
   public void testPrettyTimeViaDefaultLocaleGermanCenturies()
   {
      // The default resource bundle should be used
      Locale.setDefault(Locale.GERMAN);
      PrettyTime p = new PrettyTime(new Date(3155692597470L * 3L));
      assertEquals(p.format(new Date(0)), "vor 3 Jahrhunderten");
   }

   @Test
   public void testPrettyTimeRootLocale()
   {
      long t = 1L;
      PrettyTime p = new PrettyTime(new Date(0), Locale.ROOT);
      while (1000L * 60L * 60L * 24L * 365L * 1000000L > t) {
         assertEquals(p.format(new Date(0)).endsWith("now"), true);
         t *= 2L;
      }
   }

   @Test
   public void testPrettyTimeGermanLocale()
   {
      long t = 1L;
      PrettyTime p = new PrettyTime(new Date(0), Locale.GERMAN);
      while (1000L * 60L * 60L * 24L * 365L * 1000000L > t) {
         assertEquals(p.format(new Date(0)).startsWith("in") || p.format(new Date(0)).startsWith("Jetzt"), true);
         t *= 2L;
      }
   }

   // Method tearDown() is called automatically after every test method
   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

}
