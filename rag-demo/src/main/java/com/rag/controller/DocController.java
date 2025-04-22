package com.rag.controller;

import com.rag.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ggstudy11
 * @date Created in 2025/4/22 9:02
 * @description 知识库接口
 */
@RestController
@RequestMapping("/api/doc")
@Slf4j
@RequiredArgsConstructor
public class DocController {

    private final VectorStore vectorStore;

    @PostMapping()
    public Response<List<String>> addDoc(@RequestParam("file") MultipartFile file) {
        log.info("=== addDoc 接口调用 ===");
        log.info("时间: {}", LocalDateTime.now());
        log.info("文件名: {}", file.getName());
        log.info("=== addDoc 接口调用 ===");
        List<String> ids = new ArrayList<>();
        try {
            TikaDocumentReader docReader = new TikaDocumentReader(new InputStreamResource(file.getInputStream()));
            TokenTextSplitter splitter = new TokenTextSplitter();
            List<Document> afterSplitDoc = splitter.apply(docReader.get());
            vectorStore.accept(afterSplitDoc);
            for (Document document : afterSplitDoc) {
                String id = document.getId();
                ids.add(id);
            }
        } catch (IOException e) {
            return Response.fail(500, e.getMessage());
        }
        return Response.success(ids);
    }

    @DeleteMapping()
    public Response deleteDoc(@RequestParam("ids") String ids) {
        log.info("=== deleteDoc 接口调用 ===");
        log.info("时间: {}", LocalDateTime.now());
        log.info("删除文件ID: {}", ids);
        log.info("=== deleteDoc 接口调用 ===");
        List<String> rawIds = Arrays.stream(ids.split(",")).toList();
        vectorStore.delete(rawIds);
        return Response.success();
    }
}
