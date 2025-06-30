package ua.biblioteka.biblioteka_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ua.biblioteka.biblioteka_backend.dto.BestsellerDto;
import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;

import ua.biblioteka.biblioteka_backend.dto.NewArrivalDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionResponseDto;
import ua.biblioteka.biblioteka_backend.enums.Category;
import ua.biblioteka.biblioteka_backend.enums.Language;
import ua.biblioteka.biblioteka_backend.enums.Subcategory;
import ua.biblioteka.biblioteka_backend.mapper.BookMapper;
import ua.biblioteka.biblioteka_backend.service.BestsellerService;
import ua.biblioteka.biblioteka_backend.service.BookService;
import ua.biblioteka.biblioteka_backend.service.NewArrivalService;
import ua.biblioteka.biblioteka_backend.service.PromotionService;

import java.math.BigDecimal;
import java.util.List;


@Tag(name = "Books")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final BestsellerService bestsellerService;
    private final NewArrivalService newArrivalService;
    private final PromotionService promotionService;

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
            @RequestParam(required = false) BigDecimal min,
            @RequestParam (required = false) BigDecimal max,
            @RequestParam(required = false) Integer ageRestriction,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) Language language
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.searchBooks(title, author, category, subcategories, min, max, publisher, ageRestriction, language, pageable);
    }

    @Operation(summary = "Get all books from bestseller")
    @GetMapping("/bestseller")
    public ResponseEntity<Page<BestsellerDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(bestsellerService.getAll(pageable));
    }

    @Operation(summary = "Get books ID from bestseller")
    @GetMapping("/bestseller/{id}")
    public ResponseEntity<BestsellerDto> getByIdBes(@PathVariable String id) {
        return ResponseEntity.ok(bestsellerService.getByIdBes(id));
    }

    @Operation(summary = "Get all books from NewArrival")
    @GetMapping("/new")
    public ResponseEntity<Page<NewArrivalDto>> getAllNew(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(newArrivalService.getAll(pageable));
    }

    @Operation(summary = "Get books ID from NewArrival")
    @GetMapping("/new/{id}")
    public ResponseEntity<NewArrivalDto> getByIdNew(@PathVariable String id) {
        return ResponseEntity.ok(newArrivalService.getByIdNew(id));
    }

    @Operation(summary = "Get books from Promotion")
    @GetMapping("/promotion")
    public ResponseEntity<Page<PromotionResponseDto>> getAllPro(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(promotionService.getAll(pageable));
    }

    @Operation(summary = "Get books ID from Promotion")
    @GetMapping("/promotion/{id}")
    public ResponseEntity<PromotionResponseDto> getByBookId(@PathVariable String bookId) {
        return ResponseEntity.ok(promotionService.findByBookId(bookId));
    }



}
