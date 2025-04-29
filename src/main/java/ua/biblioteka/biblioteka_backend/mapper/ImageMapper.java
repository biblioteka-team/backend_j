package ua.biblioteka.biblioteka_backend.mapper;

import org.springframework.stereotype.Component;
import ua.biblioteka.biblioteka_backend.dto.ImageRequestDto;
import ua.biblioteka.biblioteka_backend.dto.ImageResponseDto;
import ua.biblioteka.biblioteka_backend.entity.Image;

@Component
public class ImageMapper {
    public ImageResponseDto toResponseDto(Image image) {
        if (image == null) return null;

        ImageResponseDto dto = new ImageResponseDto();
        dto.setId(image.getId());
        dto.setUrl(image.getUrl());
        dto.setPublicId(image.getPublicId());

        return dto;
    }

    public Image toEntity(ImageRequestDto dto) {
        if (dto == null) return null;

        Image image = new Image();
        image.setId(dto.getId());

        return image;
    }

}
