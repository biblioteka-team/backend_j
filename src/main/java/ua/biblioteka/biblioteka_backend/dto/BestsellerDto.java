package ua.biblioteka.biblioteka_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestsellerDto {
    private String id;
    private BookResponseDto book;
}
