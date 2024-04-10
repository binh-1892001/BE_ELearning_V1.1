package elearning.dto;

import elearning.dto.base.BaseObjectDto;

import elearning.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class CommentDto extends BaseObjectDto {
	private String content;
	private UsersDto users;
	private LessonDto lesson;
	private Long lessonId;
	private CommentDto comment;
	private int replyComment;
	private Long parentId;
	private List<CommentDto> replies;
	
	
	public CommentDto() {
	}
	
	public CommentDto(Comment entity, Boolean isGetFull) {
		this.id = entity.getId();
		this.content = entity.getContent();
		this.replyComment = entity.getCommentChildren().size();
		this.replies = entity.getCommentChildren().stream().sorted(Comparator.comparingLong(Comment::getId).reversed()).map(commentChildren -> new CommentDto(commentChildren)).toList();
		if (entity.getUsers() != null && entity.getUsers().getId() != null) {
			this.users = new UsersDto(entity.getUsers());
		}
		if (entity.getLesson() != null && entity.getLesson().getId() != null) {
//            this.lesson = new LessonDto(entity.getLesson());
			this.lessonId = entity.getLesson().getId();
		}
		if (entity.getComment() != null && entity.getComment().getId() != null) {
			this.parentId = entity.getComment().getId();
		}
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
	
	public CommentDto(Comment entity) {
		this.id = entity.getId();
		this.content = entity.getContent();
		this.replyComment = entity.getCommentChildren().size();
		this.replies = entity.getCommentChildren().stream().sorted(Comparator.comparingLong(Comment::getId).reversed()).map(commentChildren -> new CommentDto(commentChildren)).toList();
		if (entity.getUsers() != null && entity.getUsers().getId() != null) {
			this.users = new UsersDto(entity.getUsers());
		}
		if (entity.getLesson() != null && entity.getLesson().getId() != null) {
//            this.lesson = new LessonDto(entity.getLesson());
			this.lessonId = entity.getLesson().getId();
		}
		if (entity.getComment() != null && entity.getComment().getId() != null) {
			this.parentId = entity.getComment().getId();
		}
	}
}
