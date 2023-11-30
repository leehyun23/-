package com.hyunjoonlee.board.repository;

import com.hyunjoonlee.board.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * PostTagRepository Interface
 */
@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Integer> {

}