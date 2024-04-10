package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category extends BaseObject {
    private String name;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
