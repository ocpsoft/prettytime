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
package com.ocpsoft.pretty.time.units;

import java.io.Serializable;
import java.util.Comparator;

import com.ocpsoft.pretty.time.TimeUnit;

/**
 * Compares two {@link TimeUnit} objects
 * 
 * @author lb3
 */
public class TimeUnitComparator implements Comparator<TimeUnit>, Serializable {

	private static final long serialVersionUID = 1L;

	public int compare(final TimeUnit left, final TimeUnit right) {
		if (left.getMillisPerUnit() < right.getMillisPerUnit()) {
			return 1;
		} else if (left.getMillisPerUnit() > right.getMillisPerUnit()) {
			return -1;
		}
		return 0;
	}
}
