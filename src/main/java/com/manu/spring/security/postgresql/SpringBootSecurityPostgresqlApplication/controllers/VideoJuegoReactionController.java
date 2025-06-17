package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuego;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.VideoJuegoReaction;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.VideoJuegoReactionRequest;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;

import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.ReactionRepository;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.VideoJuegoReactionRepository;
import com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.VideoJuegoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reactions")

public class VideoJuegoReactionController {

    @Autowired
    private VideoJuegoReactionRepository videoJuegoReactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoJuegoRepository videoJuegoRepository;
    @Autowired
    private ReactionRepository reactionRepository;


  @GetMapping("/all")
    public Page<VideoJuegoReaction> getTweet(Pageable pageable) {
        return videoJuegoReactionRepository.findAll(pageable);
    }
  
  @PostMapping("/create")
  public VideoJuegoReaction createReaction(@Valid @RequestBody VideoJuegoReactionRequest videoJuegoReaction) {
        System.out.println("gametid : " + videoJuegoReaction.getVideojuegoId()  );
        System.out.println("reactiontid : " + videoJuegoReaction.getReactionId()  );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println("userid : " + userId  );


        User user = getValidUser(userId);
        System.out.println("user");

        System.out.println(user);

        VideoJuegoReaction myvideoJuegoReaction = new VideoJuegoReaction();
        VideoJuego myGame = getValidGame(videoJuegoReaction.getVideojuegoId());
        System.out.println("object game : " );
        System.out.println(myGame );


        Reaction myReaction = getValidReaction(videoJuegoReaction.getReactionId());
        System.out.println("object reaction : "   );
        System.out.println( myReaction );

        //myvideoJuegoReaction.setUserId(user.getId());
        //myvideoJuegoReaction.setTweetId(myGame.getId());
        //myvideoJuegoReaction.setReactionId(myReaction.getId());
        
        myvideoJuegoReaction.setUser(user);
        myvideoJuegoReaction.setVideoJuego(myGame);
        myvideoJuegoReaction.setReaction(myReaction);
        
        
        System.out.println("game reaction : "   );
        System.out.println( myvideoJuegoReaction.getReactionId());
                System.out.println( myvideoJuegoReaction.getVideoJuegoId());

                        System.out.println( myvideoJuegoReaction.getUserId());


        videoJuegoReactionRepository.save(myvideoJuegoReaction);

        return myvideoJuegoReaction;
  }
  @GetMapping("/count/{videoJuegoId}")
public ResponseEntity<Map<String, Long>> countReactions(@PathVariable Long videoJuegoId) {
    Long likeCount = videoJuegoReactionRepository.countReactionsByVideoJuegoIdAndReactionId(videoJuegoId, 1L); // ID para "like"
    Long dislikeCount = videoJuegoReactionRepository.countReactionsByVideoJuegoIdAndReactionId(videoJuegoId, 2L); // ID para "dislike"

    Map<String, Long> reactionCounts = new HashMap<>();
    reactionCounts.put("likes", likeCount);
    reactionCounts.put("dislikes", dislikeCount);

    return ResponseEntity.ok(reactionCounts);
}

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }

@GetMapping("/exists/{videoJuegoId}/{reactionId}")
public ResponseEntity<Boolean> hasUserReacted(
        @PathVariable Long videoJuegoId,
        @PathVariable Long reactionId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();

    User user = getValidUser(userId);
    VideoJuego videoJuego = getValidGame(videoJuegoId);
    Reaction reaction = getValidReaction(reactionId);
            
    boolean hasReacted = videoJuegoReactionRepository.existsByUserAndVideoJuegoAndReaction(user, videoJuego, reaction);

    return ResponseEntity.ok(hasReacted);
}

    private VideoJuego getValidGame(Long gameId) {
        Optional<VideoJuego> gameOpt = videoJuegoRepository.findById(gameId);
        if (!gameOpt.isPresent()) {
            throw new RuntimeException("game not found");
        }
        return gameOpt.get();
    }

    

    private Reaction getValidReaction(Long reactionId) {
        Optional<Reaction> reactionOpt = reactionRepository.findById(reactionId);
        if (!reactionOpt.isPresent()) {
            throw new RuntimeException("Reaction not found");
        }
        return reactionOpt.get();
    }

}
