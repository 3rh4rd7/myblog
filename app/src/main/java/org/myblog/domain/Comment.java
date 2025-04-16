package org.myblog.domain;

public record Comment(Long id, Long postId, String content) {
}
