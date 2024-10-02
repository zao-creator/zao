package com.zxz.springwork.GetHtmlFile.File;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileList {

    public File[] getFile(String path) {
        File pathFile = new File(path);
        File[] files = pathFile.listFiles();
        return files;
    }

    public List<String> XhtmlFile(File file) {
        List<String> result = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    result.addAll(XhtmlFile(f));
                }
            }
        } else if (file.getName().endsWith("xhtml")) {
            result.add(file.getAbsolutePath());
        }

        return result;
    }
    public  List<String> getXhtmlFile(String path) {
        File[] files = this.getFile(path);
        List<String> XhtmlFilePath = new ArrayList<>();
        for (File file : files) {
           XhtmlFilePath.addAll(this.XhtmlFile(file));
        }

        return XhtmlFilePath;
    }



    public static void main(String[] args) {
        FileList fileList = new FileList();



    }

}

