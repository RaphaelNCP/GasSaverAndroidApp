package br.com.project.gassaver.domain.usecase

import br.com.project.gassaver.data.model.RoutesTakenModel
import br.com.project.gassaver.data.repository.RoutesTakenRepository

class GetRouteTakenUseCase(private val repository: RoutesTakenRepository) {
    suspend operator fun invoke(): List<RoutesTakenModel> {
        return repository.getRouteTaken()
    }
}