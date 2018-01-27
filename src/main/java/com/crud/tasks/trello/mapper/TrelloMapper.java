package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDtos) {
        return trelloBoardDtos.stream()
                .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardDtos(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoardDto -> new TrelloBoardDto(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToListDto(trelloBoardDto.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());

    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloList) {
        return trelloList.stream()
                .map(trelloListDto -> new TrelloListDto(trelloListDto.getId(), trelloListDto.getName(), trelloListDto.isClosed()))
                .collect(Collectors.toList());
    }
    public TrelloCard maptoCard(TrelloCardDto trelloCardDto){
        return new TrelloCard(trelloCardDto.getName(),trelloCardDto.getDescription(),trelloCardDto.getPos(),trelloCardDto.getListId());
    }
    public TrelloCardDto maptoCardDto(TrelloCard trelloCard){
        return new TrelloCardDto(trelloCard.getName(),trelloCard.getDescription(),trelloCard.getPos(),trelloCard.getListId());
    }
}
