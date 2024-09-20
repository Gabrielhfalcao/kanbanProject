package com.gabriel.taskmanagerapi.service;

import com.gabriel.taskmanagerapi.domain.Comment;
import com.gabriel.taskmanagerapi.dto.comment.CommentDatailsDTO;
import com.gabriel.taskmanagerapi.dto.comment.CommentRegisterDTO;
import com.gabriel.taskmanagerapi.dto.comment.CommentUpdateDTO;
import com.gabriel.taskmanagerapi.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentRepository commentRepository;

    private static final String PREFIX = "There are no comments for this id: ";

    public Page<CommentDatailsDTO> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable).map(CommentDatailsDTO::new);
    }

    public CommentDatailsDTO findById(Long id) {
        if(!commentRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        return new CommentDatailsDTO(commentRepository.getReferenceById(id));
    }

    public CommentDatailsDTO register(CommentRegisterDTO commentRegisterDTO) {
        return new CommentDatailsDTO(
                commentRepository.save(convertCommentRegisterDTOToComment(commentRegisterDTO))
        );
    }

    public CommentDatailsDTO update(Long id,CommentUpdateDTO commentUpdateDTO) {
        Comment comment = commentRepository.getReferenceById(id);
        comment.setContent(commentUpdateDTO.content());
        return new CommentDatailsDTO(commentRepository.save(comment));
    }

    public void delete(Long id) {
        if(!commentRepository.existsById(id)){
            throw new EntityNotFoundException(PREFIX + id);
        }
        commentRepository.deleteById(id);
    }

    public Comment convertCommentRegisterDTOToComment(CommentRegisterDTO commentRegisterDTO) {
        Comment comment = new Comment();
        comment.setContent(commentRegisterDTO.content());
        comment.setCreationDate(LocalDateTime.now());
        comment.setAuthor(userService.findById(commentRegisterDTO.authorId()));
        comment.setTask(taskService.findById(commentRegisterDTO.taskId()));

        return comment;
    }
}
