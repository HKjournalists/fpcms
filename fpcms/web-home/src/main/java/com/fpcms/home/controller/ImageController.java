package com.fpcms.home.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/random")
public class ImageController  {

	@RequestMapping("/img.do")
	public void img(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedImage img = new BufferedImage(680,220,BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 68, 22);
		// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int index, len = ch.length;
		for (int i = 0; i < 30; i++) {
			for(int j = 0; j < 10; j++) {
				index = r.nextInt(len);
				g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
				g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的
				g.drawString("" + ch[index], (i * 15) + 3, j * 18);// 写什么数字，在图片
				sb.append(ch[index]);
			}
		}
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
	
}
