package com.hyunjoonlee.board.repository;

import com.hyunjoonlee.board.entity.BoardDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BoardDefRepository Interface
 */
@Repository
public interface BoardDefRepository extends JpaRepository<BoardDef, String> {

}