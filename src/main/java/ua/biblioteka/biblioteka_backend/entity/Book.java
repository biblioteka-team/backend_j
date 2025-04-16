package ua.biblioteka.biblioteka_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private String language;
    private double price;
    private String category;
    private String imageUrl;
}
