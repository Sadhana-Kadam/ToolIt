package com.floatinity.toolIt.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.floatinity.toolIt.constants.ToolItConstants;
import com.floatinity.toolIt.exceptions.ToolItError;
import com.floatinity.toolIt.exceptions.ToolItException;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class DateUtil {

	private static final Logger LOGGER = LogManager.getLogger(DateUtil.class);

	public static final String UTC_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String STRING_UTC_TO_DATE_FORMAT = "dd/MM/yyyy HH:mm";

	public static final String UTC_DATE_FORMAT_IN_MS = "yyyy-MM-dd HH:mm:ss:SSS";

	public static final String UI_DATE_FORMAT = "MMM dd,yyyy hh:mm:ss aaa";

	public static final String UI_DATE_ONLY_FORMAT = "MMM dd,yyyy";

	public static final String CURRENT_SYSTEM_DATE_TIME = "dd/MM/yyyy HH:mm";

	public static final String UTC = "UTC";

	public static final String GMT = "GMT";

	public static final String IST = "IST";

	public static final String DATE_ONLY_FORMAT = "dd-MMM-YY";

	public static final String FILE_TIMESTAMP = "MM_dd_yyyy_hh_mm_ss_aaa";

	public static String getCurrentSystemDateTime() {
		SimpleDateFormat fmt = new SimpleDateFormat(CURRENT_SYSTEM_DATE_TIME);
		fmt.setTimeZone(TimeZone.getTimeZone(IST));
		return fmt.format(Calendar.getInstance().getTime());
	}

	public static String getCurrentDateOnly() {
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_ONLY_FORMAT);
		fmt.setTimeZone(TimeZone.getTimeZone(IST));
		return fmt.format(Calendar.getInstance().getTime());
	}

	public static String getCurrentUTCDateAsString() {
		SimpleDateFormat fmt = new SimpleDateFormat(UTC_DATE_FORMAT);
		fmt.setTimeZone(TimeZone.getTimeZone(UTC));
		return fmt.format(new Date());
	}

	public static String getCurrentUTCDateAsString(String dateFormat) {
		SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
		fmt.setTimeZone(TimeZone.getTimeZone(UTC));
		return fmt.format(new Date());
	}

	public static String getCurrentUTCDateForToken() {
		return getCurrentUTCDateAsString(UTC_DATE_FORMAT_IN_MS);
	}

	public static Date parseUTCDate(String utcDateAsString) throws ToolItException {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(UTC_DATE_FORMAT);
			fmt.setTimeZone(TimeZone.getTimeZone(UTC));
			return fmt.parse(utcDateAsString);
		} catch (ParseException e) {
			throw new ToolItException(ToolItError.TI_APPLICATION_ERROR);
		}
	}

	public static Date parseStringToISTDate(String utcDateAsString) throws ToolItException {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(STRING_UTC_TO_DATE_FORMAT);
			fmt.setTimeZone(TimeZone.getTimeZone(IST));
			return fmt.parse(utcDateAsString);
		} catch (ParseException e) {
			throw new ToolItException(ToolItError.TI_APPLICATION_ERROR);
		}
	}

	public static Date parseUTCDate(String utcDateAsString, String dateFormat) throws ToolItException {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
			fmt.setTimeZone(TimeZone.getTimeZone(UTC));
			return fmt.parse(utcDateAsString);
		} catch (ParseException e) {
			throw new ToolItException(ToolItError.TI_APPLICATION_ERROR);
		}
	}

	public static java.sql.Timestamp getUTCTimestamp() {
		return new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public static java.sql.Timestamp getUTCTimestampForGivenDate(long date) {
		return new java.sql.Timestamp(date);
	}

	public static java.sql.Timestamp getUTCTimeStampForNoOfDays(int noOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, noOfDays);
		return new java.sql.Timestamp(cal.getTimeInMillis());
	}

	public static String formatUTCMillisecInDate(long millisec) {
		Date date = new Date(millisec);
		return new SimpleDateFormat(UI_DATE_FORMAT).format(date);
	}

	public static boolean isTimestampInGivenSpan(String tokenTimestamp, int minutes) throws ToolItException {
		long currentTimestampInMilli = new Date().getTime();
		LOGGER.info("Current time stamp in mili is - " + currentTimestampInMilli);
		long tokenTimestampInMilli = parseUTCDate(tokenTimestamp).getTime();
		long minutesInMilli = minutes * 60 * 1000;
		if (currentTimestampInMilli > tokenTimestampInMilli
				&& (currentTimestampInMilli - tokenTimestampInMilli) <= minutesInMilli) {
			return true;
		}
		return false;
	}

	public static String convertToFormattedLocalTime(Timestamp serverTimestamp, long timeDiffInMinutes) {
		if (null == serverTimestamp) {
			return ToolItConstants.BLANK_STRING;
		}
		long localTimeInMilli = serverTimestamp.getTime() - (timeDiffInMinutes * 60 * 1000);

		Timestamp ts = new Timestamp(localTimeInMilli);

		SimpleDateFormat formatter = new SimpleDateFormat(UI_DATE_FORMAT);

		return formatter.format(ts);
	}

	public static long getCurrentUTCTimestamp() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static java.sql.Timestamp getTodaysEndTimestamp() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		return new java.sql.Timestamp(date.getTimeInMillis());
	}

	public static java.sql.Timestamp getThirtyMinuteTime() {
		Date date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30));
		return new java.sql.Timestamp(date.getTime());
	}

	public static String convertToFormattedLocalTime(long timeInMilliSeconds, long timeDiffInMinutes) {
		if (0 == timeInMilliSeconds) {
			return ToolItConstants.BLANK_STRING;
		}
		long localTimeInMilli = timeInMilliSeconds - (timeDiffInMinutes * 60 * 1000);
		Timestamp ts = new Timestamp(localTimeInMilli);
		SimpleDateFormat formatter = new SimpleDateFormat(UI_DATE_FORMAT);
		return formatter.format(ts);
	}

	public static boolean isTimestampInGivenSpan(long tokenTimestamp, int minutes) {
		long currentTimestampInMilli = new Date().getTime();
		long minutesInMilli = minutes * 60 * 1000;
		if (currentTimestampInMilli > tokenTimestamp && (currentTimestampInMilli - tokenTimestamp) <= minutesInMilli) {
			return true;
		}
		return false;
	}

	public static java.sql.Timestamp getTimeStampAfterMinutes(int minutes) {
		long currentTimestampInMilli = new Date().getTime();
		long minutesInMilli = minutes * 60 * 1000;
		return new java.sql.Timestamp(currentTimestampInMilli + minutesInMilli);
	}

	public static java.sql.Timestamp getTimeStampFromGivenTime(Timestamp timestamp, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.MINUTE, minutes);
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp getEndTimeStamp(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.HOUR_OF_DAY, 23);
		calender.set(Calendar.MINUTE, 59);
		calender.set(Calendar.SECOND, 00);
		return new Timestamp(calender.getTimeInMillis());
	}

	public static Timestamp getStartTimeStamp(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.HOUR_OF_DAY, 00);
		calender.set(Calendar.MINUTE, 00);
		calender.set(Calendar.SECOND, 00);
		return new Timestamp(calender.getTimeInMillis());
	}

	public static java.sql.Timestamp parseStringToTimeStamp(String inputDate) throws ToolItException {
		try {
			return new java.sql.Timestamp(parseStringToISTDate(inputDate).getTime());
		} catch (ToolItException e) {
			throw new ToolItException(ToolItError.TI_APPLICATION_ERROR);
		}
	}

	public static long getNoOfDaysBetweenDates(java.sql.Timestamp tsBefore, java.sql.Timestamp tsAfter) {
		Date dateBefore = new Date(tsBefore.getTime());
		Date dateAfter = new Date(tsAfter.getTime());
		long diffInMillies = Math.abs(dateAfter.getTime() - dateBefore.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

	public static java.sql.Timestamp getDaysAddedTimeStamp(Timestamp timestamp, long days) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.DATE, (int) days);
		return new Timestamp(cal.getTime().getTime());
	}

	public static String getMailDate(Timestamp timeStamp) {
		SimpleDateFormat fmt = new SimpleDateFormat(UI_DATE_ONLY_FORMAT);
		fmt.setTimeZone(TimeZone.getTimeZone(IST));
		return fmt.format(new Date(timeStamp.getTime()));
	}

}