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

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.Millennium;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Month;
import org.ocpsoft.prettytime.units.Year;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;
import java.util.ResourceBundle;

public class Resources_sl extends ListResourceBundle implements TimeFormatProvider
{

   private static final Object[][] OBJECTS = new Object[][] {
            { "CenturyPattern", "%n %u" },
            { "CenturyFuturePrefix", "čez " },
            { "CenturyFutureSuffix", "" },
            { "CenturyPastPrefix", "" },
            { "CenturyPastSuffix", "nazaj" },
            { "CenturySingularName", "stoletje" },
            { "CenturyPluralName", "stoletij" },
            { "DayPattern", "%n %u" },
            { "DayFuturePrefix", "čez " },
            { "DayFutureSuffix", "" },
            { "DayPastPrefix", "" },
            { "DayPastSuffix", "nazaj" },
            { "DaySingularName", "dan" },
            { "DayPluralName", "dni" },
            { "DecadePattern", "%n %u" },
            { "DecadeFuturePrefix", "čez " },
            { "DecadeFutureSuffix", "" },
            { "DecadePastPrefix", "" },
            { "DecadePastSuffix", "nazaj" },
            { "DecadeSingularName", "desetletje" },
            { "DecadePluralName", "desetletij" },
            { "HourPattern", "%n %u" },
            { "HourFuturePrefix", "čez " },
            { "HourFutureSuffix", "" },
            { "HourPastPrefix", "" },
            { "HourPastSuffix", "nazaj" },
            { "HourSingularName", "uro" },
            { "HourPluralName", "ur" },
            { "JustNowPattern", "%u" },
            { "JustNowFuturePrefix", "čez " },
            { "JustNowFutureSuffix", "pravkar" },
            { "JustNowPastPrefix", "trenutkov nazaj" },
            { "JustNowPastSuffix", "" },
            { "JustNowSingularName", "" },
            { "JustNowPluralName", "" },
            { "MillenniumPattern", "%n %u" },
            { "MillenniumFuturePrefix", "čez " },
            { "MillenniumFutureSuffix", "" },
            { "MillenniumPastPrefix", "" },
            { "MillenniumPastSuffix", "nazaj" },
            { "MillenniumSingularName", "tisočletje" },
            { "MillenniumPluralName", "tisočletij" },
            { "MillisecondPattern", "%n %u" },
            { "MillisecondFuturePrefix", "čez " },
            { "MillisecondFutureSuffix", "" },
            { "MillisecondPastPrefix", "" },
            { "MillisecondPastSuffix", "nazaj" },
            { "MillisecondSingularName", "milisekundo" },
            { "MillisecondPluralName", "milisekund" },
            { "MinutePattern", "%n %u" },
            { "MinuteFuturePrefix", "čez " },
            { "MinuteFutureSuffix", "" },
            { "MinutePastPrefix", "" },
            { "MinutePastSuffix", "nazaj" },
            { "MinuteSingularName", "minuto" },
            { "MinutePluralName", "minut" },
            { "MonthPattern", "%n %u" },
            { "MonthFuturePrefix", "čez " },
            { "MonthFutureSuffix", "" },
            { "MonthPastPrefix", "" },
            { "MonthPastSuffix", "nazaj" },
            { "MonthSingularName", "mesec" },
            { "MonthPluralName", "mesecev" },
            { "SecondPattern", "%n %u" },
            { "SecondFuturePrefix", "čez " },
            { "SecondFutureSuffix", "" },
            { "SecondPastPrefix", "" },
            { "SecondPastSuffix", "nazaj" },
            { "SecondSingularName", "sekundo" },
            { "SecondPluralName", "sekund" },
            { "WeekPattern", "%n %u" },
            { "WeekFuturePrefix", "čez " },
            { "WeekFutureSuffix", "" },
            { "WeekPastPrefix", "" },
            { "WeekPastSuffix", "nazaj" },
            { "WeekSingularName", "teden" },
            { "WeekPluralName", "tednov" },
            { "YearPattern", "%n %u" },
            { "YearFuturePrefix", "čez " },
            { "YearFutureSuffix", "" },
            { "YearPastPrefix", "" },
            { "YearPastSuffix", "nazaj" },
            { "YearSingularName", "leto" },
            { "YearPluralName", "let" },
            { "AbstractTimeUnitPattern", "" },
            { "AbstractTimeUnitFuturePrefix", "" },
            { "AbstractTimeUnitFutureSuffix", "" },
            { "AbstractTimeUnitPastPrefix", "" },
            { "AbstractTimeUnitPastSuffix", "" },
            { "AbstractTimeUnitSingularName", "" },
            { "AbstractTimeUnitPluralName", "" } };

   @Override
   protected Object[][] getContents()
   {
      return OBJECTS;
   }

   @Override
   public TimeFormat getFormatFor(final TimeUnit t)
   {
      if (t instanceof Minute) {
         return new SlTimeFormatBuilder("Minute")
                 .addNames("minuto", 1)
                 .addNames("minuti", 2)
                 .addNames("minute", 4)
                 .addNames("minut", Long.MAX_VALUE)
                 .build(this);
      }
      else if (t instanceof Hour) {
         return new SlTimeFormatBuilder("Hour")
                 .addNames("uro", 1)
                 .addNames("uri", 2)
                 .addNames("ure", 4)
                 .addNames("ur", Long.MAX_VALUE)
                 .build(this);
      }
      else if (t instanceof Day) {
         return new SlTimeFormatBuilder("Day")
                 .addNames("dan", 1)
                 .addNames("dni", Long.MAX_VALUE)
                 .build(this);
      }
      else if (t instanceof Week) {
         return new SlTimeFormatBuilder("Week")
                 .addNames("teden", 1)
                 .addNames("tedna", 2)
                 .addNames("tedne", 4)
                 .addNames("tednov", Long.MAX_VALUE)
                 .build(this);
      }
      else if (t instanceof Month) {
         return new SlTimeFormatBuilder("Month")
                 .addNames("mesec", 1)
                 .addNames("meseca", 2)
                 .addNames("mesece", 4)
                 .addNames("mesecev", Long.MAX_VALUE)
                 .build(this);
      }
      else if (t instanceof Year) {
         return new SlTimeFormatBuilder("Year")
                 .addNames("leto", 1)
                 .addNames("leti", 2)
                 .addNames("leta", 4)
                 .addNames("let", Long.MAX_VALUE)
                 .build(this);
      }
      else if (t instanceof Millennium) {
         return new SlTimeFormatBuilder("Millennium")
                 .addNames("tisočletje", 1)
                 .addNames("tisočletji", 2)
                 .addNames("tisočletja", 4)
                 .addNames("tisočletij", Long.MAX_VALUE)
                 .build(this);
      }
      // Don't override format for other time units
      return null;
   }

   private static class SlName implements Comparable<SlName>
   {

      private final boolean isFuture;

      private final Long threshold;

      private final String value;

      public SlName(final boolean isFuture, final String value, final Long threshold)
      {
         this.isFuture = isFuture;
         this.value = value;
         this.threshold = threshold;
      }

      @Override
      public int compareTo(final SlName o)
      {
         return threshold.compareTo(o.getThreshold());
      }

      public String get()
      {
         return value;
      }

      public long getThreshold()
      {
         return threshold;
      }

      public boolean isFuture()
      {
         return isFuture;
      }
   }

   private static class SlTimeFormat extends SimpleTimeFormat
   {

      private final List<SlName> futureNames = new ArrayList<Resources_sl.SlName>();

      private final List<SlName> pastNames = new ArrayList<Resources_sl.SlName>();

      public SlTimeFormat(final String resourceKeyPrefix, final ResourceBundle bundle, final Collection<SlName> names)
      {
         setPattern(bundle.getString(resourceKeyPrefix + "Pattern"));
         setFuturePrefix(bundle.getString(resourceKeyPrefix + "FuturePrefix"));
         setFutureSuffix(bundle.getString(resourceKeyPrefix + "FutureSuffix"));
         setPastPrefix(bundle.getString(resourceKeyPrefix + "PastPrefix"));
         setPastSuffix(bundle.getString(resourceKeyPrefix + "PastSuffix"));
         setSingularName(bundle.getString(resourceKeyPrefix + "SingularName"));
         setPluralName(bundle.getString(resourceKeyPrefix + "PluralName"));

         try {
            setFuturePluralName(bundle.getString(resourceKeyPrefix + "FuturePluralName"));
         }
         catch (final Exception e) {
         }
         try {
            setFutureSingularName(bundle.getString(resourceKeyPrefix + "FutureSingularName"));
         }
         catch (final Exception e) {
         }
         try {
            setPastPluralName(bundle.getString(resourceKeyPrefix + "PastPluralName"));
         }
         catch (final Exception e) {
         }
         try {
            setPastSingularName(bundle.getString(resourceKeyPrefix + "PastSingularName"));
         }
         catch (final Exception e) {
         }

         for (final SlName name : names) {
            if (name.isFuture()) {
               futureNames.add(name);
            }
            else {
               pastNames.add(name);
            }
         }
         Collections.sort(futureNames);
         Collections.sort(pastNames);
      }

      private String getGramaticallyCorrectName(final long quantity, final List<SlName> names)
      {
         for (final SlName name : names) {
            if (name.getThreshold() >= quantity) {
               return name.get();
            }
         }
         throw new IllegalStateException("Invalid resource bundle configuration");
      }

      @Override
      protected String getGramaticallyCorrectName(final Duration d, final boolean round)
      {
         final long quantity = Math.abs(getQuantity(d, round));
         if (d.isInFuture()) {
            return getGramaticallyCorrectName(quantity, futureNames);
         }
         return getGramaticallyCorrectName(quantity, pastNames);
      }

   }

   private static class SlTimeFormatBuilder
   {

      private final List<SlName> names = new ArrayList<Resources_sl.SlName>();

      private final String resourceKeyPrefix;

      SlTimeFormatBuilder(final String resourceKeyPrefix)
      {
         this.resourceKeyPrefix = resourceKeyPrefix;
      }

      private SlTimeFormatBuilder addName(final boolean isFuture, final String name, final long limit)
      {
         names.add(new SlName(isFuture, Objects.requireNonNull(name), limit));
         return this;
      }

      SlTimeFormatBuilder addNames(final String name, final long limit)
      {
         return addName(true, name, limit).addName(false, name, limit);
      }

      SlTimeFormat build(final ResourceBundle bundle)
      {
         return new SlTimeFormat(resourceKeyPrefix, bundle, names);
      }

   }
}
