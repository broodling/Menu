package com.android.data.di

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.android.data.storage.UserPreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val MENU_DATA_STORE_PREFERENCES = "menu_data_store_preferences"

val dataStoreModule = module {

    includes(dispatchersKoinModule)

    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = get(),
            migrations = listOf(
                SharedPreferencesMigration(
                    androidContext(),
                    MENU_DATA_STORE_PREFERENCES
                )
            ),
            scope = CoroutineScope(get<CoroutineDispatcher>() + SupervisorJob()),
            produceFile = { get() }
        )
    }
    single {
        UserPreferencesRepository(get())
    }

    factory {
        ReplaceFileCorruptionHandler(

            // replace with empty prefs if serializer cannot serialize bcs the data is corrupted
            produceNewData = { emptyPreferences() }
        )
    }

    factory {
        androidContext().preferencesDataStoreFile(MENU_DATA_STORE_PREFERENCES)
    }
}