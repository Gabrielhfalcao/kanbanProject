package com.gabriel.taskmanagerapi.resource;

import com.gabriel.taskmanagerapi.dto.user.UserDetailsDTO;
import com.gabriel.taskmanagerapi.dto.user.UserRegisterDTO;
import com.gabriel.taskmanagerapi.dto.user.UserUpdateDTO;
import com.gabriel.taskmanagerapi.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok().body(new UserDetailsDTO(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailsDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(userService.findAll(pageable));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDetailsDTO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO, UriComponentsBuilder uriComponentsBuilder) {
        UserDetailsDTO user = userService.register(userRegisterDTO);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<UserDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok().body(userService.update(id, userUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
