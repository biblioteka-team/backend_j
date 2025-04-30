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
import ua.biblioteka.biblioteka_backend.service.NewArrivalService;
import ua.biblioteka.biblioteka_backend.service.PromotionService;

@Tag(name = "Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final BookService bookService;
    private final BestsellerService bestsellerService;
    private final NewArrivalService newArrivalService;
    private final PromotionService promotionService;

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
    @Operation(summary = "Add books in NewArrival")
    @PostMapping("/book/new")
    public ResponseEntity<NewArrivalDto> create(@RequestBody @Valid NewArrivalRequestDTO dto) {
        return new ResponseEntity<>(newArrivalService.create(dto), HttpStatus.CREATED);
    }

    @PostMapping("/book/promotion")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add books in Promotion")
    public ResponseEntity<PromotionResponseDto> create(@RequestBody @Valid PromotionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book")
    @PutMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable String id, @RequestBody @Valid BookRequestDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book in NewArrival")
    @PutMapping("/book/new/{id}")
    public ResponseEntity<NewArrivalDto> update(@PathVariable String id, @RequestBody @Valid NewArrivalRequestDTO dto) {
        return ResponseEntity.ok(newArrivalService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book in bestseller")
    @PutMapping("/book/bestseller/{id}")
    public ResponseEntity<BestsellerDto> update(@PathVariable String id, @RequestBody @Valid BestsellerRequestDto dto) {
        return ResponseEntity.ok(bestsellerService.update(id, dto));
    }

    @PutMapping("/book/promotion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Put book in Promotion")
    public ResponseEntity<PromotionResponseDto> update(@PathVariable String id,
                                                       @RequestBody @Valid PromotionRequestDto dto) {
        return ResponseEntity.ok(promotionService.update(id, dto));
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

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete book from NewArrival")
    @DeleteMapping("/book/new/{id}")
    public ResponseEntity<Void> removeNew(@PathVariable String id) {
        newArrivalService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/book/promotion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProm(@PathVariable String id) {
        promotionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}

