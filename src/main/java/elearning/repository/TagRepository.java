package elearning.repository;

import elearning.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tags, Long> {
    List<Tags> findAll();
}

