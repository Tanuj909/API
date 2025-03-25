package com.canvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.canvas.entities.Element;

public interface ElementRepository extends JpaRepository<Element, Long>
{

}
