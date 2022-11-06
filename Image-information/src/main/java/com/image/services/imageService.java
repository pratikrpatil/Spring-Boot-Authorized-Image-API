package com.image.services;

import org.springframework.web.multipart.MultipartFile;

import kong.unirest.JsonNode;

public interface imageService {
	
	String uploadImage(MultipartFile file);
	
	String viewImage(String imageId);
	
	String deleteImage(String imageId);

}
