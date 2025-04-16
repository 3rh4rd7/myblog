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
import java.util.stream.Collectors;

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
    private String tags;
    @MappedCollection(idColumn = "POST_ID", keyColumn = "ID")
    private Map<Long, Comment> comments;

    public List<String> getParagraphedText() {
        return Arrays.stream(content.split("\n")).toList();
    }

    public void setTags(Set<String> tagSet) {
        this.tags = String.join(" ", tagSet);
    }

    public Set<String> getTags() {
        return Arrays.stream(tags.split(" ")).collect(Collectors.toSet());
    }

    public String getTagsAsText() {
        return tags;
    }

    public String getTextPreview() {
        return content.length() > 50 ? content.substring(0, 50) : content;
    }
}
