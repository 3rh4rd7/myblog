package org.myblog.domain;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

public record Post(UUID id, String title, String content, Set<Tag> tags, Set<Comment> comments) {
}
