package com.unfamousk4y.musicplatform.service;

import com.unfamousk4y.musicplatform.model.Song;
import com.unfamousk4y.musicplatform.model.User;
import com.unfamousk4y.musicplatform.repository.SongRepository;
import com.unfamousk4y.musicplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    public Song uploadSong(MultipartFile file, String title, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String uploadDir = System.getProperty("user.home") + "/uploads/";
        new File(uploadDir).mkdirs();
        String filePath = uploadDir + file.getOriginalFilename();

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file");
        }

        Song song = new Song();
        song.setTitle(title);
        song.setFilePath(filePath);
        song.setUser(user);
        return songRepository.save(song);
    }

    public List<Song> getSongsByUser(Long userId) {
        return songRepository.findByUserId(userId);
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));
    }
}

