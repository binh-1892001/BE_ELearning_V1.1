package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Course;
import elearning.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class CourseDto extends BaseObjectDto {
    private String title;
    private String image;
    private String description;
    private MultipartFile imageFile;
    private String subDescription;
    private int totalChap;
    public CourseDto() {
    }

    public CourseDto(Course entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.subDescription = entity.getSubDescription();
        this.totalChap = entity.getChapters().size();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
    }

    public CourseDto(Course entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.subDescription = entity.getSubDescription();
        this.totalChap = entity.getChapters().size();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }
}
