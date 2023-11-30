package com.hyunjoonlee.board.repository;

import com.hyunjoonlee.board.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TagRepository Interface
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
