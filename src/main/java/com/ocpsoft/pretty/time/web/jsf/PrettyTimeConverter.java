package com.ocpsoft.pretty.time.web.jsf;

import java.io.Serializable;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.ocpsoft.pretty.time.PrettyTime;

public class PrettyTimeConverter implements Converter, Serializable
{
    private static final long serialVersionUID = 7690470362440868259L;

    private static PrettyTime prettyTime = new PrettyTime();

    public Object getAsObject(final FacesContext context, final UIComponent comp, final String value)
    {
        throw new ConverterException("Does not yet support converting String to Date");
    }

    public String getAsString(final FacesContext context, final UIComponent comp, final Object value)
    {
        if (value instanceof Date)
        {
            return prettyTime.format((Date) value);
        }
        throw new ConverterException("May only be used to convert java.util.Date objects. Got: " + value.getClass());
    }

    /**
     * Get the current {@link PrettyTime} instance being used in this Converter
     * @return
     */
    public static PrettyTime getPrettyTime()
    {
        return prettyTime;
    }

    /**
     * Set the current {@link PrettyTime} instance being used in this Converter
     * @param prettyTime
     */
    public static void setPrettyTime(PrettyTime prettyTime)
    {
        PrettyTimeConverter.prettyTime = prettyTime;
    }

}
