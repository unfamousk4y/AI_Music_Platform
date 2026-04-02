package com.unfamousk4y.musicplatform.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Map;

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

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.matches(".*\\.(mp3|wav|flac|ogg|m4a)$")) {
            throw new RuntimeException("Invalid file type. Only audio files are allowed.");
        }
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
        Song savedSong = songRepository.save(song);

        Map<String, Object> analysis = analyzeAudio(filePath);
        if (analysis != null) {
            savedSong.setMood((String) analysis.get("mood"));
            savedSong.setTempo(((Number) analysis.get("tempo")).doubleValue());
            savedSong.setEnergy(((Number) analysis.get("energy")).doubleValue());
            songRepository.save(savedSong);
        }

        return savedSong;


    }

    public List<Song> getSongsByUser(Long userId) {
        return songRepository.findByUserId(userId);
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));
    }
    private Map<String, Object> analyzeAudio(String filePath) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(filePath));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "http://localhost:8001/predict", requestEntity, Map.class);

            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }


}

