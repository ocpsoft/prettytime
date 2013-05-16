package org.ocpsoft.prettytime.nlp;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class PrettyTimeParserTest
{

   @Test
   public void testParseTimes()
   {
      List<Date> parse = new PrettyTimeParser().parse("come get me before in four days");
      Assert.assertFalse(parse.isEmpty());
      System.out.println(parse);
   }

}
