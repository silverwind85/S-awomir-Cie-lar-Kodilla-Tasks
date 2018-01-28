package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BadgesTest {
    @Test
    public void testBadges(){
        //Given
        AttachmentsByType attachmentsByType = new AttachmentsByType(new Trello(1,2));
        Badges badges = new Badges(2, attachmentsByType);
        //When
        int result = badges.getVotes();
        AttachmentsByType result2 = badges.getAttachmentsByType();
        //Then
        Assert.assertEquals(2,badges.getVotes());
        Assert.assertEquals(attachmentsByType,result2);

    }

}