package com.javarush.jira.bugtracking.task.tags;


import com.javarush.jira.bugtracking.Handlers;
import com.javarush.jira.common.error.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagsService {


    private final Handlers.TaskHandler handler;

    public Set<String> getAll(long id){
        if(handler.get(id).getTags().isEmpty()){
            throw new AppException("This task does not have tags yet");
        }
        return handler.get(id).getTags();
    }

    public void create(long id, String tags){
        if(tags.length() > 32 || tags.length() < 2){
            throw new AppException("Tag cannot be longer than 32 characters or shorter than 2 characters");
        }
        getAll(id).add(tags);
        handler.update(handler.get(id), id);
    }


    public void delete(long id, String tags) {
        if(!getAll(id).contains(tags)){
            throw new AppException("No such tag exists");
        }
        getAll(id).remove(tags);
        handler.update(handler.get(id), id);
    }

    public void update(long id, String tags, String upd) {
        getAll(id).remove(tags);
        getAll(id).add(upd);
        handler.update(handler.get(id), id);
    }
}
