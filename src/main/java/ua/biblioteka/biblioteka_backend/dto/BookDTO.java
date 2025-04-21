package ua.biblioteka.biblioteka_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String title;
    private String author;
//    private List<String> imageIds;
    private String publisher;
    private int year;
    private String description;
    private Category category;
    private List<Subcategory> subcategories;
    private int ageRestriction;
    private BigDecimal price;
    private int quantity;
    private Language language;
    private List<ImageDTO> images;
}
