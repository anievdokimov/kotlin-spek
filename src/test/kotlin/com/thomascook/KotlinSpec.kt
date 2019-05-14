package com.thomascook

import com.thomascook.dao.BookingRq
import com.thomascook.dao.ExpediaApiClient
import com.thomascook.dao.hotelItineraryNumbers
import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class KotlinSpec : Spek({

    data class TestData(val bookingType: String, val payload: BookingRq)

    describe("it should post a live event") {
        listOf(
            TestData("hotel booking", BookingRq.hotelBooking()),
            TestData("package booking", BookingRq.packageBooking())
        ).forEach { testData ->
            describe("Send a request to BHO Expedia API to post a live event for ${testData.bookingType}") {
                val apiClient = ExpediaApiClient.default()
                val bookingRs = apiClient.postLiveEvent(body = testData.payload)
                it("Receive successful response for ${testData.bookingType}") {
                    bookingRs.should.not.be.`null`
                }
            }
        }
    }
})
