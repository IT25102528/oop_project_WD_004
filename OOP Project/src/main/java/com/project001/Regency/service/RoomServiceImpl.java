package com.project001.Regency.service;

import com.project001.Regency.model.Room;
import com.project001.Regency.repository.RoomsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomServiceImpl {

    private final RoomsRepo roomRepository;

    public RoomServiceImpl(RoomsRepo roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room not found"));
    }

    public Room updateRoom(Long id, Room updatedRoom) {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        existingRoom.setRoomType(updatedRoom.getRoomType());
        existingRoom.setCapacity(updatedRoom.getCapacity());
        existingRoom.setPricePerNight(updatedRoom.getPricePerNight());
        existingRoom.setDescription(updatedRoom.getDescription());
        existingRoom.setAvailable(updatedRoom.isAvailable());

        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
