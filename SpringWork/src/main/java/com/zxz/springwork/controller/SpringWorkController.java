package com.zxz.springwork.controller;

import com.zxz.springwork.pojo.IframeBean;
import com.zxz.springwork.service.SpringWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
public class SpringWorkController
{
    @Autowired
    private SpringWorkService sws;

    @PostMapping("/getData")
    public List<IframeBean> getData(String path) throws IOException
    {
        System.out.println("path="+path);
        return sws.getData(path);
    }
}
