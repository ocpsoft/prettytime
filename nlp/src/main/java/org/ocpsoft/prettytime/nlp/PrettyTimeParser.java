package org.ocpsoft.prettytime.nlp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.ParseLocation;
import com.joestelmach.natty.Parser;

public class PrettyTimeParser
{
   private Parser parser = new Parser();
   private Map<String, String> translations = new HashMap<String, String>();

   public PrettyTimeParser()
   {
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
   }

   public List<Date> parse(String language)
   {
      language = words2numbers(language);

      List<Date> result = new ArrayList<Date>();
      List<DateGroup> groups = parser.parse(language);
      for (DateGroup group : groups) {
         List<Date> dates = group.getDates();
         int line = group.getLine();
         int column = group.getPosition();
         String matchingValue = group.getText();
         String syntaxTree = group.getSyntaxTree().toStringTree();
         Map<String, List<ParseLocation>> parseMap = group.getParseLocations();
         boolean isRecurreing = group.isRecurring();
         Date recursUntil = group.getRecursUntil();
         result.addAll(dates);
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
}
