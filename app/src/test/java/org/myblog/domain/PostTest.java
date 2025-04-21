package org.myblog.domain;

import org.junit.jupiter.api.Test;
import org.myblog.ObjectMother;

import java.util.Base64;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PostTest {
    @Test
    public void shouldReturnParagraphedContent() {
        var content = "Hello\nWorld";
        Post testPost = ObjectMother.postFixture().withContent(content).build();

        var text = testPost.getParagraphedText();

        assertEquals(2, text.size());
    }

    @Test
    void testGetTagsReturnsSetOfTags() {
        var tagSet = Set.of("java", "spring", "backend");
        Post testPost = ObjectMother.postFixture().build();
        testPost.setTags(tagSet);
        Set<String> tags = testPost.getTags();
        assertEquals(Set.of("java", "spring", "backend"), tags);
    }

    @Test
    void testGetTagsAsTextReturnsText() {
        var tagSet = Set.of("java", "spring", "backend");
        Post testPost = ObjectMother.postFixture().build();
        testPost.setTags(tagSet);
        String tags = testPost.getTagsAsText();
        assertEquals(String.join(" ", tagSet), tags);
    }

    @Test
    void testGetTagsAsTextReturnsOriginalString() {
        var tagSet = Set.of("java spring");
        Post testPost = ObjectMother.postFixture().build();
        testPost.setTags(tagSet);
        assertEquals("java spring", testPost.getTagsAsText());
    }

    @Test
    void testGetImageAsBase64ReturnsBase64EncodedString() {
        byte[] image = new byte[]{1, 2, 3};
        Post testPost = ObjectMother.postFixture().withImage(image).build();
        String expected = Base64.getEncoder().encodeToString(image);
        assertEquals(expected, testPost.getImageAsBase64());
    }

    @Test
    void testGetImageAsBase64ReturnsNullIfImageIsNull() {
        Post testPost = ObjectMother.postFixture().build();
        assertNull(testPost.getImageAsBase64());
    }

    @Test
    void testGetImageAsBase64ReturnsNullIfImageIsEmpty() {
        Post testPost = ObjectMother.postFixture().withImage(new byte[0]).build();
        assertNull(testPost.getImageAsBase64());
    }
}
