package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(new ArrayList<>());
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test_content");
        Task task = new Task();

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When && Then
        mockMvc.perform(get("/v1/task/getTask/{taskId}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test_content")));

        verify(taskMapper, times(1)).mapToTaskDto(task);
        verifyNoMoreInteractions(taskMapper);
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        mockMvc.perform(delete("/v1/task/deleteTask/").param("taskId", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(2L, "test", "test_content");
        Task task = new Task(2L,"test_task","test_task_content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);
        //When && Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.title",is("test")))
                .andExpect(jsonPath("$.content",is("test_content")));

    }

    @Test
    public void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test_content");
        Task task = new Task(2L,"test_task","test_task_content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);
        //When && Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("test")))
                .andExpect(jsonPath("$.content",is("test_content")));

    }

}