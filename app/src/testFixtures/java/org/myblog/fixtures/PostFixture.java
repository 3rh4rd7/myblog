package org.myblog.fixtures;

import org.myblog.domain.Comment;
import org.myblog.domain.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PostFixture {

    private Long id = 1L;
    private String title = "generic_title";
    private String content = "generic_content";
    private int likes = 0;
    private Set<String> tags = Set.of();
    private Map<Long, Comment> comments = new HashMap<>();

    public static PostFixture postFixture() {
        return new PostFixture();
    }

    public PostFixture withId(Long id) {
        this.id = id;
        return this;
    }

    public PostFixture withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostFixture withContent(String content) {
        this.content = content;
        return this;
    }

    public PostFixture withLikesCount(int likes) {
        this.likes = likes;
        return this;
    }

    public PostFixture withTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public PostFixture withComments(Map<Long, Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Post build() {
        return new Post(id, title, content, likes, tags, comments);
    }
}