package com.task.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.task.demo.Entity.DBFile;
import com.task.demo.Entity.Student;
import com.task.demo.repository.DBFileRepository;
import com.task.demo.repository.StudentRepository;

import net.bytebuddy.asm.Advice.Return;

@RestController
public class ImageController
{
	@Autowired
	DBFileRepository fileRepo;
	@Autowired
	StudentRepository studRepo;
	
	@RequestMapping("/")
	public String prelogin()
	{
		return "WEL COME";
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<String> saveStudentData(@RequestParam("name") String name,@RequestParam("profilepic") MultipartFile file ) throws IOException
	{
		System.out.println("Enter in to upload Controller...");
		
		Student student=new Student();

		student.setName(name);
		student.setProfilepic(file.getBytes());
		
		System.out.println(student);
		
		studRepo.save(student);

		return ResponseEntity.status(HttpStatus.OK).body("File Uploaded Successfully");
		
	}
	@PostMapping("/uploadfile")
	public ResponseEntity<String> saveData(@RequestParam("file") MultipartFile file ) throws IOException
	{
		System.out.println("Enter in to upload Controller...");
		
		DBFile dbfile=new DBFile();
		
		dbfile.setFileName(file.getOriginalFilename());
		dbfile.setFileType(file.getContentType());
		dbfile.setData(file.getBytes());
		
		fileRepo.save(dbfile);

		return ResponseEntity.status(HttpStatus.OK).body("File Uploaded Successfully");
		
	}
	
	 @PostMapping("/uploadMultipleFiles")
	 public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws IOException 
	 {
		 System.out.println("Enter into Multiple file upload controller");
		 
		 for(MultipartFile file:files)
		 {
			  saveData(file);
		 }
		 
		 return  ResponseEntity.status(HttpStatus.OK).body("All Files Uploaded Successfully");
	  }
	 
	@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) 
	{
        // Load file from database
		
        DBFile dbFile = fileRepo.findById(fileId).get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
