package org.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myblog.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostDto {
    private String title;
    private String tags;
    private String content;
    private MultipartFile image;

    public Post toDomainObject() throws IOException {
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

        if (image != null && !image.isEmpty()) {
            post.setImage(image.getBytes());
        }
        return post;
    }
}
