package ua.biblioteka.biblioteka_backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookRequestDto {
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private Category category;
    private List<Subcategory> subcategories;
    private int ageRestriction;
    private BigDecimal price;
    private int quantity;

    @NotEmpty(message = "At least one language is required")
    private List<Language> languages;

    private List<ImageRequestDto> images;
}
