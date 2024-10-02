package com.example.pacong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageCrawl1
{
    public static void main(String[] args)
    {
        String url = "https://www.gcu.edu.cn/"; // 替换为你要解析的网页URL
        try
        {
            // 获取网页内容
            String html = getPageContent(url);

            // 解析HTML内容
            Document doc = Jsoup.parse(html);

            // 获取所有图片标签
            Elements imgElements = doc.select("img");
            for (Element imgElement : imgElements)
            {
                String imgUrl = url+imgElement.attr("src");
                String imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                saveImageToLocal(imgUrl, imgName);
                System.out.println("Saved image: " + imgName);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // 获取网页内容的方法
    private static String getPageContent(String urlString) throws IOException
    {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK)
        { // success
            try (InputStream inputStream = connection.getInputStream())
            {
                StringBuilder response = new StringBuilder();
                int data;
                while ((data = inputStream.read()) != -1)
                {
                    response.append((char) data);
                }
                return response.toString();
            }
        }
        else
        {
            throw new IOException("Failed to fetch web page: HTTP error code : " + responseCode);
        }
    }

    // 保存图片到本地的方法
    private static void saveImageToLocal(String imageUrl, String imageName) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                try (InputStream inputStream = connection.getInputStream()) {
                    File file = new File("D:/picture/" + imageName);
                    file.getParentFile().mkdirs(); // 确保目录存在
                    try (FileOutputStream outputStream = new FileOutputStream(file)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            } else {
                System.err.println("Failed to fetch image: HTTP error code : " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
