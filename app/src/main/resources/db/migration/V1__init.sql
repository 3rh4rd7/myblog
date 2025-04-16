CREATE TABLE posts
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT    NOT NULL,
    content     TEXT    NOT NULL,
    likes_count INTEGER NOT NULL DEFAULT 0,
    tags        TEXT[]
);

CREATE TABLE comments
(
    id      BIGSERIAL PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content TEXT   NOT NULL,
    CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);