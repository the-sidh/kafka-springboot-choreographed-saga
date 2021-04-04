package br.com.fiap.hotelroombooking.application.rest.dto

import br.com.fiap.hotelroombooking.domain.entities.Customer

data class HotelRoomBookingDTO(
    val customerId: String?
) {

    companion object {
        fun toDomainObjet(dto: HotelRoomBookingDTO) = Customer(
            customerId = dto.customerId!!
        )
    }
}
