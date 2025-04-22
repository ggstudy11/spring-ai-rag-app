package com.rag;


import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author ggstudy11
 * @date Created in 2025/4/21 18:12
 * @description
 */
@SpringBootTest()
public class AITest {

    @Autowired
    private ChatClient chatClient;


    @Test
    public void test() {
        String resp = chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, 1)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user("50 + 60 = ?")
                .call()
                .content();
        System.out.println(resp);
        resp = chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, 1)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user("再减去50")
                .call()
                .content();
        System.out.println(resp);
    }

    @Test
    public void test2() {
        String resp = chatClient.prompt()
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, 1)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .user("营业时间")
                .call()
                .content();
        System.out.println(resp);
    }
}
