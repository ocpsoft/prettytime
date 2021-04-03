package org.ocpsoft.prettytime.jstl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.taglibs.standard.tag.common.fmt.SetLocaleSupport;
import org.ocpsoft.prettytime.PrettyTime;

/**
 * Custom tag to pretty print {@link java.util.Date} objects using <a href="http://ocpsoft.org/prettytime/">prettytime</a>.
 */
public class PrettyTimeTag extends SimpleTagSupport {

    private static final int MAX_CACHE_SIZE = 20;

    // Cache PrettyTime per locale. LRU cache to prevent memory leak.
    private static final Map<Locale, PrettyTime> PRETTY_TIME_LOCALE_MAP = new LinkedHashMap<Locale, PrettyTime>(MAX_CACHE_SIZE + 1, 1.1F, true) {
        private static final long serialVersionUID = 5093634937930600141L;

        @Override
        protected boolean removeEldestEntry(Map.Entry<Locale, PrettyTime> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    /**
     * The date to pretty print.
     */
    private Date date;

    /**
     * The locale used to localize the message.
     */
    private String locale;

    public PrettyTimeTag() {
    }

    @Override
    public void doTag() throws JspException, IOException {
        final Locale thisLocale;
        if (locale != null) {
            thisLocale = SetLocaleSupport.parseLocale(this.locale);
        } else {
            thisLocale = Locale.getDefault();
        }

        PrettyTime prettyTime = PRETTY_TIME_LOCALE_MAP.computeIfAbsent(thisLocale, PrettyTime::new);
        JspWriter out = getJspContext().getOut();
        out.print(prettyTime.format(date));
    }

    /*
     * setters for tag attributes
     */

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
