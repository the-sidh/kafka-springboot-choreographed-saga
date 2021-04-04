package br.com.fiap.hotelroombooking.application.rest.dto

import br.com.fiap.hotelroombooking.domain.entities.customerSampler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CustomerDTOTest {


    @Test
    fun `given a DTO object, should create a domain object`() {
        val dto = dtoSampler()
        val createdObject = HotelRoomBookingDTO.toDomainObjet(dto)
        Assertions.assertEquals(dto.customerId, createdObject.customerId)
    }
}

fun dtoSampler(): HotelRoomBookingDTO {
    val entity = customerSampler()
    return HotelRoomBookingDTO(
        customerId = entity.customerId
    )
}
