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
package org.ocpsoft.pretty.time;

/**
 * Represents a simple method of formatting a specific {@link Duration} of time
 * 
 * @author lb3
 */
public class BasicTimeFormat implements TimeFormat
{
   private static final String NEGATIVE = "-";
   public static final String SIGN = "%s";
   public static final String QUANTITY = "%n";
   public static final String UNIT = "%u";

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

   private String format(final Duration duration, final boolean doRounding)
   {
      String sign = getSign(duration);
      String unit = getGramaticallyCorrectName(duration);
      long quantity = getQuantity(duration, doRounding);

      return applyPattern(sign, unit, quantity);
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

   private String applyPattern(final String sign, final String unit, final long quantity)
   {
      String result = pattern.replaceAll(SIGN, sign);
      result = result.replaceAll(QUANTITY, String.valueOf(quantity));
      result = result.replaceAll(UNIT, unit);
      return result;
   }

   private long getQuantity(final Duration duration)
   {
      return getQuantity(duration, true);
   }

   private long getQuantity(final Duration duration, boolean doRounding)
   {
      long quantity = Math.abs(duration.getQuantity());

      if (duration.getDelta() != 0)
      {
         double threshold = Math
                  .abs(((double) duration.getDelta() / (double) duration.getUnit().getMillisPerUnit()) * 100);
         if (doRounding && (threshold > roundingTolerance))
         {
            quantity = quantity + 1;
         }
      }
      return quantity;
   }

   private String getGramaticallyCorrectName(final Duration d)
   {
      String result = d.getUnit().getName();
      if ((Math.abs(getQuantity(d)) == 0) || (Math.abs(getQuantity(d)) > 1))
      {
         result = d.getUnit().getPluralName();
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

   /*
    * Builder Setters
    */
   public BasicTimeFormat setPattern(final String pattern)
   {
      this.pattern = pattern;
      return this;
   }

   public BasicTimeFormat setFuturePrefix(final String futurePrefix)
   {
      this.futurePrefix = futurePrefix.trim();
      return this;
   }

   public BasicTimeFormat setFutureSuffix(final String futureSuffix)
   {
      this.futureSuffix = futureSuffix.trim();
      return this;
   }

   public BasicTimeFormat setPastPrefix(final String pastPrefix)
   {
      this.pastPrefix = pastPrefix.trim();
      return this;
   }

   public BasicTimeFormat setPastSuffix(final String pastSuffix)
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
   public BasicTimeFormat setRoundingTolerance(final int roundingTolerance)
   {
      this.roundingTolerance = roundingTolerance;
      return this;
   }

   /*
    * Normal getters
    */

   public String getPattern()
   {
      return pattern;
   }

   public String getFuturePrefix()
   {
      return futurePrefix;
   }

   public String getFutureSuffix()
   {
      return futureSuffix;
   }

   public String getPastPrefix()
   {
      return pastPrefix;
   }

   public String getPastSuffix()
   {
      return pastSuffix;
   }

   public int getRoundingTolerance()
   {
      return roundingTolerance;
   }

   @Override
   public String toString()
   {
      return "BasicTimeFormat [pattern=" + pattern + ", futurePrefix=" + futurePrefix + ", futureSuffix="
               + futureSuffix + ", pastPrefix=" + pastPrefix + ", pastSuffix=" + pastSuffix + ", roundingTolerance="
               + roundingTolerance + "]";
   }

}
