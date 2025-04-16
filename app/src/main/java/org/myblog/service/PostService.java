package org.myblog.service;

import org.myblog.domain.Comment;
import org.myblog.domain.Post;
import org.myblog.exceptions.NotFoundException;
import org.myblog.repository.CommentRepository;
import org.myblog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Page<Post> findAll(String tag, int page, int size) {

        if (hasValidTagFiltering(tag)) {
            List<Post> posts = postRepository.findByTag(tag);

            PageRequest pageRequest = PageRequest.of(page, size);

            int start = (int) pageRequest.getOffset();
            int end = Math.min(start + pageRequest.getPageSize(), posts.size());

            return new PageImpl<>(posts.subList(start, end), pageRequest, posts.size());
        }

        return postRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    public Post findById(long id) {
        return findPostOrThrowNotFoundException(id);
    }

    public void delete(long id) {
        postRepository.deleteById(id);
    }

    public Post likePost(long id) {
        Post post = findPostOrThrowNotFoundException(id);

        post.setLikes(post.getLikes() + 1);
        post = postRepository.save(post);

        return post;
    }

    public Post dislikePost(long id) {
        Post post = findPostOrThrowNotFoundException(id);

        post.setLikes(post.getLikes() - 1);
        post = postRepository.save(post);

        return post;
    }

    public Post addComment(Long postId, String commentText) {

        Post post = findPostOrThrowNotFoundException(postId);

        Comment comment = new Comment();
        comment.setContent(commentText);
        comment.setPostId(post.getId());

        commentRepository.save(comment);

        return postRepository.findById(postId).get();
    }

    public Post updateComment(long postId, long commentId, String commentText) {
        Post post = findPostOrThrowNotFoundException(postId);

        if (!post.getComments().containsKey(commentId)) {
            throw new NotFoundException("Comment with id " + commentId + "not found");
        }

        post.getComments().get(commentId).setContent(commentText);
        return postRepository.save(post);
    }

    public Post deleteComment(long postId, long commentId) {
        Post post = findPostOrThrowNotFoundException(postId);

        post.getComments().remove(commentId);
        return postRepository.save(post);
    }

    private Post findPostOrThrowNotFoundException(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post with id " + postId + "not found"));
    }

    private boolean hasValidTagFiltering(String tag) {
        return tag != null && !tag.isEmpty();
    }
}
