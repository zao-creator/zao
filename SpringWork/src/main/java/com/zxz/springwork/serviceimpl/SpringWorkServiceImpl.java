package com.zxz.springwork.serviceimpl;

import com.zxz.springwork.GetHtmlFile.File.FileList;
import com.zxz.springwork.GetHtmlFile.GetHtmlIframe;
import com.zxz.springwork.pojo.IframeBean;
import com.zxz.springwork.service.SpringWorkService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpringWorkServiceImpl implements SpringWorkService
{
    @Override
    public List<IframeBean> getData(String path) throws IOException
    {
        List<IframeBean> xhtmlAttribute;
        FileList fileList;
        GetHtmlIframe getHtmlIframe;
        List<String> xhtmlFile;
        fileList = new FileList();
        getHtmlIframe = new GetHtmlIframe();
        xhtmlFile = fileList.getXhtmlFile(path);
        List<IframeBean> list = new ArrayList<>();
        for (String path1 : xhtmlFile)
        {
            xhtmlAttribute = getHtmlIframe.getXhtmlAttribute(path1);
            for (IframeBean iframeBean : xhtmlAttribute)
            {
                list.add(iframeBean);
            }
        }
        return list;
    }
}
