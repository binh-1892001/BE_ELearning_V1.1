package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lesson extends BaseObject {
	private String title;
	private String video;
	private String resources;
	@Column(columnDefinition="LONGTEXT")
	private String description;
	private String document;
	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;
}
