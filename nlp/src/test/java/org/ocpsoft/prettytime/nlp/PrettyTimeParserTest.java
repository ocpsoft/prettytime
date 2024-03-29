package org.ocpsoft.prettytime.nlp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Ignore;
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
   @Ignore
   public void testParseAmbiguousTimes()
   {
      List<DateGroup> parse = new PrettyTimeParser().parseSyntax("let's get lunch at two");
      Assert.assertFalse(parse.isEmpty());
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(parse.get(0).getDates().get(0));
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
      String formatted = new PrettyTime(Locale.ENGLISH).format(parse.get(0));
      Assert.assertEquals("3 days ago", formatted);
   }

   @Test
   public void testParseSyntax()
   {
      List<DateGroup> parse = new PrettyTimeParser().parseSyntax("I did it three days ago");
      Assert.assertFalse(parse.isEmpty());
      String formatted = new PrettyTime(Locale.ENGLISH).format(parse.get(0).getDates().get(0));
      Assert.assertEquals("3 days ago", formatted);
      Assert.assertEquals(1, parse.get(0).getLine());
      Assert.assertEquals(10, parse.get(0).getPosition());
      Assert.assertEquals(1, parse.get(0).getDates().size());
      Assert.assertNull(parse.get(0).getRecursUntil());
      Assert.assertFalse(parse.get(0).isRecurring());
      Assert.assertEquals(-1, parse.get(0).getRecurInterval());
   }

   @Test
   @Ignore
   public void testParseSyntaxRecurring()
   {
      List<DateGroup> parse = new PrettyTimeParser().parseSyntax("I do it every three days");
      Assert.assertFalse(parse.isEmpty());
      String formatted = new PrettyTime(Locale.ENGLISH).format(parse.get(0).getDates().get(0));
      Assert.assertEquals("3 days from now", formatted);
      Assert.assertEquals(1, parse.get(0).getLine());
      Assert.assertEquals(14, parse.get(0).getPosition());
      Assert.assertEquals(1, parse.get(0).getDates().size());
      Assert.assertNull(parse.get(0).getRecursUntil());
      Assert.assertTrue(parse.get(0).isRecurring());
      Assert.assertEquals(1000 * 60 * 60 * 24 * 3l, parse.get(0).getRecurInterval());
   }

   @Test
   public void testParseYesterday()
   {
      Calendar yesterday = Calendar.getInstance();
      yesterday.setTime(new Date());
      yesterday.add(Calendar.DAY_OF_MONTH, -1);

      List<Date> parse = new PrettyTimeParser().parse("yesterday");
      Assert.assertFalse(parse.isEmpty());

      Calendar parsedDate = Calendar.getInstance();
      parsedDate.setTime(parse.get(0));

      Assert.assertEquals(yesterday.get(Calendar.DAY_OF_MONTH), parsedDate.get(Calendar.DAY_OF_MONTH));
      Assert.assertEquals(yesterday.get(Calendar.MONTH), parsedDate.get(Calendar.MONTH));
      Assert.assertEquals(yesterday.get(Calendar.YEAR), parsedDate.get(Calendar.YEAR));
   }

  @Test
  public void testParseYesterdayFromReferenceDate()
  {
    Date july14th2022 = new Date(1657830053000L);
    List<Date> parse = new PrettyTimeParser().parse("yesterday", july14th2022);

    Assert.assertEquals(1, parse.size());

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(parse.get(0));
    Assert.assertEquals(13, calendar.get(Calendar.DAY_OF_MONTH));
    Assert.assertEquals(6, calendar.get(Calendar.MONTH));
    Assert.assertEquals(2022, calendar.get(Calendar.YEAR));
  }
}
