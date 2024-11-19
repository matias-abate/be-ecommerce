package com.uade.tpo.g11.ecommerce.ecommerce.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.FormContactMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IFormContactRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IFormContactImagesRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.FormContactDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.FormContactEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.FormContactImagesEntity;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormContactService {

    @Autowired
    IFormContactRepository FormContactRepository;

    @Autowired
    IFormContactImagesRepository FormContactImagesRepository;

    @Transactional
    public FormContactDTO createFormContact(FormContactDTO FormContactDTO, List<byte[]> imagenes) throws IOException {
        // Creo y guardo el formulario
        FormContactEntity FormContactEntity = new FormContactEntity();
        FormContactEntity.setNombre(FormContactDTO.getNombre());
        FormContactEntity.setApellido(FormContactDTO.getApellido());
        FormContactEntity.setProblematica(FormContactDTO.getProblematica());
        FormContactEntity.setDescripcion(FormContactDTO.getDescripcion());

        FormContactEntity = FormContactRepository.saveAndFlush(FormContactEntity);
        System.out.println("Formulario: " + FormContactEntity);

        // Guardar las imágenes asociadas
        if (imagenes != null && !imagenes.isEmpty()) {
            for (byte[] imagenBytes : imagenes) {
                FormContactImagesEntity imagenEntity = new FormContactImagesEntity();
                imagenEntity.setFormContact(FormContactEntity); 
                imagenEntity.setImagen(imagenBytes);
                System.out.println("Guardando imagen: " + imagenEntity);
                FormContactImagesRepository.save(imagenEntity);
            }
        }

        return FormContactMapper.toDTO(FormContactEntity);
    }

}
