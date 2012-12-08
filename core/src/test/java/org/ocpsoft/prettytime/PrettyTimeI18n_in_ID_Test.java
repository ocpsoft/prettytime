package org.ocpsoft.prettytime;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

public class PrettyTimeI18n_in_ID_Test {

	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	private Locale locale;

	<T> void println(T print) { System.out.println(print); }

	@Before
    public void setUp() throws Exception
    {
        locale = new Locale("in", "ID");
        Locale.setDefault(locale);
    }

	@Test
	public void testLocaleISOCorrectness() 
	{
		assertEquals("in", this.locale.getLanguage());
		assertEquals("ID", this.locale.getCountry());
		assertEquals("Bahasa Indonesia", this.locale.getDisplayLanguage());
		assertEquals("Indonesia", this.locale.getDisplayCountry());
	}

	@Test
	public void testNow() {
		PrettyTime prettyTime = new PrettyTime(locale);
		prettyTime.format(new Date());
		assertEquals("dari sekarang", prettyTime.format(new Date()));
	}

	// -- Duplicate test from PrettyTimeTest.java -- //

	@Test
	public void testCeilingInterval() throws Exception {
		Date then = format.parse("5/20/2009");
		Date ref = format.parse("6/17/2009");
		PrettyTime t = new PrettyTime(ref);
		assertEquals("1 bulan yang lalu", t.format(then));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullDate() throws Exception {
		PrettyTime t = new PrettyTime();
		Date date = null;
		assertEquals("moments from now", t.format(date));
	}

	@Test
	public void testRightNow() throws Exception {
		PrettyTime t = new PrettyTime();
		assertEquals("dari sekarang", t.format(new Date()));
	}

	@Test
	public void testRightNowVariance() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("dari sekarang", t.format(new Date(600)));
	}

	@Test
	public void testMinutesFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("12 menit dari sekarang", t.format(new Date(1000 * 60 * 12)));
	}

	@Test
	public void testHoursFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 jam dari sekarang", t.format(new Date(1000 * 60 * 60 * 3)));
	}

	@Test
	public void testDaysFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 hari dari sekarang",
				t.format(new Date(1000 * 60 * 60 * 24 * 3)));
	}

	@Test
	public void testWeeksFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 minggu dari sekarang",
				t.format(new Date(1000 * 60 * 60 * 24 * 7 * 3)));
	}

	@Test
	public void testMonthsFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 bulan dari sekarang", t.format(new Date(2629743830L * 3L)));
	}

	@Test
	public void testYearsFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 tahun dari sekarang",
				t.format(new Date(2629743830L * 12L * 3L)));
	}

	@Test
	public void testDecadesFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 dekade dari sekarang",
				t.format(new Date(315569259747L * 3L)));
	}

	@Test
	public void testCenturiesFromNow() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		assertEquals("3 abad dari sekarang",
				t.format(new Date(3155692597470L * 3L)));
	}

	/*
	 * Past
	 */
	@Test
	public void testMomentsAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(6000));
		assertEquals("yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testMinutesAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(1000 * 60 * 12));
		assertEquals("12 menit yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testHoursAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 3));
		assertEquals("3 jam yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testDaysAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3));
		assertEquals("3 hari yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testWeeksAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 7 * 3));
		assertEquals("3 minggu yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testMonthsAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(2629743830L * 3L));
		assertEquals("3 bulan yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testCustomFormat() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		TimeUnit unit = new TimeUnit() {
			@Override
			public long getMaxQuantity() {
				return 0;
			}

			@Override
			public long getMillisPerUnit() {
				return 5000;
			}
		};
		t.clearUnits();
		t.registerUnit(
				unit,
				new SimpleTimeFormat().setSingularName("hitungan")
						.setPluralName("hitungan").setPattern("%n %u")
						.setRoundingTolerance(20).setFutureSuffix("... LARI!")
						.setFuturePrefix("hancur dalam: ")
						.setPastPrefix("telah hancur dalam: ")
						.setPastSuffix(""));

		assertEquals("hancur dalam: 5 hitungan ... LARI!",
				t.format(new Date(25000)));
		t.setReference(new Date(25000));
		assertEquals("telah hancur dalam: 5 hitungan", t.format(new Date(0)));
	}

	@Test
	public void testYearsAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(2629743830L * 12L * 3L));
		assertEquals("3 tahun yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testDecadesAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(315569259747L * 3L));
		assertEquals("3 dekade yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testCenturiesAgo() throws Exception {
		PrettyTime t = new PrettyTime(new Date(3155692597470L * 3L));
		assertEquals("3 abad yang lalu", t.format(new Date(0)));
	}

	@Test
	public void testWithinTwoHoursRounding() throws Exception {
		PrettyTime t = new PrettyTime();
		assertEquals("2 jam yang lalu",
				t.format(new Date(new Date().getTime() - 6543990)));
	}

	@Test
	public void testPreciseInTheFuture() throws Exception {
		PrettyTime t = new PrettyTime();
		List<Duration> durations = t.calculatePreciseDuration(new Date(
				new Date().getTime() + 1000 * (10 * 60 + 5 * 60 * 60)));
		assertTrue(durations.size() >= 2); // might be more because of
											// milliseconds between date
											// capturing and result
		// calculation
		assertEquals(5, durations.get(0).getQuantity());
		assertEquals(10, durations.get(1).getQuantity());
	}

	@Test
	public void testPreciseInThePast() throws Exception {
		PrettyTime t = new PrettyTime();
		List<Duration> durations = t.calculatePreciseDuration(new Date(
				new Date().getTime() - 1000 * (10 * 60 + 5 * 60 * 60)));
		assertTrue(durations.size() >= 2); // might be more because of
											// milliseconds between date
											// capturing and result
		// calculation
		assertEquals(-5, durations.get(0).getQuantity());
		assertEquals(-10, durations.get(1).getQuantity());
	}

	@Test
	public void testFormattingDurationListInThePast() throws Exception {
		PrettyTime t = new PrettyTime(new Date(1000 * 60 * 60 * 24 * 3 + 1000
				* 60 * 60 * 15 + 1000 * 60 * 38));
		List<Duration> durations = t.calculatePreciseDuration(new Date(0));
		assertEquals("3 hari 15 jam 38 menit yang lalu", t.format(durations));
	}

	@Test
	public void testFormattingDurationListInTheFuture() throws Exception {
		PrettyTime t = new PrettyTime(new Date(0));
		List<Duration> durations = t.calculatePreciseDuration(new Date(1000
				* 60 * 60 * 24 * 3 + 1000 * 60 * 60 * 15 + 1000 * 60 * 38));
		assertEquals("3 hari 15 jam 38 menit dari sekarang", t.format(durations));
	}

	// End of duplicate test -- //

	@After
    public void tearDown() throws Exception
    {
        Locale.setDefault(Locale.ENGLISH);
    }
}
