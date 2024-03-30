package com.example.Wallet.exeptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс ErrorResponse представляет собой объект ошибки API.
 * Свойства:
 * - status: строка - статус ошибки
 * - reason: строка - причина ошибки
 * - message: строка - сообщение об ошибке
 * - timestamp: LocalDateTime - дата и время возникновения ошибки (автоматически устанавливается при создании объекта)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String status;
    private String reason;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();

}
