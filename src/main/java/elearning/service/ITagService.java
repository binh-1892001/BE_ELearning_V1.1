package elearning.service;

import elearning.model.Tags;

import java.util.List;

public interface ITagService {
    List<Tags> findAllByTitle(String title);
}
