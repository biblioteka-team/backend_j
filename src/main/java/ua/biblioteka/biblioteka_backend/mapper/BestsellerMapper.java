package ua.biblioteka.biblioteka_backend.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dto.BestsellerDto;
import ua.biblioteka.biblioteka_backend.dto.BestsellerRequestDto;
import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;
import ua.biblioteka.biblioteka_backend.entity.Bestseller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BestsellerMapper {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BestsellerDto toDto(Bestseller bestseller) {
        List<BookResponseDto> books = bestseller.getBookIds().stream()
                .map(bookRepository::findById)
                .filter(Optional::isPresent)
                .map(book -> bookMapper.toResponseDto(book.get()))
                .collect(Collectors.toList());

        return new BestsellerDto(bestseller.getId(), books);
    }

    public Bestseller toEntity(BestsellerRequestDto dto) {
        return Bestseller.builder()
                .bookIds(dto.getBookIds())
                .build();
    }
}
