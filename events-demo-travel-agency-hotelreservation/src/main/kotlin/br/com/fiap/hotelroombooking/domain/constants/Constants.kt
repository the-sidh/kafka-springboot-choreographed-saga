package br.com.fiap.hotelroombooking.domain.constants

//inbound
const val START_SAGA_TOPIC = "start-saga-event"
const val CAR_RENTAL_REVERTED_TOPIC = "car-rental-revert-success-event"
const val CAR_RENTAL_FAILURE_TOPIC = "car-rental-failure-event"
//outbound
const val HOTEL_BOOKING_SUCCESS_TOPIC = "hotel-room-booking-success-event"
const val HOTEL_BOOKING_FAILURE_TOPIC = "hotel-room-booking-failure-event"
const val HOTEL_BOOKING_REVERT_SUCCESS_TOPIC = "hotel-room-booking-revert-success-event"