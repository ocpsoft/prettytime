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

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class AbstractTimeUnit
{

    protected Locale locale;
    protected TimeFormat format;
    protected String name;
    protected String pluralName;

    // Use sensitive defaults
    protected long maxQuantity = 0;
    protected long millisPerUnit = 1;

    public AbstractTimeUnit(final Locale locale)
    {
        this.locale = locale;

        // Resource bundles need to be in the given package, names start with
        // 'Resources', e.g. 'Resources_de.java'
        ResourceBundle bundle = ResourceBundle.getBundle("com.ocpsoft.pretty.time.i18n.Resources", locale);

        String pattern = bundle.getString(getResourceKeyPrefix() + "Pattern");
        String futurePrefix = bundle.getString(getResourceKeyPrefix() + "FuturePrefix");
        String futureSuffix = bundle.getString(getResourceKeyPrefix() + "FutureSuffix");
        String pastPrefix = bundle.getString(getResourceKeyPrefix() + "PastPrefix");
        String pastSuffix = bundle.getString(getResourceKeyPrefix() + "PastSuffix");
        format = new BasicTimeFormat().setPattern(pattern).setFuturePrefix(futurePrefix).setFutureSuffix(futureSuffix)
                .setPastPrefix(pastPrefix).setPastSuffix(pastSuffix);

        name = bundle.getString(getResourceKeyPrefix() + "Name");
        pluralName = (bundle.getString(getResourceKeyPrefix() + "PluralName"));
    }

    abstract protected String getResourceKeyPrefix();

}
