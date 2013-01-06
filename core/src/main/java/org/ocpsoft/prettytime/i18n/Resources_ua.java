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
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.*;

import java.util.ListResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Tumin Alexander
 * Date: 2012-12-13
 * Time: 03:33
 * 
 * reedit to Ukrainian with Eclipse).
 * User: Ihor Lavrynuk
 * Date: 2013-01-06
 * Time: 15:04
 * 
 */
public class Resources_ua extends ListResourceBundle implements TimeFormatProvider
{
    private static final Object[][] OBJECTS = new Object[0][0];

    private static final int tolerance = 50;

    // see http://translate.sourceforge.net/wiki/l10n/pluralforms
    private static final int slavicPluralForms = 3;

    private static class TimeFormatAided implements TimeFormat {
        private final String[] pluarls;

        public TimeFormatAided(String ... plurals) {
            if (plurals.length != slavicPluralForms) {
                throw new IllegalArgumentException("Wrong plural forms number for slavic language!");
            }
            this.pluarls = plurals;
        }

        @Override
        public String format(Duration duration) {
            long quantity = duration.getQuantityRounded(tolerance);
            StringBuilder result = new StringBuilder();
            result.append(quantity);
            return result.toString();
        }

        @Override
        public String formatUnrounded(Duration duration) {
            long quantity = duration.getQuantity();
            StringBuilder result = new StringBuilder();
            result.append(quantity);
            return result.toString();
        }

        @Override
        public String decorate(Duration duration, String time) {
            return performDecoration(
                    duration.isInPast(),
                    duration.isInFuture(),
                    duration.getQuantityRounded(tolerance),
                    time
            );
        }

        @Override
        public String decorateUnrounded(Duration duration, String time) {
            return performDecoration(
                    duration.isInPast(),
                    duration.isInFuture(),
                    duration.getQuantity(),
                    time
            );
        }

        private String performDecoration(boolean past, boolean future, long n, String time) {
            // a bit cryptic, yet well-tested
            // consider http://translate.sourceforge.net/wiki/l10n/pluralforms
            int pluralIdx = (n%10==1 && n%100!=11 ? 0 : n%10>=2 && n%10<=4 && (n%100<10 || n%100>=20) ? 1 : 2);
            if (pluralIdx > slavicPluralForms) {
                // impossible happening
                throw new IllegalStateException("Wrong plural index was calculated somehow for slavic language");
            }

            StringBuilder result = new StringBuilder();

            if (future) {
                result.append("через ");
            }

            result.append(time);
            result.append(' ');
            result.append(pluarls[pluralIdx]);

            if (past) {
                result.append(" тому");
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
    public TimeFormat getFormatFor(TimeUnit t) {
        if (t instanceof JustNow) {
            return new TimeFormat() {
                @Override
                public String format(Duration duration) {
                    return performFormat(duration);
                }

                @Override
                public String formatUnrounded(Duration duration) {
                    return performFormat(duration);
                }

                private String performFormat(Duration duration) {
                    if (duration.isInFuture()) {
                        return "зараз";
                    }
                    if (duration.isInPast()) {
                        return "тільки що";
                    }
                    return null;
                }

                @Override
                public String decorate(Duration duration, String time) {
                    return time;
                }

                @Override
                public String decorateUnrounded(Duration duration, String time) {
                    return time;
                }
            };
        } else if (t instanceof Century) {
            return new TimeFormatAided("століття", "століття", "столітть");
        } else if (t instanceof Day) {
            return new TimeFormatAided("день", "дні", "днів");
        } else if (t instanceof Decade) {
            return new TimeFormatAided("десятиліття", "десятиліття", "десятиліть");
        } else if (t instanceof Hour) {
            return new TimeFormatAided("годину", "години", "годин");
        } else if (t instanceof Millennium) {
            return new TimeFormatAided("тисячоліття", "тисячоліття", "тисячоліть");
        } else if (t instanceof Millisecond) {
            return new TimeFormatAided("мілісекунду", "мілісекунди", "мілісекунд");
        } else if (t instanceof Minute) {
            return new TimeFormatAided("хвилину", "хвилини", "хвилин");
        } else if (t instanceof Month) {
            return new TimeFormatAided("місяць", "місяці", "місяців");
        } else if (t instanceof Second) {
            return new TimeFormatAided("секунду", "секунди", "секунд");
        } else if (t instanceof Week) {
            return new TimeFormatAided("тиждень", "тижні", "тижнів");
        } else if (t instanceof Year) {
            return new TimeFormatAided("рік", "роки", "років");
        }
        return null; // error
    }
}
