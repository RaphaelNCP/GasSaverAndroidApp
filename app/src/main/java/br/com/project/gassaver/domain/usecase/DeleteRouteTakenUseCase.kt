package br.com.project.gassaver.domain.usecase

import br.com.project.gassaver.data.model.RoutesTakenModel
import br.com.project.gassaver.data.repository.RoutesTakenRepository

class DeleteRouteTakenUseCase(private val repository: RoutesTakenRepository) {
    suspend operator fun invoke(routeTakenId: String) {
        repository.deleteRouteTaken(routeTakenId)
    }
}