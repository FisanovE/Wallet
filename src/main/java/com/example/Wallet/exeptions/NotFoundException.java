package com.example.Wallet.exeptions;

/**
 * Исключение NotFoundException является подклассом RuntimeException и используется для обработки ошибки "Объект не найден".
 */
public class NotFoundException extends RuntimeException {

    /**
     * Создает новый объект исключения NotFoundException с заданным сообщением об ошибке.
     *
     * @param message сообщение об ошибке.
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
