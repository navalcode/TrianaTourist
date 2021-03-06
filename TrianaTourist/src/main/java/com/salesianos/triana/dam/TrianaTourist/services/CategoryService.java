package com.salesianos.triana.dam.TrianaTourist.services;

import com.salesianos.triana.dam.TrianaTourist.dto.category.CategoryDto;
import com.salesianos.triana.dam.TrianaTourist.dto.category.CategoryDtoConverter;

import com.salesianos.triana.dam.TrianaTourist.dto.category.CreateCategoryDto;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianos.triana.dam.TrianaTourist.models.Category;
import com.salesianos.triana.dam.TrianaTourist.models.Poi;
import com.salesianos.triana.dam.TrianaTourist.repositories.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.salesianos.triana.dam.TrianaTourist.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;
    private final PoiRepository poiRepository;

    public List<CategoryDto> findAll() {
        List<Category>data = categoryRepository.findAll();
        if (data.isEmpty()) {
             throw new ListEntityNotFoundException(Category.class);
        }else
            return data.stream().map(categoryDtoConverter::convertToDto).collect(java.util.stream.Collectors.toList());
    }

    public CategoryDto findById(Long id) {
        Category data = categoryRepository.findById(id).orElse(null);
        if (data == null) {
            throw new SingleEntityNotFoundException(id,Category.class);
        }else
            return categoryDtoConverter.convertToDto(data);
    }

    public CategoryDto save(CreateCategoryDto categoryDto) {
        Category category = categoryDtoConverter.convertToEntity(categoryDto);
        return categoryDtoConverter.convertToDto(categoryRepository.save(category));
    }

    public CategoryDto update(Long id, CreateCategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new SingleEntityNotFoundException(id,Category.class);
        }
        category.setName(categoryDto.getName());
        return categoryDtoConverter.convertToDto(categoryRepository.save(category));
    }

    public ResponseEntity<?> deleteById(Long id) {
     if(!categoryRepository.existsById(id)){
        throw new SingleEntityNotFoundException(id,Category.class);
    }
     List<Poi> pois = poiRepository.findAllByCategoryId(id);
    pois.stream().forEach(poi -> poi.setCategory(null));
    pois.stream().forEach(poi -> poiRepository.save(poi));
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public Category findCategoryById(Long id) {
        Category data = categoryRepository.findById(id).orElse(null);
        if (data == null) {
            throw new SingleEntityNotFoundException(id,Category.class);
        }else
            return data;
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
