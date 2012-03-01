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
package com.ocpsoft.pretty.time;

/**
 * Format a Duration object.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public interface TimeFormat
{
   /**
    * Given a populated Duration object. Apply plurality formatting (with rounding) and output the result.
    */
   public abstract String format(final Duration duration);

   /**
    * Return formatted duration without rounding
    */
   public String formatUnrounded(Duration duration);

   /**
    * Decorate with past or future prefix/suffix
    * 
    * @param The original {@link Duration} instance from which the time string should be decorated.
    */
   public String decorate(Duration duration, String time);

}