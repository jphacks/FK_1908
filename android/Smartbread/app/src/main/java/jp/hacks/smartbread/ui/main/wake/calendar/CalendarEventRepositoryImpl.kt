package jp.hacks.smartbread.ui.main.wake.calendar

import com.github.kittinunf.fuel.Fuel
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import jp.hacks.smartbread.ui.main.wake.calendar.model.CalendarEvent

internal object CalendarEventRepositoryImpl: CalendarEventRepository {

    private val requestAdapter by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(CalendarEvent::class.java)
    }

    override suspend fun sendCalendarEvent(event: CalendarEvent){
        Fuel.post("http://example.com/sample/api").body(requestAdapter.toJson(event)).responseString()
    }
}