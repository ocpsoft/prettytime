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
package org.ocpsoft.prettytime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat;
import org.ocpsoft.prettytime.impl.ResourcesTimeUnit;
import org.ocpsoft.prettytime.units.Century;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Decade;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millennium;
import org.ocpsoft.prettytime.units.Millisecond;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Month;
import org.ocpsoft.prettytime.units.Second;
import org.ocpsoft.prettytime.units.TimeUnitComparator;
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Year;

/**
 * A utility for creating social-networking style timestamps. (e.g. "just now", "moments ago", "3 days ago",
 * "within 2 months")
 * <p>
 * <b>Usage:</b>
 * <p>
 * <code>
 * PrettyTime t = new PrettyTime();<br/>
 * String timestamp = t.format(new Date());<br/>
 * //result: moments from now
 * </code>
 * </p>
 * <code>
 * String timestamp = t.format(new Date(System.currentTimeMillis() + 1000 * 60 * 10));<br/>
 * //result: 10 minutes from now
 * </code>
 * <p>
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class PrettyTime
{
   private volatile Date reference;
   private volatile Locale locale = Locale.getDefault();
   private volatile Map<TimeUnit, TimeFormat> units = new LinkedHashMap<TimeUnit, TimeFormat>();
   private volatile List<TimeUnit> cachedUnits;
   private String overrideResourceBundle;

   /**
    * Create a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting.
    */
   public PrettyTime()
   {
      initTimeUnits();
   }

   /**
    * Create a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting.
    * Will use {@link String} as an optional override to the default resource bundles.
    */
   public PrettyTime(String overrideResourceBundle)
   {
      this.overrideResourceBundle = overrideResourceBundle;
      initTimeUnits();
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link Date} timestamp to represent the reference
    * point for {@link Date} comparison, and will use {@link Locale#getDefault()} as the selected {@link Locale} for
    * language and dialect formatting. If the given {@link Date} is <code>null</code>, this instance will always use the
    * current value of {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison.
    * <p>
    * 
    * <p>
    * See {@code PrettyTime#setReference(Date timestamp)}.
    */
   public PrettyTime(final Date reference)
   {
      this();
      setReference(reference);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link Date} timestamp to represent the reference
    * point for {@link Date} comparison, and will use {@link Locale#getDefault()} as the selected {@link Locale} for
    * language and dialect formatting. If the given {@link Date} is <code>null</code>, this instance will always use the
    * current value of {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison.
    * Will use {@link String} as an optional override to the default resource bundles.
    * <p>
    *
    * <p>
    * See {@code PrettyTime#setReference(Date timestamp)}.
    */
   public PrettyTime(final Date reference, String overrideResourceBundle)
   {
      this(overrideResourceBundle);
      setReference(reference);
   }

   /**
    * Construct a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison. This instance
    * will use the given {@link Locale} instead of the system default. If the provided {@link Locale} is
    * <code>null</code>, {@link Locale#getDefault()} will be used.
    */
   public PrettyTime(final Locale locale)
   {
      setLocale(locale);
      initTimeUnits();
   }

   /**
    * Construct a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison. This instance
    * will use the given {@link Locale} instead of the system default. If the provided {@link Locale} is
    * <code>null</code>, {@link Locale#getDefault()} will be used.
    * Will use {@link String} as an optional override to the default resource bundles.
    */
   public PrettyTime(final Locale locale, String overrideResourceBundle)
   {
      this.overrideResourceBundle = overrideResourceBundle;
      setLocale(locale);
      initTimeUnits();
   }

   /**
    * Construct a new {@link PrettyTime} instance that will use the given {@link Date} timestamp to represent the
    * reference point for {@link Date} comparison, and will use the given {@link Locale} instead of the system default.
    * <p>
    * If the provided {@link Locale} is <code>null</code>, {@link Locale#getDefault()} will be used instead.<br>
    * If the given {@link Date} is <code>null</code>, this instance will always use current value of
    * {@link System#currentTimeMillis()} will be used to represent the reference point for {@link Date} comparison.
    * <p>
    * See {@code PrettyTime#setReference(Date timestamp)}.
    */
   public PrettyTime(final Date reference, final Locale locale)
   {
      this(locale);
      setReference(reference);
   }

   /**
    * Construct a new {@link PrettyTime} instance that will use the given {@link Date} timestamp to represent the
    * reference point for {@link Date} comparison, and will use the given {@link Locale} instead of the system default.
    * <p>
    * If the provided {@link Locale} is <code>null</code>, {@link Locale#getDefault()} will be used instead.<br>
    * If the given {@link Date} is <code>null</code>, this instance will always use current value of
    * {@link System#currentTimeMillis()} will be used to represent the reference point for {@link Date} comparison.
    * Will use {@link String} as an optional override to the default resource bundles.
    * <p>
    * See {@code PrettyTime#setReference(Date timestamp)}.
    */
   public PrettyTime(final Date reference, final Locale locale, String overrideResourceBundle)
   {
      this(locale, overrideResourceBundle);
      setReference(reference);
   }

   /**
    * Calculate the approximate {@link Duration} between the reference {@link Date} and given {@link Date}. If the given
    * {@link Date} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * See {@code PrettyTime#getReference()}.
    */
   public Duration approximateDuration(Date then)
   {
      if (then == null)
         then = now();

      Date ref = reference;
      if (null == ref)
         ref = now();

      long difference = then.getTime() - ref.getTime();
      return calculateDuration(difference);
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Date}, and the given {@link Date}. If the given {@link Date} is
    * <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is granular enough to represent the remainder
    * of time (in milliseconds).
    * 
    * @param then The {@link Date} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @return A sorted {@link List} of {@link Duration} objects, from largest to smallest. Each element in the list
    *         represents the approximate duration (number of times) that {@link TimeUnit} to fit into the previous
    *         element's delta. The first element is the largest {@link TimeUnit} to fit within the total difference
    *         between compared dates.
    */
   public List<Duration> calculatePreciseDuration(Date then)
   {
      if (then == null)
         then = now();

      Date reference = this.reference;
      if (null == reference)
         reference = now();

      List<Duration> result = new ArrayList<Duration>();
      long difference = then.getTime() - reference.getTime();
      Duration duration = calculateDuration(difference);
      result.add(duration);
      while (0 != duration.getDelta())
      {
         duration = calculateDuration(duration.getDelta());
         if (result.size() > 0)
         {
            Duration last = result.get(result.size() - 1);
            if (last.getUnit().equals(duration.getUnit()))
            {
               break;
            }
         }

         if (duration.getUnit().isPrecise())
            result.add(duration);
      }
      return result;
   }

   /**
    * Format the given {@link Date} object. If the given {@link Date} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param then the {@link Date} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(Date then)
   {
      if (then == null)
         then = now();

      Duration d = approximateDuration(then);
      return format(d);
   }

   /**
    * Format the given {@link Calendar} object. If the given {@link Calendar} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param then the {@link Calendar} whose date is to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(Calendar then)
   {
      if (then == null)
         return format(now());
      return format(then.getTime());
   }

   /**
    * Format the given {@link Duration} object, using the {@link TimeFormat} specified by the {@link TimeUnit} contained
    * within. If the given {@link Duration} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param duration the {@link Duration} to be formatted
    * @return A formatted string representing {@code duration}
    */
   public String format(final Duration duration)
   {
      if (duration == null)
         return format(now());

      TimeFormat format = getFormat(duration.getUnit());
      String time = format.format(duration);
      return format.decorate(duration, time);
   }

   /**
    * Format the given {@link Duration} objects, using the {@link TimeFormat} specified by the {@link TimeUnit}
    * contained within. Rounding rules are ignored for all but the last {@link Duration} element. If the given
    * {@link Duration} {@link List} is <code>null</code> or empty, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param durations the {@link Duration}s to be formatted
    * @return A list of formatted strings representing {@code durations}
    */
   public String format(final List<Duration> durations)
   {
      if (durations == null || durations.isEmpty())
         return format(now());

      StringBuilder result = new StringBuilder();

      Duration duration = null;
      TimeFormat format = null;
      for (int i = 0; i < durations.size(); i++) {
         duration = durations.get(i);
         format = getFormat(duration.getUnit());

         /*
          * Round only the last element 
          */
         if (i < durations.size() - 1)
            result.append(format.formatUnrounded(duration)).append(" ");
         else
            result.append(format.format(duration));
      }

      return format.decorateUnrounded(duration, result.toString());
   }

   /**
    * Format the given {@link Date} object. Rounding rules are ignored. If the given {@link Date} is <code>null</code>,
    * the current value of {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param then the {@link Date} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(Date then)
   {
      if (then == null)
         then = now();

      Duration d = approximateDuration(then);
      return formatUnrounded(d);
   }

   /**
    * Format the given {@link Calendar} object. This method applies the {@link PrettyTime#approximateDuration(Date)}
    * method to perform its calculation. Rounding rules are ignored. If the given {@link Calendar} is <code>null</code>,
    * the current value of {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param then the {@link Calendar} whose date is to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(Calendar then)
   {
      if (then == null)
         return formatUnrounded(now());
      return formatUnrounded(then.getTime());
   }

   /**
    * Format the given {@link Duration} object, using the {@link TimeFormat} specified by the {@link TimeUnit} contained
    * within. Rounding rules are ignored. If the given {@link Duration} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param duration the {@link Duration} to be formatted
    * @return A formatted string representing {@code duration}
    */
   public String formatUnrounded(Duration duration)
   {
      if (duration == null)
         return formatUnrounded(now());

      TimeFormat format = getFormat(duration.getUnit());
      String time = format.formatUnrounded(duration);
      return format.decorateUnrounded(duration, time);
   }

   /**
    * Format the given {@link Duration} objects, using the {@link TimeFormat} specified by the {@link TimeUnit}
    * contained within. Rounding rules are ignored. If the given {@link Duration} {@link List} is <code>null</code> or
    * empty, the current value of {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param durations the {@link Duration}s to be formatted
    * @return A list of formatted strings representing {@code durations}
    */
   public String formatUnrounded(List<Duration> durations)
   {
      if (durations == null || durations.isEmpty())
         return format(now());

      StringBuilder result = new StringBuilder();

      Duration duration = null;
      TimeFormat format = null;
      for (int i = 0; i < durations.size(); i++) {
         duration = durations.get(i);
         format = getFormat(duration.getUnit());

         result.append(format.formatUnrounded(duration));
         if (i < durations.size() - 1)
            result.append(" ");
      }

      return format.decorateUnrounded(duration, result.toString());
   }

   /**
    * Format the given {@link Date} and return a non-relative (not decorated with past or future tense) {@link String}
    * for the approximate duration of its difference between the reference {@link Date}. If the given {@link Date} is
    * <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * 
    * @param then the date to be formatted
    * @return A formatted string of the given {@link Date}
    */
   public String formatDuration(Date then)
   {
      Duration duration = approximateDuration(then);
      return formatDuration(duration);
   }

   /**
    * Format the given {@link Calendar} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Date}. If the given
    * {@link Calendar} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * <p>
    * 
    * @param then the date to be formatted
    * @return A formatted string of the given {@link Date}
    */
   public String formatDuration(Calendar then)
   {
      if (then == null)
         return formatDuration(now());

      Duration duration = approximateDuration(then.getTime());
      return formatDuration(duration);
   }

   /**
    * Format the given {@link Duration} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of the difference between the reference {@link Date} and the given
    * {@link Duration}. If the given {@link Duration} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param duration the duration to be formatted
    * @return A formatted string of the given {@link Duration}
    */
   public String formatDuration(Duration duration)
   {
      if (duration == null)
         return format(now());

      TimeFormat timeFormat = getFormat(duration.getUnit());
      return timeFormat.format(duration);
   }

   /**
    * Format the given {@link Duration} {@link List} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Date}. If the given
    * {@link Duration} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * 
    * @param duration the duration to be formatted
    * @return A formatted string of the given {@link Duration}
    */
   public String formatDuration(final List<Duration> durations)
   {
      if (durations == null || durations.isEmpty())
         return format(now());

      StringBuilder result = new StringBuilder();

      Duration duration = null;
      TimeFormat format = null;
      for (int i = 0; i < durations.size(); i++) {
         duration = durations.get(i);
         format = getFormat(duration.getUnit());

         /*
          * Round only the last element 
          */
         if (i < durations.size() - 1)
            result.append(format.formatUnrounded(duration)).append(" ");
         else
            result.append(format.format(duration));
      }

      return result.toString();
   }

   /**
    * Format the given {@link Date} and return a non-relative (not decorated with past or future tense) {@link String}
    * for the approximate duration of its difference between the reference {@link Date}. Rounding rules are ignored. If
    * the given {@link Date} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * <p>
    * 
    * @param then the date to be formatted
    * @return A formatted string of the given {@link Date}
    */
   public String formatDurationUnrounded(Date then)
   {
      Duration duration = approximateDuration(then);
      return formatDurationUnrounded(duration);
   }

   /**
    * Format the given {@link Calendar} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Date}. Rounding rules
    * are ignored. If the given {@link Calendar} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * 
    * @param then the date to be formatted
    * @return A formatted string of the given {@link Date}
    */
   public String formatDurationUnrounded(Calendar then)
   {
      if (then == null)
         return formatDuration(now());

      Duration duration = approximateDuration(then.getTime());
      return formatDurationUnrounded(duration);
   }

   /**
    * Format the given {@link Duration} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Date}. Rounding rules
    * are ignored. If the given {@link Duration} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param duration the duration to be formatted
    * @return A formatted string of the given {@link Duration}
    */
   public String formatDurationUnrounded(Duration duration)
   {
      if (duration == null)
         return format(now());

      TimeFormat timeFormat = getFormat(duration.getUnit());
      return timeFormat.formatUnrounded(duration);
   }

   /**
    * Format the given {@link Duration} {@link List} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Date}. Rounding rules
    * are ignored. If the given {@link Duration} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param duration the duration to be formatted
    * @return A formatted string of the given {@link Duration}
    */
   public String formatDurationUnrounded(final List<Duration> durations)
   {
      if (durations == null || durations.isEmpty())
         return format(now());

      StringBuilder result = new StringBuilder();
      Duration duration = null;
      TimeFormat format = null;
      for (int i = 0; i < durations.size(); i++) {
         duration = durations.get(i);
         format = getFormat(duration.getUnit());

         result.append(format.formatUnrounded(duration));
         if (i < durations.size() - 1)
            result.append(" ");
      }

      return result.toString();
   }

   /**
    * Get the registered {@link TimeFormat} for the given {@link TimeUnit} or <code>null</code> if none exists.
    */
   public TimeFormat getFormat(TimeUnit unit)
   {
      if (unit != null && units.get(unit) != null)
      {
         return units.get(unit);
      }
      return null;
   }

   /**
    * Get the current reference {@link Date}, or <code>null</code> if no reference {@link Date} is set.
    * <p>
    * See {@code PrettyTime.setReference(Date timestamp)}
    */
   public Date getReference()
   {
      return reference;
   }

   /**
    * Set the reference {@link Date}. If <code>null</code>, {@link PrettyTime} will always use the current value of
    * {@link System#currentTimeMillis()} as the reference {@link Date}.
    * <p>
    * If the {@link Date} formatted is before the reference {@link Date}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Date} formatted is after the reference {@link Date}, the
    * format command will produce a {@link String} that is in the future tense.
    */
   public PrettyTime setReference(final Date timestamp)
   {
      reference = timestamp;
      return this;
   }

   /**
    * Get the unmodifiable {@link List} of the current configured {@link TimeUnit} instances in calculations.
    */
   public List<TimeUnit> getUnits()
   {
      if (cachedUnits == null) {
         List<TimeUnit> result = new ArrayList<TimeUnit>(units.keySet());
         Collections.sort(result, new TimeUnitComparator());
         cachedUnits = Collections.unmodifiableList(result);
      }

      return cachedUnits;
   }

   /**
    * Get the registered {@link TimeUnit} for the given {@link TimeUnit} type or <code>null</code> if none exists.
    */
   @SuppressWarnings("unchecked")
   public <UNIT extends TimeUnit> UNIT getUnit(final Class<UNIT> unitType)
   {
      if (unitType == null)
         return null;

      for (TimeUnit unit : units.keySet()) {
         if (unitType.isAssignableFrom(unit.getClass()))
         {
            return (UNIT) unit;
         }
      }
      return null;
   }

   /**
    * Register the given {@link TimeUnit} and corresponding {@link TimeFormat} instance to be used in calculations. If
    * an entry already exists for the given {@link TimeUnit}, its {@link TimeFormat} will be overwritten with the given
    * {@link TimeFormat}. ({@link TimeUnit} and {@link TimeFormat} must not be <code>null</code>.)
    */
   public PrettyTime registerUnit(final TimeUnit unit, TimeFormat format)
   {
      if (unit == null)
         throw new IllegalArgumentException("Unit to register must not be null.");
      if (format == null)
         throw new IllegalArgumentException("Format to register must not be null.");

      cachedUnits = null;

      units.put(unit, format);
      if (unit instanceof LocaleAware)
         ((LocaleAware<?>) unit).setLocale(locale);
      if (format instanceof LocaleAware)
         ((LocaleAware<?>) format).setLocale(locale);
      return this;
   }

   /**
    * Removes the mapping for the given {@link TimeUnit} type. This effectively de-registers the {@link TimeUnit} so it
    * will not be used in formatting. Returns the {@link TimeFormat} that was removed, or <code>null</code> if no unit
    * of the given type was registered.
    */
   public <UNIT extends TimeUnit> TimeFormat removeUnit(final Class<UNIT> unitType)
   {
      if (unitType == null)
         return null;

      for (TimeUnit unit : units.keySet()) {
         if (unitType.isAssignableFrom(unit.getClass()))
         {
            cachedUnits = null;

            return units.remove(unit);
         }
      }
      return null;
   }

   /**
    * Removes the mapping for the given {@link TimeUnit}. This effectively de-registers the {@link TimeUnit} so it will
    * not be used in formatting. Returns the {@link TimeFormat} that was removed, or null if no such unit was
    * registered.
    */
   public TimeFormat removeUnit(final TimeUnit unit)
   {
      if (unit == null)
         return null;

      cachedUnits = null;

      return units.remove(unit);
   }

   /**
    * Get the currently configured {@link Locale} for this {@link PrettyTime} object.
    */
   public Locale getLocale()
   {
      return locale;
   }

   /**
    * Set the the {@link Locale} for this {@link PrettyTime} object. This may be an expensive operation, since this
    * operation calls {@link TimeUnit#setLocale(Locale)} for each {@link TimeUnit} in {@link #getUnits()}.
    */
   public PrettyTime setLocale(Locale locale)
   {
      if (locale == null)
         locale = Locale.getDefault();

      this.locale = locale;
      for (TimeUnit unit : units.keySet()) {
         if (unit instanceof LocaleAware)
            ((LocaleAware<?>) unit).setLocale(locale);
      }
      for (TimeFormat format : units.values()) {
         if (format instanceof LocaleAware)
            ((LocaleAware<?>) format).setLocale(locale);
      }
      return this;
   }

   @Override
   public String toString()
   {
      return "PrettyTime [reference=" + reference + ", locale=" + locale + "]";
   }

   /**
    * Remove all registered {@link TimeUnit} instances. Returns all {@link TimeUnit} instances that were removed.
    */
   public List<TimeUnit> clearUnits()
   {
      List<TimeUnit> result = getUnits();
      cachedUnits = null;
      units.clear();
      return result;
   }

   /*
    * Internal methods.
    */
   private Date now()
   {
      return new Date();
   }

   private void initTimeUnits()
   {
      addUnit(new JustNow());
      addUnit(new Millisecond());
      addUnit(new Second());
      addUnit(new Minute());
      addUnit(new Hour());
      addUnit(new Day());
      addUnit(new Week());
      addUnit(new Month());
      addUnit(new Year());
      addUnit(new Decade());
      addUnit(new Century());
      addUnit(new Millennium());
   }

   private void addUnit(ResourcesTimeUnit unit)
   {
      registerUnit(unit, new ResourcesTimeFormat(unit, overrideResourceBundle));
   }

   private Duration calculateDuration(final long difference)
   {
      long absoluteDifference = Math.abs(difference);

      /*
       * Required for thread-safety
       */
      List<TimeUnit> localUnits = getUnits();

      DurationImpl result = new DurationImpl();

      for (int i = 0; i < localUnits.size(); i++)
      {
         TimeUnit unit = localUnits.get(i);
         long millisPerUnit = Math.abs(unit.getMillisPerUnit());
         long quantity = Math.abs(unit.getMaxQuantity());

         boolean isLastUnit = (i == localUnits.size() - 1);

         if ((0 == quantity) && !isLastUnit)
         {
            quantity = localUnits.get(i + 1).getMillisPerUnit() / unit.getMillisPerUnit();
         }

         /*
          * Does our unit encompass the time duration?
          */
         if ((millisPerUnit * quantity > absoluteDifference) || isLastUnit)
         {
            result.setUnit(unit);
            if (millisPerUnit > absoluteDifference)
            {
               result.setQuantity(getSign(difference));
               result.setDelta(0);
            }
            else
            {
               result.setQuantity(difference / millisPerUnit);
               result.setDelta(difference - result.getQuantity() * millisPerUnit);
            }
            break;
         }

      }
      return result;
   }

   private long getSign(final long difference)
   {
      if (0 > difference)
      {
         return -1;
      }
      else
      {
         return 1;
      }
   }
}
