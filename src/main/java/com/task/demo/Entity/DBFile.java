package com.task.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

import lombok.Data;

@Data
@Entity
public class DBFile 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;
}
