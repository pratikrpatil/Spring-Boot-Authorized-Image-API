package com.image.services;

import org.springframework.web.multipart.MultipartFile;

public interface imageService {
	
	void uploadImage(MultipartFile file);
	
	void viewImage(String imageId);
	
	void deleteImage(String imageId);

}
