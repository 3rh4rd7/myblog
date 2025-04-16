package org.myblog.domain;

import java.util.Set;

public record Post(Long id, String title, String content, int likesCount, Set<String> tags) {
}
