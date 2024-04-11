package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
//    private String tags;
    private String image;
    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(
            name = "blog_tag",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tags> tags;
}
