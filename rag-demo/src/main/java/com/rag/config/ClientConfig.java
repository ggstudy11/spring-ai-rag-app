package com.rag.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.rag.prompt.Prompt.FRUIT_SHOP_AGENT;

/**
 * @author ggstudy11
 * @date Created in 2025/4/21 19:18
 * @description Chat客户端配置
 */
@Configuration

public class ClientConfig {

    /** 创建chatClient
    * 这里我创建的agent是一位水果店agent, 相应的我会嵌入对应的知识
    * @param chatClientBuilder -chatClient BUILDER
    * @param chatMemory - 对话记忆    用于对话存储
    * @param vectorStore - 向量数据库 用于rag检索
    * @return ChatClient
    * @author ggstudy11
    */
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, VectorStore vectorStore) {
        return chatClientBuilder.defaultSystem(FRUIT_SHOP_AGENT)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }
}
