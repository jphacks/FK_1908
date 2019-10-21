package jp.hacks.smartbread.ui.main.wake

import jp.hacks.smartbread.CoroutinesTestRule
import jp.hacks.smartbread.ui.main.wake.toast.StartToastUsecaseImpl
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class StartToastUsecaseImplTest {

    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    @Test
    @Ignore
    fun execute() = coroutineTestRule.testDispatcher.runBlockingTest {
        val startBurnBreadUsecaseImpl =
            StartToastUsecaseImpl()
        startBurnBreadUsecaseImpl.execute()
    }
}