package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private DbService dbService;

    @Test
    public void testGetAllTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        IntStream.iterate(1,n->n+1)
                .limit(10)
                .forEach(n->tasks.add(new Task(0L+n,"test_tittle"+n,"test_content"+n)));
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        assertEquals(new Long(1),tasks.get(0).getId());
        assertEquals("test_tittle1", tasks.get(0).getTitle());
        assertEquals("test_content1", tasks.get(0).getContent());

    }

    @Test
    public void testSaveTask() throws Exception {
        //Given
        Task task = new Task(0L,"test_tittle","test_content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task result = dbService.saveTask(task);
        //Then
        assertEquals(new Long(0),result.getId());
        assertEquals("test_tittle",result.getTitle());
        assertEquals("test_content",result.getContent());
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Task task = new Task(0L,"test_tittle","test_content");
        when(taskRepository.findById(0L)).thenReturn(Optional.of(task));
        //When
        Optional<Task> result = dbService.getTask(task.getId());
        //Then
        assertEquals(new Long(0),result.get().getId());
        assertEquals("test_tittle",result.get().getTitle());
        assertEquals("test_content",result.get().getContent());

    }

}