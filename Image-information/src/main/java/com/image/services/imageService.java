package com.image.services;

import org.springframework.web.multipart.MultipartFile;

import kong.unirest.JsonNode;

public interface imageService {
	
	JsonNode uploadImage(MultipartFile file);
	
	JsonNode viewImage(String imageId);
	
	JsonNode deleteImage(String imageId);

}
