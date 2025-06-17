package com.manu.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

public class VideoJuegoReactionRequest {
 private Long videojuegoId;
 private Long reactionId;

public Long getVideojuegoId() {
  return videojuegoId;
}

 public void setVideojuegoId(Long videojuegoId) {
  this.videojuegoId = videojuegoId;
 }

 public Long getReactionId() {
   return reactionId;
 }

 public void setReactionId(Long reactionId) {
   this.reactionId = reactionId;
 }
}
