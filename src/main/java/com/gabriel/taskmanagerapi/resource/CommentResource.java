package com.gabriel.taskmanagerapi.resource;

import com.gabriel.taskmanagerapi.dto.comment.CommentDatailsDTO;
import com.gabriel.taskmanagerapi.dto.comment.CommentRegisterDTO;
import com.gabriel.taskmanagerapi.dto.comment.CommentUpdateDTO;
import com.gabriel.taskmanagerapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comments")
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDatailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CommentDatailsDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(commentService.findAll(pageable));
    }

    @PostMapping("/register")
    public ResponseEntity<CommentDatailsDTO> register(@RequestBody CommentRegisterDTO commentRegisterDTO, UriComponentsBuilder uriComponentsBuilder) {
        CommentDatailsDTO commentDatailsDTO = commentService.register(commentRegisterDTO);
        var uri = UriComponentsBuilder.fromPath("/comments/{id}").buildAndExpand(commentDatailsDTO).toUri();
        return ResponseEntity.created(uri).body(commentDatailsDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDatailsDTO> update(@PathVariable Long id, @Valid @RequestBody CommentUpdateDTO commentUpdateDTO) {
        return ResponseEntity.ok().body(commentService.update(id, commentUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
