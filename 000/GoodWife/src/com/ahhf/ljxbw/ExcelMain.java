package com.ahhf.ljxbw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * 
 * @author wenjin.zhu
 *
 */
public class ExcelMain {
	// 把xml文件读成 字符流
	public static String xmlToLiu(String path) throws Exception {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			throw new FileNotFoundException();
		}
		// 以"GB2312"编码，解决中文乱码问题
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
		BufferedReader br = new BufferedReader(read);
		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = br.readLine();
		while (temp != null) {
			sb.append(temp + "\n");
			temp = br.readLine();
		}
		br.close();
		read.close();
		return sb.toString();
	}

	/*
	 * public static void main(String[] args) throws Exception { String path =
	 * "C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xls";
	 * System.out.println(xmlToLiu(path)); }
	 */

	public static void main(String[] args) throws IOException, BiffException {
		// 这里是生成工作簿
		Workbook wb = null;
		// path.put("pt", "c:/excel.xls");
		// 创建根节点;
		Element root = new Element("sheet");
		// 将根节点添加到文档中；
		Document Doc = new Document(root);
		InputStream instream = new FileInputStream("C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xls");

		wb = Workbook.getWorkbook(instream);
		// 获取第一张Sheet表
		Sheet sheet = wb.getSheet(0);
		// 获取Sheet表中所包含的总列数
		int columns = sheet.getColumns();
		// 获取Sheet表中所包含的总行数
		int rows = sheet.getRows();
		// 获取指定单元格的对象引用
		for (int i = 0; i < rows; i++) {
			Element elements = new Element("tr");
			for (int j = 0; j < columns; j++) {
				Cell cell = sheet.getCell(j, i);
				// str[i][j]=cell.getContents();//在此创建一个二维数组，获取单元格的数据
				// 生成xml文件
				elements.addContent(new Element("cell").setText(cell.getContents())); // 填写单元格的数据。
				root.addContent(elements.detach());
			}
		}
		Format format = Format.getPrettyFormat();
		format.setEncoding("gb2312");
		XMLOutputter XMLOut = new XMLOutputter(format);
		XMLOut.output(Doc, new FileOutputStream("C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xml"));

	}

	@Test
	public void test01() throws BiffException, IOException {
		// 这里是生成工作簿
		Workbook book = Workbook.getWorkbook(new File("C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xls"));
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
		System.err.println("cols:" + cols + "---rows:" + rows);
		String[] colsName = new String[cols];
		for (int k = 0; k < cols; k++) {
			Cell cell = sheet.getCell(k, 0);
			System.err.println(k + "---" + cell.getContents());
			colsName[k] = cell.getContents();
		}

		for (int i = 0; i < rows; i++) {

			Element secondRoot02 = new Element("Fpxx");// 创建二级根节点第二个;
			Element thirdRoot01 = new Element("Zsl");
			thirdRoot01.addContent(i+"");
			secondRoot02.addContent(thirdRoot01);
			Element thirdRoot02 = new Element("Fpsj");
			Element forthRoot01 = new Element("Fp");
			thirdRoot02.addContent(forthRoot01);
			
			for (int h = 0; h < cols; h++) {
				Cell cell = sheet.getCell(h, i);
				Element element = new Element(colsName[h]).addContent(cell.getContents());
				forthRoot01.addContent(element);
			}
			root.addContent(secondRoot02);
		}
		Format format = Format.getPrettyFormat();
		format.setEncoding("gb2312");
		XMLOutputter XMLOut = new XMLOutputter(format);
		XMLOut.output(Doc, new FileOutputStream("C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xml"));

	}

}