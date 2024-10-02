package com.zxz.springwork.GetHtmlFile;

import com.zxz.springwork.pojo.IframeBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GetHtmlIframe {
    private IframeBean iframeBean;

    public List<Element> getHtml(String path) throws IOException {
        File htmlfile = new File(path);
        Document parsehtml = Jsoup.parse(htmlfile, "UTF-8", "");
        List<Element> iframes = parsehtml.select("iframe");
        return iframes;
    }

    public List<IframeBean> getXhtmlAttribute(String path) throws IOException {
        List<Element> elementhtml = this.getHtml(path);
        List<IframeBean> iframeBeans = new ArrayList<>();
        for (Element element : elementhtml) {
            iframeBean = new IframeBean();
            String classname = element.attr("class");
            String data_id = element.attr("data-chaucer-element-id");
            String src = element.attr("src");
            String height = element.attr("height");
            String width = element.attr("width");
            String lang = element.attr("lang");
            String title = element.attr("title");
            String dataresponsive = element.attr("data-responsivedesigned");
            String dataminiwidth = element.attr("data-minwidth");
            String dataminheight = element.attr(" data-minheight");
            String dataimsrequired = element.attr("data-lmsrequired");
            String dataofflinesupport = element.attr("data-offlinesupport");
            String resource = element.attr("resource");
            iframeBean.setClas(classname);
            iframeBean.setData_chaucer_element_id(data_id);
            iframeBean.setSrc(src);
            iframeBean.setHeight(height);
            iframeBean.setWidth(width);
            iframeBean.setLang(lang);
            iframeBean.setTitle(title);
            iframeBean.setData_responsivedesigned(dataresponsive);
            iframeBean.setData_minwidth(dataminiwidth);
            iframeBean.setData_minheight(dataminheight);
            iframeBean.setData_lmsrequired(dataimsrequired);
            iframeBean.setData_offlinesupport(dataofflinesupport);
            iframeBean.setResource(resource);
            iframeBeans.add(iframeBean);
        }
        return iframeBeans;
    }
}