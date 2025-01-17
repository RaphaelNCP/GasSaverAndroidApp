package br.com.project.gassaver.data.model

data class RoutesTakenModel(
    val id: String = "",
    val routeName: String = "",
    val distance: String = "",
    val fuelConsumption: String = "",
    val fuelPrice: String = "",
    val fuelConsumptionResult: String = "",
    val fuelType: String = "",
) {
    constructor() : this("", "", "", "", "", "", "")
}
