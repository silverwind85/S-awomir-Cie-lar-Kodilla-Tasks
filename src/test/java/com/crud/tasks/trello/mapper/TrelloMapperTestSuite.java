package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    private List<TrelloBoard> trelloBoards = new ArrayList<>();
    private List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
    private List<TrelloList> trelloList = new ArrayList<>();
    private List<TrelloListDto> trelloListDto = new ArrayList<>();


    @Before
    public void before() {
        trelloListDto.add(new TrelloListDto("2", "test listDto", true));
        trelloList.add(new TrelloList("1", "test list", false));
        trelloBoardDtos.add(new TrelloBoardDto("3","test boardDto",trelloListDto));
        trelloBoards.add(new TrelloBoard("4","test board",trelloList));

    }

    @Test
    public void testMapToList() {
        //Given
        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDto);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("2", result.get(0).getId());
        Assert.assertEquals("test listDto", result.get(0).getName());
        Assert.assertTrue(result.get(0).isClosed());
    }
    @Test
    public void testMapToListDto(){
        //Given
        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloList);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("test list", result.get(0).getName());
        Assert.assertFalse(result.get(0).isClosed());
    }
    @Test
    public void testMapToBoards(){
        //Given
        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("3", result.get(0).getId());
        Assert.assertEquals("test boardDto", result.get(0).getName());
        Assert.assertEquals(1,result.size());

    }
    @Test
    public void testMapToBoardDtos(){
        //Given
        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardDtos(trelloBoards);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("4", result.get(0).getId());
        Assert.assertEquals("test board", result.get(0).getName());
        Assert.assertEquals(1,result.size());

    }
    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "What to do yesterday",
                "May be I will go snowboard",
                "top",
                "eee222");
        //When
        TrelloCardDto result = trelloMapper.maptoCardDto(trelloCard);
        //Then
        Assert.assertEquals("What to do yesterday",result.getName());
        Assert.assertEquals("May be I will go snowboard",result.getDescription());
        Assert.assertEquals("top",result.getPos());
        Assert.assertEquals("eee222",result.getListId());
    }
    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "What to do yesterday",
                "May be I will go snowboard",
                "top",
                "eee222");
        //When
        TrelloCard result = trelloMapper.maptoCard(trelloCardDto);
        //Then
        Assert.assertEquals("What to do yesterday",result.getName());
        Assert.assertEquals("May be I will go snowboard",result.getDescription());
        Assert.assertEquals("top",result.getPos());
        Assert.assertEquals("eee222",result.getListId());
    }
}
