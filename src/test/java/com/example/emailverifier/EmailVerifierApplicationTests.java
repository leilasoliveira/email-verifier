package com.example.emailverifier;

import com.example.emailverifier.configuration.EmailCredentials;
import com.example.emailverifier.model.vo.EmailVO;
import com.example.emailverifier.service.EmailService;
import com.example.emailverifier.utils.EmailVerifierUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class EmailVerifierApplicationTests {

    @Autowired
    EmailCredentials emailCredentialsReal;

    @Mock
    EmailCredentials emailCredentials;

    @InjectMocks
    EmailVerifierUtil emailVerifierUtil;

    @Test
    void mustReadInbox() throws MessagingException, IOException {
        Mockito.when(emailCredentials.getUsername()).thenReturn(emailCredentialsReal.getUsername());
        Mockito.when(emailCredentials.getPassword()).thenReturn(emailCredentialsReal.getPassword());
        emailVerifierUtil.getNewMessages();
    }

    @Test
    void mustThrowExceptionWhenCredentialsAreIncorrect() {
        Mockito.when(emailCredentials.getUsername()).thenReturn("teste@teste.com");
        Mockito.when(emailCredentials.getPassword()).thenReturn("P@ssw0rd!");

        Exception exception = assertThrows(AuthenticationFailedException.class, () -> emailVerifierUtil.getNewMessages());
        assertTrue(exception instanceof AuthenticationFailedException);
    }

    @Test
    void mustThrowExceptionWhenCredentialsNotExists() {
        Exception exception = assertThrows(AuthenticationFailedException.class, () -> emailVerifierUtil.getNewMessages());
        assertTrue(exception instanceof AuthenticationFailedException);
    }

    @Test
    void mustReturnNewMessages() throws MessagingException, IOException {
        Mockito.when(emailCredentials.getUsername()).thenReturn(emailCredentialsReal.getUsername());
        Mockito.when(emailCredentials.getPassword()).thenReturn(emailCredentialsReal.getPassword());
        List<EmailVO> newMessages = emailVerifierUtil.getNewMessages();
        Assert.assertFalse("A lista de mensagens está vazia", newMessages.isEmpty());
    }

}
