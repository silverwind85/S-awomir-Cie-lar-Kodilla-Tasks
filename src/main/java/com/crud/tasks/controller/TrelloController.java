package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {


    @Autowired
    private TrelloFacade trelloFacade;


    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards3")
    public List<TrelloBoardDto> getTrelloBoards3() {
        return trelloFacade.fetchTrelloBoards().stream().filter(a -> a.getName()!=null&&a.getId()!=null&&a.getName().contains("Kodilla")).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards2")
    public void getTrelloBoards2() {
        List<TrelloBoardDto> trelloBords = trelloFacade.fetchTrelloBoards();
        trelloBords.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
            System.out.println("This board contain lists");

            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
        });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto cratedTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
}
