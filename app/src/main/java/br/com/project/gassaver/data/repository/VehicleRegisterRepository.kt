package br.com.project.gassaver.data.repository

import br.com.project.gassaver.data.model.VehicleRegisterModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class VehicleRegisterRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("vehicleRegister")

    suspend fun addVehicleRegister(vehicleRegister: VehicleRegisterModel) {
        collection.add(vehicleRegister)
            .await()
    }

    // Obter todos os registros
    suspend fun getVehicleRegister(): List<VehicleRegisterModel> {
        val snapshot = collection.get().await()
        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(VehicleRegisterModel::class.java)?.copy(id = doc.id)
        }
    }

    // Atualizar um registro
    suspend fun updateVehicleRegister(id: String, updatedRecord: VehicleRegisterModel) {
        collection.document(id).set(updatedRecord).await()
    }

    // Remover um registro
    suspend fun deleteVehicleRegister(id: String) {
        collection.document(id).delete().await()
    }

}