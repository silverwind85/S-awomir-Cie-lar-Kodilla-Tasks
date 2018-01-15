package com.crud.tasks.scheduler;


import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {

    private final static String MESSAGE = "Currently in database you got: 1 task";

    @Mock
    private TaskRepository taskRepository;


    @Mock
    private EmailScheduler emailScheduler;

    @Test
    public void testSendInformationEmail(){
        //Given
        when(taskRepository.count()).thenReturn((long)1);
        //When
        Mail result = emailScheduler.sendInformationEmail();
        //Then
        Assert.assertEquals(result.getMessage(),MESSAGE);
    }
}
