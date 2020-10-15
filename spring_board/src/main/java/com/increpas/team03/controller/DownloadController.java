package com.increpas.team03.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownloadController {
    
	@RequestMapping(value="/file_download.do",method=RequestMethod.GET)
	public void download(@RequestParam String filename, HttpServletRequest req, HttpServletResponse resp) {
		{
	
            
        OutputStream outputStream = null;
        
        try {
        	//파일경로
            //File file = new File("C:/myjava/workspace_spring/myspring.SpringBoard/WebContent/upload/" + filename);
        	File file = new File("c:/images/" + filename);
            System.out.println(file.getName());
            String filetype = filename.substring(filename.indexOf(".") + 1, filename.length());
    
            if (filetype.trim().equalsIgnoreCase("txt")) {
                resp.setContentType("text/plain");
            } else {
                resp.setContentType("application/octet-stream");
            }
    
            resp.setContentLength((int) file.length());
    
            boolean ie = req.getHeader("User-Agent").indexOf("MSIE") != -1;
            if (ie) {
                filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", " ");
            } else {
                filename = new String(filename.getBytes("UTF-8"), "8859_1");
            }
             //Content-Disposition 을 attachment; filename 하게되면
            // 브라우저는 다운창을 활성화
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
    
            outputStream = resp.getOutputStream();
            FileInputStream fis = null;
            
            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, outputStream);
            } finally {
                if (fis!= null) {
                    fis.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
                resp.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
}