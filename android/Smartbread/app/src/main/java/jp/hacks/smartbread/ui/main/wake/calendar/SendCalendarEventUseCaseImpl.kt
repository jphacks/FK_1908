package jp.hacks.smartbread.ui.main.wake.calendar

import jp.hacks.smartbread.ui.main.wake.calendar.model.CalendarEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

internal class SendCalendarEventUseCaseImpl: SendCalendarEventUseCase {
    private val repository: CalendarEventRepository = CalendarEventRepositoryImpl()

    override suspend fun execute(event: CalendarEvent) = coroutineScope {
        withContext(Dispatchers.IO){
            repository.sendCalendarEvent(event)
        }
    }
}