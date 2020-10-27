package nl.wesselbarten.newsapp.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import nl.wesselbarten.newsapp.presentation.error.ErrorHandler
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideErrorHandler(@ApplicationContext context: Context): ErrorHandler {
        return ErrorHandler(context.resources)
    }
}