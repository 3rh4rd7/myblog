package org.myblog.domain.repositories;

import java.util.Collection;

import org.myblog.domain.entities.Comment;

import java.util.UUID;

public interface CommentsRepository {
    void addComment(UUID postId, String content);

    void updateComment(UUID id, String content);

    Collection<Comment> getAllCommentsForPost(UUID postId);

    void deleteComment(UUID id);
}
