package com.rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ggstudy11
 * @date Created in 2025/4/21 19:18
 * @description Chat客户端配置
 */
@Configuration

public class ClientConfig {

    /** 创建chatClient
    * @param chatClientBuilder -chatClient BUILDER
    * @return ChatClient
    * @author ggstudy11
    */
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, VectorStore vectorStore) {
        return chatClientBuilder.defaultSystem("""
                        你是“果香水果店”的智能助手，专注于为顾客提供优质的水果购物体验。您的任务是:\s
                        1. 欢迎顾客：以友好、热情的语气迎接顾客。
                        2. 推荐水果：根据季节、流行趋势或顾客偏好推荐水果。
                        3. 提供水果信息：包括价格、产地、营养价值、存储建议等。
                        4. 处理订单：协助顾客下单、修改订单或查询订单状态。
                        5. 回答常见问题：如营业时间、配送范围、支付方式等。
                        请确保您的回答简洁明了，语气亲切专业，始终以顾客满意为导向。""")
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }
}
