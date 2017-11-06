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
	// ��xml�ļ����� �ַ���
	public static String xmlToLiu(String path) throws Exception {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			throw new FileNotFoundException();
		}
		// ��"GB2312"���룬���������������
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
		// ���������ɹ�����
		Workbook wb = null;
		// path.put("pt", "c:/excel.xls");
		// �������ڵ�;
		Element root = new Element("sheet");
		// �����ڵ���ӵ��ĵ��У�
		Document Doc = new Document(root);
		InputStream instream = new FileInputStream("C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xls");

		wb = Workbook.getWorkbook(instream);
		// ��ȡ��һ��Sheet��
		Sheet sheet = wb.getSheet(0);
		// ��ȡSheet������������������
		int columns = sheet.getColumns();
		// ��ȡSheet������������������
		int rows = sheet.getRows();
		// ��ȡָ����Ԫ��Ķ�������
		for (int i = 0; i < rows; i++) {
			Element elements = new Element("tr");
			for (int j = 0; j < columns; j++) {
				Cell cell = sheet.getCell(j, i);
				// str[i][j]=cell.getContents();//�ڴ˴���һ����ά���飬��ȡ��Ԫ�������
				// ����xml�ļ�
				elements.addContent(new Element("cell").setText(cell.getContents())); // ��д��Ԫ������ݡ�
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
		// ���������ɹ�����
		Workbook book = Workbook.getWorkbook(new File("C:\\Users\\wenjin.zhu\\Desktop\\KP0101.xls"));
		// ��ȡ��һ��Sheet��
		Sheet sheet = book.getSheet(0);
		// �������ڵ�;
		Element root = new Element("Kp");
		// �����������ڵ��һ��;
		Element secondRoot01 = new Element("Version");
		// �����ڵ���ӵ��ĵ��У�
		secondRoot01.addContent("2.0");
		root.addContent(secondRoot01);
		Document Doc = new Document(root);
		// ��ȡSheet������������������
		int cols = sheet.getColumns();
		// ��ȡSheet������������������
		int rows = sheet.getRows();
		System.err.println("cols:" + cols + "---rows:" + rows);
		String[] colsName = new String[cols];
		for (int k = 0; k < cols; k++) {
			Cell cell = sheet.getCell(k, 0);
			System.err.println(k + "---" + cell.getContents());
			colsName[k] = cell.getContents();
		}

		for (int i = 0; i < rows; i++) {

			Element secondRoot02 = new Element("Fpxx");// �����������ڵ�ڶ���;
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