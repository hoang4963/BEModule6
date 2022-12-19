package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * from comments where house_id = :id", nativeQuery = true)
    Iterable<Comment> HouseComment(@Param("id") Long id);

    @Query(value = "select * from comments join houses h on comments.house_id = h.id where is_read = true and" +
            " users_id= :userId", nativeQuery = true)
    Iterable<Comment> getAllByCommentAndIsReadTrue(@Param("userId") Long userId);

    @Query(value = "select * from comments join houses h on comments.house_id = h.id where is_read = false and" +
            " users_id= :userId", nativeQuery = true)
    Iterable<Comment> getAllByCommentAndIsReadFalse(@Param("userId") Long userId);
}
