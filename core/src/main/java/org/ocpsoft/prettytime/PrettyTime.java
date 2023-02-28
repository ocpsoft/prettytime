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

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

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
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Year;

/**
 * A utility for creating social-networking style timestamps. (e.g. "just now", "moments ago", "3 days ago", "within 2
 * months")
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
   private volatile Instant reference;
   private volatile Locale locale = Locale.getDefault();
   private final Map<TimeUnit, TimeFormat> units = new ConcurrentHashMap<>();
   private volatile List<TimeUnit> cachedUnits;
   private String overrideResourceBundle;

   /**
    * Create a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting.
    */
   public PrettyTime()
   {
      this((String) null);
   }

   /**
    * Create a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting. Will use
    * {@link String} as an optional override to the default resource bundles.
    */
   public PrettyTime(final String overrideResourceBundle)
   {
      this.overrideResourceBundle = overrideResourceBundle;
      this.initTimeUnits();
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link Date} timestamp to represent the reference
    * point for {@link Date} comparison, and will use {@link Locale#getDefault()} as the selected {@link Locale} for
    * language and dialect formatting. If the given {@link Date} is <code>null</code>, this instance will always use the
    * current value of {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison.
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
    *
    * @see #PrettyTime(Date).
    */
   public PrettyTime(final Date reference, final String overrideResourceBundle)
   {
      this(overrideResourceBundle);
      setReference(reference);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link Instant} timestamp to represent the
    * reference point for {@link Instant} comparison, and will use {@link Locale#getDefault()} as the selected
    * {@link Locale} for language and dialect formatting. If the given {@link Instant} is <code>null</code>, this
    * instance will always use the current value of {@link System#currentTimeMillis()} to represent the reference point
    * for {@link Instant} comparison.
    */
   public PrettyTime(final Instant reference)
   {
      this();
      setReference(reference);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link Instant} timestamp to represent the
    * reference point for {@link Instant} comparison, and will use {@link Locale#getDefault()} as the selected
    * {@link Locale} for language and dialect formatting. If the given {@link Instant} is <code>null</code>, this
    * instance will always use the current value of {@link System#currentTimeMillis()} to represent the reference point
    * for {@link Instant} comparison. Will use {@link String} as an optional override to the default resource bundles.
    *
    * @see #PrettyTime(Instant)
    */
   public PrettyTime(final Instant reference, final String overrideResourceBundle)
   {
      this(overrideResourceBundle);
      setReference(reference);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDateTime} and the system default
    * {@link ZoneId} to represent the reference point for {@link Instant} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting. If the given
    * {@link LocalDateTime} is <code>null</code>, this instance will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Instant} comparison.
    */
   public PrettyTime(final LocalDateTime reference)
   {
      this(reference, ZoneId.systemDefault());
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDateTime} and the system default
    * {@link ZoneId} to represent the reference point for {@link Instant} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting. If the given
    * {@link LocalDateTime} is <code>null</code>, this instance will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Instant} comparison. Will use
    * {@link String} as an optional override to the default resource bundles.
    *
    * @see #PrettyTime(Instant)
    */
   public PrettyTime(final LocalDateTime reference, final String overrideResourceBundle)
   {
      this(reference, ZoneId.systemDefault(), overrideResourceBundle);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDateTime} and {@link ZoneId} to
    * represent the reference point for {@link Instant} comparison, and will use {@link Locale#getDefault()} as the
    * selected {@link Locale} for language and dialect formatting. If the given {@link LocalDateTime} is
    * <code>null</code>, this instance will always use the current value of {@link System#currentTimeMillis()} to
    * represent the reference point for {@link Instant} comparison.
    */
   public PrettyTime(final LocalDateTime reference, final ZoneId zoneId)
   {
      this(reference, zoneId, null);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDateTime} and {@link ZoneId} to
    * represent the reference point for {@link Instant} comparison, and will use {@link Locale#getDefault()} as the
    * selected {@link Locale} for language and dialect formatting. If the given {@link LocalDateTime} is
    * <code>null</code>, this instance will always use the current value of {@link System#currentTimeMillis()} to
    * represent the reference point for {@link Instant} comparison. Will use {@link String} as an optional override to
    * the default resource bundles.
    *
    * @see #PrettyTime(Instant)
    */
   public PrettyTime(final LocalDateTime reference, final ZoneId zoneId, final String overrideResourceBundle)
   {
      this(reference != null ? reference.atZone(zoneId).toInstant() : null, overrideResourceBundle);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDate} and the system default
    * {@link ZoneId} to represent the reference point for {@link Instant} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting. If the given
    * {@link LocalDate} is <code>null</code>, this instance will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Instant} comparison.
    */
   public PrettyTime(final LocalDate reference)
   {
      this(reference, ZoneId.systemDefault(), null);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDate} and the system default
    * {@link ZoneId} to represent the reference point for {@link Instant} comparison, and will use
    * {@link Locale#getDefault()} as the selected {@link Locale} for language and dialect formatting. If the given
    * {@link LocalDate} is <code>null</code>, this instance will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Instant} comparison. Will use
    * {@link String} as an optional override to the default resource bundles.
    *
    * @see #PrettyTime(Instant)
    */
   public PrettyTime(final LocalDate reference, final String overrideResourceBundle)
   {
      this(reference, ZoneId.systemDefault(), overrideResourceBundle);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link LocalDate} and {@link ZoneId} to represent
    * the reference point for {@link Instant} comparison, and will use {@link Locale#getDefault()} as the selected
    * {@link Locale} for language and dialect formatting. If the given {@link LocalDate} is <code>null</code>, this
    * instance will always use the current value of {@link System#currentTimeMillis()} to represent the reference point
    * for {@link Instant} comparison.
    */
   public PrettyTime(final LocalDate reference, final ZoneId zoneId)
   {
      this(reference, zoneId, null);
   }

   /**
    * Create a new {@link PrettyTime} instance that will use the given {@link Instant} timestamp to represent the
    * reference point for {@link Instant} comparison, and will use {@link Locale#getDefault()} as the selected
    * {@link Locale} for language and dialect formatting. If the given {@link Instant} is <code>null</code>, this
    * instance will always use the current value of {@link System#currentTimeMillis()} to represent the reference point
    * for {@link Instant} comparison. Will use {@link String} as an optional override to the default resource bundles.
    *
    * @see #PrettyTime(Instant)
    */
   public PrettyTime(final LocalDate reference, final ZoneId zoneId, final String overrideResourceBundle)
   {
      this(reference != null ? reference.atStartOfDay(zoneId).toInstant() : null, overrideResourceBundle);
   }

   /**
    * Construct a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison. This instance
    * will use the given {@link Locale} instead of the system default. If the provided {@link Locale} is
    * <code>null</code>, {@link Locale#getDefault()} will be used.
    */
   public PrettyTime(final Locale locale)
   {
      this();
      setLocale(locale);
   }

   /**
    * Construct a new {@link PrettyTime} instance that will always use the current value of
    * {@link System#currentTimeMillis()} to represent the reference point for {@link Date} comparison. This instance
    * will use the given {@link Locale} instead of the system default. If the provided {@link Locale} is
    * <code>null</code>, {@link Locale#getDefault()} will be used. Will use {@link String} as an optional override to
    * the default resource bundles.
    */
   public PrettyTime(final Locale locale, String overrideResourceBundle)
   {
      this(overrideResourceBundle);
      setLocale(locale);
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
    * {@link System#currentTimeMillis()} will be used to represent the reference point for {@link Date} comparison. Will
    * use {@link String} as an optional override to the default resource bundles.
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
    *
    * @see #getReference()
    */
   public Duration approximateDuration(Date then)
   {
      if (then == null)
         then = now();

      final Instant ref = reference != null ? reference : Instant.now();
      long difference = then.getTime() - ref.toEpochMilli();
      if (difference == 0) {
         difference = 1;
      }
      return calculateDuration(difference);
   }

   /**
    * Calculate the approximate {@link Duration} between the reference {@link Instant} and given {@link Instant}. If the
    * given {@link Instant} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    *
    * @see #getReference()
    */
   public Duration approximateDuration(Instant then)
   {
      return approximateDuration(then != null ? Date.from(then) : null);
   }

   /**
    * Calculate the approximate {@link Duration} between the reference {@link Instant} and given {@link LocalDateTime}.
    * If the given {@link LocalDateTime} is <code>null</code>, the current value of {@link System#currentTimeMillis()}
    * will be used instead.
    *
    * @see #getReference()
    */
   public Duration approximateDuration(LocalDate then)
   {
      return approximateDuration(then != null ? then.atStartOfDay(ZoneId.systemDefault()).toInstant() : null);
   }

   /**
    * Calculate the approximate {@link Duration} between the reference {@link Instant} and given {@link LocalDate}. If
    * the given {@link LocalDate} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be
    * used instead.
    *
    * @param then The {@link LocalDate} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @param zoneId The {@link ZoneId} to be used, not null
    * @see #getReference()
    */
   public Duration approximateDuration(LocalDate then, final ZoneId zoneId)
   {
      return approximateDuration(then != null ? then.atStartOfDay(ZoneId.systemDefault()).toInstant() : null);
   }

   /**
    * Calculate the approximate {@link Duration} between the reference {@link Instant} and given {@link LocalDateTime}.
    * If the given {@link LocalDateTime} is <code>null</code>, the current value of {@link System#currentTimeMillis()}
    * will be used instead.
    *
    * @param then The {@link LocalDate} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @see #getReference()
    */
   public Duration approximateDuration(LocalDateTime then)
   {
      return approximateDuration(then != null ? then.atZone(ZoneId.systemDefault()).toInstant() : null);
   }

   /**
    * Calculate the approximate {@link Duration} between the reference {@link Instant} and given {@link LocalDateTime}.
    * If the given {@link LocalDateTime} is <code>null</code>, the current value of {@link System#currentTimeMillis()}
    * will be used instead.
    *
    * @param then The {@link LocalDate} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @param zoneId The {@link ZoneId} to be used, not null
    * @see #getReference()
    */
   public Duration approximateDuration(LocalDateTime then, final ZoneId zoneId)
   {
      return approximateDuration(then != null ? then.atZone(zoneId).toInstant() : null);
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Instant} and the given {@link Date}. If the given {@link Date} is
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
   public List<Duration> calculatePreciseDuration(final Date then)
   {
      return calculatePreciseDuration(then != null ? then.toInstant() : null);
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Instant} and the given {@link Instant}. If the given {@link Instant}
    * is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is granular enough to represent the remainder
    * of time (in milliseconds).
    *
    * @param then The {@link Instant} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @return A sorted {@link List} of {@link Duration} objects, from largest to smallest. Each element in the list
    *         represents the approximate duration (number of times) that {@link TimeUnit} to fit into the previous
    *         element's delta. The first element is the largest {@link TimeUnit} to fit within the total difference
    *         between compared dates.
    */
   public List<Duration> calculatePreciseDuration(Instant then)
   {
      if (then == null)
         then = Instant.now();

      final Instant reference = this.reference != null ? this.reference : Instant.now();

      List<Duration> result = new ArrayList<>();
      long difference = then.toEpochMilli() - reference.toEpochMilli();
      Duration duration = calculateDuration(difference);
      result.add(duration);
      while (0 != duration.getDelta()) {
         duration = calculateDuration(duration.getDelta());
         if (!result.isEmpty()) {
            Duration last = result.get(result.size() - 1);
            if (last.getUnit().equals(duration.getUnit())) {
               break;
            }
         }

         if (duration.getUnit().isPrecise())
            result.add(duration);
      }
      return result;
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Instant} and the given {@link LocalDateTime} using the system default
    * {@link ZoneId}. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is granular enough to represent the remainder
    * of time (in milliseconds).
    *
    * @param then The {@link LocalDateTime} to be compared against the reference timestamp, or <i>now</i> if no
    *           reference timestamp was provided
    * @return A sorted {@link List} of {@link Duration} objects, from largest to smallest. Each element in the list
    *         represents the approximate duration (number of times) that {@link TimeUnit} to fit into the previous
    *         element's delta. The first element is the largest {@link TimeUnit} to fit within the total difference
    *         between compared dates.
    */
   public List<Duration> calculatePreciseDuration(final LocalDateTime then)
   {
      return calculatePreciseDuration(then, ZoneId.systemDefault());
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Instant} and the given {@link LocalDateTime} using the given
    * {@link ZoneId}. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is granular enough to represent the remainder
    * of time (in milliseconds).
    *
    * @param then The {@link LocalDateTime} to be compared against the reference timestamp, or <i>now</i> if no
    *           reference timestamp was provided
    * @param zoneId The {@link ZoneId} to be used, not null
    * @return A sorted {@link List} of {@link Duration} objects, from largest to smallest. Each element in the list
    *         represents the approximate duration (number of times) that {@link TimeUnit} to fit into the previous
    *         element's delta. The first element is the largest {@link TimeUnit} to fit within the total difference
    *         between compared dates.
    */
   public List<Duration> calculatePreciseDuration(final LocalDateTime then, final ZoneId zoneId)
   {
      return calculatePreciseDuration(then != null ? then.atZone(zoneId).toInstant() : null);
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Instant} and the given {@link LocalDate} using the system default
    * {@link ZoneId}. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is granular enough to represent the remainder
    * of time (in milliseconds).
    *
    * @param then The {@link LocalDate} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @return A sorted {@link List} of {@link Duration} objects, from largest to smallest. Each element in the list
    *         represents the approximate duration (number of times) that {@link TimeUnit} to fit into the previous
    *         element's delta. The first element is the largest {@link TimeUnit} to fit within the total difference
    *         between compared dates.
    */
   public List<Duration> calculatePreciseDuration(final LocalDate then)
   {
      return calculatePreciseDuration(then != null ? then.atStartOfDay() : null);
   }

   /**
    * Calculate to the precision of the smallest provided {@link TimeUnit}, the exact {@link Duration} represented by
    * the difference between the reference {@link Instant} and the given {@link LocalDate} using the given
    * {@link ZoneId}. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is granular enough to represent the remainder
    * of time (in milliseconds).
    *
    * @param then The {@link LocalDate} to be compared against the reference timestamp, or <i>now</i> if no reference
    *           timestamp was provided
    * @param zoneId The {@link ZoneId} to be used, not null
    * @return A sorted {@link List} of {@link Duration} objects, from largest to smallest. Each element in the list
    *         represents the approximate duration (number of times) that {@link TimeUnit} to fit into the previous
    *         element's delta. The first element is the largest {@link TimeUnit} to fit within the total difference
    *         between compared dates.
    */
   public List<Duration> calculatePreciseDuration(final LocalDate then, final ZoneId zoneId)
   {
      return calculatePreciseDuration(then != null ? then.atStartOfDay(zoneId).toInstant() : null);
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
         // check if format is null, if so, throw an exception
         if (format == null)
            throw new IllegalArgumentException("Unsupported time unit: " + duration.getUnit());

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
    * Format the given {@link Instant} object. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link Instant} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(final Instant then)
   {
      return format(approximateDuration(then));
   }

   /**
    * Format the given {@link ZonedDateTime} object. If the given {@link ZonedDateTime} is <code>null</code>, the
    * current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link ZonedDateTime} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(final ZonedDateTime then)
   {
      return format(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link OffsetDateTime} object. If the given {@link OffsetDateTime} is <code>null</code>, the
    * current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link OffsetDateTime} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(final OffsetDateTime then)
   {
      return format(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link LocalDateTime} object using the given {@link ZoneId}. If the given {@link LocalDateTime}
    * is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @param zoneId the {@link ZoneId} to be used, not null
    * @return A formatted string representing {@code then}
    */
   public String format(final LocalDateTime then, final ZoneId zoneId)
   {
      return format(then != null ? then.atZone(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDateTime} object using the system default {@link ZoneId}. If the given
    * {@link LocalDateTime} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(final LocalDateTime then)
   {
      return format(then, ZoneId.systemDefault());
   }

   /**
    * Format the given {@link LocalDate} object using the given {@link ZoneId}. If the given {@link LocalDate} is
    * <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @param zoneId the {@link ZoneId} to be used, not null
    * @return A formatted string representing {@code then}
    */
   public String format(final LocalDate then, final ZoneId zoneId)
   {
      return format(then != null ? then.atStartOfDay(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDate} object using the system default {@link ZoneId}. If the given {@link LocalDate}
    * is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String format(final LocalDate then)
   {
      return format(then != null ? then.atStartOfDay() : null);
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
    * Format the given {@link Instant} object. Rounding rules are ignored. If the given {@link Instant} is
    * <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link Instant} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final Instant then)
   {
      return formatUnrounded(approximateDuration(then));
   }

   /**
    * Format the given {@link ZonedDateTime} object. Rounding rules are ignored. If the given {@link ZonedDateTime} is
    * <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link ZonedDateTime} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final ZonedDateTime then)
   {
      return formatUnrounded(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link OffsetDateTime} object. Rounding rules are ignored. If the given {@link OffsetDateTime} is
    * <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * @param then the {@link OffsetDateTime} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final OffsetDateTime then)
   {
      return formatUnrounded(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link LocalDateTime} object using the given {@link ZoneId}. Rounding rules are ignored. If the
    * given {@link LocalDateTime} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be
    * used instead.
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @param zoneId the {@link ZoneId} to be used, not null
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final LocalDateTime then, final ZoneId zoneId)
   {
      return formatUnrounded(then != null ? then.atZone(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDateTime} object using the system default {@link ZoneId}. Rounding rules are ignored.
    * If the given {@link LocalDateTime} is <code>null</code>, the current value of {@link System#currentTimeMillis()}
    * will be used instead.
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final LocalDateTime then)
   {
      return formatUnrounded(then, ZoneId.systemDefault());
   }

   /**
    * Format the given {@link LocalDate} object using the given {@link ZoneId}. Rounding rules are ignored. If the given
    * {@link LocalDate} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @param zoneId the {@link ZoneId} to be used, not null
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final LocalDate then, final ZoneId zoneId)
   {
      return formatUnrounded(then != null ? then.atStartOfDay(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDate} object using the system default {@link ZoneId}. Rounding rules are ignored. If
    * the given {@link LocalDate} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be
    * used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @return A formatted string representing {@code then}
    */
   public String formatUnrounded(final LocalDate then)
   {
      return formatUnrounded(then != null ? then.atStartOfDay() : null);
   }

   /**
    * Format the given {@link Date} and return a non-relative (not decorated with past or future tense) {@link String}
    * for the approximate duration of its difference between the reference {@link Instant}. If the given {@link Date} is
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
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. If the given
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
    * {@link String} for the approximate duration of the difference between the reference {@link Instant} and the given
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
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. If the given
    * {@link Duration} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * 
    * @param durations the durations to be formatted
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
    * Format the given {@link Instant} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. If the given
    * {@link Instant} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * <p>
    *
    * @param then the {@link Instant} to be formatted
    * @return A formatted string of the given {@link Instant}
    */
   public String formatDuration(final Instant then)
   {
      return formatDuration(approximateDuration(then));
   }

   /**
    * Format the given {@link Instant} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. If the given
    * {@link Instant} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * <p>
    *
    * @param then the {@link ZonedDateTime} to be formatted
    * @return A formatted string of the given {@link Instant}
    */
   public String formatDuration(final ZonedDateTime then)
   {
      return formatDuration(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link Instant} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. If the given
    * {@link Instant} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be used
    * instead.
    * <p>
    *
    * @param then the {@link OffsetDateTime} to be formatted
    * @return A formatted string of the given {@link Instant}
    */
   public String formatDuration(final OffsetDateTime then)
   {
      return formatDuration(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link LocalDateTime} using the given {@link ZoneId} and return a non-relative (not decorated
    * with past or future tense) {@link String} for the approximate duration of its difference between the reference
    * {@link Date}. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @param zoneId the {@link ZoneId} to be used, not null
    * @return A formatted string of the given {@link LocalDateTime}
    */
   public String formatDuration(final LocalDateTime then, final ZoneId zoneId)
   {
      return formatDuration(then != null ? then.atZone(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDateTime} using the system default {@link ZoneId} and return a non-relative (not
    * decorated with past or future tense) {@link String} for the approximate duration of its difference between the
    * reference {@link Instant}. If the given {@link LocalDateTime} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @return A formatted string of the given {@link LocalDateTime}
    */
   public String formatDuration(final LocalDateTime then)
   {
      return formatDuration(then, ZoneId.systemDefault());
   }

   /**
    * Format the given {@link LocalDate} using the given {@link ZoneId} and return a non-relative (not decorated with
    * past or future tense) {@link String} for the approximate duration of its difference between the reference
    * {@link Date}. If the given {@link LocalDate} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @param zoneId the {@link ZoneId} to be used, not null
    * @return A formatted string of the given {@link LocalDate}
    */
   public String formatDuration(final LocalDate then, final ZoneId zoneId)
   {
      return formatDuration(then != null ? then.atStartOfDay(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDate} using the system default {@link ZoneId} and return a non-relative (not
    * decorated with past or future tense) {@link String} for the approximate duration of its difference between the
    * reference {@link Instant}. If the given {@link LocalDate} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @return A formatted string of the given {@link LocalDate}
    */
   public String formatDuration(final LocalDate then)
   {
      return formatDuration(then != null ? then.atStartOfDay() : null);
   }

   /**
    * Format the given {@link Date} and return a non-relative (not decorated with past or future tense) {@link String}
    * for the approximate duration of its difference between the reference {@link Instant}. Rounding rules are ignored.
    * If the given {@link Date} is <code>null</code>, the current value of {@link System#currentTimeMillis()} will be
    * used instead.
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
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. Rounding
    * rules are ignored. If the given {@link Calendar} is <code>null</code>, the current value of
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
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. Rounding
    * rules are ignored. If the given {@link Duration} is <code>null</code>, the current value of
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
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. Rounding
    * rules are ignored. If the given {@link Duration} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * 
    * @param durations the durations to be formatted
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
    * Format the given {@link Instant} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. Rounding
    * rules are ignored. If the given {@link Instant} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link Instant} to be formatted
    * @return A formatted string of the given {@link Instant}
    */
   public String formatDurationUnrounded(final Instant then)
   {
      return formatDurationUnrounded(approximateDuration(then));
   }

   /**
    * Format the given {@link ZonedDateTime} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. Rounding
    * rules are ignored. If the given {@link Date} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link ZonedDateTime} to be formatted
    * @return A formatted string of the given {@link ZonedDateTime}
    */
   public String formatDurationUnrounded(final ZonedDateTime then)
   {
      return formatDurationUnrounded(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link OffsetDateTime} and return a non-relative (not decorated with past or future tense)
    * {@link String} for the approximate duration of its difference between the reference {@link Instant}. Rounding
    * rules are ignored. If the given {@link OffsetDateTime} is <code>null</code>, the current value of
    * {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link OffsetDateTime} to be formatted
    * @return A formatted string of the given {@link OffsetDateTime}
    */
   public String formatDurationUnrounded(final OffsetDateTime then)
   {
      return formatDurationUnrounded(then != null ? then.toInstant() : null);
   }

   /**
    * Format the given {@link LocalDateTime} using the given {@link ZoneId} and return a non-relative (not decorated
    * with past or future tense) {@link String} for the approximate duration of its difference between the reference
    * {@link Date}. Rounding rules are ignored. If the given {@link LocalDateTime} is <code>null</code>, the current
    * value of {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @return A formatted string of the given {@link LocalDateTime}
    */
   public String formatDurationUnrounded(final LocalDateTime then, final ZoneId zoneId)
   {
      return formatDurationUnrounded(then != null ? then.atZone(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDateTime} using the system default {@link ZoneId} and return a non-relative (not
    * decorated with past or future tense) {@link String} for the approximate duration of its difference between the
    * reference {@link Instant}. Rounding rules are ignored. If the given {@link LocalDateTime} is <code>null</code>,
    * the current value of {@link System#currentTimeMillis()} will be used instead.
    * <p>
    *
    * @param then the {@link LocalDateTime} to be formatted
    * @return A formatted string of the given {@link LocalDateTime}
    */
   public String formatDurationUnrounded(final LocalDateTime then)
   {
      return formatDurationUnrounded(then, ZoneId.systemDefault());
   }

   /**
    * Format the given {@link LocalDate} using the given {@link ZoneId} and return a non-relative (not decorated with
    * past or future tense) {@link String} for the approximate duration of its difference between the reference
    * {@link Date}. Rounding rules are ignored. If the given {@link LocalDate} is <code>null</code>, the current value
    * of {@link System#currentTimeMillis()} will be used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @return A formatted string of the given {@link LocalDate}
    */
   public String formatDurationUnrounded(final LocalDate then, final ZoneId zoneId)
   {
      return formatDurationUnrounded(then != null ? then.atStartOfDay(zoneId) : null);
   }

   /**
    * Format the given {@link LocalDate} using the system default {@link ZoneId} and return a non-relative (not
    * decorated with past or future tense) {@link String} for the approximate duration of its difference between the
    * reference {@link Date}. Rounding rules are ignored. If the given {@link LocalDate} is <code>null</code>, the
    * current value of {@link System#currentTimeMillis()} will be used instead.
    *
    * <p>
    * This assumes that the time of the given date is midnight.
    * </p>
    *
    * @param then the {@link LocalDate} to be formatted
    * @return A formatted string of the given {@link LocalDate}
    */
   public String formatDurationUnrounded(final LocalDate then)
   {
      return formatDurationUnrounded(then != null ? then.atStartOfDay() : null);
   }

   /**
    * Get the registered {@link TimeFormat} for the given {@link TimeUnit} or <code>null</code> if none exists.
    */
   public TimeFormat getFormat(TimeUnit unit)
   {
      if (unit == null) return null;
      if (units.get(unit) != null) {
         return units.get(unit);
      } else {
         // Trying to transform the TimeUnit to String does the trick
         Map<String, TimeFormat> map = new ConcurrentHashMap<>();
         units.keySet().forEach(key -> map.put(key.toString(), units.get(key)));
         return map.get(unit.toString());
      }
   }

   /**
    * Get the current reference {@link Instant}, or <code>null</code> if no reference {@link Instant} is set.
    *
    * See {@code PrettyTime.setReference(Date timestamp)}
    * 
    * @see #setReference(Instant)
    */
   public Instant getReference()
   {
      return reference;
   }

   /**
    * Get the current reference {@link Instant} as a {@link Date}, or <code>null</code> if no reference {@link Instant}
    * is set.
    *
    * @see #getReference()
    * @see #setReference(Date)
    */
   public Date getReferenceAsLegacyDate()
   {
      return reference != null ? Date.from(reference) : null;
   }

   /**
    * Converts the given {@link Date} to the reference {@link Instant}. If <code>null</code>, {@link PrettyTime} will
    * always use the current value of {@link System#currentTimeMillis()} as the reference {@link Instant}.
    * <p>
    * If the {@link Date} formatted is before the reference {@link Instant}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Instant} formatted is after the reference {@link Instant},
    * the format command will produce a {@link String} that is in the future tense.
    *
    * @see #setReference(Instant)
    */
   public PrettyTime setReference(final Date timestamp)
   {
      return setReference(timestamp != null ? timestamp.toInstant() : null);
   }

   /**
    * Set the reference {@link Instant}. If <code>null</code>, {@link PrettyTime} will always use the current value of
    * {@link System#currentTimeMillis()} as the reference {@link Instant}.
    * <p>
    * If the {@link Instant} formatted is before the reference {@link Instant}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Instant} formatted is after the reference {@link Instant},
    * the format command will produce a {@link String} that is in the future tense.
    */
   public PrettyTime setReference(final Instant timestamp)
   {
      reference = timestamp;
      return this;
   }

   /**
    * Converts the given {@link LocalDateTime} to the reference {@link Instant} using the system default {@link ZoneId}.
    * If <code>null</code>, {@link PrettyTime} will always use the current value of {@link System#currentTimeMillis()}
    * as the reference {@link Instant}.
    * <p>
    * If the {@link Instant} formatted is before the reference {@link Instant}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Instant} formatted is after the reference {@link Instant},
    * the format command will produce a {@link String} that is in the future tense.
    *
    * @see #setReference(Instant)
    */
   public PrettyTime setReference(final LocalDateTime localDateTime)
   {
      return setReference(localDateTime, ZoneId.systemDefault());
   }

   /**
    * Converts the given {@link LocalDateTime} to the reference {@link Instant} using the given {@link ZoneId}. If
    * <code>null</code>, {@link PrettyTime} will always use the current value of {@link System#currentTimeMillis()} as
    * the reference {@link Instant}.
    * <p>
    * If the {@link Instant} formatted is before the reference {@link Instant}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Instant} formatted is after the reference {@link Instant},
    * the format command will produce a {@link String} that is in the future tense.
    *
    * @see #setReference(Instant)
    */
   public PrettyTime setReference(final LocalDateTime localDateTime, final ZoneId zoneId)
   {
      return setReference(localDateTime != null ? localDateTime.atZone(zoneId).toInstant() : null);
   }

   /**
    * Converts the given {@link LocalDate} to the reference {@link Instant} using the given {@link ZoneId}. If
    * <code>null</code>, {@link PrettyTime} will always use the current value of {@link System#currentTimeMillis()} as
    * the reference {@link Instant}.
    * <p>
    * If the {@link Instant} formatted is before the reference {@link Instant}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Instant} formatted is after the reference {@link Instant},
    * the format command will produce a {@link String} that is in the future tense.
    *
    * @see #setReference(Instant)
    */
   public PrettyTime setReference(final LocalDate localDate)
   {
      return setReference(localDate != null ? localDate.atStartOfDay() : null);
   }

   /**
    * Converts the given {@link LocalDate} to the reference {@link Instant} using the given {@link ZoneId}. If
    * <code>null</code>, {@link PrettyTime} will always use the current value of {@link System#currentTimeMillis()} as
    * the reference {@link Instant}.
    * <p>
    * If the {@link Instant} formatted is before the reference {@link Instant}, the format command will produce a
    * {@link String} that is in the past tense. If the {@link Instant} formatted is after the reference {@link Instant},
    * the format command will produce a {@link String} that is in the future tense.
    *
    * @see #setReference(Instant)
    */
   public PrettyTime setReference(final LocalDate localDate, final ZoneId zoneId)
   {
      return setReference(localDate != null ? localDate.atStartOfDay(zoneId).toInstant() : null);
   }

   /**
    * Get the unmodifiable {@link List} of the current configured {@link TimeUnit} instances in calculations.
    */
   public List<TimeUnit> getUnits()
   {
      if (cachedUnits == null) {
         List<TimeUnit> result = new ArrayList<>(units.keySet());
         Collections.sort(result, Comparator.comparing(TimeUnit::getMillisPerUnit));
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
         if (unitType.isAssignableFrom(unit.getClass())) {
            return (UNIT) unit;
         }
      }
      return null;
   }

   /**
    * Get the registered {@link TimeUnit} for the given {@link ChronoUnit} type or {@code null}
    * if none exists.
    *
    * @param unit The {@code ChronoUnit}
    * @return the time unit
    * @throws IllegalArgumentException if no corresponding {@code TimeUnit} was found for the given
    * {@code ChronoUnit}
    */
   public TimeUnit getUnit(final ChronoUnit unit)
   {
      return getUnit(TimeUnit.of(unit).getClass());
   }

   /**
    * Register the given {@link TimeUnit} and corresponding {@link TimeFormat} instance to be used in calculations. If
    * an entry already exists for the given {@link TimeUnit}, its {@link TimeFormat} will be overwritten with the given
    * {@link TimeFormat}. ({@link TimeUnit} and {@link TimeFormat} must not be <code>null</code>.)
    */
   public PrettyTime registerUnit(final TimeUnit unit, TimeFormat format)
   {
      cachedUnits = null;

      units.put(Objects.requireNonNull(unit, "TimeUnit to register must not be null."),
               Objects.requireNonNull(format, "TimeFormat to register must not be null."));
      if (unit instanceof LocaleAware)
         ((LocaleAware<?>) unit).setLocale(locale);
      if (format instanceof LocaleAware)
         ((LocaleAware<?>) format).setLocale(locale);
      return this;
   }

   /**
    * Register the given {@link ChronoUnit} and corresponding {@link TimeFormat}.
    * @param unit The {@code ChronoUnit} to be registered
    * @param format The {@code TimeFormat} to be registered
    * @return the current {@code PrettyTime} object
    * @throws IllegalArgumentException if no corresponding {@code TimeUnit} was found for the given
    * {@code ChronoUnit}
    */
   public PrettyTime registerUnit(final ChronoUnit unit, final TimeFormat format)
   {
      return registerUnit(TimeUnit.of(unit), format);
   }

   public PrettyTime setUnits(final ResourcesTimeUnit... units)
   {
      if (units == null || units.length == 0)
         throw new IllegalArgumentException("TimeUnit instance(s) to register must be provided.");

      this.clearUnits();
      for (ResourcesTimeUnit unit : units) {
         TimeFormat format = new ResourcesTimeFormat(unit);
         this.registerUnit(unit, format);
      }

      return this;
   }

   public PrettyTime setUnits(TimeFormat format, final TimeUnit... units)
   {
      if (units == null || units.length == 0)
         throw new IllegalArgumentException("TimeUnit instance(s) to register must be provided.");
      Objects.requireNonNull(format, "TimeFormat to register must not be null.");

      this.clearUnits();
      for (TimeUnit unit : units) {
         this.registerUnit(unit, format);
      }

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
         if (unitType.isAssignableFrom(unit.getClass())) {
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
    * Removes the mapping corresponding to the given {@link ChronoUnit}, returning the
    * {@link TimeFormat} if available.
    *
    * @param unit The {@code ChronoUnit} to be removed
    * @return the corresponding {@code TimeFormat}
    * @throws IllegalArgumentException if no {@link TimeUnit} corresponding to the given
    * {@code ChronoUnit} was found.
    */
   public TimeFormat removeUnit(final ChronoUnit unit)
   {
      return removeUnit(TimeUnit.of(unit));
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
    * operation calls {@link LocaleAware#setLocale(Locale)} for each {@link TimeUnit} in {@link #getUnits()}.
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
      cachedUnits = null;
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

      for (int i = 0; i < localUnits.size(); i++) {
         TimeUnit unit = localUnits.get(i);
         long millisPerUnit = Math.abs(unit.getMillisPerUnit());
         long quantity = Math.abs(unit.getMaxQuantity());

         boolean isLastUnit = (i == localUnits.size() - 1);

         if ((0 == quantity) && !isLastUnit) {
            quantity = localUnits.get(i + 1).getMillisPerUnit() / unit.getMillisPerUnit();
         }

         /*
          * Does our unit encompass the time duration?
          */
         if ((millisPerUnit * quantity > absoluteDifference) || isLastUnit) {
            result.setUnit(unit);
            if (millisPerUnit > absoluteDifference) {
               result.setQuantity(getSign(difference));
               result.setDelta(0);
            }
            else {
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
      if (0 > difference) {
         return -1;
      }
      else {
         return 1;
      }
   }
}
