package com.image.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.services.imageService;

@RestController()
@RequestMapping("/image")
public class imageController {
	
	@Autowired
	private imageService imageService;
	
	@GetMapping("/view")
	public String viewImage(@RequestParam("imageId") String imageId)
	{
		imageService.viewImage(imageId);
		return "Get image view is successfull.";
	}
	
	@PostMapping("/upload")
	public String uploadNewImage(@RequestParam("image") MultipartFile image)
	{
		imageService.uploadImage(image);
		return "file uploaded";
	}
	
	@DeleteMapping("/delete")
	public String deleteImage(@RequestParam("imageId") String imageId)
	{
		imageService.deleteImage(imageId);
		return "file deleted successfully";
	}
	
}
