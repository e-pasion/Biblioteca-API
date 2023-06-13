package com.pragma.biblioteca.service;

import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.mapper.EditorialMapper;
import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.repository.EditorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EditorialService {
    EditorialRepository editorialRepository;
    EditorialMapper mapper;


    public List<EditorialDTO>verEditoriales(){//devolver todas las editoriales
        List<Editorial> editorialList = editorialRepository.findAll();
        List<EditorialDTO> editorialDTOList= mapper.toEditorialesDTO(editorialList);
        return editorialDTOList;
    }

    public EditorialDTO verEditorial(Long id){//devolver una editorial
        Optional<Editorial> resultado=editorialRepository.findById(id);
        Editorial editorial= resultado.orElseThrow(() -> new ValidacionException("No existe ningun editorial con este id en la base de datos"));
        EditorialDTO editorialDTO=mapper.toEditorialDTO(editorial);
        return editorialDTO;
    }

    public EditorialDTO crearEditorial(EditorialDTO editorialDTO,BindingResult bindingResult){//crear una editorial
        if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }validacionEditorial(editorialDTO);
        Editorial editorial=mapper.toEditorial(editorialDTO);
        Editorial resultado=editorialRepository.save(editorial);
        return mapper.toEditorialDTO(resultado);
    }

    public EditorialDTO actualizarEditorial(Long id,EditorialDTO editorialDTO, BindingResult bindingResult){//actualizar una editorial
        if (!editorialRepository.existsById(id)) {
            throw new ValidacionException("Esta editorial no existe");
        }
        else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validacionEditorial(editorialDTO);
        Editorial editorial= mapper.toEditorialConID(editorialDTO,id);
        Editorial resultado=editorialRepository.save(editorial);
        return mapper.toEditorialDTO(resultado);
    }

    public void borrarEditorial(Long id){//borrar una editorial
        if(!editorialRepository.existsById(id)) {
            throw new ValidacionException("Esta editorial no existe");
        }
        editorialRepository.deleteById(id);
    }

    public void validacionEditorial(EditorialDTO editorialDTO){
         if (editorialRepository.existsByNombre(editorialDTO.getNombre())) {
             throw new ValidacionException("Este nombre ya existe");
        }
    }
}
