package ua.biblioteka.biblioteka_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.biblioteka.biblioteka_backend.dto.BestsellerDto;
import ua.biblioteka.biblioteka_backend.dto.BestsellerRequestDto;

public interface BestsellerService {
    BestsellerDto create(BestsellerRequestDto dto);
    Page<BestsellerDto> getAll(Pageable pageable);
    BestsellerDto getByIdBes(String id);
    void remove(String id);
    BestsellerDto update(String id, BestsellerRequestDto dto);
}
