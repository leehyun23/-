package com.hyunjoonlee.board.repository;

import com.hyunjoonlee.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PostRepository Interface
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}