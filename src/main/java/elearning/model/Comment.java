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
public class Comment extends BaseObject {
	private String content;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;

	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Comment> commentChildren = new HashSet<>();
}
