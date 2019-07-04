package com.huowolf.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author huowolf
 * @date 2019/7/4
 * @description
 */
@RestController
public class PdfController {


    @GetMapping(value = "/genpdf")
    public void genPdf(HttpServletResponse response) throws Exception {

        // 存储pdf 文件
        String pdfFolder = "pdf/";
        File pdfFile = new File(pdfFolder);
        if (!pdfFile.exists()) {
            pdfFile.mkdir();
        }
        String pdf = pdfFolder + UUID.randomUUID() + ".pdf";

        // 1.新建documnet对象
        Document doc = new Document(PageSize.A4, 0, 0, 50, 0);//SUPPRESS
        FileOutputStream fos = new FileOutputStream(pdf);
        PdfWriter.getInstance(doc, fos);

        // 字体设置
        BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);

        // 创建字体对象
        Font font = new Font(baseFont, 14, Font.NORMAL);//SUPPRESS
        Font font2 = new Font(baseFont, 21, Font.BOLD);//SUPPRESS

        Paragraph start = new Paragraph("常住人口登记表",font2);
        start.setAlignment(Paragraph.ALIGN_CENTER);
        start.setSpacingAfter(30);


        //添加6列表格
        PdfPTable table = new PdfPTable(6);

        // 设置各列列宽
        table.setTotalWidth(new float[]{120, 100, 100, 100, 100, 100});//SUPPRESS

        table.addCell(getPDFCell("姓名", font));
        table.addCell(mergeCol("", font, 3));//SUPPRESS
        table.addCell(getPDFCell("性别", font));
        // 性别
        table.addCell("");

        table.addCell(getPDFCell("曾用名", font));
        table.addCell(mergeCol("", font, 3));//SUPPRESS
        table.addCell(getPDFCell("民族", font));
        // 民族
        table.addCell("");

        table.addCell(getPDFCell("出生日期", font));
        // 出生日期
        table.addCell(mergeCol("", font, 5));//SUPPRESS

        // 监护人
        table.addCell(getPDFCell("监护人", font));
        // 监护关系
        table.addCell("");
        table.addCell("");
        table.addCell(getPDFCell("出生地", font));
        table.addCell(mergeCol("", font, 2));//SUPPRESS

        table.addCell(getPDFCell("住址", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS

        table.addCell(getPDFCell("本市（县）其他住址", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS

        table.addCell(getPDFCell("籍贯", font));
        table.addCell(mergeCol("", font, 3));//SUPPRESS
        table.addCell(getPDFCell("宗教信仰", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("公民身份证编号", font));
        table.addCell(mergeCol("", font, 3));//SUPPRESS
        table.addCell(getPDFCell("居民身份证签发日期", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("文化程度", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("婚姻状况", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("兵役状况", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("身高", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("血型", font));
        table.addCell(getPDFCell("", font));
        table.addCell(getPDFCell("职业", font));
        table.addCell(getPDFCell("", font));

        table.addCell(getPDFCell("服务处所", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS

        table.addCell(getPDFCell("何时何因由何地迁来本市(县)", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS
        table.addCell(getPDFCell("何时何因由何地迁来本市(县)", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS
        table.addCell(getPDFCell("何时何因迁往何地", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS
        table.addCell(getPDFCell("何时何因注销户口", font));
        table.addCell(mergeCol("", font, 5));//SUPPRESS

        Paragraph end = new Paragraph("申报人签章");
        end.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph end2 = new Paragraph("申报人签章            加盖户口登记机关专用专用章", font);
        end2.setFirstLineIndent(90);//SUPPRESS

        Paragraph end3 = new Paragraph("申报人签章");
        end3.setPaddingTop(20);//SUPPRESS

        Paragraph end4 = new Paragraph("承办人签章            登记日期：  年  月  日", font);
        end4.setFirstLineIndent(90);//SUPPRESS

        String path = this.getClass().getClassLoader().getResource("test.jpg").getPath();
        Image image = Image.getInstance(path);

        //设置图片位置的x轴和y周（以左下角为原点）
        image.setAbsolutePosition(220f, 120f);
        //设置图片的宽度和高度
        image.scaleAbsolute(100, 100);

        doc.open();
        doc.add(start);
        doc.add(table);
        doc.add(end);
        doc.add(end2);
        doc.add(end3);
        doc.add(end4);
        doc.add(image);
        doc.close();

        String name = URLEncoder.encode("常住人口登记.pdf", "utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-msdownload");
        response.addHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + name);
        try (OutputStream out = response.getOutputStream()) {
            InputStream is = new FileInputStream(pdf);
            byte[] bys = new byte[1024];
            int len = 0;
            while ((len = is.read(bys)) != -1) {
                out.write(bys, 0, len);
            }
            is.close();
        }

    }


    /**
     * 合并单元格
     * @param value
     * @param font
     * @param colspan
     * @return
     */
    private PdfPCell mergeCol(String value, Font font, int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }


    /**
     * 创建单元格
     * @param value
     * @param font
     * @return
     */
    private PdfPCell getPDFCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }


}
