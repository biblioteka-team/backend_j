package ua.biblioteka.biblioteka_backend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionRequestDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionResponseDto;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.Promotion;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PromotionMapper {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Promotion toEntity(PromotionRequestDto dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + dto.getBookId()));

        return Promotion.builder()
                .book(book)
                .discountPercentage(dto.getDiscountPercentage())
                .build();
    }

    public PromotionResponseDto toDto(Promotion promotion) {
        Book originalBook = promotion.getBook();
        BookResponseDto bookDto = bookMapper.toResponseDto(originalBook);

        // Змінюємо ціну відповідно до знижки
        if (bookDto.getPrice() != null && promotion.getDiscountPercentage() > 0) {
            BigDecimal discount = bookDto.getPrice()
                    .multiply(BigDecimal.valueOf(promotion.getDiscountPercentage()))
                    .divide(BigDecimal.valueOf(100));
            bookDto.setPrice(bookDto.getPrice().subtract(discount));
        }

        return PromotionResponseDto.builder()
                .id(promotion.getId())
                .discountPercentage(promotion.getDiscountPercentage())
                .book(bookDto)
                .build();
    }
}
