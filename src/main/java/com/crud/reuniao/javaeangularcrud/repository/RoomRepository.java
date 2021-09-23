package com.crud.reuniao.javaeangularcrud.repository;

import com.crud.reuniao.javaeangularcrud.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    Optional<Room> findByName(String name);

}
