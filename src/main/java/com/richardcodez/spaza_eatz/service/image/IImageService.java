package com.richardcodez.spaza_eatz.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.richardcodez.spaza_eatz.model.Image;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageByiId(Long id);
    Image saveImage(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
