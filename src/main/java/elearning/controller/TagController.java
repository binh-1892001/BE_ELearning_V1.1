package elearning.controller;

import elearning.model.Tags;
import elearning.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    @Autowired
    ITagService tagService;
    @GetMapping("/")
    public ResponseEntity<List<Tags>> getAll(){
        List<Tags> tagsList = tagService.findAll();
        return new ResponseEntity<>(tagsList, HttpStatus.OK);
    }
}
