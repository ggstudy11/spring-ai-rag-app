package com.rag.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author ggstudy11
 * @date Created in 2025/4/21 16:55
 * @description 大模型问答接口
 */
@Slf4j
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {


    private final ChatClient chatClient;



    @GetMapping("/ai")
    public String generation(String userInput) {
        return this.chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, 1)
                .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(userInput)
                .call()
                .content();
    }
}
