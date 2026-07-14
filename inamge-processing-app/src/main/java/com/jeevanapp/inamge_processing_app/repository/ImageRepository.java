package com.jeevanapp.inamge_processing_app.repository;

import com.jeevanapp.inamge_processing_app.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository  extends JpaRepository<Image,Long> {
}
