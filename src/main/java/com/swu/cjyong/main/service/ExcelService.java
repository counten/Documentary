package com.swu.cjyong.main.service;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    /**
     * 获取统计信息excel
     *
     * @return List<User>
     */
    void getAllDataExcel(HttpServletResponse response);
}
