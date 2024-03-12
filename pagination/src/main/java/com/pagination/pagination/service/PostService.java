package com.pagination.pagination.service;

import com.pagination.pagination.entity.Post;
import com.pagination.pagination.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
@Autowired
    public PostRepository postRepository;
    public Post savePost(Post post) {
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }
    public List<Post> listAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts=postRepository.findAll(pageable);
        List<Post>listOfPosts=posts.getContent();
//        List<Post>posts=postRepository.findAll();
        return listOfPosts;
    }
    public Post getByPostId(String postId) {
        Optional<Post> byId = postRepository.findById(postId);
        return byId.get();
    }

}
