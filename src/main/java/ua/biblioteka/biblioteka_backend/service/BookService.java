package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dao.ImageRepository;

import ua.biblioteka.biblioteka_backend.dto.BookRequestDto;
import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;

import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.Image;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;
import ua.biblioteka.biblioteka_backend.mapper.BookMapper;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ImageRepository imageRepository;
    private final BookMapper bookMapper;



    @Transactional
    public BookResponseDto createBook(BookRequestDto requestDto) {

        List<String> imageIds = requestDto.getImages().stream()
                .map(img -> img.getId())
                .collect(Collectors.toList());

        // Завантажуємо всі зображення за списком id
        List<Image> images = imageRepository.findAllById(imageIds);

        // Перевірка: чи всі id знайдені
        if (images.size() != imageIds.size()) {
            throw new IllegalArgumentException("One or more images not found for the provided ids.");
        }

        // Перетворюємо DTO в Entity
        Book book = bookMapper.toEntity(requestDto);

        book.setImages(images);

        // Зберігаємо книгу
        Book savedBook = bookRepository.save(book);

        // Перетворюємо назад в DTO
        return bookMapper.toResponseDto(savedBook);
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDto> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toResponseDto);

//        return bookRepository.findAll(pageable).map(bookMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public BookResponseDto getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return bookMapper.toResponseDto(book);
    }


    public Page<BookResponseDto> searchBooks(String title,
                                     String author, Category category,
                                     List<Subcategory> subcategories, BigDecimal price,
                                     Language language, Pageable pageable) {
        Page<Book> books = bookRepository.searchBooks(title, author,
                category, subcategories, price, language, pageable);
        return books.map(bookMapper::toResponseDto);

    }



    @Transactional
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public BookResponseDto updateBook(String id, BookRequestDto dto) {
        Book book = bookRepository.findById(id)
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));


        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
//        book.setImages(images);
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setDescription(dto.getDescription());
        book.setCategory(dto.getCategory());
        book.setSubcategories(dto.getSubcategories());
        book.setAgeRestriction(dto.getAgeRestriction());
        book.setPrice(dto.getPrice());
        book.setQuantity(dto.getQuantity());
        book.setLanguage(dto.getLanguage());

        if (dto.getImages() != null) {
            List<Image> images = dto.getImages().stream()
                    .map(imageId -> {
                        Image image = new Image();
                        image.setId(String.valueOf(id));
                        return image;
                    })
                    .collect(Collectors.toList());
            book.setImages(images);
        }

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(updatedBook);
    }

}




