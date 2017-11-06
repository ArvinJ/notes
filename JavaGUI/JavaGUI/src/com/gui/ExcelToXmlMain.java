package com.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToXmlMain {

	public static void importFile(String fileUrl, String exFile)
			throws FileNotFoundException, IOException, BiffException {
		// 这里是生成工作簿
		// "C:\\Users\\我家华华\\Desktop\\KP0101.xls"
		// "C:\\Users\\我家华华\\Desktop\\KP0101.xml"
		System.err.println("exFile----"+exFile);
		Workbook book = Workbook.getWorkbook(new File(fileUrl));
		// 获取第一张Sheet表
		Sheet sheet = book.getSheet(0);
		// 创建根节点;
		Element root = new Element("Kp");
		// 创建二级根节点第一个;
		Element secondRoot01 = new Element("Version");
		// 将根节点添加到文档中；
		secondRoot01.addContent("2.0");
		root.addContent(secondRoot01);
		Document Doc = new Document(root);
		// 获取Sheet表中所包含的总列数
		int cols = sheet.getColumns();
		// 获取Sheet表中所包含的总行数
		int rows = sheet.getRows();
		// System.err.println("cols:" + cols + "---rows:" + rows);
		String[] colsName = new String[cols];
		for (int k = 0; k < cols; k++) {
			Cell cell = sheet.getCell(k, 0);
			colsName[k] = cell.getContents();
		}

		for (int i = 1; i < rows; i++) {

			Element secondRoot02 = new Element("Fpxx");// 创建二级根节点第二个;
			Element thirdRoot01 = new Element("Zsl");
			thirdRoot01.addContent(i + "");
			secondRoot02.addContent(thirdRoot01);
			Element thirdRoot02 = new Element("Fpsj");
			secondRoot02.addContent(thirdRoot02);
			Element forthRoot01 = new Element("Fp");
			thirdRoot02.addContent(forthRoot01);

			for (int h = 1; h < cols; h++) {
				Cell cell = sheet.getCell(h, i);
				if (!"".equals(cell.getContents())) {
					// System.err.println(""+h+":"+i+"===="+cell.getContents());
					Element element = new Element(colsName[h]).addContent(cell.getContents());
					forthRoot01.addContent(element);
				}

			}
			Cell cell2 = sheet.getCell(0, i);
			if (!"".equals(cell2.getContents())) {
				root.addContent(secondRoot02);
			}
		}
		Format format = Format.getPrettyFormat();
		format.setEncoding("gb2312");
		XMLOutputter XMLOut = new XMLOutputter(format);
		XMLOut.output(Doc, new FileOutputStream(exFile));
	}


}
