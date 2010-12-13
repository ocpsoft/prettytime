/*
 * PrettyTime is an OpenSource Java time comparison library for creating human
 * readable time.
 * 
 * Copyright (C) 2009 - Lincoln Baxter, III <lincoln@ocpsoft.com>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see the file COPYING.LESSER3 or visit the
 * GNU website at <http://www.gnu.org/licenses/>.
 */
package com.ocpsoft.pretty.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ocpsoft.pretty.time.units.Century;
import com.ocpsoft.pretty.time.units.Day;
import com.ocpsoft.pretty.time.units.Decade;
import com.ocpsoft.pretty.time.units.Hour;
import com.ocpsoft.pretty.time.units.JustNow;
import com.ocpsoft.pretty.time.units.Millennium;
import com.ocpsoft.pretty.time.units.Millisecond;
import com.ocpsoft.pretty.time.units.Minute;
import com.ocpsoft.pretty.time.units.Month;
import com.ocpsoft.pretty.time.units.Second;
import com.ocpsoft.pretty.time.units.Week;
import com.ocpsoft.pretty.time.units.Year;

/**
 * A utility for creating social-networking style timestamps. (e.g. "just now",
 * "moments ago", "3 days ago", "within 2 months")
 * <p>
 * <b>Usage:</b>
 * <p>
 * <code>
 * PrettyTime t = new PrettyTime();<br/>
 * String timestamp = t.format(new Date());<br/>
 * //result: moments from now
 * <p>
 * </code>
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
 */
public class PrettyTime
{

    private volatile Date reference;
    private volatile List<TimeUnit> timeUnits;
    private volatile Locale locale = Locale.getDefault();

    /**
     * Default constructor
     */
    public PrettyTime()
    {
        initTimeUnits();
    }

    /**
     * Constructor accepting a Date timestamp to represent the point of
     * reference for comparison. This may be changed by the user, after
     * construction.
     * <p>
     * See {@code PrettyTime.setReference(Date timestamp)}.
     * 
     * @param reference
     */
    public PrettyTime(final Date reference)
    {
        this();
        setReference(reference);
    }

    public PrettyTime(final Locale locale)
    {
        this.setLocale(locale);
        initTimeUnits();
    }

    public PrettyTime(final Date reference, final Locale locale)
    {
        this(locale);
        setReference(reference);
    }

    /**
     * Calculate the approximate duration between the referenceDate and date
     * 
     * @param date
     * @return
     */
    public Duration approximateDuration(final Date then)
    {
        Date ref = reference;
        if (null == ref)
        {
            ref = new Date();
        }

        long difference = then.getTime() - ref.getTime();
        return calculateDuration(difference);
    }

    private void initTimeUnits()
    {
        timeUnits = new ArrayList<TimeUnit>();
        timeUnits.add(new JustNow(locale));
        timeUnits.add(new Millisecond(locale));
        timeUnits.add(new Second(locale));
        timeUnits.add(new Minute(locale));
        timeUnits.add(new Hour(locale));
        timeUnits.add(new Day(locale));
        timeUnits.add(new Week(locale));
        timeUnits.add(new Month(locale));
        timeUnits.add(new Year(locale));
        timeUnits.add(new Decade(locale));
        timeUnits.add(new Century(locale));
        timeUnits.add(new Millennium(locale));
    }

    private Duration calculateDuration(final long difference)
    {
        long absoluteDifference = Math.abs(difference);

        // Required for thread-safety
        List<TimeUnit> units = new ArrayList<TimeUnit>(timeUnits.size());
        units.addAll(timeUnits);

        Duration result = new Duration();

        for (int i = 0; i < units.size(); i++)
        {
            TimeUnit unit = units.get(i);
            long millisPerUnit = Math.abs(unit.getMillisPerUnit());
            long quantity = Math.abs(unit.getMaxQuantity());

            boolean isLastUnit = (i == units.size() - 1);

            if ((0 == quantity) && !isLastUnit)
            {
                quantity = units.get(i + 1).getMillisPerUnit() / unit.getMillisPerUnit();
            }

            // does our unit encompass the time duration?
            if ((millisPerUnit * quantity > absoluteDifference) || isLastUnit)
            {
                result.setUnit(unit);
                if (millisPerUnit > absoluteDifference)
                {
                    // we are rounding up: get 1 or -1 for past or future
                    result.setQuantity(getSign(difference, absoluteDifference));
                }
                else
                {
                    result.setQuantity(difference / millisPerUnit);
                }
                result.setDelta(difference - result.getQuantity() * millisPerUnit);
                break;
            }

        }
        return result;
    }

    private long getSign(final long difference, final long absoluteDifference)
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

    /**
     * Calculate to the precision of the smallest provided {@link TimeUnit}, the
     * exact duration represented by the difference between the reference
     * timestamp, and {@code then}
     * <p>
     * <b>Note</b>: Precision may be lost if no supplied {@link TimeUnit} is
     * granular enough to represent one millisecond
     * 
     * @param then
     *            The date to be compared against the reference timestamp, or
     *            <i>now</i> if no reference timestamp was provided
     * @return A sorted {@link List} of {@link Duration} objects, from largest
     *         to smallest. Each element in the list represents the approximate
     *         duration (number of times) that {@link TimeUnit} to fit into the
     *         previous element's delta. The first element is the largest
     *         {@link TimeUnit} to fit within the total difference between
     *         compared dates.
     */
    public List<Duration> calculatePreciseDuration(final Date then)
    {
        if (null == reference)
        {
            reference = new Date();
        }

        List<Duration> result = new ArrayList<Duration>();
        long difference = then.getTime() - reference.getTime();
        Duration duration = calculateDuration(difference);
        result.add(duration);
        while (0 < duration.getDelta())
        {
            duration = calculateDuration(duration.getDelta());
            result.add(duration);
        }
        return result;
    }

    /**
     * Format the given {@link Duration} object, using the {@link TimeFormat}
     * specified by the {@link TimeUnit} contained within
     * 
     * @param duration
     *            the {@link Duration} to be formatted
     * @return A formatted string representing {@code duration}
     */
    public String format(final Duration duration)
    {
        TimeFormat format = duration.getUnit().getFormat();
        return format.format(duration);
    }

    /**
     * Format the given {@link Date} object. This method applies the {@code
     * PrettyTime.approximateDuration(date)} method to perform its calculation.
     * If {@code then} is null, it will default to {@code new Date()}
     * 
     * @param duration
     *            the {@link Date} to be formatted
     * @return A formatted string representing {@code then}
     */
    public String format(Date then)
    {
        if (then == null)
        {
            then = new Date();
        }
        Duration d = approximateDuration(then);
        return format(d);
    }

    /**
     * Get the current reference timestamp.
     * <p>
     * See {@code PrettyTime.setReference(Date timestamp)}
     * 
     * @return
     */
    public Date getReference()
    {
        return reference;
    }

    /**
     * Set the reference timestamp.
     * <p>
     * If the Date formatted is before the reference timestamp, the format
     * command will produce a String that is in the past tense. If the Date
     * formatted is after the reference timestamp, the format command will
     * produce a string that is in the future tense.
     * 
     * @param timestamp
     */
    public void setReference(final Date timestamp)
    {
        reference = timestamp;
    }

    /**
     * Get a {@link List} of the current configured {@link TimeUnit}s in this
     * instance.
     * 
     * @return
     */
    public List<TimeUnit> getUnits()
    {
        return Collections.unmodifiableList(timeUnits);
    }

    /**
     * Set the current configured {@link TimeUnit}s to be used in calculations
     * 
     * @return
     */
    public void setUnits(final List<TimeUnit> units)
    {
        this.timeUnits = units;
    }

    public void setUnits(final TimeUnit... units)
    {
        this.timeUnits = Arrays.asList(units);
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(final Locale locale)
    {
        this.locale = locale;
    }

}
