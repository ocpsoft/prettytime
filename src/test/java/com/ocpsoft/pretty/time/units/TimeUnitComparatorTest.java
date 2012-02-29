package com.ocpsoft.pretty.time.units;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeUnitComparatorTest {

    // Stores current locale so that it can be restored
    private Locale locale;

    // Method setUp() is called automatically before every test method
    @Before
    public void setUp() throws Exception
    {
        locale = Locale.getDefault();
        Locale.setDefault(Locale.ROOT);
    }

    @Test
    public void testComparingOrder() throws Exception
    {
        TimeUnitComparator comparator = new TimeUnitComparator();
        assertEquals(-1, comparator.compare(new Hour(locale), new Day(locale)));
    }

    // Method tearDown() is called automatically after every test method
    @After
    public void tearDown() throws Exception
    {
        Locale.setDefault(locale);
    }

}
