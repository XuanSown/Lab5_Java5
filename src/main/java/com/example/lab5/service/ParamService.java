package com.example.lab5.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return value == null ? defaultValue : value;
    }

    public int getInt(String name, int defaultValue) {
        String value = getString(name, null);
        return value == null ? Integer.parseInt(value) : defaultValue;
    }

    public double getDouble(String name, double defaultValue) {
        String value = getString(name, null);
        return value == null ? Double.parseDouble(value) : defaultValue;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = getString(name, null);
        return value == null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public Date getDate(String name, String pattern) {
        String value = getString(name, null);
        if (value == null) return null;
        try {
            return new SimpleDateFormat(pattern).parse(value);
        } catch (Exception e) {
            throw new RuntimeException("lỗi định dạng sai ngày tháng!",e);
        }
    }

    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) return null;
        try {
            //lấy đường dẫn trong server thực tế
            File dir = new File(request.getServletContext().getRealPath(path));
            if (!dir.exists()) dir.mkdirs();
            File saveFile = new File(dir, file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new RuntimeException("lỗi lưu file!",e);
        }
    }
}
