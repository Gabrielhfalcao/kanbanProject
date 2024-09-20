package com.gabriel.taskmanagerapi.dto.comment;

import com.gabriel.taskmanagerapi.domain.Comment;

public record CommentRegisterDTO(
        String content,
        Long authorId,
        Long taskId
) {
    public CommentRegisterDTO(Comment comment) {
        this(
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getTask().getId());
    }
}
