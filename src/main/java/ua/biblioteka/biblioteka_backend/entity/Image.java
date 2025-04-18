package ua.biblioteka.biblioteka_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "images")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    private String id;

    private String url;
    private String publicId;
}
