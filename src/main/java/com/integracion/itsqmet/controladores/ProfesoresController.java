package com.integracion.itsqmet.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.integracion.itsqmet.entidades.Materias;
import com.integracion.itsqmet.entidades.Profesores;
import com.integracion.itsqmet.repositorios.MateriasRepository;

@RestController
public class ProfesoresController {

    @Autowired
    ProfesorRepository profesorRepository;

    @GetMapping("/profesores")
    public List<Profesores> profesores() {
        List<Profesores> profesores = profesorRepository.findAll();
        return profesores;
    }

    
    @PostMapping("/profesores")
    public Profesores create(@RequestBody Profesores profesor) {
        return profesorRepository.save(profesor);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Boolean> deleteProfesor(@PathVariable int id) {

        Optional<Profesores> profesor = profesorRepository.findById(id);

        if (profesor.isPresent()) {
            profesorRepository.delete(profesor.get()); // Eliminar el autor de la base de datos
            return ResponseEntity.ok(true); // Devolver éxito
        } else {
            return ResponseEntity.ok(false); // Devolver falso si el autor no existe
        }

    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesores> updateProfesor(@PathVariable int id, @RequestBody Profesores profesorData) {
        // Buscar el autor por ID
        Optional<Profesores> optionalProfesor = profesorRepository.findById(id);

        // Verificar si el autor existe
        if (optionalProfesor.isPresent()) {
            Profesores profesor = optionalProfesor.get();

            // Actualizar los campos del autor con los datos proporcionados
            profesor.setNombreProfesor(profesorData.getNombreProfesor());
            profesor.setApellidoProfesor(profesorData.getApellidoProfesor());
            profesor.setMateriaProfesor(profesorData.getMateriaProfesor());
            profesor.setCorreoProfesor(profesorData.getCorreoProfesor());

            // Guardar los cambios en la base de datos
            Profesores profesorSaved = profesorRepository.save(profesor);
            return ResponseEntity.ok(profesorSaved); // Devolver el autor actualizado
        } else {
            return ResponseEntity.notFound().build(); // Devolver error si el autor no existe
        }
    }

}

