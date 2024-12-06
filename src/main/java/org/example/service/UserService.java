package org.example.service;

import org.example.entity.UserEntity;
import org.example.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private boolean userVerification(UserEntity user) {
        if (user == null) {
            logger.warn("User is not initialized");
            return false;
        }
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || (user.getAge() == 0)) {
            return false;
        }
        return true;
    }

    public void saveUser(UserEntity user) {
        if (userVerification(user)) {
            repository.save(user);
            logger.info("save user: " + user);
        }
    }

    public void updateUser(UserEntity newUser, Long id) {
        if (userVerification(newUser)) {
            repository.updateUserEntitiesById(newUser, id);
            logger.info("update user: " + newUser);
        }
    }

    public Optional<UserEntity> findUserById(Long id) {
        logger.info("find user, id: " + id);
        return repository.findById(id);
    }

    public void deleteUserById(Long id) {
        logger.info("delete user, id: " + id);
        repository.deleteById(id);
    }
}
