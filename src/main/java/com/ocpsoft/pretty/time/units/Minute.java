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
package com.ocpsoft.pretty.time.units;

import java.util.Locale;

import com.ocpsoft.pretty.time.AbstractTimeUnit;
import com.ocpsoft.pretty.time.TimeFormat;
import com.ocpsoft.pretty.time.TimeUnit;

public class Minute extends AbstractTimeUnit implements TimeUnit {

	public Minute(Locale locale) {
		super(locale);
		millisPerUnit = 1000L * 60L;
	}

	protected String getResourceKeyPrefix() {
		return "Minute";
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
