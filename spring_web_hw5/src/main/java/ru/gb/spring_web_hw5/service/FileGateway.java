package ru.gb.spring_web_hw5.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import ru.gb.spring_web_hw5.domain.Task;

@MessagingGateway(defaultRequestChannel = "textInputChannel")
public interface FileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, Task task);
}
