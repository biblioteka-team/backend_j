package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.biblioteka.biblioteka_backend.dao.BookRepository;
import ua.biblioteka.biblioteka_backend.dao.NewArrivalRepository;
import ua.biblioteka.biblioteka_backend.dto.NewArrivalDto;
import ua.biblioteka.biblioteka_backend.dto.NewArrivalRequestDTO;


import ua.biblioteka.biblioteka_backend.entity.Book;
import ua.biblioteka.biblioteka_backend.entity.NewArrival;
import ua.biblioteka.biblioteka_backend.mapper.NewArrivalMapper;

@Service
@RequiredArgsConstructor
public class NewArrivalServiceImp implements NewArrivalService{

    private final NewArrivalRepository newArrivalRepository;
    private final NewArrivalMapper newArrivalMapper;
    private final BookRepository bookRepository;


    @Override
    public NewArrivalDto create(NewArrivalRequestDTO dto) {
        NewArrival newArrival = newArrivalMapper.toEntity(dto);
        return newArrivalMapper.toDto(newArrivalRepository.save(newArrival));
    }

    @Override
    public Page<NewArrivalDto> getAll(Pageable pageable) {
        return newArrivalRepository.findAll(pageable)
                .map(newArrivalMapper::toDto);
    }

    @Override
    public NewArrivalDto getByIdNew(String id) {
        NewArrival newArrival = newArrivalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NewArrival not found with id: " + id));
        return newArrivalMapper.toDto(newArrival);
    }

    @Override
    public void remove(String id) {
        newArrivalRepository.deleteById(id);
    }

    @Override
    public NewArrivalDto update(String id, NewArrivalRequestDTO dto) {
        NewArrival existing = newArrivalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NewArrival not found with id: " + id));

        Book bookToUpdate = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + dto.getBookId()));


        existing.setBook(bookToUpdate);


        return newArrivalMapper.toDto(newArrivalRepository.save(existing));
    }
}
