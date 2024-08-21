package com.uade.tpo.g11.ecommerce.ecommerce.service;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Favorito;
import com.uade.tpo.g11.ecommerce.ecommerce.repository.favoritoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class FavoritoService {
    @Autowired
    private favoritoRepository favoritoRepository;

    public FavoritoService() {
    }

    public List<Favorito> findAll() {
        return this.favoritoRepository.findAll();
    }
}