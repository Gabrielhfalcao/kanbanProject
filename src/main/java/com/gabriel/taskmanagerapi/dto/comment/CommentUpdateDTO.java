package com.gabriel.taskmanagerapi.dto.comment;

import com.gabriel.taskmanagerapi.domain.Comment;
import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDTO(
        @NotBlank
        String content
){
    public CommentUpdateDTO(Comment comment) {
        this(comment.getContent());
    }
}
