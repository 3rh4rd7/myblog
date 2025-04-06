package org.myblog.domain.entities;

import java.util.UUID;

public record Post(UUID id, String title, String content, int likesCount) {
}
