package com.codegym.webthuenha.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
