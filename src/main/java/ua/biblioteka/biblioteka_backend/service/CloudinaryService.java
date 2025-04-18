package ua.biblioteka.biblioteka_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(@Value("${CLOUDINARY_NAME}") String cloudName,
                             @Value("${CLOUDINARY_KEY}") String apiKey,
                             @Value("${CLOUDINARY_SECRET}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    public Map<String, String> uploadImage(MultipartFile file) throws IOException {
        var uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return Map.of(
                "url", uploadResult.get("secure_url").toString(),
                "publicId", uploadResult.get("public_id").toString()
        );
    }

    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
