package com.example.email.controller;

import com.example.email.model.EmailRequest;
import com.example.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/emails")
public class EmailController {
    private final EmailService emailService;
    private final ExecutorService virtualThreadExecutor;

    @PostMapping("/send")
    public void sendEmail(@Valid @RequestBody EmailRequest request) {
        log.info("Sending email request: {}", request);

        virtualThreadExecutor.execute(() -> {
            try {
                emailService.sendHtmlMail(request);
            } catch (MessagingException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });
    }

    @GetMapping("")
    public String hello() {
        return "Hello World";
    }
}
