package com.uade.tpo.g11.ecommerce.ecommerce.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.FormContactDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.FormContactEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.services.FormContactService;

@RestController
@RequestMapping("/api/formulario")
public class FormContactController {

    @Autowired
    FormContactService FormContactService;

    @PostMapping("/create")
    public ResponseEntity<String> enviarFormulario(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String descripcion,
            @RequestParam String problematica,
            @RequestParam("imagenes") List<MultipartFile> imagenes
    ) throws IOException {
        // Convertir las imágenes a listas de bytes
        List<byte[]> imagenesBytes = imagenes.stream()
                .map(imagen -> {
                    try {
                        System.out.println("Tamaño original de la imagen: " + imagen.getSize());
                        byte[] imagenBytes = imagen.getBytes();
                        System.out.println("Tamaño en bytes: " + imagenBytes.length);
                        if (imagenBytes.length > 10 * 1024 * 1024) { // 10 MB límite
                            throw new IllegalArgumentException("La imagen excede el tamaño permitido de 10 MB");
                        }
                        return imagenBytes;
                    } catch (IOException e) {
                        throw new RuntimeException("Error al leer la imagen", e);
                    }
                })
                .collect(Collectors.toList());

        FormContactDTO FormContactDTO = new FormContactDTO();
        FormContactDTO.setNombre(nombre);
        FormContactDTO.setApellido(apellido);
        FormContactDTO.setProblematica(problematica);
        FormContactDTO.setDescripcion(descripcion);

        try {
            FormContactService.createFormContact(FormContactDTO, imagenesBytes);
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al guardar la imagen", e);
        }

        return ResponseEntity.ok("El FormContact ha sido registrado correctamente.");
    }
}
