package com.example.BookingService.repository;

import com.example.BookingService.model.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<Client> findByName(String name);

    Boolean existsByNameAndEmail(String name, String Email);

}
