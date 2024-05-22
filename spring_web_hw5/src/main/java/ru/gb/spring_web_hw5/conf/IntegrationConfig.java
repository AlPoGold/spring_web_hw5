package ru.gb.spring_web_hw5.conf;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import ru.gb.spring_web_hw5.domain.Task;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class IntegrationConfig {
    @Value("${file.output.path}")
    private String fileOutputPath;

    @Bean
    public MessageChannel textInputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChannel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<Task, String> mainTransformer(){
        return task -> {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode taskNode = mapper.createObjectNode();
            taskNode.put("id", task.getId());
            taskNode.put("description", task.getDescription());
            taskNode.put("status", task.getStatus().ordinal());
            taskNode.put("creatingTime", task.getCreatingTime().toString());
            ObjectNode rootNode = mapper.createObjectNode();
            rootNode.set("task", taskNode);

            try {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler customHandler(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File(fileOutputPath));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }



}
