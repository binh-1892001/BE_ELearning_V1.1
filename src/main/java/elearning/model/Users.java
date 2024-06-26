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
public class Users extends BaseObject {
//	private String username;
	private String fullName;
	@Column(unique = true)
	private String phone;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable(
			  name = "user_role",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "wish_list",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Set<Course> favourite;
}
