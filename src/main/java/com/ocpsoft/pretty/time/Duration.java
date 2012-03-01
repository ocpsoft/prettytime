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

   public boolean isInPast()
   {
      return getQuantity() < 0;
   }

   public boolean isInFuture()
   {
      return !isInPast();
   }
}
