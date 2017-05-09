package org.ocpsoft.prettytime.i18n;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.*;

import java.util.ListResourceBundle;

/**
 * Created by Azimkhan Yerzhan on 5/8/2017
 */
public class Resources_kk extends ListResourceBundle implements TimeFormatProvider {

    private static final Object[][] OBJECTS = new Object[0][0];
    @Override
    protected Object[][] getContents() {
        return OBJECTS;
    }

    private static class KkTimeFormat implements TimeFormat
    {
        private final int tolerance = 50;
        private final String[] forms;

        public KkTimeFormat(String... plurals)
        {
            if (plurals.length != 2) {
                throw new IllegalArgumentException("Future and past forms must be provided for kazakh language!");
            }
            this.forms = plurals;
        }

        @Override
        public String format(Duration duration)
        {
            long quantity = duration.getQuantityRounded(tolerance);
            StringBuilder result = new StringBuilder();
            result.append(quantity);
            return result.toString();
        }

        @Override
        public String formatUnrounded(Duration duration)
        {
            long quantity = duration.getQuantity();
            StringBuilder result = new StringBuilder();
            result.append(quantity);
            return result.toString();
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
            StringBuilder builder = new StringBuilder();
            int formIndex = past ? 0 : 1;

            builder.append(time);
            builder.append(' ');
            builder.append(forms[formIndex]);
            builder.append(' ');

            if (past) {
                builder.append("бұрын");
            }

            if (future) {
                builder.append("кейін");
            }
            return builder.toString();
        }
    }

    @Override
    public TimeFormat getFormatFor(TimeUnit t) {
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
                        return "дәл қазір";
                    }
                    if (duration.isInPast()) {
                        return "жана ғана";
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
            return new KkTimeFormat("ғасыр","ғасырдан");
        }
        else if (t instanceof Day) {
            return new KkTimeFormat("күн", "күннен");
        }
        else if (t instanceof Decade) {
            return new KkTimeFormat("онжылдық", "онжылдықтан");
        }
        else if (t instanceof Hour) {
            return new KkTimeFormat("сағат", "сағаттан");
        }
        else if (t instanceof Millennium) {
            return new KkTimeFormat("мыңжылдық", "мыңжылдықтан");
        }
        else if (t instanceof Millisecond) {
            return new KkTimeFormat("миллисекунд", "миллисекундтан");
        }
        else if (t instanceof Minute) {
            return new KkTimeFormat("минут", "минуттан");
        }
        else if (t instanceof Month) {
            return new KkTimeFormat("ай", "айдан");
        }
        else if (t instanceof Second) {
            return new KkTimeFormat("секунд", "секундтан");
        }
        else if (t instanceof Week) {
            return new KkTimeFormat("апта", "аптадан");
        }
        else if (t instanceof Year) {
            return new KkTimeFormat("жыл", "жылдан");
        }
        return null;
    }
}
