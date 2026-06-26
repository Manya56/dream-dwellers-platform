package com.greedyraccoon.backend.repository;

import com.greedyraccoon.backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    // JpaRepository gives us save(), findAll(), findById(), and deleteById() automatically!
}