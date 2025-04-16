package org.myblog.api.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.myblog.ObjectMother;
import org.myblog.domain.Post;
import org.myblog.service.PostService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostControllerTest {

    private MockMvc mockMvc;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = Mockito.mock(PostService.class);
        PostController postController = new PostController(postService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(postController)
                .build();
    }


    @Test
    void shouldReturnIndividualPostPage() throws Exception {
        var post = ObjectMother.postFixture().build();

        when(postService.findById(post.getId())).thenReturn(post);

        mockMvc.perform(get("/posts/{id}", post.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", post));

        verify(postService, times(1)).findById(post.getId());
    }

    @Test
    void shouldAddNewPost() throws Exception {
        var post = ObjectMother.postFixture().build();

        mockMvc.perform(post("/posts")
                        .param("title", post.getTitle())
                        .param("content", post.getContent()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blog/posts"));

        verify(postService, times(1)).save(any(Post.class));
    }

    @Test
    void shouldEditPost() throws Exception {
        var post = ObjectMother.postFixture().build();

        mockMvc.perform(post("/posts/{id}", post.getId())
                        .param("title", post.getTitle())
                        .param("content", post.getContent()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blog/posts"));

        verify(postService, times(1)).save(any(Post.class));
    }

    @Test
    void shouldDeletePost() throws Exception {
        var postId = 1L;

        mockMvc.perform(post("/posts/{id}/delete", postId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/blog/posts"));

        verify(postService, times(1)).delete(postId);
    }

    @Test
    void shouldLikePost() throws Exception {
        var post = ObjectMother.postFixture().build();
        post.setLikesCount(post.getLikesCount() + 1);

        when(postService.likePost(post.getId())).thenReturn(post);

        mockMvc.perform(post("/posts/{id}/like", post.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", post));

        verify(postService, times(1)).likePost(post.getId());
    }

    @Test
    void shouldDislikePost() throws Exception {
        var post = ObjectMother.postFixture().build();
        post.setLikesCount(post.getLikesCount() - 1);

        when(postService.dislikePost(post.getId())).thenReturn(post);

        mockMvc.perform(post("/posts/{id}/dislike", post.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", post));

        verify(postService, times(1)).dislikePost(post.getId());
    }

    @Test
    void shouldAddCommentToPost() throws Exception {
        var post = ObjectMother.postFixture().build();
        var commentText = "This is a comment";

        when(postService.addComment(post.getId(), commentText)).thenReturn(post);

        mockMvc.perform(post("/posts/{postId}/comments", post.getId())
                        .param("text", commentText))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", post));

        verify(postService, times(1)).addComment(post.getId(), commentText);
    }

    @Test
    void shouldUpdateComment() throws Exception {
        var post = ObjectMother.postFixture().build();
        var commentId = 1L;
        var newContent = "Updated comment";

        when(postService.updateComment(post.getId(), commentId, newContent)).thenReturn(post);

        mockMvc.perform(post("/posts/{postId}/comments/{commentId}", post.getId(), commentId)
                        .param("text", newContent))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", post));

        verify(postService, times(1)).updateComment(post.getId(), commentId, newContent);
    }

    @Test
    void shouldDeleteComment() throws Exception {
        var post = ObjectMother.postFixture().build();
        var commentId = 1L;

        when(postService.deleteComment(post.getId(), commentId)).thenReturn(post);

        mockMvc.perform(post("/posts/{postId}/comments/{commentId}/delete", post.getId(), commentId))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", post));

        verify(postService, times(1)).deleteComment(post.getId(), commentId);
    }
}
