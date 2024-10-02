package com.zxz.springwork.service;

import com.zxz.springwork.pojo.IframeBean;

import java.io.IOException;
import java.util.List;

public interface SpringWorkService
{
    List<IframeBean> getData(String path) throws IOException;
}
