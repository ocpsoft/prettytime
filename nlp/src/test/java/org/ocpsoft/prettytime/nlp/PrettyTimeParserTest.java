package org.ocpsoft.prettytime.nlp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.nlp.parse.DateGroup;

public class PrettyTimeParserTest
{

   @Test
   public void testParseTimes()
   {
      List<Date> parse = new PrettyTimeParser().parse("let's get lunch at two pm");
      Assert.assertFalse(parse.isEmpty());
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(parse.get(0));
      Assert.assertEquals(14, calendar.get(Calendar.HOUR_OF_DAY));
   }

   @Test
   public void testParseAmbiguousTimes()
   {
      List<Date> parse = new PrettyTimeParser().parse("let's get lunch at two");
      Assert.assertFalse(parse.isEmpty());
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(parse.get(0));
      int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
      if (hourOfDay >= 2 && hourOfDay < 14)
         Assert.assertEquals(14, calendar.get(Calendar.HOUR_OF_DAY));
      else
         Assert.assertEquals(2, calendar.get(Calendar.HOUR_OF_DAY));

   }

   @Test
   public void testParsePrettyTimeTime()
   {
      List<Date> parse = new PrettyTimeParser().parse("I did it three days ago");
      Assert.assertFalse(parse.isEmpty());
      String formatted = new PrettyTime().format(parse.get(0));
      Assert.assertEquals("3 days ago", formatted);
   }

   @Test
   public void testParseSyntax()
   {
      List<DateGroup> parse = new PrettyTimeParser().parseSyntax("I did it three days ago");
      Assert.assertFalse(parse.isEmpty());
      String formatted = new PrettyTime().format(parse.get(0).getDates().get(0));
      Assert.assertEquals("3 days ago", formatted);
      Assert.assertEquals(1, parse.get(0).getLine());
      Assert.assertEquals(9, parse.get(0).getPosition());
      Assert.assertEquals(1, parse.get(0).getDates().size());
      Assert.assertNull(parse.get(0).getRecursUntil());
      Assert.assertFalse(parse.get(0).isRecurring());
      Assert.assertEquals(-1, parse.get(0).getRecurInterval());
   }

   @Test
   public void testParseSyntaxRecurring()
   {
      List<DateGroup> parse = new PrettyTimeParser().parseSyntax("I do it every three days");
      Assert.assertFalse(parse.isEmpty());
      String formatted = new PrettyTime().format(parse.get(0).getDates().get(0));
      Assert.assertEquals("3 days from now", formatted);
      Assert.assertEquals(1, parse.get(0).getLine());
      Assert.assertEquals(14, parse.get(0).getPosition());
      Assert.assertEquals(1, parse.get(0).getDates().size());
      Assert.assertNull(parse.get(0).getRecursUntil());
      Assert.assertTrue(parse.get(0).isRecurring());
      Assert.assertEquals(1000 * 60 * 60 * 24 * 3, parse.get(0).getRecurInterval());
   }

}
