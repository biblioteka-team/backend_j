package ua.biblioteka.biblioteka_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestsellerRequestDto {
    private List<String> bookIds;
}
