package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Blog extends BaseObject {
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private String tags;
    private String image;
    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
