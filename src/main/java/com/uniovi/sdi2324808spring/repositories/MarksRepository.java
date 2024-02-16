package com.uniovi.sdi2324808spring.repositories;

import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

public interface MarksRepository extends CrudRepository<Mark,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
    void updateResend(Boolean resend, Long id);
    @Query("SELECT r FROM Mark r WHERE r.user = ?1 ORDER BY r.id ASC")
    Page<Mark> findAllByUser(Pageable pageable, User user);

    @Query("SELECT r FROM Mark r where (lower(r.description) like lower(?1) or lower(r.user.name) like lower(?1))")
    Page<Mark> searchByDescriptionAndName (Pageable pageable,String searchtext);

    @Query("SELECT r FROM Mark r where (lower(r.description) like lower(?1) or lower(r.user.name) like lower(?1)) and r.user=?2")
    Page<Mark >searchByDescriptionNameAndUser (Pageable pageable,String searchtext, User user);

    Page<Mark> findAll(Pageable pageable);
}
