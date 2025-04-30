package ua.biblioteka.biblioteka_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromotionRequestDto {
    private String bookId;
    private int discountPercentage;
}
