package elearning.service.impl;

import elearning.model.Tags;
import elearning.repository.TagRepository;
import elearning.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tags> findAllByTitle(String title) {
        return tagRepository.findAllByTitle(title);
    }
}
