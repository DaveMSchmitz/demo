package org.daveschmitz.demo.repositories;

import org.daveschmitz.demo.models.PersonModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonModel, Long> {
    List<PersonModel> findByFirstName(String firstName);
}
