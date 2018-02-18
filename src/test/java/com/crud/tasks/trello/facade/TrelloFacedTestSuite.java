package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.mapper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacedTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;


    private List<TrelloListDto> trelloListDtos;
    private List<TrelloList> mappedTrelloLists;
    private List<TrelloBoard> mappedTrelloBoards;
    private List<TrelloBoardDto> trelloBoardDtos;

    @Before
    public void before(){
        trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list",false));

         trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1","test",trelloListDtos));

         mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1","test_list",false));

        mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1","test",mappedTrelloLists));
    }

    @Test
    public void shouldFetchEmptyList(){
        //Given
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardDtos(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoard(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtos1 = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos1);
        assertEquals(0,trelloBoardDtos1.size());
    }

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoards(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardDtos(anyList())).thenReturn(trelloBoardDtos);
        when(trelloValidator.validateTrelloBoard(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos1 = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos1);
        assertEquals(1,trelloBoardDtos1.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1",trelloBoardDto.getId());
            assertEquals("test",trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1",trelloListDto.getId());
                assertEquals("test_list",trelloListDto.getName());
                assertEquals(false,trelloListDto.isClosed());
            });
        });
    }
    @Test
    public void testCreateCard(){
        //Given
        TrelloCard trelloCard = new TrelloCard("test","des test","top","id_test");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test","des test","top","id_test");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","test","desc_test","http://test.com",new Badges(1,new AttachmentsByType(new Trello(1,1))));
        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDto1 = trelloFacade.createCard(trelloCardDto);
        //Then
        assertEquals("1",createdTrelloCardDto1.getId());
        assertEquals("test",createdTrelloCardDto1.getName());
        assertEquals("desc_test",createdTrelloCardDto1.getDescription());
        assertEquals("http://test.com",createdTrelloCardDto1.getShortUrl());


    }

}
