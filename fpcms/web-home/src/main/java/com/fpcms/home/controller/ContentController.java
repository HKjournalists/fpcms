/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */


package com.fpcms.home.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.tika.io.IOUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duowan.common.util.DateConvertUtils;
import com.fpcms.common.BaseController;
import com.fpcms.common.util.SpiderUtil;
import com.fpcms.common.util.WebUtil;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsChannelQuery;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsContentService;


/**
 * [CmsChannel] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
public class ContentController extends BaseController{
	
	private CmsChannelService cmsChannelService;
	private CmsContentService cmsContentService;
	
	private final String LIST_ACTION = "redirect:/admin/cmschannel/refreshParent.jsp";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCmsChannelService(CmsChannelService service) {
		this.cmsChannelService = service;
	}
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
	}

	public String last(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		CmsContent content = cmsContentService.findLastBySite(getSite());
		if(content == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return show0(model, content.getId(), response, DateConvertUtils.extract(content.getDateCreated(),"yyyyMMdd"), content);
	}

	/**
	 * 用于外链使用的链接
	 * @param model
	 * @param dateCreatedString
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/linked/{dateCreated}.do")
	public String linked(ModelMap model,@PathVariable("dateCreated") String dateCreatedString,HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(SpiderUtil.isSpider(request) || StringUtils.isNotBlank(request.getParameter("debug"))) {
			Date dateCreated = null;
			try {
				dateCreated = DateConvertUtils.parse(dateCreatedString, "yyyyMMdd");
			}catch(Exception e) {
				return last(model,request,response);
			}
			
			CmsContent cmsContent = cmsContentService.findFirstByCreatedDay(getSite(),dateCreated);
			if(cmsContent == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			}
//			return show0(model, cmsContent.getId(), response, dateCreated, cmsContent);
			WebUtil.send301Redirect(response,cmsContent.getUrl());
			return null;
		}else {
			return "/linked";
		}
	}
	
	/**
	 * 显示内容
	 */
	@RequestMapping("/content/{dateCreated}/{contentId}.do")
	public String show(ModelMap model,CmsChannelQuery query,@PathVariable("dateCreated") String dateCreatedString,@PathVariable("contentId") long contentId,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Date dateCreated = DateConvertUtils.parse(dateCreatedString, "yyyyMMdd");
		CmsContent cmsContent = cmsContentService.getById(dateCreated,contentId);
		return show0(model, contentId, response, dateCreated, cmsContent);
	}

	private String show0(ModelMap model, long contentId,
			HttpServletResponse response, Date dateCreated,
			CmsContent cmsContent) throws IOException {
		if(cmsContent == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
//		if(!cmsContent.getSite().equals(getSite())) {
//			String location = "http://"+cmsContent.getSite()+"/content/show/"+contentId+".do";
//			WebUtil.send301Redirect(response, location);
//			return null;
//		}
		
		model.put("cmsContent", cmsContent);
		model.put("nextCmsContent", cmsContentService.getNextCmsContent(dateCreated,cmsContent.getSite(),contentId));
		model.put("preCmsContent", cmsContentService.getPreCmsContent(dateCreated,cmsContent.getSite(),contentId));
		
		return "/content/show";
	}
	
	/**
	 * 显示内容
	 */
	@RequestMapping("/content/show/{contentId}.do")
	@Deprecated
	public void showDeprecated(ModelMap model,CmsChannelQuery query,@PathVariable("contentId") long contentId,HttpServletRequest request,HttpServletResponse response) throws IOException {
		CmsContent cmsContent = cmsContentService.getById(contentId);
		if(cmsContent == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		String day = DateConvertUtils.format(cmsContent.getDateCreated(), "yyyyMMdd");
		String location = request.getContextPath() + "/content/"+day+"/"+contentId+".do";
		WebUtil.send301Redirect(response,location);
	}
	
	@RequestMapping("/content_img/{dateCreated}/{contentId}.do")
	public void content_img(@PathVariable("dateCreated") String dateCreatedString,@PathVariable("contentId") long contentId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg;");
		response.setDateHeader("Expires", DateUtils.addDays(new Date(), 360 * 5).getTime());
		response.setHeader("Cache-Control", "max-age=180000");
		
		Date dateCreated = DateConvertUtils.parse(dateCreatedString, "yyyyMMdd");
		CmsContent cmsContent = cmsContentService.getById(dateCreated,contentId);
		String content = cmsContent.getContent();
		
		BufferedImage backgroudImage = getBackgroudImage();
		BufferedImage outputImage = new GenImageByContentProcessor(content,backgroudImage).execute();
		ImageIO.write(outputImage, "JPG", response.getOutputStream());
	}

	public class GenImageByContentProcessor {
		private String content;
		private BufferedImage backgroudImage;
		int width;
		int height;
		int ROWS = 30;
		int COLS = 20;
		
		public GenImageByContentProcessor(String content,
				BufferedImage backgroudImage) {
			super();
			this.content = content;
			this.backgroudImage = backgroudImage;
			width = backgroudImage.getWidth();
			height = backgroudImage.getHeight();
		}

		private BufferedImage execute() {
			BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			// 得到该图片的绘图对象
			Graphics g = outputImage.getGraphics();
			// 填充整个图片的颜色
			g.fillRect(0, 0, 680, 220);
			g.drawImage(backgroudImage,0,0,null);
			
			for (int i = 0; i < ROWS; i++) {
				for(int j = 0; j < COLS; j++) {
					g.setColor(new Color(0,0,255));
					g.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 15));// 输出的
					int index = i * COLS + j;
					if(index >= content.length()) {
						break;
					}
					char charAt = content.charAt(index);
					int x = (i * 15) + 3;
					int y = j * 18;
					
					if(x >= width || y >= height) {
						break;
					}
					
					g.drawString(""+charAt,x, y);
				}
			}
			return outputImage;
		}
	}

	static BufferedImage backgroudImage = null;
	private static BufferedImage getBackgroudImage() throws IOException {
		if(backgroudImage == null) {
			InputStream input = ContentController.class.getResourceAsStream("/img/content_img_backgroud.jpg");
			try {
				backgroudImage = ImageIO.read(input);
				return backgroudImage;
			}finally {
				IOUtils.closeQuietly(input);
			}
		}
		return backgroudImage;
	}

}

