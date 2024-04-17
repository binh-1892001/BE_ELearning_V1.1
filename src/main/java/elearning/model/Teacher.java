package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Teacher extends BaseObject {
	private String name;
	private String image;
	private String specialize;
	private Boolean status;
}
