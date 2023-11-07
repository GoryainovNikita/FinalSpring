package com.javarush.jira.bugtracking.task.tags;


import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.bugtracking.task.TaskController;
import com.javarush.jira.bugtracking.task.TaskRepository;
import com.javarush.jira.bugtracking.task.to.TaskToExt;
import com.javarush.jira.bugtracking.task.to.TaskToFull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.javarush.jira.common.BaseHandler.createdResponse;

@Slf4j
@RestController
@RequestMapping(value = TagsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagsController {

    public static final String REST_URL = "/api/tasks/tags";
    private final TagsService tagsService;


    @GetMapping("/{id}")
    public @ResponseBody Set<String> get(@PathVariable long id) {
        log.info("get tags by id task={}", id);
        return tagsService.getAll(id);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@PathVariable long id,
                                     @RequestParam(value = "tags") String tags){
        log.info("create new tag by id task={}", id);
        tagsService.create(id, tags);
        return "redirect:" + REST_URL +"/"+ id;
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable long id,
                         @RequestParam(value = "tags") String tags){
        log.info("delete tag by id task={}", id);
        tagsService.delete(id, tags);
        return "redirect:" + REST_URL +"/"+ id;
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id,
                         @RequestParam(value = "tags") String tags,
                         @RequestParam(value = "upd") String upd){

        log.info("update tag by id task={}", id);
        tagsService.update(id, tags, upd);
        return "redirect:" + REST_URL +"/"+ id;
    }
}
