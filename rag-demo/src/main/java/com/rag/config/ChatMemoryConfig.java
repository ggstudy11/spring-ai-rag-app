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

    /** 对话记忆存储于内存，因此关闭程序后会丢失，若考虑持久化可以采用jdbc等其他方式
    * @return ChatMemory
    * @author ggstudy11
    */
    @Bean
    public ChatMemory InMemoryChatMemory() {
        return new InMemoryChatMemory();
    }
}
