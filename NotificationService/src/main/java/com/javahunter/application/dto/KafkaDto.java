package com.javahunter.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaDto<T> {
    private String message;
    private T data;
}

