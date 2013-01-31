<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<FORM name=regform action=http://utility.baidu.com/addurl/apply.php method=post target="_top">
          <table cellspacing=0 cellpadding=0 width=700 align=center border=0>
            <tr>
              <td valign=top class="f13">　　　（例：http://www.baidu.com）<br>
      　　　
        <input id=url2 size=50 value="${param.url}" name=url >
                <br>
                　　　 请输入验证码　
<img src="http://utility.baidu.com/addurl/image_validate_code.php?key=BUheOwY8BW0=" border="0" hspace="3" align="absmiddle">
                
<input type=hidden name=ivc value="BUheOwY8BW0=">
                <input size=10 name=code value="L399">
                <input  type=submit value="提交网站" name=Submit2>
              </td>
            </tr>
          </table>
</FORM>

