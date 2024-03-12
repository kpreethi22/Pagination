package com.pagination.pagination.controller;
import com.pagination.pagination.entity.Post;
import com.pagination.pagination.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    public PostService postService;
    //    http://localhost:8081/api/posts
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post newPost =postService.savePost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
    //    http://localhost:8081/api/posts
//    http://localhost:8081/api/posts?pageNo=1&pageSize=4&sortBy&sortDir
    @GetMapping
    public List<Post> listAllPosts( //pagination
                                    @RequestParam(value="pageNo",defaultValue="0",required=false)int pageNo,
                                    @RequestParam(value="pageSize",defaultValue="5",required=false)int pageSize,
                                    @RequestParam(value="sortBy",defaultValue="id",required=false)String sortBy,
                                    @RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir){
        return postService.listAllPosts(pageNo,pageSize,sortBy,sortDir);
    }


    //    http://localhost:8081/api/posts/{postId}
    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId) {
        Post post = postService.getByPostId(postId);
        return post;
    }

}


