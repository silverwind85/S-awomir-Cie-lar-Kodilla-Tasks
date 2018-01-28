package com.crud.tasks.trello.validator;


import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import static  org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTestSuite {


    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testvalidateTrelloBoard(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","test",true));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","test",trelloLists));
        TrelloBoard trelloBoard =  new TrelloBoard("1","test",trelloLists);
        List<TrelloBoard> result = trelloValidator.validateTrelloBoard(trelloBoards);

       assertEquals("1",result.get(0).getId());
        assertEquals("test",result.get(0).getName());

    }

}
