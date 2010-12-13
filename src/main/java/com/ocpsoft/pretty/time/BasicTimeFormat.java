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
    private int roundingTolerance = 0;

    public String format(final Duration duration)
    {
        String sign = getSign(duration);
        String unit = getGramaticallyCorrectName(duration);
        long quantity = getQuantity(duration);

        String result = applyPattern(sign, unit, quantity);
        result = decorate(sign, result);

        return result;
    }

    private String decorate(final String sign, String result)
    {
        if (NEGATIVE.equals(sign))
        {
            result = pastPrefix + " " + result + " " + pastSuffix;
        }
        else
        {
            result = futurePrefix + " " + result + " " + futureSuffix;
        }
        return result.trim();
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
        long quantity = Math.abs(duration.getQuantity());

        if (duration.getDelta() != 0)
        {
            double threshold = Math
                    .abs(((double) duration.getDelta() / (double) duration.getUnit().getMillisPerUnit()) * 100);
            if (threshold < roundingTolerance)
            {
                quantity = quantity + 1;
            }
        }
        return quantity;
    }

    private String getGramaticallyCorrectName(final Duration d)
    {
        String result = d.getUnit().getName();
        if ((Math.abs(d.getQuantity()) == 0) || (Math.abs(d.getQuantity()) > 1))
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
     * The percentage of the current {@link TimeUnit}.getMillisPerUnit() for
     * which the quantity may be rounded up by one.
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
}
