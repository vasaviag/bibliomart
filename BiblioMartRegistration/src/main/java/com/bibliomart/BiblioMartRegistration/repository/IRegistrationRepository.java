package com.bibliomart.BiblioMartRegistration.repository;

import com.bibliomart.BiblioMartRegistration.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IRegistrationRepository extends CrudRepository<User, Integer>
{
    List<User> findByEmail(String email);
}