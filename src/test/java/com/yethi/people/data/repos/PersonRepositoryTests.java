package com.yethi.people.data.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.yethi.people.data.entity.Person;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class PersonRepositoryTests {
	
	@Autowired
	private PersonRepository personRepository;
	
	private Person getPerson(String firstName, String lastName, String email, LocalDate dateOfBirth) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(email);
		person.setDateOfBirth(dateOfBirth);
		person.setPersonId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		person.setCity("Palo Alto");
		person.setCountryCode("US");
		
		return person;
	}
	
	@Test
	void testSave() {
		Person person = getPerson("John", "Doe", "john.doe@yethi.in", LocalDate.of(1982, 1, 1));
		personRepository.save(person);
		assertNotNull(person.getId());
	}
	
	@Test
	void testFindAll() {
		personRepository.save(getPerson("John", "Doe", "john.doe@yethi.in", LocalDate.of(1982, 1, 1)));
		personRepository.save(getPerson("John", "Doe", "john.doe2@yethi.in", LocalDate.of(1982, 1, 1)));
		assertEquals(2, personRepository.findAll().size());
	}
	
	@Test
	void testFindByPersonId() {
		Person person = getPerson("John", "Doe", "john.doe@yethi.in", LocalDate.of(1982, 1, 1));
		personRepository.save(person);
		
		Optional<Person> personFromDatabase = personRepository.findByPersonId(person.getPersonId());
		assertTrue(personFromDatabase.isPresent());
		assertEquals("john.doe@yethi.in", personFromDatabase.get().getEmail());
	}
	
	@Test
	void testFindByPersonIdWithInvalidId() {
		Person person = getPerson("John", "Doe", "john.doe@yethi.in", LocalDate.of(1982, 1, 1));
		personRepository.save(person);
		
		Optional<Person> personFromDatabase = personRepository.findByPersonId("SOMEINVALIDID");
		assertFalse(personFromDatabase.isPresent());
	}
	
	@Test
	void testFindByEmail() {
		Person person = getPerson("John", "Doe", "john.doe@yethi.in", LocalDate.of(1982, 1, 1));
		personRepository.save(person);
		
		Optional<Person> personFromDatabase = personRepository.findByEmail(person.getEmail());
		assertTrue(personFromDatabase.isPresent());
		assertEquals(person.getPersonId(), personFromDatabase.get().getPersonId());
	}
	
	@Test
	void testFindByEmailWithInvalidEmail() {
		Person person = getPerson("John", "Doe", "john.doe@yethi.in", LocalDate.of(1982, 1, 1));
		personRepository.save(person);
		
		Optional<Person> personFromDatabase = personRepository.findByEmail("SOMEINVALIDEMAIL@EMAIL.COM");
		assertFalse(personFromDatabase.isPresent());
	}
}
