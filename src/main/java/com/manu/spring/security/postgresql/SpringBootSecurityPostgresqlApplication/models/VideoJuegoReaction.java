package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import jakarta.persistence.*;

@Entity
@Table(
    name = "videojuego_reactions"
)

public class VideoJuegoReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reaction_id")
    private Long reactionId;

    @ManyToOne
    @JoinColumn(name = "reaction_id", insertable = false, updatable = false)
    private Reaction reaction;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "video_juego_id")
    private Long videoJuegoId;

    @ManyToOne
    @JoinColumn(name = "video_juego_id", insertable = false, updatable = false)
    private VideoJuego videoJuego;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reactionId = reaction.getId();
        this.reaction = reaction;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.userId = user.getId();
        this.user = user;
    }

    public Long getVideoJuegoId() {
        return videoJuegoId;
    }

    public void setVideoJuegoId(Long videoJuegoId) {
        this.videoJuegoId = videoJuegoId;
    }

    public VideoJuego getVideoJuego() {
        return videoJuego;
    }

    public void setVideoJuego(VideoJuego videoJuego) {
        this.videoJuegoId = videoJuego.getId();
        this.videoJuego = videoJuego;
    }
}
