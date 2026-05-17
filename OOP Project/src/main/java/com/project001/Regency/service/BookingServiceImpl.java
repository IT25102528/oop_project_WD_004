package com.project001.Regency.service;

import com.project001.Regency.model.Booking;
import com.project001.Regency.model.Room;
import com.project001.Regency.repository.BookingRepo;
import com.project001.Regency.repository.RoomsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingServiceImpl{

    private final BookingRepo bookingRepo;
    private final RoomsRepo roomsRepo;   // ✅ ADD THIS

    // ✅ Constructor injection (IMPORTANT)
    public BookingServiceImpl(BookingRepo bookingRepo, RoomsRepo roomsRepo) {
        this.bookingRepo = bookingRepo;
        this.roomsRepo = roomsRepo;
    }

    // ================= ADD BOOKING =================
    public Booking addBooking(Booking booking) {

        // 🔍 Check date conflicts
        List<Booking> conflicts = bookingRepo.findConflictingBookings(
                booking.getRoomId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Room already reserved for selected dates!");
        }

        // 🔥 FIXED: use injected repo (NOT static)
        Room room = roomsRepo.findById(booking.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setAvailable(false);   // mark as booked
        roomsRepo.save(room);

        return bookingRepo.save(booking);
    }

    // ================= GET ALL BOOKINGS =================
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    // ================= GET BY ID =================
    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));
    }

    // ================= UPDATE =================
    public Booking updateBooking(Long id, Booking updatedBooking) {

        Booking existingBooking = bookingRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));

        existingBooking.setUserName(updatedBooking.getUserName());
        existingBooking.setEmail(updatedBooking.getEmail());
        existingBooking.setRoomId(updatedBooking.getRoomId());
        existingBooking.setNumberOfGuests(updatedBooking.getNumberOfGuests());
        existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
        existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
        existingBooking.setPhoneNumber(updatedBooking.getPhoneNumber());
        existingBooking.setSpecialReq(updatedBooking.getSpecialReq());

        return bookingRepo.save(existingBooking);
    }

    // ================= DELETE =================
    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }
}

