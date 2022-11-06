package com.image.services;

import java.io.IOException;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.entities.Image;
import com.image.entities.User;
import com.image.repositories.ImageRepository;
import com.image.repositories.UserRepository;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

@Service
public class imageServiceImplementation implements imageService {
	
	@Autowired
	private ImageRepository imageRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public String uploadImage(MultipartFile file) {
		
		String encodeBase64String=null;
		HttpResponse<JsonNode> asJson=null;
		JSONObject jsonObject = null; 
		try {
			
			encodeBase64String = Base64.encodeBase64String(file.getBytes());
			asJson = Unirest.post("https://api.imgur.com/3/image")
					.header("Authorization", "Bearer b736efc8d81f8e284fbc2530a35c9ba3d95bd2e2")
					.multiPartContent()
					.field("image", encodeBase64String)
					.asJson();
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		System.out.println(asJson.getBody().getObject().getJSONObject("data").getString("id"));
		
		Image image = new Image();
		image.setId(asJson.getBody().getObject().getJSONObject("data").getString("id"));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username=auth.getName();
		image.setUserId(username);
		
		imageRepo.save(image);
		System.out.println(asJson.getBody());
		return "Image uploaded successfully with image id : "+asJson.getBody().getObject().getJSONObject("data").getString("id");
		
	}

	@Override
	public String viewImage(String imageId) {
		
		HttpResponse<JsonNode> response = Unirest.get("https://api.imgur.com/3/image/"+imageId)
									.header("Authorization", "Bearer b736efc8d81f8e284fbc2530a35c9ba3d95bd2e2")
									.asJson();
		
		Optional<Image> image = imageRepo.findById(imageId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username=auth.getName();
	    
		if(image.isEmpty())
		{
			return null;
		}
	    
		if(image.get().getUserId().equals(username))
		{
			return response.getBody().getObject().getJSONObject("data").getString("link");
		}
			
		return null;
	}

	@Override
	public String deleteImage(String imageId) {
		
		HttpResponse<JsonNode> response = Unirest.delete("https://api.imgur.com/3/image/"+imageId)
		  .header("Authorization", "Bearer b736efc8d81f8e284fbc2530a35c9ba3d95bd2e2")
		  .multiPartContent()
		  .asJson();
		
		Optional<Image> image = imageRepo.findById(imageId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username=auth.getName();
	    
		if(image.isEmpty()) {
			return null;
		}
		
		if(image.get().getUserId().equals(username))
		{
			return "deleted";
		}
		
		return null;
	}

}
