package jp.hacks.smartbread.ui.main.wake.calendar

import jp.hacks.smartbread.ui.main.wake.calendar.model.CalendarEvent

internal interface CalendarEventRepository {

    suspend fun sendCalendarEvent(event: CalendarEvent)
}