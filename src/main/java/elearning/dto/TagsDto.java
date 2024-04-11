package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Tags;
import elearning.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagsDto extends BaseObjectDto {
    private String title;

    public TagsDto() {
    }

    public TagsDto(Tags entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}
