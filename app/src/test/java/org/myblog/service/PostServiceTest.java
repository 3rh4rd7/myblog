package org.myblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.myblog.ObjectMother;
import org.myblog.domain.Comment;
import org.myblog.domain.Post;
import org.myblog.exceptions.NotFoundException;
import org.myblog.repository.CommentRepository;
import org.myblog.repository.PostRepository;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository, commentRepository);
        Mockito.reset(postRepository, commentRepository);
    }

    @Test
    public void createNewPost() {
        Post testPost = ObjectMother.postFixture().build();

        when(postRepository.save(testPost)).thenReturn(testPost);

        postService.save(testPost);

        verify(postRepository, times(1)).save(eq(testPost));
    }

    @Test
    public void deletesExistingPost() {
        Long id = 1L;

        doNothing().when(postRepository).deleteById(id);

        postService.delete(id);

        verify(postRepository, times(1)).deleteById(eq(id));
    }

    @Test
    public void findsExistingPostById() {
        Post testPost = ObjectMother.postFixture().build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));

        postService.findById(testPost.getId());

        verify(postRepository, times(1)).findById(eq(testPost.getId()));
    }

    @Test
    public void findNonExistingPostByIdThrowsException() {
        Long id = 1L;
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postService.findById(id));

        verify(postRepository, times(1)).findById(eq(id));
    }

    @Test
    public void likePost() {
        Post testPost = ObjectMother.postFixture().withLikesCount(0).build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));
        when(postRepository.save(testPost)).thenReturn(testPost);

        postService.likePost(testPost.getId());

        verify(postRepository, times(1)).findById(eq(testPost.getId()));

        assertEquals(1, testPost.getLikesCount());
    }

    @Test
    public void dislikePost() {
        Post testPost = ObjectMother.postFixture().withLikesCount(1).build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));
        when(postRepository.save(testPost)).thenReturn(testPost);

        postService.dislikePost(testPost.getId());

        verify(postRepository, times(1)).findById(eq(testPost.getId()));

        assertEquals(0, testPost.getLikesCount());
    }

    @Test
    public void addCommentToExistingPost() {
        Post testPost = ObjectMother.postFixture().build();
        Comment expectedComment = ObjectMother.commentFixture().build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));
        when(commentRepository.save(any(Comment.class))).thenAnswer(i ->
        {
            Comment comment = (Comment) i.getArguments()[0];
            comment.setId(testPost.getId());
            return comment;
        });

        postService.addComment(testPost.getId(), expectedComment.getContent());

        verify(commentRepository, times(1)).save(eq(expectedComment));
    }

    @Test
    public void addCommentToNonExistingPost() {
        Post testPost = ObjectMother.postFixture().build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postService.addComment(testPost.getId(), "test"));

        verify(commentRepository, times(0)).save(any(Comment.class));
    }

    @Test
    public void updateComment() {
        Comment expectedComment = ObjectMother.commentFixture().withContent("old content").build();
        Post testPost = ObjectMother.postFixture()
                .withComments(Map.of(expectedComment.getId(), expectedComment)).build();
        String newCommentContent = "new comment content";

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));
        when(postRepository.save(any(Post.class))).thenReturn(testPost);

        Post actualPost = postService.updateComment(testPost.getId(), expectedComment.getId(), newCommentContent);

        verify(postRepository, times(1)).save(eq(testPost));
        assertEquals(newCommentContent, actualPost.getComments().get(expectedComment.getId()).getContent());
    }

    @Test
    public void updateCommentToNonExistingPost() {
        Long id = 1L;

        when(postRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postService.updateComment(id, 1L, "test"));

        verify(commentRepository, times(0)).save(any(Comment.class));
        verify(postRepository, times(0)).save(any(Post.class));
    }

    @Test
    public void updateNonExistingComment() {
        Post testPost = ObjectMother.postFixture().build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));

        assertThrows(NotFoundException.class, () -> postService.updateComment(testPost.getId(), 1L, "test"));

        verify(commentRepository, times(0)).save(any(Comment.class));
        verify(postRepository, times(0)).save(any(Post.class));
    }

    @Test
    public void deleteCommentFromExistingPost() {
        Post testPost = ObjectMother.postFixture().build();

        when(postRepository.findById(testPost.getId())).thenReturn(Optional.of(testPost));

        postService.deleteComment(testPost.getId(), 1L);

        verify(postRepository, times(1)).save(testPost);
    }

    @Test
    public void deleteCommentFromNonExistingPost() {
        Long id = 1L;

        when(postRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postService.deleteComment(id, 1L));

        verify(postRepository, times(0)).save(any(Post.class));
    }

}
