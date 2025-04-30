package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dao.PromotionRepository;
import ua.biblioteka.biblioteka_backend.dto.PromotionDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionRequestDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionResponseDto;
import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.Promotion;
import ua.biblioteka.biblioteka_backend.mapper.PromotionMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final BookRepository bookRepository;

    @Override
    public PromotionResponseDto create(PromotionRequestDto dto) {
        Promotion promotion = promotionMapper.toEntity(dto);
        return promotionMapper.toDto(promotionRepository.save(promotion));
    }

    @Override
    public Page<PromotionResponseDto> getAll(Pageable pageable) {
        return promotionRepository.findAll(pageable)
                .map(promotionMapper::toDto);

    }

    @Override
    public PromotionResponseDto findByBookId(String bookId) {
        Promotion promotion = promotionRepository.findByBook_Id(bookId)
                .orElseThrow(() -> new RuntimeException("Promotion not found for book ID: " + bookId));
        return promotionMapper.toDto(promotion);
    }

    @Override
    public PromotionResponseDto update(String id, PromotionRequestDto dto) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + dto.getBookId()));

        promotion.setBook(book);
        promotion.setDiscountPercentage(dto.getDiscountPercentage());

        return promotionMapper.toDto(promotionRepository.save(promotion));
    }

    @Override
    public void deleteById(String id) {
        promotionRepository.deleteById(id);
    }
}
