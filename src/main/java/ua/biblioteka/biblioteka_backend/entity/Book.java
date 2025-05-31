package ua.biblioteka.biblioteka_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.biblioteka.biblioteka_backend.dto.ImageDTO;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.List;

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
    private List<Image> images;
    private String publisher;
    private int year;
    private String description;


    private Category category;


    private List<Subcategory> subcategories;

    private Integer ageRestriction;
    private BigDecimal price;
    private int quantity;


    private List<Language> languages;
}
