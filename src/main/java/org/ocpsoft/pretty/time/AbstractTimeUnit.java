/*
 * Copyright 2012 <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ocpsoft.pretty.time;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public abstract class AbstractTimeUnit
{

   protected Locale locale;
   protected TimeFormat format;
   protected String name;
   protected String pluralName;

   // Use sensitive defaults
   protected long maxQuantity = 0;
   protected long millisPerUnit = 1;

   public AbstractTimeUnit(final Locale locale)
   {
      setLocale(locale);
   }

   public void setLocale(Locale locale)
   {
      this.locale = locale;

      // Resource bundles need to be in the given package, names start with
      // 'Resources', e.g. 'Resources_de.java'
      ResourceBundle bundle = ResourceBundle.getBundle("org.ocpsoft.pretty.time.i18n.Resources", locale);

      String pattern = bundle.getString(getResourceKeyPrefix() + "Pattern");
      String futurePrefix = bundle.getString(getResourceKeyPrefix() + "FuturePrefix");
      String futureSuffix = bundle.getString(getResourceKeyPrefix() + "FutureSuffix");
      String pastPrefix = bundle.getString(getResourceKeyPrefix() + "PastPrefix");
      String pastSuffix = bundle.getString(getResourceKeyPrefix() + "PastSuffix");
      format = new BasicTimeFormat().setPattern(pattern).setFuturePrefix(futurePrefix).setFutureSuffix(futureSuffix)
               .setPastPrefix(pastPrefix).setPastSuffix(pastSuffix);

      name = bundle.getString(getResourceKeyPrefix() + "Name");
      pluralName = (bundle.getString(getResourceKeyPrefix() + "PluralName"));
   }

   abstract protected String getResourceKeyPrefix();

}
