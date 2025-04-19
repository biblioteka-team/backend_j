package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.biblioteka.biblioteka_backend.dao.ImageRepository;
import ua.biblioteka.biblioteka_backend.entity.Image;
import ua.biblioteka.biblioteka_backend.service.CloudinaryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Images")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add images only formats: .jpeg, .png, .webp and size max 5MB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Images uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @Parameter(description = "List of image files")
            @RequestParam("files") List<MultipartFile> files
    ) throws IOException {
        List<String> allowedContentTypes = List.of("image/jpeg", "image/png", "image/webp");
        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) {

            String contentType = file.getContentType();
            if (contentType == null || !allowedContentTypes.contains(contentType)) {
                return ResponseEntity.badRequest().body("Unsupported file type: " + file.getOriginalFilename());
            }

            var result = cloudinaryService.uploadImage(file);

           Image image = Image.builder()
                    .url(result.get("url"))
                    .publicId(result.get("publicId"))
                    .build();

            Image savedImage = imageRepository.save(image);
            images.add(savedImage);
        }

        return ResponseEntity.ok(images);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all images")
    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get images ID")
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable String id) {
        return imageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete images from database and cloudinary")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable String id) throws IOException {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isEmpty()) return ResponseEntity.notFound().build();

        Image image = imageOpt.get();
        cloudinaryService.deleteImage(image.getPublicId());
        imageRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
