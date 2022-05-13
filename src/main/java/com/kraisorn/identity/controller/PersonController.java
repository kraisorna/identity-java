package com.kraisorn.identity.controller;

import com.kraisorn.identity.exception.PersonNotFoundException;
import com.kraisorn.identity.repository.PersonRepository;
import com.kraisorn.identity.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path="/person")
public class PersonController {

    @Autowired
    PersonRepository repository;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/{id}")
    Person one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @GetMapping("/add")
    public Person person(@RequestParam(value = "name", defaultValue = "Kraisorn") String name) {
        return repository.save(new Person("Kraisorn","Arjharnapisaranont", String.format(template, name)));
    }

    @GetMapping("/index")
    public String index() {
        return "People from Spring Boot!";
    }
}
