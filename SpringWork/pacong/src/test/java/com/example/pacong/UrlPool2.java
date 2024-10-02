package com.example.pacong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.Jsoup.*;

public class UrlPool2 {

    /**
     * 从指定网页爬取图片URL并保存到本地
     *
     * @param webPageUrl 网页的URL
     * @param saveDir     保存图片的本地目录
     * @throws IOException 如果发生IO异常
     */
    public static void crawlAndSaveImages(String webPageUrl, String saveDir) throws IOException {
        // 解析网页
        Document doc = connect(webPageUrl).get();

        // 提取所有图片元素
        Elements imgElements = doc.select("img");
        List<String> imageUrls = new ArrayList<>();

        // 收集所有图片的URL
        for (Element imgElement : imgElements) {
            String imgUrl = imgElement.attr("src");
            // 确保URL是完整的（处理相对URL）
            imgUrl = connect(webPageUrl).response().toString();
            imageUrls.add(imgUrl);
        }

        // 下载并保存图片
        for (String imageUrl : imageUrls) {
            String fileName = Paths.get(imageUrl).getFileName().toString(); // 使用URL的路径部分作为文件名
            String savePath = Paths.get(saveDir, fileName).toString();
            downloadImage(imageUrl, savePath);
        }
    }

    /**
     * 下载图片并保存到本地
     *
     * @param imageUrl 图片的URL
     * @param destinationPath 保存图片的路径
     * @throws IOException 如果发生IO异常
     */
    private static void downloadImage(String imageUrl, String destinationPath) throws IOException {
        URL url = new URL(imageUrl); // 这里应该传递一个有效的 URL 字符串
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(destinationPath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {
        // 示例用法
        String webPageUrl = "https://www.gcu.edu.cn/"; // 替换为实际的网页URL
        String saveDir = "C:\\pachong"; // 替换为实际的保存目录

        try {
            crawlAndSaveImages(webPageUrl, saveDir);
            System.out.println("图片爬取和保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("图片爬取或保存失败！");
        }
    }
}