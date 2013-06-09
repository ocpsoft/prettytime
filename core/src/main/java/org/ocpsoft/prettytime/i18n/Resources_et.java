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
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.Day;


public class Resources_et extends Resources_fi {
    private static final Object[][] OBJECTS = new Object[][] {
        { "CenturyPattern", "%u" },
        { "CenturyPluralPattern", "%n %u" },
        { "CenturyPastSingularName", "sajand" },
        { "CenturyPastPluralName", "sajandit" },
        { "CenturyFutureSingularName", "sajandi" },
        { "CenturyPastSuffix", "tagasi" },
        { "CenturyFutureSuffix", "pärast" },
        { "DayPattern", "%u" },
        { "DayPluralPattern", "%n %u" },
        { "DayPastSingularName", "eile" },
        { "DayPastPluralName", "päeva" },
        { "DayFutureSingularName", "homme" },
        { "DayFuturePluralName", "päeva" },
        { "DayPastSuffix", "tagasi" },
        { "DayFutureSuffix", "pärast" },
        { "DecadePattern", "%u" },
        { "DecadePluralPattern", "%n %u" },
        { "DecadePastSingularName", "aastakümme" },
        { "DecadePastPluralName", "aastakümmet" },
        { "DecadeFutureSingularName", "aastakümne" },
        { "DecadePastSuffix", "tagasi" },
        { "DecadeFutureSuffix", "pärast" },
        { "HourPattern", "%u" },
        { "HourPluralPattern", "%n %u" },
        { "HourPastSingularName", "tund" },
        { "HourPastPluralName", "tundi" },
        { "HourFutureSingularName", "tunni" },
        { "HourPastSuffix", "tagasi" },
        { "HourFutureSuffix", "pärast" },
        { "JustNowPattern", "%u" },
        { "JustNowPastSingularName", "hetk" },
        { "JustNowFutureSingularName", "hetke" },
        { "JustNowPastSuffix", "tagasi" },
        { "JustNowFutureSuffix", "pärast" },
        { "MillenniumPattern", "%u" },
        { "MillenniumPluralPattern", "%n %u" },
        { "MillenniumPastSingularName", "aastatuhat" },
        { "MillenniumPastPluralName", "aastatuhandet" },
        { "MillenniumFutureSingularName", "aastatuhande" },
        { "MillenniumPastSuffix", "tagasi" },
        { "MillenniumFutureSuffix", "pärast" },
        { "MillisecondPattern", "%u" },
        { "MillisecondPluralPattern", "%n %u" },
        { "MillisecondPastSingularName", "millisekund" },
        { "MillisecondPastPluralName", "millisekundit" },
        { "MillisecondFutureSingularName", "millisekundi" },
        { "MillisecondFuturePluralName", "millisekundi" },
        { "MillisecondPastSuffix", "tagasi" },
        { "MillisecondFutureSuffix", "pärast" },
        { "MinutePattern", "%u" },
        { "MinutePluralPattern", "%n %u" },
        { "MinutePastSingularName", "minut" },
        { "MinutePastPluralName", "minutit" },
        { "MinuteFutureSingularName", "minuti" },
        { "MinuteFuturePluralName", "minuti" },
        { "MinutePastSuffix", "tagasi" },
        { "MinuteFutureSuffix", "pärast" },
        { "MonthPattern", "%u" },
        { "MonthPluralPattern", "%n %u" },
        { "MonthPastSingularName", "kuu" },
        { "MonthPastPluralName", "kuud" },
        { "MonthFutureSingularName", "kuu" },
        { "MonthPastSuffix", "tagasi" },
        { "MonthFutureSuffix", "pärast" },
        { "SecondPattern", "%u" },
        { "SecondPluralPattern", "%n %u" },
        { "SecondPastSingularName", "sekund" },
        { "SecondPastPluralName", "sekundit" },
        { "SecondFutureSingularName", "sekundi" },
        { "SecondFuturePluralName", "sekundi" },
        { "SecondPastSuffix", "tagasi" },
        { "SecondFutureSuffix", "pärast" },
        { "WeekPattern", "%u" },
        { "WeekPluralPattern", "%n %u" },
        { "WeekPastSingularName", "nädal" },
        { "WeekPastPluralName", "nädalat" },
        { "WeekFutureSingularName", "nädala" },
        { "WeekFuturePluralName", "nädala" },
        { "WeekPastSuffix", "tagasi" },
        { "WeekFutureSuffix", "pärast" },
        { "YearPattern", "%u" },
        { "YearPluralPattern", "%n %u" },
        { "YearPastSingularName", "aasta" },
        { "YearPastPluralName", "aastat" },
        { "YearFutureSingularName", "aasta" },
        { "YearFuturePluralName", "aasta" },
        { "YearPastSuffix", "tagasi" },
        { "YearFutureSuffix", "pärast" },
        { "AbstractTimeUnitPattern", "" },
        { "AbstractTimeUnitFuturePrefix", "" },
        { "AbstractTimeUnitFutureSuffix", "" },
        { "AbstractTimeUnitPastPrefix", "" },
        { "AbstractTimeUnitPastSuffix", "" },
        { "AbstractTimeUnitSingularName", "" },
        { "AbstractTimeUnitPluralName", "" } }; 

	@Override
	protected Object[][] getContents() {
		return OBJECTS;
	}
}
