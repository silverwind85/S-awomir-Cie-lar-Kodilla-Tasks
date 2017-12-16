package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value ="getTasks"  )
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value ="getTask/{id}" )
    public TaskDto getTask(@PathVariable("id") long id){
        return  taskMapper.mapToTaskDto(service.getTask(id));

    }

    @RequestMapping(method = RequestMethod.DELETE, value ="deleteTask" )
    public void deleteTask(String taskId){

    }

    @RequestMapping(method = RequestMethod.PUT, value ="updateTask" )
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto((long)1,"Edit title","Edit content");
    }

    @RequestMapping(method = RequestMethod.POST, value ="createTask" )
    public void createTask(TaskDto taskDto){
        System.out.println("k");
    }

}