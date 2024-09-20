package com.gabriel.taskmanagerapi.dto.comment;

import com.gabriel.taskmanagerapi.domain.Comment;

public record CommentDatailsDTO(
        String content,
        String authorName,
        String taskName
) {
    public CommentDatailsDTO(Comment comment) {
        this(
                comment.getContent(),
                comment.getAuthor().getName(),
                comment.getTask().getTitle()
        );
    }
}
