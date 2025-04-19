package ua.biblioteka.biblioteka_backend.mapper;

import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dto.BookDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
//        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setCategory(book.getCategory());
        dto.setSubcategories(book.getSubcategories());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setLanguage(book.getLanguage());
        dto.setAgeRestriction(book.getAgeRestriction());
//        dto.setImageIds(book.getImages());
        return dto;
    }

    public Book toEntity(BookDTO dto) {
        Book book = new Book();
//        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setSubcategories(dto.getSubcategories());
        book.setPrice(dto.getPrice());
        book.setQuantity(dto.getQuantity());
        book.setLanguage(dto.getLanguage());
        book.setAgeRestriction(dto.getAgeRestriction());
//        book.setImages(dto.());
        return book;
    }
}
