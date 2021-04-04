package br.com.fiap.hotelroombooking.domain.gateway

interface HotelBookingClient {
    fun bookRoom(): Boolean
}