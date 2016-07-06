package org.ocpsoft.prettytime.i18n;

import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;

public class Resources_hrTest {

	   private static final int MINUTES = 1000 * 60;

	@Test
	   public void testFormatMinute() throws Exception
	   {
	      PrettyTime prettyTime = new PrettyTime(new Locale("hr"));

	      Assert.assertEquals("za 1 minutu", prettyTime.format(new Date(System.currentTimeMillis() + 1*MINUTES)));
	      Assert.assertEquals("za 2 minute", prettyTime.format(new Date(System.currentTimeMillis() + 2*MINUTES)));
	      Assert.assertEquals("za 3 minute", prettyTime.format(new Date(System.currentTimeMillis() + 3*MINUTES)));
	      Assert.assertEquals("za 4 minute", prettyTime.format(new Date(System.currentTimeMillis() + 4*MINUTES)));
	      Assert.assertEquals("za 5 minuta", prettyTime.format(new Date(System.currentTimeMillis() + 5*MINUTES)));
	      Assert.assertEquals("za 6 minuta", prettyTime.format(new Date(System.currentTimeMillis() + 6*MINUTES)));
	      
	      Assert.assertEquals("prije 1 minutu", prettyTime.format(new Date(System.currentTimeMillis() - 1*MINUTES)));
	      Assert.assertEquals("prije 2 minute", prettyTime.format(new Date(System.currentTimeMillis() - 2*MINUTES)));
	      Assert.assertEquals("prije 3 minute", prettyTime.format(new Date(System.currentTimeMillis() - 3*MINUTES)));
	      Assert.assertEquals("prije 4 minute", prettyTime.format(new Date(System.currentTimeMillis() - 4*MINUTES)));
	      Assert.assertEquals("prije 5 minuta", prettyTime.format(new Date(System.currentTimeMillis() - 5*MINUTES)));
	      Assert.assertEquals("prije 6 minuta", prettyTime.format(new Date(System.currentTimeMillis() - 6*MINUTES)));
	   }

}
