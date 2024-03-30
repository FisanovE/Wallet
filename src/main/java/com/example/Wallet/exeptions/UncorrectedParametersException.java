package com.example.Wallet.exeptions;

/**
 * Исключение UncorrectedParametersException является подклассом RuntimeException
 * и используется для обработки ошибки "Некорректные параметры".
 */
public class UncorrectedParametersException extends RuntimeException {

    /**
     * Создает новый объект исключения UncorrectedParametersException с заданным сообщением об ошибке.
     *
     * @param message сообщение об ошибке.
     */
    public UncorrectedParametersException(final String message) {
        super(message);
    }
}
