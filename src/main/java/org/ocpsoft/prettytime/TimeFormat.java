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

/**
 * Format a Duration object.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public interface TimeFormat
{
   /**
    * Given a populated {@link Duration} object. Apply formatting (with rounding) and output the result.
    * 
    * @param The original {@link Duration} instance from which the time string should be decorated.
    */
   public abstract String format(final Duration duration);

   /**
    * Given a populated {@link Duration} object. Apply formatting (without rounding) and output the result.
    * 
    * @param The original {@link Duration} instance from which the time string should be decorated.
    */
   public String formatUnrounded(Duration duration);

   /**
    * Decorate with past or future prefix/suffix (with rounding)
    * 
    * @param duration The original {@link Duration} instance from which the time string should be decorated.
    * @param time The formatted time string.
    */
   public String decorate(Duration duration, String time);

   /**
    * Decorate with past or future prefix/suffix (without rounding)
    * 
    * @param duration The original {@link Duration} instance from which the time string should be decorated.
    * @param time The formatted time string.
    */
   public String decorateUnrounded(Duration duration, String time);

}