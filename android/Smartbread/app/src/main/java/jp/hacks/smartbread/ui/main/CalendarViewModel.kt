package jp.hacks.smartbread.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.hacks.smartbread.ui.main.wake.calendar.CalendarHelper
import jp.hacks.smartbread.ui.main.wake.calendar.SendCalendarEventUseCase
import jp.hacks.smartbread.ui.main.wake.calendar.SendCalendarEventUseCaseImpl
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {

    private val useCase: SendCalendarEventUseCase = SendCalendarEventUseCaseImpl()

    fun sendCalendarEvent(context: Context) {
        try {
            viewModelScope.launch {
                useCase.execute(CalendarHelper.setupCalender(context))
            }
        }catch (e: Exception){
            // Error
        }
    }
}