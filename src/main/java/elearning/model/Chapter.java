package elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import elearning.model.base.BaseObject;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Chapter extends BaseObject {
	private String title;
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Lesson> lessons = new HashSet<>();
}
