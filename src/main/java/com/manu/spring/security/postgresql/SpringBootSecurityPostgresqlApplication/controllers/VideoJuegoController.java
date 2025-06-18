package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuego;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.VideoJuegoResponse;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/game")

public class VideoJuegoController {

    @Autowired
    private VideoJuegoRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

  

    @GetMapping("/all")
public Page<VideoJuegoResponse> getVideoJuego(Pageable pageable) {
    return gameRepository.findAll(pageable)
            .map(VideoJuegoResponse::new); }
  
@PostMapping("/create")
public VideoJuego createGame(@Valid @RequestBody VideoJuego game) {
    // Verifica que el objeto 'game' contiene los valores que esperas
    System.out.println("Recibiendo datos del juego: " + game.getNombre() + ", " + game.getCreador() + ", " + game.getFechaDeLanzamiento() + ", " + game.getPlataformas() + ", " + game.getGenero());
        
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userId = authentication.getName();
       System.out.println("userid : " + userId  );


       User user = getValidUser(userId);
       System.out.println("user");

       System.out.println(user);

    // Crear el objeto VideoJuego con los datos recibidos
    VideoJuego mygame = new VideoJuego(game.getNombre(), game.getCreador(), game.getFechaDeLanzamiento(), game.getPlataformas(), game.getGenero());
    mygame.setPostedBy(user);
    gameRepository.save(mygame);

    return mygame;
}

     private User getValidUser(String userId) {
       Optional<User> userOpt = userRepository.findByUsername(userId);
       if (!userOpt.isPresent()) {
           throw new RuntimeException("User not found");
       }
       return userOpt.get();
   }

     



}
