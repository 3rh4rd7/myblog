package org.myblog.domain.entities;

import java.util.UUID;

public record Comment(UUID id, UUID postId, String content) {
}
