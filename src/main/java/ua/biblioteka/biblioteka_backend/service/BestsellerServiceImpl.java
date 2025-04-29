package ua.biblioteka.biblioteka_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.biblioteka.biblioteka_backend.dao.BestsellerRepository;
import ua.biblioteka.biblioteka_backend.dto.BestsellerDto;
import ua.biblioteka.biblioteka_backend.dto.BestsellerRequestDto;
import ua.biblioteka.biblioteka_backend.entity.Bestseller;
import ua.biblioteka.biblioteka_backend.mapper.BestsellerMapper;

@Service
@RequiredArgsConstructor
public class BestsellerServiceImpl implements BestsellerService{

    private final BestsellerRepository bestsellerRepository;
    private final BestsellerMapper bestsellerMapper;

    @Override
    public BestsellerDto create(BestsellerRequestDto dto) {
        Bestseller bestseller = bestsellerMapper.toEntity(dto);
        return bestsellerMapper.toDto(bestsellerRepository.save(bestseller));
    }

    @Override
    public Page<BestsellerDto> getAll(Pageable pageable) {
        return bestsellerRepository.findAll(pageable)
                .map(bestsellerMapper::toDto);
    }

    @Override
    public BestsellerDto getByIdBes(String id) {
        Bestseller bestseller = bestsellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bestseller not found with id: " + id));
        return bestsellerMapper.toDto(bestseller);
    }

    @Override
    public void remove(String id) {
        bestsellerRepository.deleteById(id);
    }

    @Override
    public BestsellerDto update(String id, BestsellerRequestDto dto) {
        Bestseller existing = bestsellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bestseller not found with id: " + id));

        existing.setBookIds(dto.getBookIds());
        return bestsellerMapper.toDto(bestsellerRepository.save(existing));
    }
}
