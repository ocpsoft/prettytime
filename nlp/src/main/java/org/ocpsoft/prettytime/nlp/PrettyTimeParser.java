package org.ocpsoft.prettytime.nlp;

import java.util.ArrayList;
import java.util.Calendar;
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
      translations.put("zero", "0");
      translations.put("one", "1");
      translations.put("two", "2");
      translations.put("three", "3");
      translations.put("four", "4");
      translations.put("five", "5");
      translations.put("six", "6");
      translations.put("seven", "7");
      translations.put("eight", "8");
      translations.put("nine", "9");
      translations.put("ten", "10");
      translations.put("eleven", "11");
      translations.put("twelve", "12");
      translations.put("thirteen", "13");
      translations.put("fourteen", "14");
      translations.put("fifteen", "15");
      translations.put("sixteen", "16");
      translations.put("seventeen", "17");
      translations.put("eighteen", "18");
      translations.put("nineteen", "19");
      translations.put("twenty", "20");
      translations.put("thirty", "30");
      translations.put("fourty", "40");
      translations.put("fifty", "50");
      translations.put("sixty", "60");
      translations.put("seventy", "70");
      translations.put("eighty", "80");
      translations.put("ninety", "90");

      periods.add("morning");
      periods.add("afternooon");
      periods.add("evening");
      periods.add("night");
      periods.add("am");
      periods.add("pm");
      periods.add("ago");
      periods.add("from now");
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
         List<Date> dates = relativize(group);
         result.addAll(dates);
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
      for (com.joestelmach.natty.DateGroup group : groups) {
         result.add(new DateGroupImpl(group));
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

   private List<Date> relativize(com.joestelmach.natty.DateGroup group)
   {
      String matchingValue = group.getText();
      boolean ambiguous = true;

      for (String qualifier : periods)
      {
         if (matchingValue.contains(qualifier))
            ambiguous = false;
      }

      List<Date> result = group.getDates();
      if (ambiguous)
      {
         Date now = new Date();
         for (int i = 0; i < result.size(); i++) {
            Date date = result.get(i);
            if (date.before(now))
            {
               Calendar calendar = Calendar.getInstance();
               calendar.setTime(date);
               calendar.add(Calendar.HOUR_OF_DAY, 12);
               date = calendar.getTime();
               result.set(i, date);
            }
         }
      }
      return result;
   }

   private class DateGroupImpl implements DateGroup
   {
      private List<Date> dates;
      private int line;
      private int position;
      private Date recursUntil;
      private String text;

      public DateGroupImpl(com.joestelmach.natty.DateGroup group)
      {
         dates = group.getDates();
         line = group.getLine();
         position = group.getPosition();
         recursUntil = group.getRecursUntil();
         text = group.getText();
      }

      public List<Date> getDates()
      {
         return dates;
      }

      public int getLine()
      {
         return line;
      }

      public int getPosition()
      {
         return position;
      }

      public Date getRecursUntil()
      {
         return recursUntil;
      }

      public String getText()
      {
         return text;
      }

   }
}
