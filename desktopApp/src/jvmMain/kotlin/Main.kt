import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sigeschool.App
import com.sigeschool.di.initKoin
import com.sigeschool.di.uiModule
import com.sigeschool.local.di.localModule
import com.sigeschool.local.di.databaseModule

fun main() {
    initKoin(
        additionalModules = listOf(
            uiModule, 
            localModule, 
            databaseModule(), 
            com.sigeschool.local.di.repositoryImplementationModule,
            com.sigeschool.di.desktopPlatformModule
        )
    )
    application {
        Window(onCloseRequest = ::exitApplication, title = "SigeSchool Pro") {
            App()
        }
    }
}
