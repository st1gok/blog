package ru.practicum.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.blog.dto.CommentDto;
import ru.practicum.blog.services.CommentService;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/posts/{postId}/comment")
    public String commentPost(@PathVariable(name = "postId") Long postId, @ModelAttribute CommentDto comment) {
        commentService.saveComment(comment);
        return "redirect:/posts/{postId}";
    }

    @PostMapping(value = "/posts/{postId}/removeComment/{commentId}", params = "_method=delete")
    public String deleteComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/posts/{postId}";
    }
}
