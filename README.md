# Spring-Boot-Authorized-Image-API

Image API using Springboot and H2 in memory database. API authentication also implemented using Basic Auth.

This API basically provides endpoints for below operations :

1. Register user(http://localhost:8088/register/user): 
    
    This endpoint(POST) basically provides operation for register user to database with thier basic details like first name,lastname, username, password etc.
   
2. Upload new image(http://localhost:8088/image/upload):
    
    This endpoint(POST) basically provides operation for uploading a new image(size less than 10MB) using imgur api, which are integrated internally in this endpoints,    
    where actual image details will be stored. It will return image id to the user which can be used for other operations like view image, delete image.
    
3. View image(http://localhost:8088/image/view):

    This endpoint(GET) basically provides operation for just view the image whatever particular user has uploaded. The upload operation provides one image id, using that
    
    image id in view image operation user can check thier image. It will simply return particular image url in response if input id is correct. 
    
    NOTE: The authenticated users can see only their images not others images.
    
4. Delete image(http://localhost:8088/image/delete):

    This endpoint(DELETE) basically provides operation for just deleting the particular image based upon input image id, which you will get in upload image operation.
    
    This will simply return delete message is successfull or not.
    
    NOTE: The authenticated users can delete their images not others images.
    
