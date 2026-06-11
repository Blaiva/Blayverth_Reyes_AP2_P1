package edu.ucne.blayverth_reyes_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.repository.AmonestacionRepositoryImpl
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindChequeRepository(
        impl: AmonestacionRepositoryImpl
    ): AmonestacionRepository
}