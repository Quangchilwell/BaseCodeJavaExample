package com.example.demo.Service;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

public interface TestService {
    void exportExcelExample(HttpServletResponse response) throws Exception;

    ByteArrayInputStream exportPdfExample();

    void pageNavigationExample();

    void sqlExample();
}
