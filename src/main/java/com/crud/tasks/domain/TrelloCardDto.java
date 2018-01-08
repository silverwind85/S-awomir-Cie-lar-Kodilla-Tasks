package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;
    private List<Badges> badgesList;
}

