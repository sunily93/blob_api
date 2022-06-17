package com.example.blob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blob.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
