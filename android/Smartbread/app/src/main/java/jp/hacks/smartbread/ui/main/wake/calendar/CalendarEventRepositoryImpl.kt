package jp.hacks.smartbread.ui.main.wake.calendar

import com.github.kittinunf.fuel.Fuel
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import jp.hacks.smartbread.ui.main.wake.calendar.model.CalendarEvent

internal class CalendarEventRepositoryImpl : CalendarEventRepository {

    private val requestAdapter by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(CalendarEvent::class.java)
    }

    override suspend fun sendCalendarEvent(event: CalendarEvent) {
        Fuel.post("https://ch84nlat8b.execute-api.us-east-1.amazonaws.com/v1/calendar")
            .body(requestAdapter.toJson(event)).responseString()
    }
}