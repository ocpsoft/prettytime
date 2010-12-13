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

public class Duration
{
    private long quantity;
    private long delta;
    private TimeUnit unit;

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(final long quantity)
    {
        this.quantity = quantity;
    }

    public TimeUnit getUnit()
    {
        return unit;
    }

    public void setUnit(final TimeUnit unit)
    {
        this.unit = unit;
    }

    public long getDelta()
    {
        return delta;
    }

    public void setDelta(final long delta)
    {
        this.delta = delta;
    }
}
