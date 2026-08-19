package com.example.bai1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agent")
public class BankingAgentController {

    private static final Logger log = LoggerFactory.getLogger(BankingAgentController.class);

    private final ChatClient chatClient;
    private final ToolCallback bankTransferTool;

    public BankingAgentController(
            ChatClient.Builder chatClientBuilder,
            @Qualifier("bankTransferTool") ToolCallback bankTransferTool) {
        this.chatClient = chatClientBuilder.build();
        this.bankTransferTool = bankTransferTool;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String content) {
        log.info("Received user prompt: {}", content);
        try {
            // Truyền trực tiếp instance ToolCallback vào phương thức .tools()
            String response = chatClient
                    .prompt()
                    .user(content)
                    .tools(this.bankTransferTool)
                    .call()
                    .content();
            log.info("AI final response: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error processing banking chat request", e);
            throw new RuntimeException("Lỗi khi xử lý yêu cầu: " + e.getMessage());
        }
    }
}