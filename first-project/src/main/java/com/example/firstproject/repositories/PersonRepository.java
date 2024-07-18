package com.example.firstproject.repositories;

import com.example.firstproject.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM people WHERE UPPER(first_name) LIKE CONCAT('%', UPPER(:firstName), '%')"
    )
    Page<Person> findPeopleByFirstName(@Param("firstName") String firstName, Pageable pageable);

}
