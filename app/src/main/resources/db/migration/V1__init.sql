CREATE TABLE "post"
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       TEXT    NOT NULL,
    content     TEXT    NOT NULL,
    likes_count INTEGER NOT NULL DEFAULT 0,
    tags        TEXT -- Could store as comma-separated values or JSON
);

CREATE TABLE "comment"
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content TEXT   NOT NULL,
    CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES "post" (id) ON DELETE CASCADE
);