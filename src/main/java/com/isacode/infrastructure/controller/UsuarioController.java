package com.isacode.infrastructure.controller;

import com.isacode.application.service.UsuarioService;
import com.isacode.domain.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario createUsu = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(createUsu, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long usuarioId) {
        return usuarioService.getUsuario(usuarioId)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(usuarioId, usuario)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long usuarioId) {
        usuarioService.eliminar(usuarioId);
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
}
