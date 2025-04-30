package ua.biblioteka.biblioteka_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromotionResponseDto {
    private String id;
    private int discountPercentage;
    private BookResponseDto book;
}
