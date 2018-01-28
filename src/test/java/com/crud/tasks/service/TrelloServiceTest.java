package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;


    @Test
    public  void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test", true));
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "test", trelloListDtos));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1, result.size());
        result.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("test", trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("test", trelloListDto.getName());
                assertTrue(trelloListDto.isClosed()); });
        });
    }
    @Test
    public void  testCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test",
                "desc_test",
                "top",
                "test_listId");

        CreatedTrelloCardDto createdTrelloCardDto= new CreatedTrelloCardDto("1",
                "test",
                "desc_test",
                "http://test.com",
                new Badges(1,new AttachmentsByType(new Trello(1,1))));
        when(trelloClient.createdNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDto1 = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        assertEquals("1",createdTrelloCardDto1.getId());
        assertEquals("test",createdTrelloCardDto1.getName());
        assertEquals("desc_test",createdTrelloCardDto1.getDescription());
        assertEquals("http://test.com",createdTrelloCardDto1.getShortUrl());

    }
}