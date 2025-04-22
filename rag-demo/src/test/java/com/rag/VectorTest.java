package com.rag;

import org.junit.jupiter.api.Test;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author ggstudy11
 * @date Created in 2025/4/22 9:03
 * @description 向量测试
 */
@SpringBootTest
public class VectorTest {

    @Autowired
    VectorStore vectorStore;

    @Test
    public void test() {
        String pdfPath = "classpath:data/果香水果店欢迎您.pdf";
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfPath,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .build())
                        .withPagesPerDocument(1)
                        .build());
        vectorStore.accept(pdfReader.get());
    }
}
