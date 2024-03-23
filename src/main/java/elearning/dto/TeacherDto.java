package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Course;
import elearning.model.Teacher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TeacherDto extends BaseObjectDto {
    private String name;
    private String image;
    private String specialize;
    private MultipartFile imageFile;

    public TeacherDto() {
    }

    public TeacherDto(Teacher entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.specialize = entity.getSpecialize();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
    }

    public TeacherDto(Teacher entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.specialize = entity.getSpecialize();
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
