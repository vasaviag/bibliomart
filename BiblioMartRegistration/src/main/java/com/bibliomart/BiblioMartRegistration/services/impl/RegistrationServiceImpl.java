package com.bibliomart.BiblioMartRegistration.services.impl;

import com.bibliomart.BiblioMartRegistration.entity.User;
import com.bibliomart.BiblioMartRegistration.repository.IRegistrationRepository;
import com.bibliomart.BiblioMartRegistration.services.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl implements IRegistrationService {

    @Autowired
    IRegistrationRepository iRegistrationRepository;

    @Transactional(readOnly = false)
    public User save(User user)
    {
        return iRegistrationRepository.save(user);
    }
    public User findById(int id)
    {
        return iRegistrationRepository.findById(id).get();
    }
    @Transactional(readOnly = false)
    public void deleteById(int id)
    {
        iRegistrationRepository.deleteById(id);
    }
    public List<User> findAll()
    {
        Iterable<User> userIterable = iRegistrationRepository.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        return userList;
    }
    @Override
    public User findByEmail(String email)
    {
        return iRegistrationRepository.findByEmail(email).get(0);
    }

}
