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
package org.ocpsoft.prettytime.i18n;

import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;

public class SimpleTimeFormatTimeQuantifiedNameTest
{
   private Locale locale;

   @Before
   public void setUp() throws Exception
   {
      locale = Locale.getDefault();
      Locale.setDefault(new Locale("yy"));
   }

   @Test
   public void testFuturePluralName() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(0));
      Assert.assertEquals("2 futuredays from now", p.format(new Date(1000 * 60 * 60 * 24 * 2)));
   }

   @Test
   public void testPastPluralName() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 2));
      Assert.assertEquals("2 pastdays ago", p.format(new Date(0)));
   }

   @Test
   public void testFutureSingularName() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(0));
      Assert.assertEquals("1 futureday from now", p.format(new Date(1000 * 60 * 60 * 24)));
   }

   @Test
   public void testPastSingularName() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(1000 * 60 * 60 * 24));
      Assert.assertEquals("1 pastday ago", p.format(new Date(0)));
   }

   @Test
   public void testFuturePluralNameEmpty() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(0));
      Assert.assertEquals("2 from now", p.format(new Date(1000 * 60 * 60 * 2)));
   }

   @Test
   public void testPastPluralNameMissing() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(1000 * 60 * 60 * 2));
      Assert.assertEquals("2 hours ago", p.format(new Date(0)));
   }

   @Test
   public void testFutureSingularNameCopy() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(0));
      Assert.assertEquals("1 hour from now", p.format(new Date(1000 * 60 * 60)));
   }

   @Test
   public void testPastSingularNameNull() throws Exception
   {
      PrettyTime p = new PrettyTime(new Date(1000 * 60 * 60));
      Assert.assertEquals("1 hour ago", p.format(new Date(0)));
   }

   // Method tearDown() is called automatically after every test method
   @After
   public void tearDown() throws Exception
   {
      Locale.setDefault(locale);
   }

}
