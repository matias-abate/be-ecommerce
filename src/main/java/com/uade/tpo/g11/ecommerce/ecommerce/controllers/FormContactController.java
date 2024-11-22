package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.FormContactDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ApiResponseDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.FormContactImagesDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.services.FormContactService;

@RestController
@RequestMapping("/api/formulario")
public class FormContactController {

    @Autowired
    FormContactService formContactService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO> enviarFormulario(
        @RequestParam String nombre,
        @RequestParam String apellido,
        @RequestParam String descripcion,
        @RequestParam String problematica,
        @RequestParam("imagenes") List<MultipartFile> imagenes
    ){
    try {
        // Convertir las imágenes a bytes
        List<byte[]> imagenesBytes = imagenes.stream()
                .map(imagen -> {
                try {
                        return imagen.getBytes();
                } catch (IOException e) {
                        throw new RuntimeException("Error al procesar la imagen", e);
                }
                })
                .collect(Collectors.toList());

        FormContactDTO formContactDTO = new FormContactDTO();
        formContactDTO.setNombre(nombre);
        formContactDTO.setApellido(apellido);
        formContactDTO.setProblematica(problematica);
        formContactDTO.setDescripcion(descripcion);

        FormContactDTO savedForm = formContactService.createFormContact(formContactDTO, imagenesBytes);

        // Creo la respuesta de éxito con el DTO
        ApiResponseDTO response = new ApiResponseDTO(
                "El formulario ha sido registrado correctamente.",
                savedForm
        );
        return ResponseEntity.ok(response);
    } catch (IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDTO(e.getMessage(), null));
    }  catch (Exception e) {
        e.printStackTrace();
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponseDTO("Ocurrió un error inesperado.", null));
}
 }
}
