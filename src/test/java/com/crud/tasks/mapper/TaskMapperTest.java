package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    private List<TaskDto> taskDtos;
    private List<Task> tasks;
    private TaskDto taskDto;
    private Task task;

    @Before
    public void before(){
        taskDtos = new ArrayList<>();
        tasks = new ArrayList<>();

        taskDto= new TaskDto(1L,"test_taskDto","test_contentDto");
        task = new Task(2L,"test_task","test_content");

        taskDtos.add(taskDto);
        tasks.add(task);
    }

    @Test
    public void testMapToTask() throws Exception {
        //When
        Task result = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(new Long(1L),result.getId());
        assertEquals("test_taskDto",result.getTitle());
        assertEquals("test_contentDto",result.getContent());
    }

    @Test
    public void testMapToTaskDto() throws Exception {
        //When
        TaskDto result = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(new Long(2L),result.getId());
        assertEquals("test_task",result.getTitle());
        assertEquals("test_content",result.getContent());

    }

    @Test
    public void testMapToTaskDtoList() throws Exception {
        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(new Long(2L),result.get(0).getId());
        assertEquals("test_task",result.get(0).getTitle());
        assertEquals("test_content",result.get(0).getContent());
    }

}