<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
	<!-- created with Free Online Sitemap Generator www.xml-sitemaps.com -->
	<url>
		<loc>http://${site}/</loc>
		<changefreq>hourly</changefreq>
	</url>
	<url>
		<loc>http://${site}/news.do</loc>
		<changefreq>hourly</changefreq>
	</url>
	<c:forEach items="${navChannelList}" var="item">
	<url>
		<loc>http://${site}/${item.channelCode}.do</loc>
		<changefreq>weekly</changefreq>
	</url>
	</c:forEach>
</urlset>