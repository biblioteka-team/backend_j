package ua.biblioteka.biblioteka_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biblioteka.biblioteka_backend.dto.PromotionDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionRequestDto;
import ua.biblioteka.biblioteka_backend.dto.PromotionResponseDto;

import java.util.List;

public interface PromotionService {
    PromotionResponseDto create(PromotionRequestDto dto);
    Page<PromotionResponseDto> getAll(Pageable pageable);
    PromotionResponseDto findByBookId(String bookId);
    PromotionResponseDto update(String id, PromotionRequestDto dto);
    void deleteById(String id);
}
