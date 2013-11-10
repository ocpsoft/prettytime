/*
 * Copyright 2013 Martin Kouba
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
 * @author Martin Kouba
 */
public class Resources_cs extends ListResourceBundle implements TimeFormatProvider
{
   private static final Object[][] OBJECTS = new Object[][] {

            { "CenturyPattern", "%n %u" },
            { "CenturyFuturePrefix", "za " },
            { "CenturyFutureSuffix", "" },
            { "CenturyPastPrefix", "před " },
            { "CenturyPastSuffix", "" },
            { "CenturySingularName", "století" },
            { "CenturyPluralName", "století" },
            { "CenturyPastSingularName", "stoletím" },
            { "CenturyPastPluralName", "stoletími" },
            { "CenturyFutureSingularName", "století" },
            { "CenturyFuturePluralName", "století" },

            { "DayPattern", "%n %u" },
            { "DayFuturePrefix", "za " },
            { "DayFutureSuffix", "" },
            { "DayPastPrefix", "před " },
            { "DayPastSuffix", "" },
            { "DaySingularName", "den" },
            { "DayPluralName", "dny" },

            { "DecadePattern", "%n %u" },
            { "DecadeFuturePrefix", "za " },
            { "DecadeFutureSuffix", "" },
            { "DecadePastPrefix", "před " },
            { "DecadePastSuffix", "" },
            { "DecadeSingularName", "desetiletí" },
            { "DecadePluralName", "desetiletí" },
            { "DecadePastSingularName", "desetiletím" },
            { "DecadePastPluralName", "desetiletími" },
            { "DecadeFutureSingularName", "desetiletí" },
            { "DecadeFuturePluralName", "desetiletí" },

            { "HourPattern", "%n %u" },
            { "HourFuturePrefix", "za " },
            { "HourFutureSuffix", "" },
            { "HourPastPrefix", "před" },
            { "HourPastSuffix", "" },
            { "HourSingularName", "hodina" },
            { "HourPluralName", "hodiny" },

            { "JustNowPattern", "%u" },
            { "JustNowFuturePrefix", "" },
            { "JustNowFutureSuffix", "za chvíli" },
            { "JustNowPastPrefix", "před chvílí" },
            { "JustNowPastSuffix", "" },
            { "JustNowSingularName", "" },
            { "JustNowPluralName", "" },

            { "MillenniumPattern", "%n %u" },
            { "MillenniumFuturePrefix", "za " },
            { "MillenniumFutureSuffix", "" },
            { "MillenniumPastPrefix", "před " },
            { "MillenniumPastSuffix", "" },
            { "MillenniumSingularName", "tisíciletí" },
            { "MillenniumPluralName", "tisíciletí" },

            { "MillisecondPattern", "%n %u" },
            { "MillisecondFuturePrefix", "za " },
            { "MillisecondFutureSuffix", "" },
            { "MillisecondPastPrefix", "před " },
            { "MillisecondPastSuffix", "" },
            { "MillisecondSingularName", "milisekunda" },
            { "MillisecondPluralName", "milisekundy" },
            { "MillisecondPastSingularName", "milisekundou" },
            { "MillisecondPastPluralName", "milisekundami" },
            { "MillisecondFutureSingularName", "milisekundu" },
            { "MillisecondFuturePluralName", "milisekund" },

            { "MinutePattern", "%n %u" },
            { "MinuteFuturePrefix", "za " },
            { "MinuteFutureSuffix", "" },
            { "MinutePastPrefix", "před " },
            { "MinutePastSuffix", "" },
            { "MinuteSingularName", "minuta" },
            { "MinutePluralName", "minuty" },

            { "MonthPattern", "%n %u" },
            { "MonthFuturePrefix", "za " },
            { "MonthFutureSuffix", "" },
            { "MonthPastPrefix", "před " },
            { "MonthPastSuffix", "" },
            { "MonthSingularName", "měsíc" },
            { "MonthPluralName", "měsíce" },

            { "SecondPattern", "%n %u" },
            { "SecondFuturePrefix", "za " },
            { "SecondFutureSuffix", "" },
            { "SecondPastPrefix", "před " },
            { "SecondPastSuffix", "" },
            { "SecondSingularName", "sekunda" },
            { "SecondPluralName", "sekundy" },

            { "WeekPattern", "%n %u" },
            { "WeekFuturePrefix", "za " },
            { "WeekFutureSuffix", "" },
            { "WeekPastPrefix", "před " },
            { "WeekPastSuffix", "" },
            { "WeekSingularName", "týden" },
            { "WeekPluralName", "týdny" },

            { "YearPattern", "%n %u" },
            { "YearFuturePrefix", "za " },
            { "YearFutureSuffix", "" },
            { "YearPastPrefix", "před " },
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
    public TimeFormat getFormatFor(TimeUnit t) {
        if(t instanceof Minute) {
            return new CsTimeFormatBuilder("Minute")
                    .addFutureName("minutu", 1)
                    .addFutureName("minuty", 4)
                    .addFutureName("minut", Long.MAX_VALUE)
                    .addPastName("minutou", 1)
                    .addPastName("minutami", Long.MAX_VALUE)
                    .build(this);
        } else if(t instanceof Hour) {
            return new CsTimeFormatBuilder("Hour")
                    .addFutureName("hodinu", 1)
                    .addFutureName("hodiny", 4)
                    .addFutureName("hodin", Long.MAX_VALUE)
                    .addPastName("hodinou", 1)
                    .addPastName("hodinami", Long.MAX_VALUE)
                    .build(this);
        } else if(t instanceof Day) {
            return new CsTimeFormatBuilder("Day")
                    .addFutureName("den", 1)
                    .addFutureName("dny", 4)
                    .addFutureName("dní", Long.MAX_VALUE)
                    .addPastName("dnem", 1)
                    .addPastName("dny", Long.MAX_VALUE)
                    .build(this);
        }  else if(t instanceof Week) {
            return new CsTimeFormatBuilder("Week")
                    .addFutureName("týden", 1)
                    .addFutureName("týdny", 4)
                    .addFutureName("týdnů", Long.MAX_VALUE)
                    .addPastName("týdnem", 1)
                    .addPastName("týdny", Long.MAX_VALUE)
                    .build(this);
        } else if(t instanceof Month) {
            return new CsTimeFormatBuilder("Month")
                    .addFutureName("měsíc", 1)
                    .addFutureName("měsíce", 4)
                    .addFutureName("měsíců", Long.MAX_VALUE)
                    .addPastName("měsícem", 1)
                    .addPastName("měsíci", Long.MAX_VALUE)
                    .build(this);
        } else if(t instanceof Year) {
            return new CsTimeFormatBuilder("Year")
                    .addFutureName("rok", 1)
                    .addFutureName("roky", 4)
                    .addFutureName("let", Long.MAX_VALUE)
                    .addPastName("rokem", 1)
                    .addPastName("roky", Long.MAX_VALUE)
                    .build(this);
        }
        // Don't override format for other time units
        return null;
    }

    private static class CsTimeFormatBuilder {

        private List<CsName> names = new ArrayList<Resources_cs.CsName>();

        private String resourceKeyPrefix;

        CsTimeFormatBuilder(String resourceKeyPrefix) {
            this.resourceKeyPrefix = resourceKeyPrefix;
        }

        CsTimeFormatBuilder addFutureName(String name, long limit) {
            return addName(true, name, limit);
        }

        CsTimeFormatBuilder addPastName(String name, long limit) {
           return addName(false, name, limit);
        }

        private CsTimeFormatBuilder addName(boolean isFuture, String name, long limit) {
            if(name == null) {
                throw new IllegalArgumentException();
            }
            names.add(new CsName(isFuture, name, limit));
            return this;
        }

        CsTimeFormat build(final ResourceBundle bundle) {
            return new CsTimeFormat(resourceKeyPrefix, bundle, names);
        }

    }

    private static class CsTimeFormat extends SimpleTimeFormat implements TimeFormat {

        private final List<CsName> futureNames = new ArrayList<Resources_cs.CsName>();

        private final List<CsName> pastNames = new ArrayList<Resources_cs.CsName>();

        public CsTimeFormat(String resourceKeyPrefix, ResourceBundle bundle, Collection<CsName> names) {
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
                if(name.isFuture()) {
                    futureNames.add(name);
                } else {
                    pastNames.add(name);
                }
            }
            Collections.sort(futureNames);
            Collections.sort(pastNames);
        }

        @Override
        protected String getGramaticallyCorrectName(Duration d, boolean round) {
            long quantity = Math.abs(getQuantity(d, round));
            if(d.isInFuture()) {
                return getGramaticallyCorrectName(quantity, futureNames);
            }
            return getGramaticallyCorrectName(quantity, pastNames);
        }

        private String getGramaticallyCorrectName(long quantity, List<CsName> names) {
            for (CsName name : names) {
                if(name.getThreshold() >= quantity) {
                    return name.get();
                }
            }
            throw new IllegalStateException("Invalid resource bundle configuration");
        }

    }

    private static class CsName implements Comparable<CsName>{

        private final boolean isFuture;

        private final String value;

        private final Long threshold;

        public CsName(boolean isFuture, String value, Long threshold) {
            this.isFuture = isFuture;
            this.value = value;
            this.threshold = threshold;
        }

        public boolean isFuture() {
            return isFuture;
        }

        public String get() {
            return value;
        }

        public long getThreshold() {
            return threshold;
        }

        @Override
        public int compareTo(CsName o) {
            return threshold.compareTo(o.getThreshold());
        }

    }

}
