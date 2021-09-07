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

import java.util.ListResourceBundle;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;

public class Resources_de extends ListResourceBundle implements TimeFormatProvider
{
    private static final Object[][] OBJECTS = new Object[][] {
                { "CenturyPattern", "%n %u" },
                { "CenturyFuturePrefix", "in " },
                { "CenturyFutureSuffix", "" },
                { "CenturyPastPrefix", "vor " },
                { "CenturyPastSuffix", "" },
                { "CenturySingularName", "Jahrhundert" },
                { "CenturyPluralName", "Jahrhunderte" },
                { "DayPattern", "%n %u" },
                { "DayFuturePrefix", "in " },
                { "DayFutureSuffix", "" },
                { "DayPastPrefix", "vor " },
                { "DayPastSuffix", "" },
                { "DaySingularName", "Tag" },
                { "DayPluralName", "Tage" },
                { "DecadePattern", "%n %u" },
                { "DecadeFuturePrefix", "in " },
                { "DecadeFutureSuffix", "" },
                { "DecadePastPrefix", "vor " },
                { "DecadePastSuffix", "" },
                { "DecadeSingularName", "Jahrzehnt" },
                { "DecadePluralName", "Jahrzehnte" },
                { "HourPattern", "%n %u" },
                { "HourFuturePrefix", "in " },
                { "HourFutureSuffix", "" },
                { "HourPastPrefix", "vor " },
                { "HourPastSuffix", "" },
                { "HourSingularName", "Stunde" },
                { "HourPluralName", "Stunden" },
                { "JustNowPattern", "%u" },
                { "JustNowFuturePrefix", "Jetzt" },
                { "JustNowFutureSuffix", "" },
                { "JustNowPastPrefix", "gerade eben" },
                { "JustNowPastSuffix", "" },
                { "JustNowSingularName", "" },
                { "JustNowPluralName", "" },
                { "MillenniumPattern", "%n %u" },
                { "MillenniumFuturePrefix", "in " },
                { "MillenniumFutureSuffix", "" },
                { "MillenniumPastPrefix", "vor " },
                { "MillenniumPastSuffix", "" },
                { "MillenniumSingularName", "Jahrtausend" },
                { "MillenniumPluralName", "Jahrtausende" },
                { "MillisecondPattern", "%n %u" },
                { "MillisecondFuturePrefix", "in " },
                { "MillisecondFutureSuffix", "" },
                { "MillisecondPastPrefix", "vor " },
                { "MillisecondPastSuffix", "" },
                { "MillisecondSingularName", "Millisekunde" },
                { "MillisecondPluralName", "Millisekunden" },
                { "MinutePattern", "%n %u" },
                { "MinuteFuturePrefix", "in " },
                { "MinuteFutureSuffix", "" },
                { "MinutePastPrefix", "vor " },
                { "MinutePastSuffix", "" },
                { "MinuteSingularName", "Minute" },
                { "MinutePluralName", "Minuten" },
                { "MonthPattern", "%n %u" },
                { "MonthFuturePrefix", "in " },
                { "MonthFutureSuffix", "" },
                { "MonthPastPrefix", "vor " },
                { "MonthPastSuffix", "" },
                { "MonthSingularName", "Monat" },
                { "MonthPluralName", "Monate" },
                { "SecondPattern", "%n %u" },
                { "SecondFuturePrefix", "in " },
                { "SecondFutureSuffix", "" },
                { "SecondPastPrefix", "vor " },
                { "SecondPastSuffix", "" },
                { "SecondSingularName", "Sekunde" },
                { "SecondPluralName", "Sekunden" },
                { "WeekPattern", "%n %u" },
                { "WeekFuturePrefix", "in " },
                { "WeekFutureSuffix", "" },
                { "WeekPastPrefix", "vor " },
                { "WeekPastSuffix", "" },
                { "WeekSingularName", "Woche" },
                { "WeekPluralName", "Wochen" },
                { "YearPattern", "%n %u" },
                { "YearFuturePrefix", "in " },
                { "YearFutureSuffix", "" },
                { "YearPastPrefix", "vor " },
                { "YearPastSuffix", "" },
                { "YearSingularName", "Jahr" },
                { "YearPluralName", "Jahre" },
                { "AbstractTimeUnitPattern", "" },
                { "AbstractTimeUnitFuturePrefix", "" },
                { "AbstractTimeUnitFutureSuffix", "" },
                { "AbstractTimeUnitPastPrefix", "" },
                { "AbstractTimeUnitPastSuffix", "" },
                { "AbstractTimeUnitSingularName", "" },
                { "AbstractTimeUnitPluralName", "" }

    };

    @Override
    protected Object[][] getContents()
    {
        return OBJECTS;
    }

    @Override
    public TimeFormat getFormatFor(TimeUnit t)
    {
        return new DeTimeFormat(this, t);
    }

    private static class DeTimeFormat extends SimpleTimeFormat
    {
        // Map for changing all occurring nominative plurals to dative (for achieving "in x Jahren" or "vor x Monaten")
        private static final Map<String, String> unitsToAdjust =
                Stream.of("Jahrtausende", "Jahrhunderte", "Jahrzehnte", "Jahre", "Monate", "Tage")
                      .collect(Collectors.toMap(Function.identity(), s -> s + "n"));

        private static final Pattern grammerReplacementPattern =
                Pattern.compile("\\b(" + String.join("|", unitsToAdjust.keySet()) + ")\\b");

        public DeTimeFormat(final ResourceBundle bundle, final TimeUnit unit)
        {
            String prefix = unit.getClass().getSimpleName();
            setPattern(bundle.getString(prefix + "Pattern"));
            setFuturePrefix(bundle.getString(prefix + "FuturePrefix"));
            setFutureSuffix(bundle.getString(prefix + "FutureSuffix"));
            setPastPrefix(bundle.getString(prefix + "PastPrefix"));
            setPastSuffix(bundle.getString(prefix + "PastSuffix"));
            setSingularName(bundle.getString(prefix + "SingularName"));
            setPluralName(bundle.getString(prefix + "PluralName"));
        }

        @Override
        public String decorate(Duration duration, String time)
        {
            return super.decorate(duration, adjustGrammar(time));
        }

        @Override
        public String decorateUnrounded(Duration duration, String time)
        {
            return super.decorateUnrounded(duration, adjustGrammar(time));
        }

        private String adjustGrammar(String time)
        {
            Matcher matcher = grammerReplacementPattern.matcher(time);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, unitsToAdjust.get(matcher.group(1)));
            }
            matcher.appendTail(sb);

            return sb.toString();
        }
    }
}
