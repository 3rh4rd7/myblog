package org.myblog.api.http;

import org.myblog.domain.Post;
import org.myblog.dto.PostDto;
import org.myblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPosts(
            Model model,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "search", required = false) String tag
    ) {
        Page<Post> posts = postService.findAll(tag, currentPage, pageSize);
        model.addAttribute("posts", posts.getContent());
        model.addAttribute("paging", posts);
        return "posts";
    }

    @RequestMapping("/{id}")
    public String getPost(@PathVariable("id") int id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping("/{id}/edit")
    public String editPost(@PathVariable("id") int id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "add-post";
    }

    @RequestMapping("/add")
    public String addPost() {
        return "add-post";
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addPost(@ModelAttribute PostDto postDto) throws IOException {
        Post post = postDto.toDomainObject();
        postService.save(post);
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String editPost(
            @ModelAttribute PostDto postDto,
            @PathVariable("id") long id
    ) throws IOException {

        Post post = postDto.toDomainObject();
        post.setId(id);
        postService.save(post);
        return "redirect:/posts";
    }

    @PostMapping("{id}/delete")
    public String deletePost(@PathVariable("id") long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

    @PostMapping("{id}/like")
    public String likePost(Model model, @PathVariable("id") long id) {
        Post post = postService.likePost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("{id}/dislike")
    public String dislikePost(Model model, @PathVariable("id") long id) {
        Post post = postService.dislikePost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/{postId}/comments")
    public String addComment(
            Model model,
            @PathVariable("postId") long postId,
            @RequestParam("text") String commentText
    ) {
        Post post = postService.addComment(postId, commentText);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/{postId}/comments/{commentId}")
    public String updateComment(
            Model model,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId,
            @RequestParam("text") String commentText
    ) {
        Post post = postService.updateComment(postId, commentId, commentText);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping("/{postId}/comments/{commentId}/delete")
    public String deleteComment(
            Model model,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ) {
        Post post = postService.deleteComment(postId, commentId);
        model.addAttribute("post", post);
        return "post";
    }
}