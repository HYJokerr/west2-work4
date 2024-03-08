package com.niumo.utils;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;

public class MarkdownToTextUtil {

    public static String convertMarkdownToPlainText(String markdown) {
        // 创建一个Markdown解析器
        Parser parser = Parser.builder().build();

        // 解析Markdown文本到文档节点
        Node document = parser.parse(markdown);

        // 创建一个文本内容渲染器，用于提取纯文本
        TextContentRenderer renderer = TextContentRenderer.builder().build();

        // 渲染Markdown为纯文本
        String plainText = renderer.render(document);

        return plainText;

    }
}
