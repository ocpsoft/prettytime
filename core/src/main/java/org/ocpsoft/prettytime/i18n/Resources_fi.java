package org.ocpsoft.prettytime.i18n;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;
import org.ocpsoft.prettytime.impl.TimeFormatProvider;
import org.ocpsoft.prettytime.units.Day;

public class Resources_fi extends ListResourceBundle implements TimeFormatProvider {

	private static Object[][] CONTENTS =  new Object[][] {
		{"JustNowPattern", "%u"},
		{"JustNowPastName", "hetki"},
		{"JustNowFutureName", "hetken"},
		{"JustNowPastSuffix", "sitten"},
		{"JustNowFutureSuffix", "päästä"},
		{"MillisecondPattern", "%u"},
		{"MillisecondPluralPattern", "%n %u"},
		{"MillisecondPastName", "millisekunti"},
		{"MillisecondPastPluralName", "millisekuntia"},
		{"MillisecondFutureName", "millisekunnin"},
		{"MillisecondPastSuffix", "sitten"},
		{"MillisecondFutureSuffix", "päästä"},
		{"SecondPattern", "%u"},
		{"SecondPluralPattern", "%n %u"},
		{"SecondPastName", "sekunti"},
		{"SecondPastPluralName", "sekuntia"},
		{"SecondFutureName", "sekunnin"},
		{"SecondPastSuffix", "sitten"},
		{"SecondFutureSuffix", "päästä"},
		{"MinutePattern", "%u"},
		{"MinutePluralPattern", "%n %u"},
		{"MinutePastName", "minuutti"},
		{"MinutePastPluralName", "minuuttia"},
		{"MinuteFutureName", "minuutin"},
		{"MinutePastSuffix", "sitten"},
		{"MinuteFutureSuffix", "päästä"},
		{"HourPattern", "%u"},
		{"HourPluralPattern", "%n %u"},
		{"HourPastName", "tunti"},
		{"HourPastPluralName", "tuntia"},
		{"HourFutureName", "tunnin"},
		{"HourPastSuffix", "sitten"},
		{"HourFutureSuffix", "päästä"},
		{"DayPattern", "%u"},
		{"DayPluralPattern", "%n %u"},
		{"DayPastName", "eilen"},
		{"DayPastPluralName", "päivää"},
		{"DayFutureName", "huomenna"},
		{"DayFuturePluralName", "päivän"},
		{"DayPastSuffix", "sitten"},
		{"DayFutureSuffix", "päästä"},
		{"WeekPattern", "%u"},
		{"WeekPluralPattern", "%n %u"},
		{"WeekPastName", "viikko"},
		{"WeekPastPluralName", "viikkoa"},
		{"WeekFutureName", "viikon"},
		{"WeekFuturePluralName", "viikon"},
		{"WeekPastSuffix", "sitten"},
		{"WeekFutureSuffix", "päästä"},
		{"MonthPattern", "%u"},
		{"MonthPluralPattern", "%n %u"},
		{"MonthPastName", "kuukausi"},
		{"MonthPastPluralName", "kuukautta"},
		{"MonthFutureName", "kuukauden"},
		{"MonthPastSuffix", "sitten"},
		{"MonthFutureSuffix", "päästä"},
		{"YearPattern", "%u"},
		{"YearPluralPattern", "%n %u"},
		{"YearPastName", "vuosi"},
		{"YearPastPluralName", "vuotta"},
		{"YearFutureName", "vuoden"},
		{"YearPastSuffix", "sitten"},
		{"YearFutureSuffix", "päästä"},
		{"DecadePattern", "%u"},
		{"DecadePluralPattern", "%n %u"},
		{"DecadePastName", "vuosikymmen"},
		{"DecadePastPluralName", "vuosikymmentä"},
		{"DecadeFutureName", "vuosikymmenen"},
		{"DecadePastSuffix", "sitten"},
		{"DecadeFutureSuffix", "päästä"},
		{"CenturyPattern", "%u"},
		{"CenturyPluralPattern", "%n %u"},
		{"CenturyPastName", "vuosisata"},
		{"CenturyPastPluralName", "vuosisataa"},
		{"CenturyFutureName", "vuosisadan"},
		{"CenturyPastSuffix", "sitten"},
		{"CenturyFutureSuffix", "päästä"},
		{"MillenniumPattern", "%u"},
		{"MillenniumPluralPattern", "%n %u"},
		{"MillenniumPastName", "vuosituhat"},
		{"MillenniumPastPluralName", "vuosituhatta"},
		{"MillenniumFutureName", "vuosituhannen"},
		{"MillenniumPastSuffix", "sitten"},
		{"MillenniumFutureSuffix", "päästä"},
	};
	private volatile ConcurrentMap<TimeUnit, TimeFormat> formatMap = new ConcurrentHashMap<TimeUnit, TimeFormat>();

	public Resources_fi() {
	}
	
	@Override
	public TimeFormat getFormatFor(TimeUnit t) {
		if(!formatMap.containsKey(t)) {
			formatMap.putIfAbsent(t, new FiTimeFormat(this, t));
		}
		return formatMap.get(t);
	}

	@Override
	protected Object[][] getContents() {
		return CONTENTS;
	}
	
	

	private static class FiTimeFormat extends SimpleTimeFormat {
		private final ResourceBundle bundle;
		private String pastName = "";
		private String futureName = "";
		private String pastPluralName = "";
		private String futurePluralName = "";
		private String pluralPattern = "";


		public FiTimeFormat(final ResourceBundle rb, final TimeUnit unit) {
			super();
			this.bundle = rb;
		
			if(bundle.containsKey(getUnitName(unit) + "PastName")) {
				this.setPastName(bundle.getString(getUnitName(unit) + "PastName"))
				.setFutureName(bundle.getString(getUnitName(unit) + "FutureName"))
				.setPastPluralName(bundle.getString(getUnitName(unit) + "PastName"))
				.setFuturePluralName(bundle.getString(getUnitName(unit) + "FutureName"))
				.setPluralPattern(bundle.getString(getUnitName(unit) + "Pattern"));

				if(bundle.containsKey(getUnitName(unit) + "PastPluralName")) {
					this.setPastPluralName(bundle.getString(getUnitName(unit) + "PastPluralName"));
				}

				if(bundle.containsKey(getUnitName(unit) + "FuturePluralName")) {
					this.setFuturePluralName(bundle.getString(getUnitName(unit) + "FuturePluralName"));
				}

				if(bundle.containsKey(getUnitName(unit) + "PluralPattern")) {
					this.setPluralPattern(bundle.getString(getUnitName(unit) + "PluralPattern"));
				}

				this.setPattern(bundle.getString(getUnitName(unit) + "Pattern"))
				.setPastSuffix(bundle.getString(getUnitName(unit) + "PastSuffix"))
				.setFutureSuffix(bundle.getString(getUnitName(unit) + "FutureSuffix"))
				.setFuturePrefix("")
				.setPastPrefix("")
				.setName("")
				.setPluralName("");
			}
		}

		public String getPastName() {
			return pastName;
		}

		public String getFutureName() {
			return futureName;
		}

		public String getPastPluralName() {
			return pastPluralName;
		}

		public String getFuturePluralName() {
			return futurePluralName;
		}

		public String getPluralPattern() {
			return pluralPattern;
		}

		public FiTimeFormat setPastName(String pastName) {
			this.pastName = pastName;
			return this;
		}

		public FiTimeFormat setFutureName(String futureName) {
			this.futureName = futureName;
			return this;
		}

		public FiTimeFormat setPastPluralName(String pastName) {
			this.pastPluralName = pastName;
			return this;
		}

		public FiTimeFormat setFuturePluralName(String futureName) {
			this.futurePluralName = futureName;
			return this;
		}

		public FiTimeFormat setPluralPattern(String pattern) {
			this.pluralPattern = pattern;
			return this;
		}

		@Override
		protected String getGramaticallyCorrectName(Duration d, boolean round) {
			String result = d.isInPast() ? getPastName() : getFutureName();
			if ((Math.abs(getQuantity(d, round)) == 0) || (Math.abs(getQuantity(d, round)) > 1))
			{
				result = d.isInPast() ? getPastPluralName() : getFuturePluralName();
			}
			return result;
		}

		@Override
		protected String getPattern(long quantity) {
			if(Math.abs(quantity) == 1) {
				return getPattern();
			}
			return getPluralPattern();

		}

		@Override
		public String decorate(Duration duration, String time) {
			String result ="";
			if(duration.getUnit() instanceof Day && Math.abs(duration.getQuantity()) == 1) {
				result = time;
			} else {
				result = super.decorate(duration, time);
			}
			return result;
		}

		private String getUnitName(TimeUnit unit) {
			return unit.getClass().getSimpleName();
		}

	}



}
