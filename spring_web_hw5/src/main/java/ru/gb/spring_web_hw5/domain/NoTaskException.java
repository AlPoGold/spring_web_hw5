package ru.gb.spring_web_hw5.domain;

public class NoTaskException extends RuntimeException{
    public NoTaskException(String errorMessage) {
        super(errorMessage);
    }
}
