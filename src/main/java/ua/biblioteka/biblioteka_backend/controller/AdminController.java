package ua.biblioteka.biblioteka_backend.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.biblioteka.biblioteka_backend.dto.*;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.service.BestsellerService;
import ua.biblioteka.biblioteka_backend.service.BookService;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final BookService bookService;
    private final BestsellerService bestsellerService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create and add new books")
    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> create(@RequestBody BookRequestDto dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add books in Bestseller")
    @PostMapping("/book/bestseller")
    public ResponseEntity<BestsellerDto> create(@RequestBody @Valid BestsellerRequestDto dto) {
        return new ResponseEntity<>(bestsellerService.create(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book")
    @PutMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable String id, @RequestBody @Valid BookRequestDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book in bestseller")
    @PutMapping("/book/bestseller/{id}")
    public ResponseEntity<BestsellerDto> update(@PathVariable String id, @RequestBody @Valid BestsellerRequestDto dto) {
        return ResponseEntity.ok(bestsellerService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete book from bestseller")
    @DeleteMapping("/book/bestseller/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        bestsellerService.remove(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete book")
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}

