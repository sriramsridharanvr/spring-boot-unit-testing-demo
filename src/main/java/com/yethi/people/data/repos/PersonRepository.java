package com.yethi.people.data.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yethi.people.data.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	public Optional<Person> findByPersonId(String personId);
	public Optional<Person> findByEmail(String email);
}
