package org.myblog.domain.repositories;

import org.myblog.domain.entities.Post;

import java.util.Collection;
import java.util.UUID;

public interface PostRepository {
    Post save(Post post);

    void deleteById(UUID id);

    Post getById(UUID id);

    Collection<Post> getAllPosts();
}
