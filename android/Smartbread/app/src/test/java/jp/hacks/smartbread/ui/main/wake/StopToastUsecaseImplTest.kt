package jp.hacks.smartbread.ui.main.wake

import jp.hacks.smartbread.CoroutinesTestRule
import jp.hacks.smartbread.ui.main.wake.toast.StopToastUsecaseImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class StopToastUsecaseImplTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Test
    @Ignore
    fun execute() = coroutineTestRule.testDispatcher.runBlockingTest {
        val stopBurnBreadusecase = StopToastUsecaseImpl()
        stopBurnBreadusecase.execute()
    }
}