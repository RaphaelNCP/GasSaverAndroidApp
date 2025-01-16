package br.com.project.gassaver.domain.usecase

import br.com.project.gassaver.data.model.RoutesTakenModel
import br.com.project.gassaver.data.repository.RoutesTakenRepository

class SaveRouteTakenUseCase(private val repository: RoutesTakenRepository) {
    suspend operator fun invoke(fuelRecord: RoutesTakenModel) {
        repository.addRouteTaken(fuelRecord)
    }
}