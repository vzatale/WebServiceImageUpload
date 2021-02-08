package com.task.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.demo.Entity.DBFile;

public interface DBFileRepository extends JpaRepository<DBFile, Integer>
{

}
