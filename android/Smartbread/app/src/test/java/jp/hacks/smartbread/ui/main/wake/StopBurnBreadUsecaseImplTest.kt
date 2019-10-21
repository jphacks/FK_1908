package jp.hacks.smartbread.ui.main.wake

import jp.hacks.smartbread.CoroutinesTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class StopBurnBreadUsecaseImplTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Test
    @Ignore
    fun execute() = coroutineTestRule.testDispatcher.runBlockingTest {
        val stopBurnBreadusecase = StopBurnBreadUsecaseImpl()
        stopBurnBreadusecase.execute()
    }
}