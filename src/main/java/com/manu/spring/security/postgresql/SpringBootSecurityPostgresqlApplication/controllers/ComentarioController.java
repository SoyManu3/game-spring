package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Comentario;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuego;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.ComentarioRequest;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.ComentarioPublicacionResponse;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.ComentarioPublicacionRepository;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.VideoJuegoRepository;

import jakarta.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioPublicacionRepository comentarioRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoJuegoRepository videoJuegoRepository;


   
    

    @PostMapping("/create")
    public Comentario createComentario(@Valid @RequestBody ComentarioRequest comentarioRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userId = authentication.getName();
       System.out.println("userid : " + userId  );


       User user = getValidUser(userId);
       System.out.println("user");

     
       Comentario myComentario = new Comentario(comentarioRequest.getComentario());
       VideoJuego myVideoJuego = getValidGame(comentarioRequest.getVideojuegoId());

       myComentario.setAutor(user);
       myComentario.setVideoJuego(myVideoJuego);

       comentarioRepository.save(myComentario);

        return myComentario;
    }

  @GetMapping("/game/{videojuegoId}")
public ResponseEntity<List<ComentarioPublicacionResponse>> obtenerComentariosPorVideoJuego(@PathVariable Long videojuegoId) {
    List<Comentario> comentarios = comentarioRepository.findByVideoJuego_Id(videojuegoId);
    
    // Convertir los Comentarios a ComentarioPublicacionResponse
    List<ComentarioPublicacionResponse> response = comentarios.stream().map(comentario -> {
        ComentarioPublicacionResponse comentarioResponse = new ComentarioPublicacionResponse();
        comentarioResponse.setComentario(comentario.getComentario());
        comentarioResponse.setFecha(comentario.getFecha());
        comentarioResponse.setNombreAutor(comentario.getAutor().getUsername()); // Asumiendo que `User` tiene un campo `username`
        return comentarioResponse;
    }).collect(Collectors.toList());

    return ResponseEntity.ok(response);
}
     private User getValidUser(String userId) {
       Optional<User> userOpt = userRepository.findByUsername(userId);
       if (!userOpt.isPresent()) {
           throw new RuntimeException("User not found");
       }
       return userOpt.get();
   }

    private VideoJuego getValidGame(Long gameId) {
        Optional<VideoJuego> gameOpt = videoJuegoRepository.findById(gameId);
        if (!gameOpt.isPresent()) {
            throw new RuntimeException("game not found");
        }
        return gameOpt.get();
    }
    
    


    
}
