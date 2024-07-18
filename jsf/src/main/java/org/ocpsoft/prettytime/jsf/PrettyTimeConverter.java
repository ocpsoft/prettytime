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
package org.ocpsoft.prettytime.jsf;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class PrettyTimeConverter implements Converter, Serializable {

    private static final long serialVersionUID = 7690470362440868260L;

    private static final int MAX_CACHE_SIZE = 20;

    // Cache PrettyTime per locale. LRU cache to prevent memory leak.
    private static final Map<Locale, PrettyTime> PRETTY_TIME_LOCALE_MAP = new LinkedHashMap<Locale, PrettyTime>(MAX_CACHE_SIZE + 1, 1.1F, true) {
        private static final long serialVersionUID = 5093634937930600141L;

        @Override
        protected boolean removeEldestEntry(Map.Entry<Locale, PrettyTime> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    public Object getAsObject(final FacesContext context, final UIComponent comp, final String value) {
        throw new ConverterException("Does not yet support converting String to Date");
    }

    public String getAsString(final FacesContext context, final UIComponent comp, final Object value) {
        // Use locale of current viewer.
        Locale locale = context.getViewRoot().getLocale();
        PrettyTime prettyTime;

        synchronized (PRETTY_TIME_LOCALE_MAP) {
            prettyTime = PRETTY_TIME_LOCALE_MAP.computeIfAbsent(locale, PrettyTime::new);
        }
        if (value instanceof Date) {
            return prettyTime.format((Date) value);
        }
        if (value instanceof Calendar) {
            return prettyTime.format((Calendar) value);
        }
        if (value instanceof Duration) {
            return prettyTime.format((Duration) value);
        }
        if (value instanceof Instant) {
            return prettyTime.format((Instant) value);
        }
        if (value instanceof ZonedDateTime) {
            return prettyTime.format((ZonedDateTime) value);
        }
        if (value instanceof OffsetDateTime) {
            return prettyTime.format((OffsetDateTime) value);
        }
        if (value instanceof LocalDateTime) {
            return prettyTime.format((LocalDateTime) value);
        }
        if (value instanceof LocalDate) {
            return prettyTime.format((LocalDate) value);
        }
        throw new ConverterException("May only be used to convert java.util.Date,java.util.Calendar,"
                + "org.ocpsoft.prettytime.Duration,java.time.Instant,java.time.ZonedDateTime,"
                + "java.time.OffsetDateTime,java.time.LocalDateTime,java.time.LocalDate objects. "
                + "Got: " + (value != null ? value.getClass() : "null"));
    }
}
