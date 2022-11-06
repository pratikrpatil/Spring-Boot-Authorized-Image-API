package com.image.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.image.entities.Image;

public interface ImageRepository extends JpaRepository<Image, String> {

}
