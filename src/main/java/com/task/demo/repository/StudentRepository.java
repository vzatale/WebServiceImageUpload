package com.task.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.demo.Entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> 
{

}
