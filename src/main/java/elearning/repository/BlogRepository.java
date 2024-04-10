package elearning.repository;

import elearning.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select blog from Blog blog left join fetch blog.users where blog.users.phone = :phone")
    List<Blog> findAllBlogsByUserLogin(@Param("phone") String phone);

}
