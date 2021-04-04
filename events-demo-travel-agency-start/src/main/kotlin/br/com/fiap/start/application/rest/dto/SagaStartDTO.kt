package br.com.fiap.start.application.rest.dto

import br.com.fiap.start.domain.entities.Customer

data class SagaStartDTO(
    val customerId: String?
) {

    companion object {
        fun toDomainObjet(dto: SagaStartDTO) = Customer(
            customerId = dto.customerId!!
        )
    }
}
