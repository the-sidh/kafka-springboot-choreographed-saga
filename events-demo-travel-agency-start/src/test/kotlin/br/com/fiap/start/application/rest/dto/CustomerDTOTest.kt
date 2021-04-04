package br.com.fiap.start.application.rest.dto

import br.com.fiap.start.domain.entities.sagaStartSample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CustomerDTOTest {


    @Test
    fun `given a DTO object, should create a domain object`() {
        val dto = dtoSampler()
        val createdObject = SagaStartDTO.toDomainObjet(dto)
        Assertions.assertEquals(dto.customerId, createdObject.customerId)
    }
}

fun dtoSampler(): SagaStartDTO {
    val entity = sagaStartSample()
    return SagaStartDTO(
        customerId = entity.customerId
    )
}
