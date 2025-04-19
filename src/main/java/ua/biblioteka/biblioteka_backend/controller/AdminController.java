package ua.biblioteka.biblioteka_backend.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.biblioteka.biblioteka_backend.dto.BookDTO;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.service.BookService;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create and add new books")
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book")
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable String id, @RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete book")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}

