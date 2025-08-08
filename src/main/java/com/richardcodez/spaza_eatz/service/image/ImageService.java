package com.richardcodez.spaza_eatz.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.richardcodez.spaza_eatz.exceptions.ResourceNotFoundException;
import com.richardcodez.spaza_eatz.model.Image;
import com.richardcodez.spaza_eatz.model.Product;
import com.richardcodez.spaza_eatz.repository.ImageRepository;
import com.richardcodez.spaza_eatz.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public void deleteImageByiId(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository:: delete, () -> { 
            throw new ResourceNotFoundException("No Image found with id: " + id);
         });
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Image found with id: " + id));
    }

    @Override
    public Image saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<Image> imageDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String urlBase = "api/vs/images/image/download/";
                String downloadUrl = urlBase + image.getId();
                image.setDownloadUrl(downloadUrl);

                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(urlBase + savedImage.getId());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileType(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
