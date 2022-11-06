package com.image.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.services.imageService;

import kong.unirest.JsonNode;

@RestController()
@RequestMapping("/image")
public class imageController {
	
	@Autowired
	private imageService imageService;
	
	@GetMapping("/view")
	public ResponseEntity<?> viewImage(@RequestParam("imageId") String imageId)
	{
		String response = imageService.viewImage(imageId);
		
		if(response == null)
		{
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Image-link : "+response, HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadNewImage(@RequestParam("image") MultipartFile image)
	{
		String uploadImage = imageService.uploadImage(image);
		return new ResponseEntity<>(uploadImage, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteImage(@RequestParam("imageId") String imageId)
	{
		String response = imageService.deleteImage(imageId);
		
		if(response == null)
		{
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
