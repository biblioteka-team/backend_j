package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dao.ImageRepository;
import ua.biblioteka.biblioteka_backend.dto.BookDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.Image;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;
import ua.biblioteka.biblioteka_backend.mapper.BookMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ImageRepository imageRepository;

    private final BookMapper bookMapper;

//    @Autowired
//    public BookService(BookRepository bookRepository, ImageRepository imageRepository, BookMapper bookMapper) {
//        this.bookRepository = bookRepository;
//        this.imageRepository = imageRepository;
//        this.bookMapper = bookMapper;
//    }

    public Book createBook(BookDTO dto) {
        List<Image> images = imageRepository.findAllById(dto.getImageIds());
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .images(images)
                .publisher(dto.getPublisher())
                .year(dto.getYear())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .subcategories(dto.getSubcategories())
                .ageRestriction(dto.getAgeRestriction())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .language(dto.getLanguage())
                .build();
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }


//    public List<BookDTO> searchBooks(String title, String author, Category category, Subcategory subcategory, BigDecimal price, Language language) {
//        List<Book> books = bookRepository.searchBooks(title, author, category, subcategory, price, language);
//        return books.stream().map(bookMapper::toDTO).toList();
//    }

    public List<BookDTO> searchBooks(String title, String author, Category category, List<Subcategory> subcategories, BigDecimal price, Language language) {
        List<Book> books = bookRepository.searchBooks(title, author, category, subcategories, price, language);
        return books.stream().map(bookMapper::toDTO).toList();
    }




    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(String id, BookDTO dto) {
        Book book = getBookById(id).orElseThrow();
        List<Image> images = imageRepository.findAllById(dto.getImageIds());

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setImages(images);
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setDescription(dto.getDescription());
        book.setCategory(dto.getCategory());
        book.setSubcategories(dto.getSubcategories());
        book.setAgeRestriction(dto.getAgeRestriction());
        book.setPrice(dto.getPrice());
        book.setQuantity(dto.getQuantity());
        book.setLanguage(dto.getLanguage());
        return bookRepository.save(book);
    }



}
