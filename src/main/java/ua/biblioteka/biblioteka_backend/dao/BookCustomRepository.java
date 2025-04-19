package ua.biblioteka.biblioteka_backend.dao;

import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;

import java.math.BigDecimal;
import java.util.List;

public interface BookCustomRepository {
    List<Book> searchBooks(String name, String author, Category category, Subcategory subcategory,
                           BigDecimal price, Language language);

}
