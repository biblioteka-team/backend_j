package ua.biblioteka.biblioteka_backend.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dto.*;

import ua.biblioteka.biblioteka_backend.entity.NewArrival;

@Component
@RequiredArgsConstructor
public class NewArrivalMapper {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public NewArrivalDto toDto(NewArrival newArrival) {
        BookResponseDto bookResponseDto = bookMapper.toResponseDto(newArrival.getBook());
        return new NewArrivalDto(newArrival.getId(), bookResponseDto);
    }

    public NewArrival toEntity(NewArrivalRequestDTO dto) {
        return bookRepository.findById(dto.getBookId())
                .map(book -> NewArrival.builder()
                        .book(book)
                        .build())
                .orElse(null);
    }
}
