package ua.biblioteka.biblioteka_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.biblioteka.biblioteka_backend.dto.NewArrivalDto;
import ua.biblioteka.biblioteka_backend.dto.NewArrivalRequestDTO;

public interface NewArrivalService {
    NewArrivalDto create(NewArrivalRequestDTO dto);
    Page<NewArrivalDto> getAll(Pageable pageable);
    NewArrivalDto getByIdNew(String id);
    void remove(String id);
    NewArrivalDto update(String id, NewArrivalRequestDTO dto);
}
