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

    @Query("SELECT ID, TITLE, CONTENT, LIKES_COUNT, TAGS, IMAGE from \"post\" where TAGS LIKE :pattern")
    List<Post> findByTag(@Param("pattern") String pattern);

}
