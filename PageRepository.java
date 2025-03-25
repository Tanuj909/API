package com.canvas.repository;

import com.canvas.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {
    @Query("SELECT p FROM Page p LEFT JOIN FETCH p.elements WHERE p.design.id = :designId")
    List<Page> findPagesWithElementsByDesignId(@Param("designId") Long designId);
}