package ua.biblioteka.biblioteka_backend.dto;

import lombok.Data;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookResponseDto {
    private String id;
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
    private Language language;
    private List<ImageResponseDto> images;
}
