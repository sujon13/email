package com.example.email.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailRequest {
    @NotNull
    private String recipient;
    @NotNull
    private String subject;
    @NotNull
    private String body;
}
