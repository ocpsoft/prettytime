package org.ocpsoft.prettytime.i18n;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.*;

import java.util.Arrays;
import java.util.ListResourceBundle;

/**
 * Created with IntelliJ IDEA. User: Tumin Alexander Date: 2012-12-13 Time: 03:33
 */
public class Resources_ru extends ListResourceBundle implements TimeFormatProvider
{
   private static final Object[][] OBJECTS = new Object[0][0];

   private static final int tolerance = 50;

   // see http://translate.sourceforge.net/wiki/l10n/pluralforms
   private static final int russianPluralForms = 4;

   private class TimeFormatAided implements TimeFormat {
      private final String[] pluarls;

      public TimeFormatAided(String... plurals) {
         if (plurals.length != russianPluralForms) {
            throw new IllegalArgumentException(String.format("Wrong plural forms number for russian language! " +
                    "Expected %s, got %s\nPlurals: %s", russianPluralForms, plurals.length, Arrays.toString(plurals)));
         }
         this.pluarls = plurals;
      }

      @Override
      public String format(Duration duration) {
         long roundedQuantity = Math.abs(duration.getQuantityRounded(tolerance));
         return performFormat(roundedQuantity, true);
      }

      @Override
      public String formatUnrounded(Duration duration) {
         long unroundedQuantity = Math.abs(duration.getQuantity());
         return performFormat(unroundedQuantity, true);
      }

      public String performFormat(long n, boolean isDuration) {
         // a bit cryptic, yet well-tested
         // consider http://translate.sourceforge.net/wiki/l10n/pluralforms
         int pluralIdx = (n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4
                  && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);

         if (pluralIdx > russianPluralForms) {
            // impossible happening
            throw new IllegalStateException("Wrong plural index was calculated somehow for russian language");
         }

         String result = String.valueOf(n) +
                 ' ' +
                 pluarls[isDuration && pluralIdx == 0 ? pluralIdx : pluralIdx + 1];

         return result;
      }

      @Override
      public String decorate(Duration duration, String time) {
         if(requiresReformatting(duration, true)) {
            long roundedQuantity = Math.abs(duration.getQuantityRounded(tolerance));
            return performDecoration(duration, performFormat(roundedQuantity, false));
         }
         return performDecoration(duration, time);
      }

      @Override
      public String decorateUnrounded(Duration duration, String time) {
         if(requiresReformatting(duration, false)) {
            long unroundedQuantity = Math.abs(duration.getQuantity());
            return performDecoration(duration, performFormat(unroundedQuantity, false));
         }
         return performDecoration(duration, time);
      }

      public String performDecoration(Duration duration, String time) {
         if (duration.isInFuture()) {
            return "через " + time;
         }
         if (duration.isInPast()) {
            return time + " назад";
         }
         return time;
      }

      /**
       * While in English format can be achieved by simply adding " ago" to formatDuration result,
       * in Russian there's difference: "1 minute" is "1 минута" but "1 minute ago" is "1 минуту назад"
       * see here: <a href="https://www.thoughtco.com/russian-cases-4768614">article about russian grammatical cases</a>
       * This hacky method checks if it's the case when result of format method should be corrected.
       * @param duration The original {@link Duration} instance from which the time string should be decorated.
       * @param isRounded Determines whether rounded quantity be checked or plain one
       * @return Is reformatting required (is case "1 минута" - "1 минуту назад" - "через 1 минуту" reached)
       */
      public boolean requiresReformatting(Duration duration, boolean isRounded) {
         long quantity = isRounded ? Math.abs(duration.getQuantityRounded(tolerance)) : Math.abs(duration.getQuantity());
         return quantity == 1;
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
                  return "сейчас";
               }
               if (duration.isInPast()) {
                  return "только что";
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
         return new TimeFormatAided("век", "век", "века", "веков");
      }
      else if (t instanceof Day) {
         return new TimeFormatAided("день", "день", "дня", "дней");
      }
      else if (t instanceof Decade) {
         return new TimeFormatAided("десятилетие", "десятилетие", "десятилетия", "десятилетий");
      }
      else if (t instanceof Hour) {
         return new TimeFormatAided("час", "час", "часа", "часов");
      }
      else if (t instanceof Millennium) {
         return new TimeFormatAided("тысячелетие", "тысячелетие", "тысячелетия", "тысячелетий");
      }
      else if (t instanceof Millisecond) {
         return new TimeFormatAided("миллисекунда", "миллисекунду", "миллисекунды", "миллисекунд");
      }
      else if (t instanceof Minute) {
         return new TimeFormatAided("минута", "минуту", "минуты", "минут");
      }
      else if (t instanceof Month) {
         return new TimeFormatAided("месяц", "месяц", "месяца", "месяцев");
      }
      else if (t instanceof Second) {
         return new TimeFormatAided("секунда", "секунду", "секунды", "секунд");
      }
      else if (t instanceof Week) {
         return new TimeFormatAided("неделя", "неделю", "недели", "недель");
      }
      else if (t instanceof Year) {
         return new TimeFormatAided("год", "год", "года", "лет");
      }
      return null; // error
   }
}
