package com.example.demo.Service.impl;

import com.example.demo.Export.ExportExcelExample;
import com.example.demo.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private ExportExcelExample exportExcelExample;

    @Override
    public void exportExcelExample(HttpServletResponse response) throws Exception {
        exportExcelExample.exportExcel(response);
    }

    @Override
    public ByteArrayInputStream exportPdfExample() {

        return null;
    }

    @Override
    public void pageNavigationExample() {

    }

    @Override
    public void sqlExample() {

    }
}
