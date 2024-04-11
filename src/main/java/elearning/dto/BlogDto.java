package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Blog;
import elearning.model.Tags;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
public class BlogDto extends BaseObjectDto {
    private String title;
    private String content;
    private String image;
    private int likes;
    private UsersDto usersDto;
    private MultipartFile fileUpload;
    private Set<Tags> tags;


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
