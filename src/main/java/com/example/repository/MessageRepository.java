package com.example.repository;

import com.example.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("SELECT m FROM Message m WHERE m.posted_by = ?1")
    public List<Message> findAllMessagesByPostedBy(int id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Message m SET m.message_text = ?2 WHERE message_id = ?1")
    public void updateMessageById(int id, String text);

}
