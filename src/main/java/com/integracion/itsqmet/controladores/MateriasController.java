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
import com.integracion.itsqmet.repositorios.MateriasRepository;

@RestController
public class MateriasController {

    @Autowired
    MateriasRepository materiasRepository;

    @GetMapping("/materias")
    public List<Materias> materias() {
        List<Materias> materias = materiasRepository.findAll();
        return materias;
    }

    
    @PostMapping("/materias")
    public Materias create(@RequestBody Materias materia) {
        return materiasRepository.save(materia);
    }

    @DeleteMapping("/materias/{id}")
    public ResponseEntity<Boolean> deleteMateria(@PathVariable int id) {

        Optional<Materias> materia = materiasRepository.findById(id);

        if (materia.isPresent()) {
            materiasRepository.delete(materia.get()); // Eliminar el autor de la base de datos
            return ResponseEntity.ok(true); // Devolver éxito
        } else {
            return ResponseEntity.ok(false); // Devolver falso si el autor no existe
        }

    }

    @PutMapping("/materias/{id}")
    public ResponseEntity<Materias> updateUser(@PathVariable int id, @RequestBody Materias materiaData) {
        // Buscar el autor por ID
        Optional<Materias> optionalMateria = materiasRepository.findById(id);

        // Verificar si el autor existe
        if (optionalMateria.isPresent()) {
            Materias materia = optionalMateria.get();

            // Actualizar los campos del autor con los datos proporcionados
            materia.setNombreMateria(materiaData.getNombreMateria());
            materia.setNota(materiaData.getNota());

            // Guardar los cambios en la base de datos
            Materias materiaSaved = materiasRepository.save(materia);
            return ResponseEntity.ok(materiaSaved); // Devolver el autor actualizado
        } else {
            return ResponseEntity.notFound().build(); // Devolver error si el autor no existe
        }
    }

}
