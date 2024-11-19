package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.FormContactDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.FormContactEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.FormContactImagesEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormContactMapper {

    public static FormContactDTO toDTO(FormContactEntity formContactEntity) {
    FormContactDTO formContactDTO = new FormContactDTO();
    formContactDTO.setId(formContactEntity.getId());
    formContactDTO.setNombre(formContactEntity.getNombre());
    formContactDTO.setApellido(formContactEntity.getApellido());
    formContactDTO.setProblematica(formContactEntity.getProblematica());
    formContactDTO.setDescripcion(formContactEntity.getDescripcion());

    if (formContactEntity.getImagenes() != null) {
        formContactDTO.setImagenes(
            formContactEntity.getImagenes()
                .stream()
                .map(FormContactImagesMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    return formContactDTO;
}


    public FormContactEntity toEntity(FormContactDTO formContactDTO, List<byte[]> imagenesBytes) {
        FormContactEntity formContactEntity = new FormContactEntity();
        formContactEntity.setId(formContactDTO.getId());
        formContactEntity.setNombre(formContactDTO.getNombre());
        formContactEntity.setApellido(formContactDTO.getApellido());
        formContactEntity.setProblematica(formContactDTO.getProblematica());
        formContactEntity.setDescripcion(formContactDTO.getDescripcion());

        // Convertir lista de byte[] a lista de ImagenEntity
        if (imagenesBytes != null) {
            List<FormContactImagesEntity> FormContactImagesEntity = imagenesBytes
                .stream()
                .map(FormContactImagesMapper::toEntity)
                .collect(Collectors.toList());
            formContactEntity.setImagenes(FormContactImagesEntity);
        }

        return formContactEntity;
    }
}
