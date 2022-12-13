package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * from comments where house_id = :id;", nativeQuery = true)
    Optional<Comment> HouseComment(@Param("id") Long id);
}
