package com.gabriel.taskmanagerapi.service;

import com.gabriel.taskmanagerapi.domain.User;
import com.gabriel.taskmanagerapi.dto.user.UserDetailsDTO;
import com.gabriel.taskmanagerapi.dto.user.UserRegisterDTO;
import com.gabriel.taskmanagerapi.dto.user.UserUpdateDTO;
import com.gabriel.taskmanagerapi.enums.UserRole;
import com.gabriel.taskmanagerapi.exception.handler.EmailAlreadyRegisteredException;
import com.gabriel.taskmanagerapi.exception.handler.RoleAlreadyAssignedException;
import com.gabriel.taskmanagerapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String PREFIX = "There are no users for this id: ";

    public Page<UserDetailsDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDetailsDTO::new);
    }

    public User findById(long id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        return userRepository.getReferenceById(id);
    }

    public UserDetailsDTO register(UserRegisterDTO userRegisterDTO) {
        if(userRepository.existsByEmail(userRegisterDTO.email())){
            throw new EmailAlreadyRegisteredException("Email already registered");
        }
        return new UserDetailsDTO(userRepository.save(new User(userRegisterDTO)));
    }

    public UserDetailsDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(PREFIX + id);
        }
        User user = userRepository.getReferenceById(id);
        if (userUpdateDTO.name() != null) {
            user.setName(userUpdateDTO.name());
        }
        if (userUpdateDTO.email() != null) {
            user.setEmail(userUpdateDTO.email());
        }
        return new UserDetailsDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        userRepository.deleteById(id);
    }

    public void changePassword(Long id, String senha) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        User user = userRepository.getReferenceById(id);
        user.setPassword(senha);
        userRepository.save(user);
    }

    public void changeRole(Long id, UserRole userRole) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        User user = userRepository.getReferenceById(id);
        if(user.getUserRole().equals(userRole)){
            throw new RoleAlreadyAssignedException("the chosen role is already in effect");
        }
        user.setUserRole(userRole);
    }

    public List<User> convertLongListToUserList(List<Long> longList) {
        List<User> userList = new ArrayList<>();
        for(Long id : longList){
            userList.add(findById(id));
        }
        return userList;
    }
}
