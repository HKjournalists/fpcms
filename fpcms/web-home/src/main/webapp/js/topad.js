

function generateLeftRightAD(leftDiv,rightDiv) {
	//左边
	leftDivContent	="<div id=LeftAda style='left:5px;POSITION:absolute;TOP:5px;'>";
	leftDivContent	+=leftDiv;
	leftDivContent	+="<a style='text-align:left; cursor: hand; display:block; background:#eee; width:100px;' onclick='javascript:closedivLeft1()' hidefocus='true'>关闭</a>";
	leftDivContent	+="</div>";

	//右边
	rightDivContent = "<div id=RightAda style='right:5px;POSITION:absolute;TOP:5px;'>";
	rightDivContent	+= rightDiv;
	rightDivContent +="<a style='text-align:right; cursor: hand; display:block; background:#e0e0e0; width:100px;' onclick='javascript:closedivLeft1()' hidefocus='true'>关闭</a>";
	rightDivContent	+="</div>";

	document.write(leftDivContent);
	document.write(rightDivContent);
	window.setInterval("heartBeatt()",1);
}

lastScrollY=0;
function heartBeatt() {
	var diffYy;
	if(document.documentElement && document.documentElement.scrollTop) {
		diffYy = document.documentElement.scrollTop;
	}
	else if (document.body) {
		diffYy = document.body.scrollTop;
	}
	else {
		/*Netscape stuff*/
	}
		//alert(diffYy);
	percent=.1*(diffYy-lastScrollY);
	if(percent>0) {
		percent=Math.ceil(percent);
	}
	else {
		percent=Math.floor(percent);
	}
	document.getElementById("LeftAda").style.top=parseInt(document.getElementById("LeftAda").style.top)+percent+"px";
	document.getElementById("RightAda").style.top=parseInt(document.getElementById("LeftAda").style.top)+percent+"px";
	
	lastScrollY=lastScrollY+percent;
}

function closedivLeft1() {
	LeftAda.style.visibility="hidden";
	RightAda.style.visibility="hidden";
}

