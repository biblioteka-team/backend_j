package ua.biblioteka.biblioteka_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewArrivalRequestDTO {
    private String bookId;
}
