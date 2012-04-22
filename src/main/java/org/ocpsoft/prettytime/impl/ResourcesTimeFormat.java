package org.ocpsoft.prettytime.impl;

import java.util.Locale;
import java.util.ResourceBundle;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.LocaleAware;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

/**
 * Represents a simple method of formatting a specific {@link Duration} of time
 * 
 * @author lb3
 */
public class ResourcesTimeFormat extends SimpleTimeFormat implements TimeFormat, LocaleAware
{
   private ResourceBundle bundle;
   private final ResourcesTimeUnit unit;
   private TimeFormat override;

   public ResourcesTimeFormat(ResourcesTimeUnit unit, Locale locale)
   {
      this.unit = unit;
      setLocale(locale);
   }

   @Override
   public void setLocale(Locale locale)
   {
      bundle = ResourceBundle.getBundle(unit.getResourceBundleName(), locale);

      if (bundle instanceof TimeFormatProvider)
      {
         TimeFormat format = ((TimeFormatProvider) bundle).getFormatFor(unit);
         if (format != null)
         {
            this.override = format;
         }
      }
      else
      {
         override = null;
      }

      if (override == null)
      {
         String pattern = bundle.getString(unit.getResourceKeyPrefix() + "Pattern");
         String futurePrefix = bundle.getString(unit.getResourceKeyPrefix() + "FuturePrefix");
         String futureSuffix = bundle.getString(unit.getResourceKeyPrefix() + "FutureSuffix");
         String pastPrefix = bundle.getString(unit.getResourceKeyPrefix() + "PastPrefix");
         String pastSuffix = bundle.getString(unit.getResourceKeyPrefix() + "PastSuffix");

         String name = (bundle.getString(unit.getResourceKeyPrefix() + "Name"));
         String pluralName = (bundle.getString(unit.getResourceKeyPrefix() + "PluralName"));

         this.setPattern(pattern)
         .setFuturePrefix(futurePrefix)
         .setFutureSuffix(futureSuffix)
         .setPastPrefix(pastPrefix)
         .setPastSuffix(pastSuffix)
         .setName(name)
         .setPluralName(pluralName);
      }
   }

   @Override
   public String decorate(Duration duration, String time)
   {
      return override == null ? super.decorate(duration, time) : override.decorate(duration, time);
   }

   @Override
   public String decorateUnrounded(Duration duration, String time)
   {
      return override == null ? super.decorateUnrounded(duration, time) : override.decorateUnrounded(duration, time);
   }

   @Override
   public String format(Duration duration)
   {
      return override == null ? super.format(duration) : override.format(duration);
   }

   @Override
   public String formatUnrounded(Duration duration)
   {
      return override == null ? super.formatUnrounded(duration) : override.formatUnrounded(duration);
   }
}