package com.pragma.biblioteca.service;

import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.mapper.EditorialMapper;
import com.pragma.biblioteca.repository.EditorialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditorialServiceTests {


    @Mock
    private EditorialRepository editorialRepository;

    @Mock
    private EditorialMapper mapper;

    @InjectMocks
    private EditorialService editorialService;

    private EditorialDTO editorialDTO;
    private Editorial editorial;
    @BeforeEach
    void setUp() {
        editorialDTO= EditorialDTO.builder()
                .nombre("Editorial Babel")
                .descripcion("Editorial Colombiana dedicada a la distribucion de todo tipo de libros")
                .build();

        editorial= Editorial.builder()
                .id(1l)
                .nombre("Editorial Babel")
                .descripcion("Editorial Colombiana dedicada a la distribucion de todo tipo de libros")
                .build();
    }

    @DisplayName("Validar listar editoriales exitoso")
    @Test
    void testListarEditorialesExito(){
        Editorial editorial1=Editorial.builder()
                .id(2l)
                .nombre("Ediciones Karin")
                .descripcion("Editorial dedicada a la traduccion de libros de otros paises")
                .build();

        EditorialDTO editorialDTO1=EditorialDTO.builder()
                .nombre("Ediciones Karin")
                .descripcion("Editorial dedicada a la traduccion de libros de otros paises")
                .build();

        when(editorialRepository.findAll()).thenReturn(List.of(editorial,editorial1));
        when(mapper.toEditorialesDTO(List.of(editorial,editorial1))).thenReturn(List.of(editorialDTO,editorialDTO1));

        List<EditorialDTO> editorialDTOLista = editorialService.verEditoriales();
        assertThat(editorialDTOLista).isNotNull();
        assertThat(editorialDTOLista).hasSize(2);
    }

    @DisplayName("Listar una Editorial Exito")
    @Test
    void testListarUnaEditorialConIdExistente(){
        when(editorialRepository.findById(1l)).thenReturn(Optional.of(editorial));
        when(mapper.toEditorialDTO(editorial)).thenReturn(editorialDTO);

        EditorialDTO resultado= editorialService.verEditorial(1l);

        assertThat(resultado).isNotNull();
    }

    @DisplayName("Listar una Editorial Error")
    @Test
    void testListarUnaEditorialConIdNoExistente(){
        when(editorialRepository.findById(999l)).thenReturn(Optional.empty());

        assertThatThrownBy(()->{
            editorialService.verEditorial(999l);
        }).isInstanceOf(ValidacionException.class).hasMessage("No existe ningun editorial con este id en la base de datos");
    }


    @DisplayName("Validar creacion de editorial exitosa")
    @Test
    void testCrearEditorialValidarExito(){
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);//simular que no hubo errores en el valid
        when(editorialRepository.existsByNombre(editorialDTO.getNombre())).thenReturn(false);
        when(mapper.toEditorial(editorialDTO)).thenReturn(editorial);
        when(editorialRepository.save(editorial)).thenReturn(editorial);
        when(mapper.toEditorialDTO(editorial)).thenReturn(editorialDTO);

        EditorialDTO editorialDTOGuardada=editorialService.crearEditorial(editorialDTO,bindingResult);

        assertThat(editorialDTOGuardada).isNotNull();
    }


    @DisplayName("Listar una Editorial Error")
    @Test
    void testActualizarUnaEditorialConIdExistente(){

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(editorialRepository.existsById(1l)).thenReturn(true);
        when(editorialRepository.existsByNombre(editorialDTO.getNombre())).thenReturn(false);
        when(editorialRepository.save(editorial)).thenReturn(editorial);
        when(mapper.toEditorialConID(editorialDTO,1l)).thenReturn(editorial);
        when(mapper.toEditorialDTO(editorial))
                .thenReturn(EditorialDTO.builder()
                    .nombre("nombre-update")
                    .descripcion("descripcion-update")
                    .build());

        EditorialDTO resultado= editorialService.actualizarEditorial(1l,editorialDTO,bindingResult);

        assertThat(resultado.getNombre()).isEqualTo("nombre-update");
    }

    @DisplayName("Validar que borre una editorial")
    @Test
    void testBorrarEditorial() {
        Long id = 1L;

        when(editorialRepository.existsById(id)).thenReturn(true);

        editorialService.borrarEditorial(id);

        verify(editorialRepository).existsById(id);
        verify(editorialRepository).deleteById(id);
    }

    @DisplayName("Validar que tire error al pasar un nombre repetido")
    @Test
    void testNombreEditorialRepetido(){
        when(editorialRepository.existsByNombre(editorialDTO.getNombre())).thenReturn(true);
        assertThatThrownBy(()->{
            editorialService.validacionEditorial(editorialDTO);
        }).isInstanceOf(ValidacionException.class).hasMessage("Este nombre ya existe");
    }


}
