package ru.practicum.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.practicum.blog.dto.CommentDto;
import ru.practicum.blog.dto.PostDto;
import ru.practicum.blog.services.LikeableCommentablePostService;

@Controller
public class PostController {

    private LikeableCommentablePostService postService;

    @Autowired
    public PostController(LikeableCommentablePostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam(required=false, value = "tag") String tag, Pageable pageable, Model model) {
        Page<PostDto> posts = postService.getNewestPosts(tag, pageable);
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String getPosts(@PathVariable(value = "id") final Long id, Model model) {
       PostDto post= postService.getPost(id);
       model.addAttribute("post", post);
       return "post";
    }


    @PostMapping("/posts")
    public String addPost(@ModelAttribute PostDto post) {
        if (post.getId() != null) {
            throw new RuntimeException("A new Post cannot already have an ID");
        }

        PostDto createdPost = postService.addPost(post);
        return "redirect:/posts/" + createdPost.getId();
    }

    @PostMapping(value = "/posts/{id}", params = "_method=delete")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}")
    public String updatePost(@ModelAttribute PostDto post, @PathVariable(name = "id") Long id) {
        PostDto createdPost = postService.addPost(post);
        return "redirect:/posts/" + createdPost.getId();
    }

    @PostMapping("posts/{id}/like")
    public String likePost(@PathVariable(name = "id") Long postId) {
        postService.addLike(postId);
        return "redirect:/posts/{id}";
    }

    @PostMapping(value = "posts/{postid}/comment")
    public String commentPost(@PathVariable(name = "postid") Long postId, @ModelAttribute CommentDto comment) {
        postService.addComment(comment, postId);
        return "redirect:/posts/{postid}";
    }

    @PostMapping(value = "posts/{postid}/removeComment/{commentId}", params = "_method=delete")
    public String deleteComment(@PathVariable(name = "postid") Long postId, @PathVariable(name = "commentId") Long commentId) {
        postService.deleteComment(commentId);
        return "redirect:/posts/{postid}";
    }
}
