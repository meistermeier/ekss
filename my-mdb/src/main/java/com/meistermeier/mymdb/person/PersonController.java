package com.meistermeier.mymdb.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private final PersonRepository personRepository;

	@Autowired
	public PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
		Person person = new Person();
		person.setFirstName("Hans");
		person.setLastName("Mueller");
		personRepository.save(person);
	}

	@GetMapping("/")
	@CrossOrigin
	public Iterable<Person> list() {
		return personRepository.findAll();
	}

	@PostMapping("/")
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Person person) {
		personRepository.save(person);
	}
}
