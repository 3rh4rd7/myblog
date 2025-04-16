package org.myblog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private long id;
    private long postId;
    private String content;
}