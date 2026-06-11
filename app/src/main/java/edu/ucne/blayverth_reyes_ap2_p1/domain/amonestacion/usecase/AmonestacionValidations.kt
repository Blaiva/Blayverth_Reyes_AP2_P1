package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase

data class ValidationResult(
    val isValid: Boolean,
    val message: String? = null
)

fun validarNombres(nombres: String): ValidationResult{
    return when {
        nombres.isBlank() -> ValidationResult(false, "El nombre es obligatorio")
        !nombres.all { it.isLetter() || it.isWhitespace() } -> ValidationResult(false, "Los nombres solo debe contener letras")
        else -> ValidationResult(true)
    }
}

fun validarRazon(razon: String): ValidationResult{
    return when{
        razon.isBlank() -> ValidationResult(false, "La razon es obligatoria")
        razon.length > 50 -> ValidationResult(false, "La razon debe ser menor a 50 caracteres")
        else -> ValidationResult(true)
    }
}

fun validarMonto(monto: String): ValidationResult{
    return when{
        monto.isBlank() -> ValidationResult(false, "El monto es obligatorio")
        monto.toDoubleOrNull() == null -> ValidationResult(false, "El monto debe ser un numero")
        monto.toDouble() <= 0 -> ValidationResult(false, "El monto debe ser mayor que 0")
        else -> ValidationResult(true)
    }
}