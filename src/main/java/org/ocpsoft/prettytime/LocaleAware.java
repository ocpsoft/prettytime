package org.ocpsoft.prettytime;

import java.util.Locale;

/**
 * An object that behaves differently for various {@link Locale} settings.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public interface LocaleAware<TYPE>
{
   /**
    * Set the {@link Locale} for which this instance should behave in.
    */
   public TYPE setLocale(Locale locale);

}
