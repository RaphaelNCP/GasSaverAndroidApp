package br.com.project.gassaver.data.repository

import br.com.project.gassaver.data.model.RoutesTakenModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RoutesTakenRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("routesTaken")

    suspend fun addRouteTaken(routeTaken: RoutesTakenModel) {
        collection.add(routeTaken)
            .await()
    }

    // Obter todos os registros
    suspend fun getRouteTaken(): List<RoutesTakenModel> {
        val snapshot = collection.get().await()
        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(RoutesTakenModel::class.java)?.copy(id = doc.id)
        }
    }

    // Atualizar um registro
    suspend fun updateRouteTaken(id: String, updatedRecord: RoutesTakenModel) {
        collection.document(id).set(updatedRecord).await()
    }

    // Remover um registro
    suspend fun deleteRouteTaken(id: String) {
        collection.document(id).delete().await()
    }
}