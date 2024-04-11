package elearning.repository;

import elearning.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tags, Long> {
    List<Tags> findAllByTitle(String title);
}
