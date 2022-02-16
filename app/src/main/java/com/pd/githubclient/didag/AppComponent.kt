import com.pd.githubclient.didag.AppModule
import com.pd.githubclient.ui.detail.DetailsFragment
import com.pd.githubclient.ui.main.MainFragment
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMain(mainFragment: MainFragment)
    fun injectDetails(detailsFragment: DetailsFragment)

}