package com.unfamousk4y.musicplatform.controller;

import com.unfamousk4y.musicplatform.model.Song;
import com.unfamousk4y.musicplatform.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/songs")

public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/upload")
    public ResponseEntity<Song> uploadSong(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("userId") Long userId ) {
        return ResponseEntity.ok(songService.uploadSong(file, title, userId));
    }
    @GetMapping
    public ResponseEntity<List<Song>> getSongs(@RequestParam Long userId) {
        return ResponseEntity.ok(songService.getSongsByUser(userId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }


}