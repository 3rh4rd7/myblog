package org.myblog;

import org.myblog.fixtures.CommentFixture;
import org.myblog.fixtures.PostFixture;

public class ObjectMother {
    private ObjectMother() {
    }

    public static CommentFixture commentFixture() {
        return CommentFixture.commentFixture();
    }

    public static PostFixture postFixture() {
        return PostFixture.postFixture();
    }
}