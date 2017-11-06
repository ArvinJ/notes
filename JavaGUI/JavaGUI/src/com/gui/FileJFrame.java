package com.gui;

import java.io.*;
import java.awt.*;
import javax.swing.*;

import jxl.read.biff.BiffException;

import java.awt.event.*;

/**
 * 
 * @author 我家华华
 * @phone 15156980156
 */
public class FileJFrame implements ActionListener {
	/**
	 * 选择的目标文件url
	 */
	private static String fileOpenUrl = "";
	/**
	 * 目标导出url
	 */
	private static String fileExUrl = "";
	/**
	 * 系统存储文件
	 */
	private static final String SYS_URL = "D:\\amySetting.txt";
	JFrame frame = new JFrame("Amy开票");
	JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
	Container con = new Container();// 布局1
	Container con1 = new Container();// 布局2
	JLabel label = new JLabel("导出的目录");
	JLabel label2 = new JLabel("选择文件");
	JTextField text = new JTextField();
	JTextField text2 = new JTextField();
	JButton button = new JButton("Open");
	JButton button2 = new JButton("Open");
	JButton buttonExport = new JButton("Export");
	JFileChooser jfc = new JFileChooser();// 文件选择器

	FileJFrame() {
		jfc.setCurrentDirectory(new File("d:\\"));// 文件选择器的初始目录定为d盘
		// 下面两行是取得屏幕的高度和宽度
		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
		frame.setSize(600, 500);// 设定窗口大小
		frame.setContentPane(tabPane);// 设置布局
		// 下面设定标签等的出现位置和高宽
		label.setBounds(10, 10, 70, 20);
		label2.setBounds(10, 30, 100, 20);
		text.setBounds(80, 10, 120, 20);
		text2.setBounds(80, 30, 120, 20);
		button.setBounds(210, 10, 68, 20);
		button2.setBounds(210, 30, 68, 20);
		buttonExport.setBounds(210, 55, 68, 20);
		button.addActionListener(this);// 添加事件处理
		button2.addActionListener(this);// 添加事件处理
		buttonExport.addActionListener(this);
		con1.add(label);
		con.add(label2);
		con1.add(text);
		con.add(text2);
		con1.add(button);
		con.add(button2);
		con.add(buttonExport);
		con.add(jfc);
		con1.add(jfc);
		tabPane.add("目录/文件选择", con);// 添加布局1
		tabPane.add("设置", con1);// 添加布局2
		frame.setVisible(true);// 窗口可见
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
	}

	public void actionPerformed(ActionEvent e) {// 事件处理
		if (e.getSource().equals(button)) {// 判断触发方法的按钮是哪个
			// 设置 导出的文件夹
			jfc.setFileSelectionMode(1);// 设定只能选择到文件夹
			int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = jfc.getSelectedFile();// f为选择到的目录
				text.setText(f.getAbsolutePath());
				fileExUrl = f.getAbsolutePath();
				System.err.println("*****导出的url---" + fileExUrl);
				File file = new File(SYS_URL);
				try {
					FileOperation.createFile(file);
					FileOperation.writeTxtFile(fileExUrl, file);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		if (e.getSource().equals(button2)) {
			// 选择转化目标文件
			jfc.setFileSelectionMode(0);// 设定只能选择到文件
			int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = jfc.getSelectedFile();// f为选择到的文件
				text2.setText(f.getAbsolutePath());
				fileOpenUrl = f.getAbsolutePath();
				System.err.println("-- Xls-- fileOpenUrl-------"+fileOpenUrl);
			}
		}

		if (e.getSource().equals(buttonExport)) {
			// 导出
			System.err.println("000-----" + fileExUrl);

			if (!"".equals(fileOpenUrl) && !"".equals(fileExUrl)) {
				System.err.println("文件路径" + fileOpenUrl);
				File file = new File(fileOpenUrl);
				String fileName = file.getName();
				/**
				 * 前缀
				 */
				String prefix = fileName.substring(0, fileName.lastIndexOf("."));
				/**
				 * 后缀
				 */
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				String exFileUrl = fileExUrl + "\\" + prefix + ".xml";
				File exFile = new File(fileExUrl + "\\" + prefix + ".xml");
				//|| "xlsx".equals(suffix)
				if ("xls".equals(suffix) ) {

					try {
						FileOperation.createFile(exFile);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {
						fileExUrl = exFileUrl;
						System.err.println("eeee--" + fileOpenUrl + "----000s---" + fileExUrl);
						ExcelToXmlMain.importFile(fileOpenUrl, fileExUrl);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BiffException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择后缀为.xls 或 .xlsx的文件！", "提示", JOptionPane.ERROR_MESSAGE);
					System.err.println("请选择后缀为.xls 或 .xlsx的文件！");
				}

			} else if ("".equals(fileOpenUrl)) {
				JOptionPane.showMessageDialog(null, "请选择打开目标文件", "提示", JOptionPane.ERROR_MESSAGE);
				System.err.println("请选择打开目标文件");
			} else if ("".equals(fileExUrl)) {
				JOptionPane.showMessageDialog(null, "请选择导出目标文件夹", "提示", JOptionPane.ERROR_MESSAGE);
				System.err.println("请选择导出目标文件夹");
			}

		}
	}

	public static void main(String[] args) {
		new FileJFrame();
	}
}