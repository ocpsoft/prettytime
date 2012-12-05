package org.ocpsoft.prettytime;

/**
 * Represents a quantity of any given {@link TimeUnit}
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 */
public interface Duration
{
   /**
    * Return the calculated quantity of {@link TimeUnit} instances.
    */
   public long getQuantity();

   /**
    * Return the calculated quantity of {@link TimeUnit} instances, rounded up if {@link #getDelta()} is greater than or
    * equal to the given tolerance.
    */
   public long getQuantityRounded(int tolerance);

   /**
    * Return the {@link TimeUnit} represented by this {@link Duration}
    */
   public TimeUnit getUnit();

   /**
    * Return the number of milliseconds left over when calculating the number of {@link TimeUnit}'s that fit into the
    * given time range.
    */
   public long getDelta();

   /**
    * Return true if this {@link Duration} represents a number of {@link TimeUnit} instances in the past.
    */
   public boolean isInPast();

   /**
    * Return true if this {@link Duration} represents a number of {@link TimeUnit} instances in the future.
    */
   public boolean isInFuture();
}
