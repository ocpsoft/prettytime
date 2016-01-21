package org.ocpsoft.prettytime.impl;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.LocaleAware;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Represents a simple method of formatting a specific {@link Duration} of time
 *
 * @author lb3
 */
public class ResourcesTimeFormat extends SimpleTimeFormat implements TimeFormat, LocaleAware<ResourcesTimeFormat>
{
   private ResourceBundle bundle;
   private final ResourcesTimeUnit unit;
   private TimeFormat override;
   private String overrideResourceBundle; // If used this bundle will override the included bundle

   public ResourcesTimeFormat(ResourcesTimeUnit unit)
   {
      this.unit = unit;
   }

   public ResourcesTimeFormat(ResourcesTimeUnit unit, String overrideResourceBundle)
   {
      this.unit = unit;
      this.overrideResourceBundle = overrideResourceBundle;
   }

   @Override
   public ResourcesTimeFormat setLocale(Locale locale)
   {
      if (overrideResourceBundle != null) {
         try {
            // Attempt to load the bundle that the user passed in, maybe it exists, maybe not
            bundle = ResourceBundle.getBundle(overrideResourceBundle, locale);
         } catch (Exception e) {
            // Throw away if the bundle doesn't contain this local
         }
      }

      // If the bundle doesn't exist then load the default included one
      if (bundle == null) {
         bundle = ResourceBundle.getBundle(unit.getResourceBundleName(), locale);
      }

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
         setPattern(bundle.getString(unit.getResourceKeyPrefix() + "Pattern"));
         setFuturePrefix(bundle.getString(unit.getResourceKeyPrefix() + "FuturePrefix"));
         setFutureSuffix(bundle.getString(unit.getResourceKeyPrefix() + "FutureSuffix"));
         setPastPrefix(bundle.getString(unit.getResourceKeyPrefix() + "PastPrefix"));
         setPastSuffix(bundle.getString(unit.getResourceKeyPrefix() + "PastSuffix"));

         setSingularName(bundle.getString(unit.getResourceKeyPrefix() + "SingularName"));
         setPluralName(bundle.getString(unit.getResourceKeyPrefix() + "PluralName"));

         try {
            setFuturePluralName(bundle.getString(unit.getResourceKeyPrefix() + "FuturePluralName"));
         }
         catch (Exception e) {}
         try {
            setFutureSingularName((bundle.getString(unit.getResourceKeyPrefix() + "FutureSingularName")));
         }
         catch (Exception e) {}
         try {
            setPastPluralName((bundle.getString(unit.getResourceKeyPrefix() + "PastPluralName")));
         }
         catch (Exception e) {}
         try {
            setPastSingularName((bundle.getString(unit.getResourceKeyPrefix() + "PastSingularName")));
         }
         catch (Exception e) {}

      }

      return this;
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