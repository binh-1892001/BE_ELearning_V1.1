package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Blog;
import elearning.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BlogDto extends BaseObjectDto {
    private String title;
    private String content;
    private String tags;
    private String image;
    private int likes;
    private UsersDto usersDto;


    public BlogDto() {
    }

    public BlogDto(Blog entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.tags = entity.getTags();
        this.image = entity.getImage();
        this.likes = entity.getLikes();
    }
}
