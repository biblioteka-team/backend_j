package ua.biblioteka.biblioteka_backend.mapper;

import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dto.BookDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.Image;

import java.util.Collections;
import java.util.stream.Collectors;

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
        dto.setPublisher(book.getPublisher());
        dto.setYear(book.getYear());
        dto.setDescription(book.getDescription());
        dto.setImageIds(book.getImages() != null
                ? book.getImages().stream().map(Image::getUrl).collect(Collectors.toList())
                : Collections.emptyList());
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
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setDescription(dto.getDescription());
        return book;
    }
}
