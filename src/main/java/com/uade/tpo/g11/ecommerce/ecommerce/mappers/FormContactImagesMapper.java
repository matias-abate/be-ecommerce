package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.FormContactImagesDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.FormContactImagesEntity;

public class FormContactImagesMapper {

    public static FormContactImagesDTO toDTO(FormContactImagesEntity entity) {
        FormContactImagesDTO dto = new FormContactImagesDTO();
        dto.setId(entity.getId());
        dto.setFormContactId(entity.getFormContact().getId()); // Asegúrate de que `FormContact` tenga un `getId`
        dto.setImagen(entity.getImagen());
        return dto;
    }

    public static FormContactImagesEntity toEntity(byte[] imagen) {
        FormContactImagesEntity entity = new FormContactImagesEntity();
        entity.setImagen(imagen);
        return entity;
    }
}
