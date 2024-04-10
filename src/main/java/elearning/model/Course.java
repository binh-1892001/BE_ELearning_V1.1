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
public class Course extends BaseObject {
	private String title;
	private String image;
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	private String subDescription;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Chapter> chapters = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			  name = "enroll_course",
			  joinColumns = @JoinColumn(name = "course_id"),
			  inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<Users> userCourse = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
}
