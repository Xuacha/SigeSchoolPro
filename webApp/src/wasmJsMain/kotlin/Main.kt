import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.sigeschool.App
import com.sigeschool.di.initKoin
import com.sigeschool.di.uiModule
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(additionalModules = listOf(uiModule))
    ComposeViewport(document.body!!) {
        App()
    }
}
