package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController { // RestController para consumir servicios, ya no una aplicacion web

	@Autowired
	private AlumnoService service;

	@GetMapping
	public ResponseEntity<List<Alumno>> listaAlumno() {
		System.out.println(">>>> lista ");
		List<Alumno> lstAlumno = service.listaAlumno();

		return ResponseEntity.ok(lstAlumno); // para convertir nuestro Arraylist en Json
	}

	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj) { // indica que Alumno va a venir en formato Json
		System.out.println(">>>> Registro!! ");
		Alumno objSalida = service.insertaActualizaAlumno(obj);
		if (objSalida != null) {
			return ResponseEntity.ok(objSalida);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj) { // indica que Alumno va a venir en formato Json
		System.out.println(">>>> Actualiza!! ");
		Optional<Alumno> optAlumno = service.obtienePorId(obj.getIdAlumno());
		if (optAlumno.isPresent()) {
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			if (objSalida != null) {
				return ResponseEntity.ok(objSalida);
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			System.out.println("No existe el ID del ALumno");
			return ResponseEntity.badRequest().build();
		}

	}

}
