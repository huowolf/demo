package com.huowolf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author huowolf
 * @date 2019/7/3
 * @description
 */
public class TestPDFDemo1 {

    //创建一个简单的pdf文件
    @Test
    public void test1() throws FileNotFoundException, DocumentException {

        // 1.新建document对象
        Document document = new Document();

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test1.pdf"));

        // 3.打开文档
        document.open();

        // 4.添加一个内容段落
        document.add(new Paragraph("Hello World!"));

        // 5.关闭文档
        document.close();

    }


    //PDF中创建表格
    @Test
    public void test2() throws FileNotFoundException, DocumentException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test2.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));

        // 3列的表.
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

        ArrayList<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 1f, 2f, 3f };
        table.setWidths(columnWidths);

        //行1
        PdfPCell cells1[]= new PdfPCell[3];
        PdfPRow row1 = new PdfPRow(cells1);

        //单元格
        cells1[0] = new PdfPCell(new Paragraph("111"));//单元格内容
        cells1[0].setBorderColor(BaseColor.BLUE);//边框验证
        cells1[0].setPaddingLeft(20);//左填充20
        cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

        cells1[1] = new PdfPCell(new Paragraph("222"));
        cells1[2] = new PdfPCell(new Paragraph("333"));

        //行2
        PdfPCell cells2[]= new PdfPCell[3];
        PdfPRow row2 = new PdfPRow(cells2);
        cells2[0] = new PdfPCell(new Paragraph("444"));

        //把第一行添加到集合
        listRow.add(row1);
        listRow.add(row2);
        //把表格添加到文件中
        document.add(table);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }


    //PDF中添加图片
    @Test
    public void test3() throws IOException, DocumentException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test3.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));

        //图片1
        String path = this.getClass().getClassLoader().getResource("test.jpg").getPath();
        Image image1 = Image.getInstance(path);

        //设置图片位置的x轴和y周（以左下角为原点）
        image1.setAbsolutePosition(200f, 200f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image1);

        //图片2
        Image image2 = Image.getInstance(new URL("https://02imgmini.eastday.com/mobile/20190704/20190704014620_775599c81b8a5cbff742dfe17db4fec7_1.jpeg"));

        image2.setAbsolutePosition(300f, 500f);
        image2.scaleAbsolute(200, 200);

        //将图片2添加到pdf文件中
        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }


    //PDF中创建列表
    @Test
    public void test4() throws FileNotFoundException, DocumentException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test4.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));

        //添加有序列表
        List orderedList = new List(List.ORDERED);
        orderedList.add(new ListItem("Item one"));
        orderedList.add(new ListItem("Item two"));
        orderedList.add(new ListItem("Item three"));
        document.add(orderedList);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }


    //PDF中设置样式/格式化输出
    @Test
    public void test5() throws IOException, DocumentException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test5.pdf"));
        //打开文件
        document.open();

        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

        //蓝色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue = new Paragraph("paragraphOne blue front", blueFont);
        document.add(paragraphBlue);

        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);
        //创建章节
        Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);

        Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
        Section section1 = chapter1.addSection(sectionTitle);

        Paragraph sectionContent = new Paragraph("部分内容", blueFont);
        section1.add(sectionContent);

        //将章节添加到文章中
        document.add(chapter1);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    @Test
    public void test(){
        String path = this.getClass().getClassLoader().getResource("test.jpg").getPath();
        System.out.println(path);
    }
}
