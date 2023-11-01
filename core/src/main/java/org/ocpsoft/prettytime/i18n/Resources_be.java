package org.ocpsoft.prettytime.i18n;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.*;

import java.util.ListResourceBundle;

/**
 * Created with IntelliJ IDEA. User: Tumin Alexander Date: 2012-12-13 Time: 03:33
 *
 * reedit to Ukrainian with Eclipse). User: Ihor Lavrynuk Date: 2013-01-06 Time: 15:04
 *
 * reedit to Belarusian with IntelliJ IDEA. User: Siarhiej Bahdaniec Date: 2023-10-01 Time: 11:02 PM
 */
public class Resources_be extends ListResourceBundle implements TimeFormatProvider
{
    private static final Object[][] OBJECTS = new Object[0][0];

    private static final int tolerance = 50;

    // see http://translate.sourceforge.net/wiki/l10n/pluralforms
    private static final int slavicPluralForms = 3;

    private static class TimeFormatAided implements TimeFormat
    {
        private final String[] pluarls;

        public TimeFormatAided(String... plurals)
        {
            if (plurals.length != slavicPluralForms) {
                throw new IllegalArgumentException("Wrong plural forms number for slavic language!");
            }
            this.pluarls = plurals;
        }

        @Override
        public String format(Duration duration)
        {
            long quantity = duration.getQuantityRounded(tolerance);
            return String.valueOf(quantity);
        }

        @Override
        public String formatUnrounded(Duration duration)
        {
            long quantity = duration.getQuantity();
            return String.valueOf(quantity);
        }

        @Override
        public String decorate(Duration duration, String time)
        {
            return performDecoration(
                    duration.isInPast(),
                    duration.isInFuture(),
                    duration.getQuantityRounded(tolerance),
                    time);
        }

        @Override
        public String decorateUnrounded(Duration duration, String time)
        {
            return performDecoration(
                    duration.isInPast(),
                    duration.isInFuture(),
                    duration.getQuantity(),
                    time);
        }

        private String performDecoration(boolean past, boolean future, long n, String time)
        {
            // a bit cryptic, yet well-tested
            // consider http://translate.sourceforge.net/wiki/l10n/pluralforms
            int pluralIdx = (n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4
                    && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            if (pluralIdx > slavicPluralForms) {
                // impossible happening
                throw new IllegalStateException("Wrong plural index was calculated somehow for slavic language");
            }

            StringBuilder result = new StringBuilder();

            if (future) {
                result.append("праз ");
            }

            result.append(time);
            result.append(' ');
            result.append(pluarls[pluralIdx]);

            if (past) {
                result.append(" таму");
            }

            return result.toString();
        }
    }

    @Override
    public Object[][] getContents()
    {
        return OBJECTS;
    }

    @Override
    public TimeFormat getFormatFor(TimeUnit t)
    {
        if (t instanceof JustNow) {
            return new TimeFormat() {
                @Override
                public String format(Duration duration)
                {
                    return performFormat(duration);
                }

                @Override
                public String formatUnrounded(Duration duration)
                {
                    return performFormat(duration);
                }

                private String performFormat(Duration duration)
                {
                    if (duration.isInFuture()) {
                        return "зараз";
                    }
                    if (duration.isInPast()) {
                        return "толькі што";
                    }
                    return null;
                }

                @Override
                public String decorate(Duration duration, String time)
                {
                    return time;
                }

                @Override
                public String decorateUnrounded(Duration duration, String time)
                {
                    return time;
                }
            };
        }
        else if (t instanceof Century) {
            return new TimeFormatAided("стагоддзе", "стагоддзі", "стагоддзяў");
        }
        else if (t instanceof Day) {
            return new TimeFormatAided("дзень", "дні", "дзён");
        }
        else if (t instanceof Decade) {
            return new TimeFormatAided("дзесяцігоддзе", "дзесяцігоддзі", "дзесяцігоддзяў");
        }
        else if (t instanceof Hour) {
            return new TimeFormatAided("гадзіну", "гадзіны", "гадзін");
        }
        else if (t instanceof Millennium) {
            return new TimeFormatAided("тысячагоддзе", "тысячагоддзі", "тысячагоддзяў");
        }
        else if (t instanceof Millisecond) {
            return new TimeFormatAided("мілісекунду", "мілісекунды", "мілісекунд");
        }
        else if (t instanceof Minute) {
            return new TimeFormatAided("хвіліну", "хвіліны", "хвілін");
        }
        else if (t instanceof Month) {
            return new TimeFormatAided("месяц", "месяцы", "месяцаў");
        }
        else if (t instanceof Second) {
            return new TimeFormatAided("секунду", "секунды", "секунд");
        }
        else if (t instanceof Week) {
            return new TimeFormatAided("тыдзень", "тыдні", "тыдняў");
        }
        else if (t instanceof Year) {
            return new TimeFormatAided("год", "гады", "гадоў");
        }
        return null; // error
    }
}