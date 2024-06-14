import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.configurui.configurablebutton.JsonRepository
import com.configurui.configurablebutton.MainViewModel
import com.configurui.configurablebutton.UIComponentConfig
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ramprasad on 6/14/24.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val jsonRepository: JsonRepository = mockk(relaxed = true)

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    // Subject under test
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(jsonRepository)
    }

    @After
    fun tearDown() {
        // Cleanup
        testScope.cleanupTestCoroutines()
    }

    /**
     * Test fetch config from json error
     *
     */
    @Test
    fun `test fetchConfigFromJson error`() = testScope.runBlockingTest {
        // Given
        val jsonString = null
        val expectedDefaultConfig = UIComponentConfig.EmptyConfig
        every {
            jsonRepository.readJsonFromAssets(any())
        } returns jsonString
        // When
        viewModel.fetchConfigFromJson(jsonString)

        // Then
        val actualConfig = viewModel.configFlow.value
        Assert.assertEquals(expectedDefaultConfig, actualConfig)
    }
}
