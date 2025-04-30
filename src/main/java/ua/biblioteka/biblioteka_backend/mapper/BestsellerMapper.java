package ua.biblioteka.biblioteka_backend.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dto.BestsellerDto;
import ua.biblioteka.biblioteka_backend.dto.BestsellerRequestDto;
import ua.biblioteka.biblioteka_backend.dto.BookResponseDto;
import ua.biblioteka.biblioteka_backend.entity.Bestseller;



@Component
@RequiredArgsConstructor
public class BestsellerMapper {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BestsellerDto toDto(Bestseller bestseller) {
        BookResponseDto bookResponseDto = bookMapper.toResponseDto(bestseller.getBook());
        return new BestsellerDto(bestseller.getId(), bookResponseDto);
    }

    public Bestseller toEntity(BestsellerRequestDto dto) {
        return bookRepository.findById(dto.getBookId())
                .map(book -> Bestseller.builder()
                        .book(book)
                        .build())
                .orElse(null);
    }



}
