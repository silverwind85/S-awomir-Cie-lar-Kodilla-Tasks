package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = null;

        trelloBoards = trelloClient.getTrelloBoards();
        List<TrelloBoardDto> list1 = trelloBoards.stream().filter(a -> a.getClass().getDeclaredFields().length == 2).collect(Collectors.toList());
        List<TrelloBoardDto> list2 = new ArrayList<>();
        for (TrelloBoardDto trelloBoardDto : list1
                ) {
            try {
                if (trelloBoardDto.getClass().getDeclaredField("id").equals("id")
                        && trelloBoardDto.getClass().getDeclaredField("name").equals("name")) ;
                list2.add(trelloBoardDto);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return list2.stream().filter(a -> a.getName().contains("Kodilla")).collect(Collectors.toList());
        }
        return list2;
    }
    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards2")
    public void getTrelloBoards2(){
        List<TrelloBoardDto> trelloBords =trelloClient.getTrelloBoards();
        trelloBords.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
            System.out.println("This board contain lists");

            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - "+ trelloList.getId() + " - " + trelloList.isClosed()));
        });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard cratedTrelloCard(@RequestBody TrelloCardDto trelloCardDto)
    {
        return trelloClient.createdNewCard(trelloCardDto);
    }
}
