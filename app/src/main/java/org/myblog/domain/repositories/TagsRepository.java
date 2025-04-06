package org.myblog.domain.repositories;

public interface TagsRepository {
    void addTag(String tagName);
    void deleteTag();
}
