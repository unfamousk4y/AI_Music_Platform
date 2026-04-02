package com.unfamousk4y.musicplatform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String filePath;

    private String mood;
    private Double tempo;
    private Double energy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {return id;}
    public void setId(Long id) { this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getMood() {return mood;}
    public void setMood(String mood) {this.mood = mood;}
    public Double getTempo() {return tempo;}
    public void setTempo(Double tempo) {this.tempo = tempo;}
    public Double getEnergy() {return energy;}
    public void setEnergy(Double energy) {this.energy = energy;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public String getFilePath() {return filePath;}
    public void setFilePath(String filePath) {this.filePath = filePath;}

}
