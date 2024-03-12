package com.pagination.pagination.repository;

import com.pagination.pagination.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
