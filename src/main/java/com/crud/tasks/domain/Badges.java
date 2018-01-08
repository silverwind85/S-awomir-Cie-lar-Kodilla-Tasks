package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Badges {
    private int vote;
    private List<AttachemntsByType> attachemntsByTypeList;
}
