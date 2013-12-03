package org.ocpsoft.prettytime.nlp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import org.ocpsoft.prettytime.nlp.parse.DateGroup;

import com.joestelmach.natty.Parser;

/**
 * A utility for parsing natural language date and time expressions. (e.g. "Let's get lunch at two pm",
 * "I did it 3 days ago")
 * <p>
 * <b>Usage:</b>
 * <p>
 * <code>
 * PrettyTimeParser p = new PrettyTimeParser();<br/>
 * List&lt;Date&gt; parsed = p.parse("I'll be there at two");<br/>
 * //result: Date - 2:00PM
 * <p>
 * </code>
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
 */
public class PrettyTimeParser
{

   private Parser parser = new Parser();
   private Map<String, String> translations = new HashMap<String, String>();
   private Set<String> periods = new HashSet<String>();

   private final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
   };

   private final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
   };

   /**
    * Create a new {@link PrettyTimeParser} with the given {@link TimeZone}.
    */
   public PrettyTimeParser(TimeZone timezone)
   {
      parser = new Parser(timezone);
   }

   /**
    * Create a new {@link PrettyTimeParser} with the current system default {@link TimeZone}.
    */
   public PrettyTimeParser()
   {
      this(TimeZone.getDefault());
      for (int hours = 0; hours < 24; hours++)
         for (int min = 0; min < 60; min++)
            translations.put(provideRepresentation(hours * 100 + min), "" + hours * 100 + min);
      translations.put(provideRepresentation(60), "" + 60);
      translations.put(provideRepresentation(70), "" + 70);
      translations.put(provideRepresentation(80), "" + 80);
      translations.put(provideRepresentation(90), "" + 90);
      translations.put(provideRepresentation(100), "" + 100);

      periods.add("morning");
      periods.add("afternoon");
      periods.add("evening");
      periods.add("night");
      periods.add("am");
      periods.add("pm");
      periods.add("ago");
      periods.add("from now");
   }

   /**
    * Provides a string representation for the number passed. This method works for limited set of numbers as parsing
    * will only be done at maximum for 2400, which will be used in military time format.
    */
   private String provideRepresentation(int number)
   {
      String key;

      if (number == 0)
         key = "zero";
      else if (number < 20)
         key = numNames[number];
      else if (number < 100)
      {
         int unit = number % 10;
         key = tensNames[number / 10] + numNames[unit];
      }
      else
      {
         int unit = number % 10;
         int ten = number % 100 - unit;
         int hundred = (number - ten) / 100;
         if (hundred < 20)
            key = numNames[hundred] + " hundred";
         else
            key = tensNames[hundred / 10] + numNames[hundred % 10] + " hundred";
         if (ten + unit < 20 && ten + unit > 10)
            key += numNames[ten + unit];
         else
            key += tensNames[ten / 10] + numNames[unit];
      }
      return key.trim();
   }

   /**
    * Parse the given language and return a {@link List} with all discovered {@link Date} instances.
    */
   public List<Date> parse(String language)
   {
      language = words2numbers(language);

      List<Date> result = new ArrayList<Date>();
      List<com.joestelmach.natty.DateGroup> groups = parser.parse(language);
      for (com.joestelmach.natty.DateGroup group : groups) {
         result.addAll(group.getDates());
      }
      return result;
   }

   /**
    * Parse the given language and return a {@link List} with all discovered {@link DateGroup} instances.
    */
   public List<DateGroup> parseSyntax(String language)
   {
      language = words2numbers(language);

      List<DateGroup> result = new ArrayList<DateGroup>();
      List<com.joestelmach.natty.DateGroup> groups = parser.parse(language);
      Date now = new Date();
      for (com.joestelmach.natty.DateGroup group : groups) {
         result.add(new DateGroupImpl(now, group));
      }
      return result;
   }

   private String words2numbers(String language)
   {
      for (Entry<String, String> entry : translations.entrySet()) {
         language = language.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
      }
      return language;
   }

   private class DateGroupImpl implements DateGroup
   {
      private List<Date> dates;
      private int line;
      private int position;
      private Date recursUntil;
      private String text;
      private boolean recurring;
      private Date now;

      public DateGroupImpl(Date now, com.joestelmach.natty.DateGroup group)
      {
         this.now = now;
         dates = group.getDates();
         line = group.getLine();
         position = group.getPosition();
         recursUntil = group.getRecursUntil();
         text = group.getText();
         recurring = group.isRecurring();
      }

      @Override
      public List<Date> getDates()
      {
         return dates;
      }

      @Override
      public int getLine()
      {
         return line;
      }

      @Override
      public int getPosition()
      {
         return position;
      }

      @Override
      public Date getRecursUntil()
      {
         return recursUntil;
      }

      @Override
      public String getText()
      {
         return text;
      }

      @Override
      public boolean isRecurring()
      {
         return recurring;
      }

      @Override
      public long getRecurInterval()
      {
         if (isRecurring())
            return getDates().get(0).getTime() - now.getTime();
         else
            return -1;
      }

   }
}
