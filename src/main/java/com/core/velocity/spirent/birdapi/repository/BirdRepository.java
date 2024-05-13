package com.core.velocity.spirent.birdapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.velocity.spirent.birdapi.model.Bird;

public interface BirdRepository extends JpaRepository<Bird, String> {

    List<Bird> findByNameAndColor(String name, String color);

}
