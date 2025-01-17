package br.com.project.gassaver.domain.usecase

import br.com.project.gassaver.data.model.VehicleRegisterModel
import br.com.project.gassaver.data.repository.VehicleRegisterRepository

class GetVehicleUseCase(
    private val vehicleRegisterRepository: VehicleRegisterRepository
) {
    suspend operator fun invoke(): List<VehicleRegisterModel> {
        return vehicleRegisterRepository.getVehicleRegister()
    }
}