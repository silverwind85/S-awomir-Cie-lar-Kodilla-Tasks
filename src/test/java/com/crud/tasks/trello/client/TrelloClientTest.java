package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;
    @Before
    public void init(){
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrellUsername()).thenReturn("test");
    }

    @Test
    public void shouldFetchTrelloBoard() throws URISyntaxException {
        // Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //when
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        Assert.assertEquals(1, fetchTrelloBoards.size());
        Assert.assertEquals("test_id", fetchTrelloBoards.get(0).getId());
        Assert.assertEquals("test_board", fetchTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchTrelloBoards.get(0).getLists());
    }
    @Test
    public void shouldCreatedCard() throws URISyntaxException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "Test description",
                "http://test.com",
                new Badges(0,new AttachmentsByType(new Trello(0,0)))
        );

        when(restTemplate.postForObject(uri,null,CreatedTrelloCardDto.class)).thenReturn(createdTrelloCard);
        //When
        CreatedTrelloCardDto newCard = trelloClient.createdNewCard(trelloCardDto);
        //Then
        Assert.assertEquals("1",newCard.getId());
        Assert.assertEquals("Test task", newCard.getName());
        Assert.assertEquals("http://test.com", newCard.getShortUrl());
    }
    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        // Given


        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        //when
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        Assert.assertTrue( fetchTrelloBoards.isEmpty());

    }
}