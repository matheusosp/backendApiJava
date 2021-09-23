package com.crud.reuniao.javaeangularcrud.controller;

import com.crud.reuniao.javaeangularcrud.dto.RoomDTO;
import com.crud.reuniao.javaeangularcrud.exception.RoomAlreadyRegisteredException;
import com.crud.reuniao.javaeangularcrud.exception.RoomNotFoundException;
import com.crud.reuniao.javaeangularcrud.model.Room;
import com.crud.reuniao.javaeangularcrud.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @CrossOrigin("*")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    RoomService service;

    @GetMapping("/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<Room> listAll()
    {

        return service.findAll();
    }

    @GetMapping("/room/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Room findById(@PathVariable("id") Long id) throws RoomNotFoundException {
        return service.findById(id);
    }

    @PostMapping("/room")
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody @Valid Room room) throws RoomAlreadyRegisteredException {
        return service.create(room);
    }

    @PutMapping("/room/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRoom(@PathVariable(value = "id") Long roomId,
                           @Valid @RequestBody RoomDTO roomDetails)
                           throws RoomNotFoundException {
        service.updateById(roomId,roomDetails);
    }

    @DeleteMapping("/room/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable(value = "id") Long roomId) throws RoomNotFoundException{
        service.deleteById(roomId);
    }
}
