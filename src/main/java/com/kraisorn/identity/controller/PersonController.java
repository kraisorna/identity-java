package com.kraisorn.identity.controller;

import com.kraisorn.identity.exception.PersonNotFoundException;
import com.kraisorn.identity.repository.PersonRepository;
import com.kraisorn.identity.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/person")
public class PersonController {

    @Autowired
    PersonRepository repository;
    private static final String template = "Hello, %s!";

    @GetMapping("/people")
    CollectionModel<EntityModel<Person>> all() {

//        CollectionModel<EntityModel<Person>> people = (CollectionModel<EntityModel<Person>>) repository.findAll().stream()
//                .map(person -> EntityModel.of(person,
//                        linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel(),
//                        linkTo(methodOn(PersonController.class).all()).withRel("employees")));
//
//        return people;

        List<EntityModel<Person>> people = repository.findAll().stream()
                .map(person -> EntityModel.of(person,
                        linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(people, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<Person> one(@PathVariable Long id) {

        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return EntityModel.of(person, //
                linkTo(methodOn(PersonController.class).one(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPeople()).withRel("person"));
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAllPeople() {
        return repository.findAll();
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
