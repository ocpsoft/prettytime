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
package org.ocpsoft.prettytime.units;

import java.io.Serializable;
import java.util.Comparator;

import org.ocpsoft.prettytime.TimeUnit;


/**
 * Compares two {@link TimeUnit} objects
 * 
 * @author lb3
 */
public class TimeUnitComparator implements Comparator<TimeUnit>, Serializable {

	private static final long serialVersionUID = 1L;

	public int compare(final TimeUnit left, final TimeUnit right) {
		if (left.getMillisPerUnit() < right.getMillisPerUnit()) {
			return -1;
		} else if (left.getMillisPerUnit() > right.getMillisPerUnit()) {
			return 1;
		}
		return 0;
	}
}
