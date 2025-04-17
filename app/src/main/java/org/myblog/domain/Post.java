package org.myblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;
import java.util.stream.Collectors;

@Table("post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private Long id;
    private String title;
    private String content;
    @Column("LIKES_COUNT")
    private int likesCount;
    private String tags;
    @Column("IMAGE")
    private byte[] image;
    @MappedCollection(idColumn = "POST_ID", keyColumn = "ID")
    private Map<Long, Comment> comments;

    public List<String> getParagraphedText() {
        return Arrays.stream(content.split("\n")).toList();
    }

    public void setTags(Set<String> tagSet) {
        this.tags = String.join(" ", tagSet);
    }

    public Set<String> getTags() {
        if (tags == null) {
            return Collections.emptySet();
        }
        return Arrays.stream(tags.split(" ")).collect(Collectors.toSet());
    }

    public String getTagsAsText() {
        return tags;
    }

    public String getTextPreview() {
        return content.length() > 50 ? content.substring(0, 50) : content;
    }

    public String getImageAsBase64() {
        if (image == null || image.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(image);
    }
}
