package org.myblog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.myblog.domain.Post;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class PostDto {
    private String title;
    private String tags;
    private String content;

    public Post toDomainObject() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        if (tags != null) {
            post.setTags(
                Arrays.stream(tags.split(" "))
                    .filter(tag -> !tag.isEmpty())
                    .collect(Collectors.toSet())
            );
        }
        return post;
    }
}
