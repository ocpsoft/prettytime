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
package org.ocpsoft.prettytime.format;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;

/**
 * Represents a simple method of formatting a specific {@link Duration} of time
 *
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class SimpleTimeFormat implements TimeFormat
{
   private static final String NEGATIVE = "-";
   public static final String SIGN = "%s";
   public static final String QUANTITY = "%n";
   public static final String UNIT = "%u";

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

   @Override
   public String format(final Duration duration)
   {
      return format(duration, true);
   }

   @Override
   public String formatUnrounded(Duration duration)
   {
      return format(duration, false);
   }

   @Override
   public String decorate(Duration duration, String time)
   {
      StringBuilder result = new StringBuilder();
      if (duration.isInPast())
      {
         result.append(pastPrefix).append(" ").append(time).append(" ").append(pastSuffix);
      }
      else
      {
         result.append(futurePrefix).append(" ").append(time).append(" ").append(futureSuffix);
      }
      return result.toString().replaceAll("\\s+", " ").trim();
   }

   @Override
   public String decorateUnrounded(Duration duration, String time)
   {
      // This format does not need to know about rounding during decoration.
      return decorate(duration, time);
   }

   private String format(final Duration duration, final boolean round)
   {
      String sign = getSign(duration);
      String unit = getGramaticallyCorrectName(duration, round);
      long quantity = getQuantity(duration, round);

      return applyPattern(sign, unit, quantity);
   }

   private String applyPattern(final String sign, final String unit, final long quantity)
   {
      String result = getPattern(quantity).replaceAll(SIGN, sign);
      result = result.replaceAll(QUANTITY, String.valueOf(quantity));
      result = result.replaceAll(UNIT, unit);
      return result;
   }

   protected String getPattern(final long quantity)
   {
      return pattern;
   }

   public String getPattern()
   {
      return pattern;
   }

   protected long getQuantity(Duration duration, boolean round)
   {
      return Math.abs(round ? duration.getQuantityRounded(roundingTolerance) : duration.getQuantity());
   }

   protected String getGramaticallyCorrectName(final Duration d, boolean round)
   {
      String result = getSingularName(d);
      if ((Math.abs(getQuantity(d, round)) == 0) || (Math.abs(getQuantity(d, round)) > 1))
      {
         result = getPluralName(d);
      }
      return result;
   }

   private String getSign(final Duration d)
   {
      if (d.getQuantity() < 0)
      {
         return NEGATIVE;
      }
      return "";
   }

   private String getSingularName(Duration duration)
   {
      if (duration.isInFuture() && futureSingularName != null && futureSingularName.length() > 0)
         return futureSingularName;
      else if (duration.isInPast() && pastSingularName != null && pastSingularName.length() > 0)
         return pastSingularName;
      else
         return singularName;
   }

   private String getPluralName(Duration duration)
   {
      if (duration.isInFuture() && futurePluralName != null && futureSingularName.length() > 0)
         return futurePluralName;
      else if (duration.isInPast() && pastPluralName != null && pastSingularName.length() > 0)
         return pastPluralName;
      else
         return pluralName;
   }

   /*
    * Builder Setters
    */
   public SimpleTimeFormat setPattern(final String pattern)
   {
      this.pattern = pattern;
      return this;
   }

   public SimpleTimeFormat setFuturePrefix(final String futurePrefix)
   {
      this.futurePrefix = futurePrefix.trim();
      return this;
   }

   public SimpleTimeFormat setFutureSuffix(final String futureSuffix)
   {
      this.futureSuffix = futureSuffix.trim();
      return this;
   }

   public SimpleTimeFormat setPastPrefix(final String pastPrefix)
   {
      this.pastPrefix = pastPrefix.trim();
      return this;
   }

   public SimpleTimeFormat setPastSuffix(final String pastSuffix)
   {
      this.pastSuffix = pastSuffix.trim();
      return this;
   }

   /**
    * The percentage of the current {@link TimeUnit}.getMillisPerUnit() for which the quantity may be rounded up by one.
    *
    * @param roundingTolerance
    * @return
    */
   public SimpleTimeFormat setRoundingTolerance(final int roundingTolerance)
   {
      this.roundingTolerance = roundingTolerance;
      return this;
   }

   public SimpleTimeFormat setSingularName(String name)
   {
      this.singularName = name;
      return this;
   }

   public SimpleTimeFormat setPluralName(String pluralName)
   {
      this.pluralName = pluralName;
      return this;
   }

   public SimpleTimeFormat setFutureSingularName(String futureSingularName)
   {
      this.futureSingularName = futureSingularName;
      return this;
   }

   public SimpleTimeFormat setFuturePluralName(String futurePluralName)
   {
      this.futurePluralName = futurePluralName;
      return this;
   }

   public SimpleTimeFormat setPastSingularName(String pastSingularName)
   {
      this.pastSingularName = pastSingularName;
      return this;
   }

   public SimpleTimeFormat setPastPluralName(String pastPluralName)
   {
      this.pastPluralName = pastPluralName;
      return this;
   }

   @Override
   public String toString()
   {
      return "SimpleTimeFormat [pattern=" + pattern + ", futurePrefix=" + futurePrefix + ", futureSuffix="
               + futureSuffix + ", pastPrefix=" + pastPrefix + ", pastSuffix=" + pastSuffix + ", roundingTolerance="
               + roundingTolerance + "]";
   }
}
