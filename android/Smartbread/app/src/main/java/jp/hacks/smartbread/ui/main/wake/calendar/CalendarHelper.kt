package jp.hacks.smartbread.ui.main.wake.calendar

import android.content.Context
import android.icu.util.Calendar
import android.text.format.DateFormat
import jp.hacks.smartbread.ui.main.wake.calendar.model.CalendarEvent
import me.everything.providers.android.calendar.CalendarProvider
import me.everything.providers.android.calendar.Event
import java.sql.Date


object CalendarHelper {

    private fun yesterdayTime(): Long {
        val date = Date(System.currentTimeMillis()).time
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1)
        // 0時以降は予定は入らない。
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        return calendar.timeInMillis
    }

    private fun yesterdayEndTime(): Long {
        val date = Date(System.currentTimeMillis()).time
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1)
        // 12時までが朝だよ！
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        return calendar.timeInMillis
    }

    private fun Event?.convertCalendarEvent(): CalendarEvent =
        if (this == null) CalendarEvent()
        else CalendarEvent(
            startTime = dTStart,
            date = dTStart.formatToString(),
            location = eventLocation
        )

    private fun Long.formatToString() =
        DateFormat.format("yyyy/MM/dd", this).toString()

    fun setupCalender(context: Context): CalendarEvent {
        val calendarProvider = CalendarProvider(context)
        val calenders = calendarProvider.calendars.list
        val calender = calenders.first()

        var minDiff: Long = 99999999999
        val startYesterday = yesterdayTime()
        val endYesterday = yesterdayEndTime()
        var event: Event? = null
        calenders.forEach { calender ->

            val events = calendarProvider.getEvents(calender.id).list.map {
                if ((it.dTStart > startYesterday && it.dTStart < (startYesterday + minDiff) && it.dTStart < endYesterday)) {
                    minDiff = it.dTStart - startYesterday
                    event = it
                }
            }
        }
        return event.convertCalendarEvent()
    }
}