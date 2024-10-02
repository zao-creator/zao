package com.example.pacong;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;



public class ImageCrawl {
    private static String url = "https://www.gcu.edu.cn/";
    public static void main(String[] args) throws IOException {
       // apacheHttpClient();
        Document document = Jsoup.connect(url).get();
        Elements   elements  = document.select(".news-picture-item");
        for(int i = 0;i < elements.size();i++){
            Elements imgElement = elements.get(i).select("a > img");
           Connection.Response response = Jsoup.connect(imgElement.attr("src"))
                   .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0")
                   .ignoreContentType(true).execute();
           String name = imgElement.attr("alt");
           ByteArrayInputStream stream = new ByteArrayInputStream(response.bodyAsBytes());
            FileUtils.copyInputStreamToFile(stream,new File("D:/pachong/" + name + ".jpg"));
        }

    }

    private static void apacheHttpClient() {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent" ,
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36 Edg/128.0.0.0");
        HttpResponse response = null;

        try{
            response = client.execute(httpGet);
           HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        }catch (Exception e){

        }finally {

        }
    }
}
