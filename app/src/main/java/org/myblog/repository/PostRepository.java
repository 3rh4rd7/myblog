package org.myblog.repository;

import org.myblog.domain.Post;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>, CrudRepository<Post, Long> {

    @Query("SELECT id, title, content, likes, tags FROM post WHERE ? = ANY(tags)")
    List<Post> findByTag(@Param("tag") String tag);

}
