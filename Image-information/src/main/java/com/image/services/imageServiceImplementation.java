package com.image.services;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.repositories.ImageRepository;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

@Service
public class imageServiceImplementation implements imageService {
	
	@Autowired
	private ImageRepository imageRepo;

	@Override
	public void uploadImage(MultipartFile file) {
		
		String encodeBase64String=null;
		HttpResponse<JsonNode> asJson=null;
		try {
			encodeBase64String = Base64.encodeBase64String(file.getBytes());
			asJson = Unirest.post("https://api.imgur.com/3/image")
					.header("Authorization", "Bearer b736efc8d81f8e284fbc2530a35c9ba3d95bd2e2")
					.multiPartContent()
					.field("image", encodeBase64String)
					.asJson();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(asJson.getBody());
		
	}

	@Override
	public void viewImage(String imageId) {
		
		HttpResponse<JsonNode> response = Unirest.get("https://api.imgur.com/3/image/"+imageId)
									.header("Authorization", "Bearer b736efc8d81f8e284fbc2530a35c9ba3d95bd2e2")
									.asJson();
		
		System.out.println(response.getBody());
	}

	@Override
	public void deleteImage(String imageId) {
		
		HttpResponse<String> response = Unirest.delete("https://api.imgur.com/3/image/"+imageId)
		  .header("Authorization", "Bearer b736efc8d81f8e284fbc2530a35c9ba3d95bd2e2")
		  .multiPartContent()
		  .asString();
		
		System.out.println(response.getBody());
	}

}
