package com.rag.controller;

import com.rag.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author ggstudy11
 * @date Created in 2025/4/21 16:55
 * @description 大模型问答接口
 */
@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {


    private final ChatClient chatClient;

    @GetMapping()
    public Response<String> chat(@RequestParam("question") String question, @RequestParam("conversationId") Integer conversationId) {
        log.info("=== deleteDoc 接口调用 ===");
        log.info("时间: {}", LocalDateTime.now());
        log.info("提问：{}", question);
        log.info("对话ID: {}", conversationId);
        log.info("=== deleteDoc 接口调用 ===");
        String resp = chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversationId)
                .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user(question)
                .call()
                .content();
        return Response.success(resp);
    }
}
