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
public class Tags extends BaseObject {
    private String title;
}
