package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dto.BookDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;
import ua.biblioteka.biblioteka_backend.mapper.BookMapper;
import ua.biblioteka.biblioteka_backend.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Books")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;


    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(summary = "Get books ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get books from params")
    @GetMapping("/search")
//    public List<BookDTO> searchBooks(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String author,
//            @RequestParam(required = false) Category category,
//            @RequestParam(required = false) Subcategory subcategory,
//            @RequestParam(required = false) BigDecimal price,
//            @RequestParam(required = false) Language language
//    ) {
//      List<Book> books = bookService.searchBooks(title, author, category,subcategory, price, language);
//       List<BookDTO> bookDTOs = books.stream().map(bookMapper::toDTO).collect(Collectors.toList());;
//      return ResponseEntity.ok(bookDTOs);
//        return bookService.searchBooks(title, author, category, subcategory, price, language);
//    }

    public List<BookDTO> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) List<Subcategory> subcategories,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Language language
    ) {
        return bookService.searchBooks(title, author, category, subcategories, price, language);
    }




}
