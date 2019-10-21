package jp.hacks.smartbread.ui.main.wake.calendar

import jp.hacks.smartbread.ui.main.wake.calendar.model.CalendarEvent

internal interface SendCalendarEventUseCase {
    suspend fun execute(event: CalendarEvent)
}