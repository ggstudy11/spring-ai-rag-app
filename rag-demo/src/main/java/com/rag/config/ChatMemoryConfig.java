package com.rag.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author ggstudy11
 * @date Created in 2025/4/21 19:53
 * @description 对话记忆
 */
@Configuration
public class ChatMemoryConfig {
    @Bean
    public ChatMemory InMemoryChatMemory() {
        return new InMemoryChatMemory();
    }
}
