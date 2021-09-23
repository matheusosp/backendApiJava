package com.crud.reuniao.javaeangularcrud.service;


import com.crud.reuniao.javaeangularcrud.dto.RoomDTO;
import com.crud.reuniao.javaeangularcrud.exception.RoomAlreadyRegisteredException;
import com.crud.reuniao.javaeangularcrud.exception.RoomNotFoundException;
import com.crud.reuniao.javaeangularcrud.model.Room;
import com.crud.reuniao.javaeangularcrud.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAll(){
        List<Room> room = repository.findAll();
        return room;
    }

    public Room findById(Long id) throws RoomNotFoundException {
        Room room = verifyIfExists(id);
        return room;
    }

    public Room create(Room room) throws RoomAlreadyRegisteredException {
        Optional<Room> alreadyRegistered = repository.findByName(room.getName());
        if (alreadyRegistered.isPresent()) {
            throw new RoomAlreadyRegisteredException(String.format("Room with name %s already registered in the system.", room.getName()));
        }
        Room savedRoom = repository.save(room);
        return savedRoom;
    }

    public void updateById(Long id, RoomDTO roomDTO) throws RoomNotFoundException{
        verifyIfExists(id);
        Room updatedRoom = new Room();
        updatedRoom.setId(id);
        updatedRoom.setName(roomDTO.getName());
        updatedRoom.setDate(roomDTO.getDate());
        updatedRoom.setStartHour(roomDTO.getStartHour());
        updatedRoom.setEndHour(roomDTO.getEndHour());
        repository.save(updatedRoom);
    }

    public void deleteById(Long id) throws RoomNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }
    private Room verifyIfExists(Long id) throws RoomNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

}
