<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${keyword},${company}</title>
	<meta name="keywords" content="最值得信赖:${keyword}" />
	<meta name="description" content="${company}代理有限公司于2005年挂牌成立,是经政府批准的具有${city}开发票，代开发票资格的专业税务开票公司,可开材料费发票|住宿费发票|餐饮费发票|酒店发票|广告费发票|等各类发票" />
</duowan:override>

<duowan:override name="pageRight">

	<div class="subject_bg">
		<div class="subject_title">公司简介</div>
	</div>
	<div >
		<div class="neirong">
			&nbsp; &nbsp;${company}于2005年挂牌树立,是经政府同意的首家具有${city}开发票，<span>${city}</span>代开发票资历的专业税务开公司。我司具有一支高素质、经验丰富、训练有素的高效率办税专业财税咨询团队。通过多年来的展开，与工商、税务、财政、海关、银行等部分及各地多家公司树立了紧密的合作关系，在全国各地开设了分公司。公司首要供给<span>${city}</span>开发票，发票征询，开记账，彻底触及全国事务。<br />
<span>${company}</span>与全国各地首要城市都有事务协作公司，长时间承受财税署理事务，署理记帐、补帐、旧帐收拾、管帐（财政）征询。署理交税谋划征询，尽可能地获得“节税”的税收利益。<br />
&nbsp; &nbsp; &nbsp;<span style="font-family:宋体;">本公司本着为贵单位合理合法,节约运营成本和开支的, 在诚信保密的基础上,专业的理财。发票征询办理效劳范围：房屋租赁发票,税务机关发票一式三份,增值税专用发票，商品销售发票,餐饮发票,运输发票,广告发票,咨询发票,住宿发票,搬运发票,酒店服务发票,劳务费发票,建筑安装发票,加工修理发票,会议费发票,餐饮定额发票,租赁发票,服务发票 ,工业统一发票,商业统一发票,地方税控发票,等各行业发票等...</span><span style="font-family:宋体;">！</span><br />
<p>
	&nbsp; &nbsp; &nbsp; 愿同各工商界携手、做交税人兄弟。恪守职业道德，诚挚为广大客户效劳，忠诚实行本身的效劳许诺。咱们拿手为委托人完成最小、合理的交税而进行描绘和运筹，协助客户躲避交税危险，下降公司税负，完成赢利最大化。&nbsp;
</p>
<p>
</p>			
		</div>
	</div>
	
	<div class="subject_bg">
		<div class="subject_title">产品中心</div>
	</div>
	<div >
		<div class="neirong">
			<div style="float: left;">
				<img src="${ctx}/images/faipiao/1335531700.jpg" />
				<br />
				<font align="center">
					建筑发票
				</font>
			</div>
			<div style="float: left;">
				<img src="${ctx}/images/faipiao/1335531865.jpg" />
				<br />
				<font align="center">
					广告业发票
				</font>
			</div>
			
			<div  style="float: left;">
				<img src="${ctx}/images/faipiao/1335531855.jpg" />
				<br />
				<font align="center">
					运输业发票
				</font>
			</div>
			<div  style="float: left;">
				<img src="${ctx}/images/faipiao/1335531542.jpg" />
				<br />
				<font align="center">
					商品发票
				</font>
			</div>
			<!-- 
			<div style="float: none;">
				<img src="${ctx}/images/faipiao/1335531696.jpg" />
				<font align="center">
					服务业发票
				</font>
			</div>
			 -->
			
		</div>
	</div>
				
</duowan:override>

<duowan:override name="foot">
<div>
	<div id="hiddenNews" >
		<c:forEach items="${newsPage.itemList}" var="item">
			<b><a href="${ctx}/content/show/${item.id}.do" title="${item.title}">${item.title}</a></b>
		</c:forEach>
	</div>
	<div>
		<div style="position: absolute; background:#FFF; display: block; " id="hiddenDiv" ondblclick="this.style.display='none'" ></div>
		<script type="text/javascript">
			$(document).ready(function() {
				var offsetTop = $('#hiddenNews').offset().top;
				var offsetLeft = $('#hiddenNews').offset().left;
				var width = $('#hiddenNews').width();
				var height = $('#hiddenNews').height();
				if($.browser.chrome){
					$('#hiddenDiv').offset({ top: offsetTop + height, left: offsetLeft });
					$('#hiddenDiv').height(height * 2);
				}else {
					$('#hiddenDiv').offset({ top: offsetTop, left: offsetLeft });
					$('#hiddenDiv').height(height);
				}
				$('#hiddenDiv').width(width);
				//alert(width+" top:"+offsetTop + " hiddenDiv:"+$('#hiddenDiv').offset().top+","+$('#hiddenDiv').offset().left);
			});
		</script>
	</div>
</div>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<jsp:include page="/layout.do"></jsp:include>