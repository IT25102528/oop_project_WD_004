package com.project001.Regency.repository;


import com.project001.Regency.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    // 🔥 Check overlapping bookings
    @Query("SELECT b FROM Booking b WHERE b.roomId = :roomId " +
            "AND (:checkIn < b.checkOutDate AND :checkOut > b.checkInDate)")
    List<Booking> findConflictingBookings(Long roomId, LocalDate checkIn, LocalDate checkOut);
}
