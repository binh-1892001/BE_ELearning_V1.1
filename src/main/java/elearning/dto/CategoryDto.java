package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto extends BaseObjectDto {
    private String name;
    private boolean status;
    private CourseDto courseDto;
    private Long courseId;
    private String courseName;

    public CategoryDto() {
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.status = entity.isStatus();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
        if(entity.getCourse() != null){
            this.courseId = entity.getCourse().getId();
            this.courseName = entity.getCourse().getTitle();
        }
    }
}
