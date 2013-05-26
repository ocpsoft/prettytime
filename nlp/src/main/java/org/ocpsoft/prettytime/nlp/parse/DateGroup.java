package org.ocpsoft.prettytime.nlp.parse;

import java.util.Date;
import java.util.List;

/**
 * Represents a {@link Date} instanced parsed out of natural language text.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
 */
public interface DateGroup
{
   /**
    * Get the line in which this {@link DateGroup} was found.
    */
   int getLine();

   /**
    * Get the text fragment parsed into this {@link DateGroup}.
    */
   String getText();

   /**
    * Get the {@link Date} to which this {@link DateGroup} recurs.
    */
   Date getRecursUntil();

   /**
    * Get the starting position of this {@link DateGroup} in the language text.
    */
   int getPosition();

   /**
    * Get all {@link Date} instances parsed from the language text.
    */
   List<Date> getDates();

   /**
    * Return <code>true</code> if this {@link DateGroup} is a recurring event.
    */
   boolean isRecurring();

   /**
    * If this {@link DateGroup} is recurring, return the interval in milliseconds with which this {@link DateGroup}
    * recurs, otherwise return -1;
    */
   long getRecurInterval();
}
