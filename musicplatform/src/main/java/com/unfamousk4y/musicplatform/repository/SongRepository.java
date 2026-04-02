package com.unfamousk4y.musicplatform.repository;

import com.unfamousk4y.musicplatform.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long>{
    List<Song> findByUserId(Long userId);
}
