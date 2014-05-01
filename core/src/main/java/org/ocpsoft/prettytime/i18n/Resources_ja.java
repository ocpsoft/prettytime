package org.ocpsoft.prettytime.i18n;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Resources_ja extends ListResourceBundle implements TimeFormatProvider {
    private static final Object[][] OBJECTS = new Object[][]{
            {"CenturyPattern", "%n%u"},
            {"CenturyFuturePrefix", "今から"},
            {"CenturyFutureSuffix", "世紀にもわたっ"},
            {"CenturyPastPrefix", ""},
            {"CenturyPastSuffix", "世紀前"},
            {"CenturySingularName", ""},
            {"CenturyPluralName", ""},
            {"DayPattern", "%n%u"},
            {"DayFuturePrefix", "今から"},
            {"DayFutureSuffix", "間"},
            {"DayPastPrefix", ""},
            {"DayPastSuffix", "前"},
            {"DaySingularName", "日"},
            {"DayPluralName", "日"},
            {"DecadePattern", "%n%u"},
            {"DecadeFuturePrefix", "今から"},
            {"DecadeFutureSuffix", "年間"},
            {"DecadePastPrefix", ""},
            {"DecadePastSuffix", "年前"},
            {"DecadeSingularName", ""},
            {"DecadePluralName", ""},
            {"HourPattern", "%n%u"},
            {"HourFuturePrefix", "今から"},
            {"HourFutureSuffix", ""},
            {"HourPastPrefix", ""},
            {"HourPastSuffix", "前"},
            {"HourSingularName", "時間"},
            {"HourPluralName", "時間"},
            {"JustNowPattern", "%u"},
            {"JustNowFuturePrefix", ""},
            {"JustNowFutureSuffix", "今からすぐ"},
            {"JustNowPastPrefix", ""},
            {"JustNowPastSuffix", "さっき"},
            {"JustNowSingularName", ""},
            {"JustNowPluralName", ""},
            {"MillenniumPattern", "%n %u"},
            {"MillenniumFuturePrefix", ""},
            {"MillenniumFutureSuffix", "今から"},
            {"MillenniumPastPrefix", ""},
            {"MillenniumPastSuffix", "前"},
            {"MillenniumSingularName", "千年"},
            {"MillenniumPluralName", "千年"},
            {"MillisecondPattern", "%n%u"},
            {"MillisecondFuturePrefix", ""},
            {"MillisecondFutureSuffix", "今から"},
            {"MillisecondPastPrefix", ""},
            {"MillisecondPastSuffix", "前"},
            {"MillisecondSingularName", "ミリ秒"},
            {"MillisecondPluralName", "ミリ秒"},
            {"MinutePattern", "%n%u"},
            {"MinuteFuturePrefix", "今から"},
            {"MinuteFutureSuffix", ""},
            {"MinutePastPrefix", ""},
            {"MinutePastSuffix", "前"},
            {"MinuteSingularName", "分"},
            {"MinutePluralName", "分"},
            {"MonthPattern", "%n%u"},
            {"MonthFuturePrefix", "今から"},
            {"MonthFutureSuffix", ""},
            {"MonthPastPrefix", ""},
            {"MonthPastSuffix", "前"},
            {"MonthSingularName", "月"},
            {"MonthPluralName", "ヶ月"},
            {"SecondPattern", "%n%u"},
            {"SecondFuturePrefix", ""},
            {"SecondFutureSuffix", "今から"},
            {"SecondPastPrefix", ""},
            {"SecondPastSuffix", "前"},
            {"SecondSingularName", "秒"},
            {"SecondPluralName", "秒"},
            {"WeekPattern", "%n%u"},
            {"WeekFuturePrefix", "今から"},
            {"WeekFutureSuffix", "週間"},
            {"WeekPastPrefix", ""},
            {"WeekPastSuffix", "週間前"},
            {"WeekSingularName", ""},
            {"WeekPluralName", ""},
            {"YearPattern", "%n%u"},
            {"YearFuturePrefix", ""},
            {"YearFutureSuffix", "年後の"},
            {"YearPastPrefix", ""},
            {"YearPastSuffix", "年前"},
            {"YearSingularName", ""},
            {"YearPluralName", ""},
            {"AbstractTimeUnitPattern", ""},
            {"AbstractTimeUnitFuturePrefix", ""},
            {"AbstractTimeUnitFutureSuffix", ""},
            {"AbstractTimeUnitPastPrefix", ""},
            {"AbstractTimeUnitPastSuffix", ""},
            {"AbstractTimeUnitSingularName", ""},
            {"AbstractTimeUnitPluralName", ""}};

    @Override
    public Object[][] getContents() {
        return OBJECTS;
    }

    private volatile ConcurrentMap<TimeUnit, TimeFormat> formatMap = new ConcurrentHashMap<TimeUnit, TimeFormat>();

    @Override
    public TimeFormat getFormatFor(TimeUnit t) {
        if (!formatMap.containsKey(t)) {
            formatMap.putIfAbsent(t, new JaTimeFormat(this, t));
        }
        return formatMap.get(t);
    }

    private static class JaTimeFormat implements TimeFormat {

        private static final String NEGATIVE = "-";
        public static final String SIGN = "%s";
        public static final String QUANTITY = "%n";
        public static final String UNIT = "%u";

        private final ResourceBundle bundle;
        private String singularName = "";
        private String pluralName = "";
        private String futureSingularName = "";
        private String futurePluralName = "";
        private String pastSingularName = "";
        private String pastPluralName = "";

        private String pattern = "";
        private String futurePrefix = "";
        private String futureSuffix = "";
        private String pastPrefix = "";
        private String pastSuffix = "";
        private int roundingTolerance = 50;

        public JaTimeFormat(final ResourceBundle bundle, final TimeUnit unit) {

            this.bundle = bundle;

            setPattern(bundle.getString(getUnitName(unit) + "Pattern"));
            setFuturePrefix(bundle.getString(getUnitName(unit) + "FuturePrefix"));
            setFutureSuffix(bundle.getString(getUnitName(unit) + "FutureSuffix"));
            setPastPrefix(bundle.getString(getUnitName(unit) + "PastPrefix"));
            setPastSuffix(bundle.getString(getUnitName(unit) + "PastSuffix"));

            setSingularName(bundle.getString(getUnitName(unit) + "SingularName"));
            setPluralName(bundle.getString(getUnitName(unit) + "PluralName"));

            try {
                setFuturePluralName(bundle.getString(getUnitName(unit) + "FuturePluralName"));
            }
            catch (Exception e) {
            }
            try {
                setFutureSingularName((bundle.getString(getUnitName(unit) + "FutureSingularName")));
            }
            catch (Exception e) {
            }
            try {
                setPastPluralName((bundle.getString(getUnitName(unit) + "PastPluralName")));
            }
            catch (Exception e) {
            }
            try {
                setPastSingularName((bundle.getString(getUnitName(unit) + "PastSingularName")));
            }
            catch (Exception e) {
            }
        }

        private String getUnitName(TimeUnit unit) {
            return unit.getClass().getSimpleName();
        }

        @Override
        public String format(final Duration duration) {
            return format(duration, true);
        }

        @Override
        public String formatUnrounded(Duration duration) {
            return format(duration, false);
        }

        private String format(final Duration duration, final boolean round) {
            String sign = getSign(duration);
            String unit = getGramaticallyCorrectName(duration, round);
            long quantity = getQuantity(duration, round);

            return applyPattern(sign, unit, quantity);
        }

        private String applyPattern(final String sign, final String unit, final long quantity) {
            String result = getPattern(quantity).replaceAll(SIGN, sign);
            result = result.replaceAll(QUANTITY, String.valueOf(quantity));
            result = result.replaceAll(UNIT, unit);
            return result;
        }

        protected String getPattern(final long quantity) {
            return pattern;
        }

        public String getPattern() {
            return pattern;
        }

        protected long getQuantity(Duration duration, boolean round) {
            return Math.abs(round ? duration.getQuantityRounded(roundingTolerance) : duration.getQuantity());
        }

        protected String getGramaticallyCorrectName(final Duration d, boolean round) {
            String result = getSingularName(d);
            if ((Math.abs(getQuantity(d, round)) == 0) || (Math.abs(getQuantity(d, round)) > 1)) {
                result = getPluralName(d);
            }
            return result;
        }

        private String getSign(final Duration d) {
            if (d.getQuantity() < 0) {
                return NEGATIVE;
            }
            return "";
        }

        private String getSingularName(Duration duration) {
            if (duration.isInFuture() && futureSingularName != null && futureSingularName.length() > 0) {
                return futureSingularName;
            }
            else if (duration.isInPast() && pastSingularName != null && pastSingularName.length() > 0) {
                return pastSingularName;
            }
            else {
                return singularName;
            }
        }

        private String getPluralName(Duration duration) {
            if (duration.isInFuture() && futurePluralName != null && futureSingularName.length() > 0) {
                return futurePluralName;
            }
            else if (duration.isInPast() && pastPluralName != null && pastSingularName.length() > 0) {
                return pastPluralName;
            }
            else {
                return pluralName;
            }
        }

        @Override
        public String decorate(Duration duration, String time) {
            StringBuilder result = new StringBuilder();
            if (duration.isInPast()) {
                result.append(pastPrefix).append(time).append(pastSuffix);
            }
            else {
                result.append(futurePrefix).append(time).append(futureSuffix);
            }
            return result.toString().replaceAll("\\s+", " ").trim();
        }

        @Override
        public String decorateUnrounded(Duration duration, String time) {
            return decorate(duration, time);
        }

        /*
         * Builder Setters
         */
        public JaTimeFormat setPattern(final String pattern) {
            this.pattern = pattern;
            return this;
        }

        public JaTimeFormat setFuturePrefix(final String futurePrefix) {
            this.futurePrefix = futurePrefix.trim();
            return this;
        }

        public JaTimeFormat setFutureSuffix(final String futureSuffix) {
            this.futureSuffix = futureSuffix.trim();
            return this;
        }

        public JaTimeFormat setPastPrefix(final String pastPrefix) {
            this.pastPrefix = pastPrefix.trim();
            return this;
        }

        public JaTimeFormat setPastSuffix(final String pastSuffix) {
            this.pastSuffix = pastSuffix.trim();
            return this;
        }

        /**
         * The percentage of the current {@link TimeUnit}.getMillisPerUnit() for which the quantity may be rounded up by one.
         *
         * @param roundingTolerance
         * @return
         */
        public JaTimeFormat setRoundingTolerance(final int roundingTolerance) {
            this.roundingTolerance = roundingTolerance;
            return this;
        }

        public JaTimeFormat setSingularName(String name) {
            this.singularName = name;
            return this;
        }

        public JaTimeFormat setPluralName(String pluralName) {
            this.pluralName = pluralName;
            return this;
        }

        public JaTimeFormat setFutureSingularName(String futureSingularName) {
            this.futureSingularName = futureSingularName;
            return this;
        }

        public JaTimeFormat setFuturePluralName(String futurePluralName) {
            this.futurePluralName = futurePluralName;
            return this;
        }

        public JaTimeFormat setPastSingularName(String pastSingularName) {
            this.pastSingularName = pastSingularName;
            return this;
        }

        public JaTimeFormat setPastPluralName(String pastPluralName) {
            this.pastPluralName = pastPluralName;
            return this;
        }

        @Override
        public String toString() {
            return "JaTimeFormat [pattern=" + pattern + ", futurePrefix=" + futurePrefix + ", futureSuffix="
                    + futureSuffix + ", pastPrefix=" + pastPrefix + ", pastSuffix=" + pastSuffix +
                    ", roundingTolerance="
                    + roundingTolerance + "]";
        }

    }
}