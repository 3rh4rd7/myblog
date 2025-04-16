package org.myblog.domain;

import org.junit.jupiter.api.Test;
import org.myblog.ObjectMother;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {
    @Test
    public void shouldReturnParagraphedContent() {
        var content = "Hello\nWorld";
        Post testPost = ObjectMother.postFixture().withContent(content).build();

        var text = testPost.getParagraphedText();

        assertEquals(2, text.size());
    }
}
