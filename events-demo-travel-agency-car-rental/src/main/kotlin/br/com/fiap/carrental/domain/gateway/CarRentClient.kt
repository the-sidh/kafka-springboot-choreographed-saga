package br.com.fiap.carrental.domain.gateway

interface CarRentClient {
    fun rentCar(): Boolean
}