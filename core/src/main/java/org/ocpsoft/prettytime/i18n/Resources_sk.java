/*
 * Copyright 2015 Michal Ursíny & Stefan Kostelny
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Month;
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Year;

/**
 *
 * @author Michal Ursíny & Stefan Kostelny based on cs variant by Martin Kouba
 */
public class Resources_sk extends ListResourceBundle implements TimeFormatProvider
{
   private static final Object[][] OBJECTS = new Object[][] {

            { "CenturyPattern", "%n %u" },
            { "CenturyFuturePrefix", "o " },
            { "CenturyFutureSuffix", "" },
            { "CenturyPastPrefix", "pred " },
            { "CenturyPastSuffix", "" },
            { "CenturySingularName", "storočie" },
            { "CenturyPluralName", "storočia" },
            { "CenturyPastSingularName", "storočím" },
            { "CenturyPastPluralName", "storočiami" },
            { "CenturyFutureSingularName", "storočí" },
            { "CenturyFuturePluralName", "storočia" },

            { "DayPattern", "%n %u" },
            { "DayFuturePrefix", "o " },
            { "DayFutureSuffix", "" },
            { "DayPastPrefix", "pred " },
            { "DayPastSuffix", "" },
            { "DaySingularName", "deň" },
            { "DayPluralName", "dni" },

            { "DecadePattern", "%n %u" },
            { "DecadeFuturePrefix", "o " },
            { "DecadeFutureSuffix", "" },
            { "DecadePastPrefix", "pred " },
            { "DecadePastSuffix", "" },
            { "DecadeSingularName", "desaťročie" },
            { "DecadePluralName", "desaťročia" },
            { "DecadePastSingularName", "desaťročím" },
            { "DecadePastPluralName", "desaťročiami" },
            { "DecadeFutureSingularName", "desaťročí" },
            { "DecadeFuturePluralName", "desaťročia" },

            { "HourPattern", "%n %u" },
            { "HourFuturePrefix", "o " },
            { "HourFutureSuffix", "" },
            { "HourPastPrefix", "pred" },
            { "HourPastSuffix", "" },
            { "HourSingularName", "hodina" },
            { "HourPluralName", "hodiny" },

            { "JustNowPattern", "%u" },
            { "JustNowFuturePrefix", "" },
            { "JustNowFutureSuffix", "o chvíľu" },
            { "JustNowPastPrefix", "pred chvíľou" },
            { "JustNowPastSuffix", "" },
            { "JustNowSingularName", "" },
            { "JustNowPluralName", "" },

            { "MillenniumPattern", "%n %u" },
            { "MillenniumFuturePrefix", "o " },
            { "MillenniumFutureSuffix", "" },
            { "MillenniumPastPrefix", "pred " },
            { "MillenniumPastSuffix", "" },
            { "MillenniumSingularName", "tísícročie" },
            { "MillenniumPluralName", "tisícročia" },

            { "MillisecondPattern", "%n %u" },
            { "MillisecondFuturePrefix", "o " },
            { "MillisecondFutureSuffix", "" },
            { "MillisecondPastPrefix", "pred " },
            { "MillisecondPastSuffix", "" },
            { "MillisecondSingularName", "milisekunda" },
            { "MillisecondPluralName", "milisekundy" },
            { "MillisecondPastSingularName", "milisekundou" },
            { "MillisecondPastPluralName", "milisekundami" },
            { "MillisecondFutureSingularName", "milisekundu" },
            { "MillisecondFuturePluralName", "milisekúnd" },

            { "MinutePattern", "%n %u" },
            { "MinuteFuturePrefix", "o " },
            { "MinuteFutureSuffix", "" },
            { "MinutePastPrefix", "pred " },
            { "MinutePastSuffix", "" },
            { "MinuteSingularName", "minúta" },
            { "MinutePluralName", "minúty" },

            { "MonthPattern", "%n %u" },
            { "MonthFuturePrefix", "o " },
            { "MonthFutureSuffix", "" },
            { "MonthPastPrefix", "pred " },
            { "MonthPastSuffix", "" },
            { "MonthSingularName", "mesiac" },
            { "MonthPluralName", "mesiace" },

            { "SecondPattern", "%n %u" },
            { "SecondFuturePrefix", "o " },
            { "SecondFutureSuffix", "" },
            { "SecondPastPrefix", "pred " },
            { "SecondPastSuffix", "" },
            { "SecondSingularName", "sekunda" },
            { "SecondPluralName", "sekundy" },

            { "WeekPattern", "%n %u" },
            { "WeekFuturePrefix", "o " },
            { "WeekFutureSuffix", "" },
            { "WeekPastPrefix", "pred " },
            { "WeekPastSuffix", "" },
            { "WeekSingularName", "týždeň" },
            { "WeekPluralName", "týždne" },

            { "YearPattern", "%n %u" },
            { "YearFuturePrefix", "o " },
            { "YearFutureSuffix", "" },
            { "YearPastPrefix", "pred " },
            { "YearPastSuffix", "" },
            { "YearSingularName", "rok" },
            { "YearPluralName", "roky" },

            { "AbstractTimeUnitPattern", "" },
            { "AbstractTimeUnitFuturePrefix", "" },
            { "AbstractTimeUnitFutureSuffix", "" },
            { "AbstractTimeUnitPastPrefix", "" },
            { "AbstractTimeUnitPastSuffix", "" },
            { "AbstractTimeUnitSingularName", "" },
            { "AbstractTimeUnitPluralName", "" } };

   @Override
   public Object[][] getContents()
   {
      return OBJECTS;
   }

   @Override
   public TimeFormat getFormatFor(TimeUnit t)
   {
      if (t instanceof Minute) {
         return new CsTimeFormatBuilder("Minute")
                  .addFutureName("minútu", 1)
                  .addFutureName("minúty", 4)
                  .addFutureName("minút", Long.MAX_VALUE)
                  .addPastName("minútou", 1)
                  .addPastName("minútami", Long.MAX_VALUE)
                  .build(this);
      }
      else if (t instanceof Hour) {
         return new CsTimeFormatBuilder("Hour")
                  .addFutureName("hodinu", 1)
                  .addFutureName("hodiny", 4)
                  .addFutureName("hodín", Long.MAX_VALUE)
                  .addPastName("hodinou", 1)
                  .addPastName("hodinami", Long.MAX_VALUE)
                  .build(this);
      }
      else if (t instanceof Day) {
         return new CsTimeFormatBuilder("Day")
                  .addFutureName("deň", 1)
                  .addFutureName("dni", 4)
                  .addFutureName("dní", Long.MAX_VALUE)
                  .addPastName("dňom", 1)
                  .addPastName("dňami", Long.MAX_VALUE)
                  .build(this);
      }
      else if (t instanceof Week) {
         return new CsTimeFormatBuilder("Week")
                  .addFutureName("týždeň", 1)
                  .addFutureName("týždne", 4)
                  .addFutureName("týždňov", Long.MAX_VALUE)
                  .addPastName("týždňom", 1)
                  .addPastName("týždňami", Long.MAX_VALUE)
                  .build(this);
      }
      else if (t instanceof Month) {
         return new CsTimeFormatBuilder("Month")
                  .addFutureName("mesiac", 1)
                  .addFutureName("mesiace", 4)
                  .addFutureName("mesiacov", Long.MAX_VALUE)
                  .addPastName("mesiacom", 1)
                  .addPastName("mesiacmi", Long.MAX_VALUE)
                  .build(this);
      }
      else if (t instanceof Year) {
         return new CsTimeFormatBuilder("Year")
                  .addFutureName("rok", 1)
                  .addFutureName("roky", 4)
                  .addFutureName("rokov", Long.MAX_VALUE)
                  .addPastName("rokom", 1)
                  .addPastName("rokmi", Long.MAX_VALUE)
                  .build(this);
      }
      // Don't override format for other time units
      return null;
   }

   private static class CsTimeFormatBuilder
   {

      private List<CsName> names = new ArrayList<Resources_sk.CsName>();

      private String resourceKeyPrefix;

      CsTimeFormatBuilder(String resourceKeyPrefix)
      {
         this.resourceKeyPrefix = resourceKeyPrefix;
      }

      CsTimeFormatBuilder addFutureName(String name, long limit)
      {
         return addName(true, name, limit);
      }

      CsTimeFormatBuilder addPastName(String name, long limit)
      {
         return addName(false, name, limit);
      }

      private CsTimeFormatBuilder addName(boolean isFuture, String name, long limit)
      {
         if (name == null) {
            throw new IllegalArgumentException();
         }
         names.add(new CsName(isFuture, name, limit));
         return this;
      }

      CsTimeFormat build(final ResourceBundle bundle)
      {
         return new CsTimeFormat(resourceKeyPrefix, bundle, names);
      }

   }

   private static class CsTimeFormat extends SimpleTimeFormat implements TimeFormat
   {

      private final List<CsName> futureNames = new ArrayList<Resources_sk.CsName>();

      private final List<CsName> pastNames = new ArrayList<Resources_sk.CsName>();

      public CsTimeFormat(String resourceKeyPrefix, ResourceBundle bundle, Collection<CsName> names)
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
         catch (Exception e) {}
         try {
            setFutureSingularName((bundle.getString(resourceKeyPrefix + "FutureSingularName")));
         }
         catch (Exception e) {}
         try {
            setPastPluralName((bundle.getString(resourceKeyPrefix + "PastPluralName")));
         }
         catch (Exception e) {}
         try {
            setPastSingularName((bundle.getString(resourceKeyPrefix + "PastSingularName")));
         }
         catch (Exception e) {}

         for (CsName name : names) {
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

      @Override
      protected String getGramaticallyCorrectName(Duration d, boolean round)
      {
         long quantity = Math.abs(getQuantity(d, round));
         if (d.isInFuture()) {
            return getGramaticallyCorrectName(quantity, futureNames);
         }
         return getGramaticallyCorrectName(quantity, pastNames);
      }

      private String getGramaticallyCorrectName(long quantity, List<CsName> names)
      {
         for (CsName name : names) {
            if (name.getThreshold() >= quantity) {
               return name.get();
            }
         }
         throw new IllegalStateException("Invalid resource bundle configuration");
      }

   }

   private static class CsName implements Comparable<CsName>
   {

      private final boolean isFuture;

      private final String value;

      private final Long threshold;

      public CsName(boolean isFuture, String value, Long threshold)
      {
         this.isFuture = isFuture;
         this.value = value;
         this.threshold = threshold;
      }

      public boolean isFuture()
      {
         return isFuture;
      }

      public String get()
      {
         return value;
      }

      public long getThreshold()
      {
         return threshold;
      }

      @Override
      public int compareTo(CsName o)
      {
         return threshold.compareTo(o.getThreshold());
      }

   }

}
