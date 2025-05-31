package ua.biblioteka.biblioteka_backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.List;

public interface BookCustomRepository {

    Page<Book> searchBooks(String title, String author, Category category, List<Subcategory> subcategories,
                           BigDecimal min, BigDecimal max, String publisher, Integer ageRestriction, List<Language> languages, Pageable pageable);


}
