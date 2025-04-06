package org.myblog.domain;

import java.util.UUID;

public record Comment(UUID id, UUID postId, String content) {
}
