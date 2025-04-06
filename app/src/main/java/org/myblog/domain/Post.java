package org.myblog.domain;

import java.util.Set;
import java.util.UUID;

public record Post(UUID id, String title, String content) {
}
