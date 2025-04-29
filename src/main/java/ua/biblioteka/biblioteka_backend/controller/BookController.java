package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;

import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;
import ua.biblioteka.biblioteka_backend.mapper.BookMapper;
import ua.biblioteka.biblioteka_backend.service.BookService;

import java.math.BigDecimal;
import java.util.List;


@Tag(name = "Books")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;


    @Operation(summary = "Get all books")
    @GetMapping
    public Page<BookResponseDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Get books ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable String id) {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);

    }

    @Operation(summary = "Get books from params")
    @GetMapping("/search")
    public Page<BookResponseDto> searchBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,

            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) List<Subcategory> subcategories,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Language language
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.searchBooks(title, author, category, subcategories, price, language, pageable);
    }


}
