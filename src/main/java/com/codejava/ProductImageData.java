package com.codejava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

public class ProductImageData {

	public static String ImageUploade(MultipartFile image, HttpSession httpSession ) throws IOException {
		if(!image.isEmpty()) {
			
			String filename = image.getOriginalFilename();
			byte[] data = image.getBytes();
			String path = httpSession.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "image"+ File.separator + filename;
			System.out.println("path : " + path);
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			return filename;
		}		
		return null;
		
	}
	
}
