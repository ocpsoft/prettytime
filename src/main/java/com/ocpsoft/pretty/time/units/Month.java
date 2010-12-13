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

import java.util.Locale;

import com.ocpsoft.pretty.time.AbstractTimeUnit;
import com.ocpsoft.pretty.time.TimeFormat;
import com.ocpsoft.pretty.time.TimeUnit;

public class Month extends AbstractTimeUnit implements TimeUnit {

	public Month(Locale locale) {
		super(locale);
		millisPerUnit = 2629743830L;
	}

	protected String getResourceKeyPrefix() {
		return "Month";
	}

	public long getMillisPerUnit() {
		return millisPerUnit;
	}

	public TimeFormat getFormat() {
		return format;
	}

	public void setFormat(final TimeFormat format) {
		this.format = format;
	}

	public long getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(final long maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(final String pluralName) {
		this.pluralName = pluralName;
	}

}
