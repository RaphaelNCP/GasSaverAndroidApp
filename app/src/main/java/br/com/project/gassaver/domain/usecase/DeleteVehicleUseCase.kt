package br.com.project.gassaver.domain.usecase

import br.com.project.gassaver.data.repository.VehicleRegisterRepository

class DeleteVehicleUseCase(
    private val vehicleRegisterRepository: VehicleRegisterRepository
) {
    suspend operator fun invoke(vehicleId: String) {
        vehicleRegisterRepository.deleteVehicleRegister(vehicleId)
    }
}