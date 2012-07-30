package org.ocpsoft.prettytime.i18n;
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


import java.util.ListResourceBundle;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.Minute;

public class Resources_xx extends ListResourceBundle implements TimeFormatProvider
{
   private static final Object[][] OBJECTS = new Object[][] {};

   @Override
   public Object[][] getContents()
   {
      return OBJECTS;
   }

   @Override
   public TimeFormat getFormatFor(TimeUnit t)
   {
      if (t instanceof Minute)
      {
         return new TimeFormat() {

            @Override
            public String decorate(Duration duration, String time)
            {
               String result = duration.getQuantityRounded(50) > 1 ? time + "i" : "o";
               result += duration.isInPast() ? " ago" : " from now";
               return result;
            }

            @Override
            public String decorateUnrounded(Duration duration, String time)
            {
               String result = duration.getQuantity() > 1 ? time + "i" : "o";
               result += duration.isInPast() ? " ago" : " from now";
               return result;
            }

            @Override
            public String format(Duration duration)
            {
               return duration.getQuantityRounded(50) + " minut";
            }

            @Override
            public String formatUnrounded(Duration duration)
            {
               return duration.getQuantity() + " minut";
            }
         };
      }
      return null;
   }

}
