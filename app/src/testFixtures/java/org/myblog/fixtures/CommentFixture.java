package org.myblog.fixtures;

import org.myblog.domain.Comment;

public class CommentFixture {

    private Long id = 1L;
    private Long postId  = 1L;
    private String content = "generic_content";

    public static CommentFixture commentFixture() {
        return new CommentFixture();
    }

    public CommentFixture withId(Long id) {
        this.id = id;
        return this;
    }

    public CommentFixture withPostId(Long postId) {
        this.postId = postId;
        return this;
    }

    public CommentFixture withContent(String content) {
        this.content = content;
        return this;
    }

    public Comment build() {
        return new Comment(id, postId, content);
    }
}
