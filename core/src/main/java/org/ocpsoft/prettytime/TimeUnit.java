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

import org.ocpsoft.prettytime.units.Century;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Decade;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.Millennium;
import org.ocpsoft.prettytime.units.Millisecond;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Month;
import org.ocpsoft.prettytime.units.Second;
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Year;

import java.time.temporal.ChronoUnit;

/**
 * Defines a Unit of time (e.g. seconds, minutes, hours) and its conversion to milliseconds.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public interface TimeUnit
{

   /**
    * The number of milliseconds represented by each instance of this TimeUnit. Must be a positive number greater than
    * zero.
    */
   public long getMillisPerUnit();

   /**
    * The maximum quantity of this Unit to be used as a threshold for the next largest Unit (e.g. if one
    * <code>Second</code> represents 1000ms, and <code>Second</code> has a maxQuantity of 5, then if the difference
    * between compared timestamps is larger than 5000ms, PrettyTime will move on to the next smallest TimeUnit for
    * calculation; <code>Minute</code>, by default)
    * <p>
    * millisPerUnit * maxQuantity = maxAllowedMs
    * <p>
    * If maxQuantity is zero, it will be equal to the next highest <code>TimeUnit.getMillisPerUnit() /
    * this.getMillisPerUnit()</code> or infinity if there are no greater TimeUnits
    */
   public long getMaxQuantity();

   /**
    * Whether or not this {@link TimeUnit} represents a price measurement of time, or a general concept of time. E.g:
    * "minute" as opposed to "moment".
    */
   public boolean isPrecise();

   /**
    * Converts the given {@link ChronoUnit} to a {@link TimeUnit}, if possible.
    * @param chronoUnit The {@code ChronoUnit} to be converted
    * @return the corresponding {@code TimeUnit}
    * @throws IllegalArgumentException if there is no corresponding {@code TimeUnit}
    */
   public static TimeUnit of(final ChronoUnit chronoUnit) {
      switch (chronoUnit) {
         case MILLIS:
            return new Millisecond();
         case SECONDS:
            return new Second();
         case MINUTES:
            return new Minute();
         case HOURS:
            return new Hour();
         case DAYS:
            return new Day();
         case WEEKS:
            return new Week();
         case MONTHS:
            return new Month();
         case YEARS:
            return new Year();
         case DECADES:
            return new Decade();
         case CENTURIES:
            return new Century();
         case MILLENNIA:
            return new Millennium();
         default:
            throw new IllegalArgumentException("No corresponding TimeUnit for given ChronoUnit");
      }
   }

   /**
    * Converts the given {@link TimeUnit} to a {@link ChronoUnit}, if possible.
    *
    * @param timeUnit The {@code TimeUnit} to be converted
    * @return the corresponding {@code ChronoUnit}
    * @throws IllegalArgumentException if there is no corresponding {@code ChronoUnit}.
    */
   public static ChronoUnit toChronoUnit(final TimeUnit timeUnit) {
      if (timeUnit instanceof Millisecond) {
         return ChronoUnit.MILLIS;
      } else if (timeUnit instanceof Second) {
         return ChronoUnit.SECONDS;
      } else if (timeUnit instanceof Minute) {
         return ChronoUnit.MINUTES;
      } else if (timeUnit instanceof Hour) {
         return ChronoUnit.HOURS;
      } else if (timeUnit instanceof Day) {
         return ChronoUnit.DAYS;
      } else if (timeUnit instanceof Week) {
         return ChronoUnit.WEEKS;
      } else if (timeUnit instanceof Month) {
         return ChronoUnit.MONTHS;
      } else if (timeUnit instanceof Year) {
         return ChronoUnit.YEARS;
      } else if (timeUnit instanceof Decade) {
         return ChronoUnit.DECADES;
      } else if (timeUnit instanceof Century) {
         return ChronoUnit.CENTURIES;
      } else if (timeUnit instanceof Millennium) {
         return ChronoUnit.MILLENNIA;
      } else {
         throw new IllegalArgumentException("No corresponding ChronoUnit for given TimeUnit");
      }
   }
}
