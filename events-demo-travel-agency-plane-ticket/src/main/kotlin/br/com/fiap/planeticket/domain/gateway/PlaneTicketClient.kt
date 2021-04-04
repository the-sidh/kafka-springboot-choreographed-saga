package br.com.fiap.planeticket.domain.gateway

interface PlaneTicketClient {
    fun buyPlaneTicket(): Boolean
}