package ua.biblioteka.biblioteka_backend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import ua.biblioteka.biblioteka_backend.dto.BookRequestDto;
import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;

import ua.biblioteka.biblioteka_backend.entity.Book;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class BookMapper {

    private final ImageMapper imageMapper;

    public BookResponseDto toResponseDto(Book book) {
        if (book == null) return null;

        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setDescription(book.getDescription());
        dto.setPublisher(book.getPublisher());
        dto.setYear(book.getYear());
        dto.setCategory(book.getCategory());
        dto.setSubcategories(book.getSubcategories());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setAgeRestriction(book.getAgeRestriction());
        dto.setLanguage(book.getLanguage());
        dto.setImages(book.getImages().stream()
                .map(imageMapper::toResponseDto)
                .collect(Collectors.toList())
        );
        return dto;
    }

    public Book toEntity(BookRequestDto dto) {
        if (dto == null) return null;

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setCategory(dto.getCategory());
        book.setSubcategories(dto.getSubcategories());
        book.setPrice(dto.getPrice());
        book.setQuantity(dto.getQuantity());
        book.setAgeRestriction(dto.getAgeRestriction());
        book.setLanguage(dto.getLanguage());
        book.setImages(dto.getImages().stream()
                .map(imageMapper::toEntity)
                .collect(Collectors.toList())
        );
        return book;
    }


}
