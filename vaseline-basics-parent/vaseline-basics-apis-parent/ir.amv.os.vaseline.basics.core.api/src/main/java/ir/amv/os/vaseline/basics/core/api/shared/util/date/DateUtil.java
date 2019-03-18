package ir.amv.os.vaseline.basics.core.api.shared.util.date;

import org.joda.time.Days;
import org.joda.time.Instant;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static IBaseDateProvider baseDateProvider;

	public static int subtractInDays(Date startDate, Date endDate) {
		Days daysBetween = Days.daysBetween(new Instant(startDate.getTime()), new Instant(endDate.getTime()));
		return daysBetween.getDays();
	}
	
	public static Date getDayStart(Date date) {
		Calendar calendar = convertDateToCalendar(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	private static Calendar convertDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date getDayEnd(Date date) {
		Calendar calendar = convertDateToCalendar(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date addDays(Date date, int days) {
		return addCalendarItem(date, Calendar.DAY_OF_YEAR, days);
	}

	private static Date addCalendarItem(Date date, int calendarItem, int amount) {
		Calendar calendar = convertDateToCalendar(date);
		calendar.add(calendarItem, amount);
		return calendar.getTime();
	}
	
	public static Date addYears(Date date, int years) {
		return addCalendarItem(date, Calendar.YEAR, years);
	}

	public static Date newDate(Long time) {
		return new Date(time);
	}

	public static Date newDate() {
		if (baseDateProvider == null) {
			return new Date();
		}
		return baseDateProvider.getCurrentDate();
	}

	public static void setBaseDateProvider(IBaseDateProvider baseDateProvider) {
		DateUtil.baseDateProvider = baseDateProvider;
	}

//	DEPRECATED replace with locale-sensitive methods
// /* Jalali Calendar methods*/
//
//    /**
//     * returns first day of next month even if the date is the first day of a month
//     * @param date
//     * @return
//     */
//    public static Date getFirstDayOfNextMonthShamsi(Date date) {
//        JalaliCalendar jalaliCalendar = newJalaliCalendar(date);
//        int year = jalaliCalendar.getYear();
//        int month = jalaliCalendar.getMonth();
//        month = (month + 1) % 13;
//        if (month == 0) {
//            year++;
//            month = 1;
//        }
//        return newDate(new JalaliCalendar(year, month, 1));
//    }
//
//	public static Date newDate(JalaliCalendar jalaliCalendar) {
//		return jalaliCalendar.toJavaUtilGregorianCalendar().getTime();
//	}
//
//	public static JalaliCalendar newJalaliCalendar(Date date) {
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.setTime(date);
//		JalaliCalendar result = new JalaliCalendar(calendar);
//		return result;
//	}
//
//	public static JalaliCalendar newJalaliCalendar() {
//		return newJalaliCalendar(newDate());
//	}
//
//	public static JalaliCalendar newJalaliCalendar(String startDateShamsi) {
//		String[] split = startDateShamsi.split("-");
//		return new JalaliCalendar(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
//	}
//
//	public static String toString(JalaliCalendar newJalaliCalendar) {
//		return newJalaliCalendar.toString();
//	}
}
