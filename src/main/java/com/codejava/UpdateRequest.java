package com.codejava;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
	 private Long id;
	 private String name;
	    private String brand; 
	    private Integer price;
	    @Lob
	    private MultipartFile image;

}
