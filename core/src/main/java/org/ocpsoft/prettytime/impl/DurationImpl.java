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
package org.ocpsoft.prettytime.impl;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeUnit;

public class DurationImpl implements Duration
{

   private long quantity;
   private long delta;
   private TimeUnit unit;

   @Override
   public long getQuantity()
   {
      return quantity;
   }

   public void setQuantity(final long quantity)
   {
      this.quantity = quantity;
   }

   @Override
   public TimeUnit getUnit()
   {
      return unit;
   }

   public void setUnit(final TimeUnit unit)
   {
      this.unit = unit;
   }

   @Override
   public long getDelta()
   {
      return delta;
   }

   public void setDelta(final long delta)
   {
      this.delta = delta;
   }

   @Override
   public boolean isInPast()
   {
      return getQuantity() < 0;
   }

   @Override
   public boolean isInFuture()
   {
      return !isInPast();
   }

   @Override
   public long getQuantityRounded(int tolerance)
   {
      long quantity = Math.abs(getQuantity());

      if (getDelta() != 0)
      {
         double threshold = Math
                  .abs(((double) getDelta() / (double) getUnit().getMillisPerUnit()) * 100);
         if (threshold > tolerance)
         {
            quantity = quantity + 1;
         }
      }
      return quantity;
   }

   @Override
   public String toString()
   {
      return "DurationImpl [" + quantity + " " + unit + ", delta=" + delta + "]";
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int) (delta ^ (delta >>> 32));
      result = prime * result + (int) (quantity ^ (quantity >>> 32));
      result = prime * result + ((unit == null) ? 0 : unit.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      DurationImpl other = (DurationImpl) obj;
      if (delta != other.delta)
         return false;
      if (quantity != other.quantity)
         return false;
      if (unit == null) {
         if (other.unit != null)
            return false;
      }
      else if (!unit.equals(other.unit))
         return false;
      return true;
   }
}
