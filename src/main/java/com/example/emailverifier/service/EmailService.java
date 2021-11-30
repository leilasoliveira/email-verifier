package com.example.emailverifier.service;

import com.example.emailverifier.model.vo.EmailVO;
import com.example.emailverifier.utils.EmailVerifierUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmailVerifierUtil emailVerifierUtil;

    @Autowired
    public EmailService(EmailVerifierUtil emailVerifierUtil) {
        this.emailVerifierUtil = emailVerifierUtil;
    }

    @Scheduled(cron = "*/30 * * * * *") // default: every 30 seconds
    public void getEmailsNeverSeen() throws MessagingException, IOException {
        List<EmailVO> newMessages = emailVerifierUtil.getNewMessages();
        logger.info("Found " + newMessages.size() + " new message(s)!");
    }
}
