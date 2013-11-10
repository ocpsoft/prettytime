package org.ocpsoft.prettytime.jstl;

import org.apache.taglibs.standard.tag.common.fmt.SetLocaleSupport;
import org.ocpsoft.prettytime.PrettyTime;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

/**
 * Custom tag to pretty print {@link java.util.Date} objects using <a href="http://ocpsoft.org/prettytime/">prettytime</a>.
 */
public class PrettyTimeTag extends SimpleTagSupport {

    /**
     * The pretty time object.
     */
    private PrettyTime prettyTime;

    /**
     * The date to pretty print.
     */
    private Date date;

    /**
     * The locale used to localize the message.
     */
    private String locale;

    public PrettyTimeTag() {
        prettyTime = new PrettyTime();
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (locale != null) {
            Locale locale = SetLocaleSupport.parseLocale(this.locale);
            prettyTime.setLocale(locale);
        }

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
