package com.kraisorn.identity.repository;

import com.kraisorn.identity.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends CrudRepository<Person, Long>, PagingAndSortingRepository<Person, Long> {
    List<Person> findByLastName(@Param("lastName") String lastName);
    Person findById(long id);
}
