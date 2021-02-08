package com.task.demo.Entity;

import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
public class Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rollno")
	@SequenceGenerator(initialValue = 101,name = "rollno")
	private int rollno;
	private String name;
	@Lob
	private byte[] profilepic;
	
}
