package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.MemberCount;
import com.swu.cjyong.main.service.ExcelService;
import com.swu.cjyong.main.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService{
    @Autowired
    private  UserRepository  userRepository;
    @Autowired
    private UserService userService;

    @Override
    public void getAllDataExcel(HttpServletResponse response) {
        // 创建excel
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sh_account = wb.createSheet("汇总");
        HSSFSheet sh_district = wb.createSheet("区县");
        HSSFSheet sh_city = wb.createSheet("城市");
        HSSFSheet sh_school = wb.createSheet("学校");

        // 写入数据
        MemberCount memberCount = userService.getAllMeberNumAndAccout4();
        HSSFRow row0 = sh_account.createRow(0);
        row0.createCell(0).setCellValue("团支部总数");
        row0.createCell(1).setCellValue(memberCount.getAccount4Count());
        HSSFRow row1 = sh_account.createRow(1);
        row1.createCell(0).setCellValue("团员总数");
        row1.createCell(1).setCellValue(memberCount.getMemberCount());
        HSSFRow row2 = sh_account.createRow(2);
        row2.createCell(0).setCellValue("区县团支部总数");
        row2.createCell(1).setCellValue(memberCount.getAccount4_district());
        HSSFRow row3 = sh_account.createRow(3);
        row3.createCell(0).setCellValue("区县团员总数");
        row3.createCell(1).setCellValue(memberCount.getMember_district());
        HSSFRow row4 = sh_account.createRow(4);
        row4.createCell(0).setCellValue("城市团支部总数");
        row4.createCell(1).setCellValue(memberCount.getAccount4_city());
        HSSFRow row5 = sh_account.createRow(5);
        row5.createCell(0).setCellValue("城市团员总数");
        row5.createCell(1).setCellValue(memberCount.getMember_city());
        HSSFRow row6 = sh_account.createRow(6);
        row6.createCell(0).setCellValue("学校团支部总数");
        row6.createCell(1).setCellValue(memberCount.getAccount4_school());
        HSSFRow row7 = sh_account.createRow(7);
        row7.createCell(0).setCellValue("学校团员总数");
        row7.createCell(1).setCellValue(memberCount.getMember_school());


        String path = "CountData_"+new Date().getTime()+".xls";
        dataTosheet(User.DISTRICT, sh_district);
        dataTosheet(User.CITY, sh_city);
        dataTosheet(User.SCHOOL, sh_school);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        try {
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

    private void dataTosheet(int userType, HSSFSheet sh){
        List<User> users = userRepository.findByUserKindAndUserType(userType, User.SECOND_USER);
        HSSFRow row_first = sh.createRow(0);
        row_first.createCell(0).setCellValue("名称");
        row_first.createCell(1).setCellValue("通过活动数量");
        row_first.createCell(2).setCellValue("活动参与人次");
        int i =1;
        for (User u : users){
            HSSFRow row_t = sh.createRow(i++);
            row_t.createCell(0).setCellValue(u.getName());
            row_t.createCell(1).setCellValue(u.getNumPass());
            row_t.createCell(2).setCellValue(u.getParticipantsNum());
        }
    }
}
