package org.myblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Table("post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private long id;
    private String title;
    private String content;
    @Column("LIKES_COUNT")
    private int likesCount;
    private Set<String> tags;
    @MappedCollection(idColumn = "POST_ID", keyColumn = "ID")
    private Map<Long, Comment> comments;

    public List<String> getParagraphedText() {
        return Arrays.stream(content.split("\n")).toList();
    }

    public String getTagsAsText() {
        return String.join(", ", tags);
    }
}
