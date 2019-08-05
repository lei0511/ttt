package com.th.ox.cleaver.activiti.util;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectUtil{
	//发生get请求
	public static String doGet(String getUrl){
		StringBuffer sb = new StringBuffer();
		BufferedReader in = null;
		try{
			URL realUrl = new URL(getUrl);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept","*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0; Window NT 5.1;SV1)");
			connection.setConnectTimeout(600000);
			connection.setReadTimeout(600000);
			connection.connect();
			/*Map<String,List<String>> map = connection.getHeaderFields();*/
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String tempLine = null;
			while((tempLine = in.readLine()) != null){
				sb.append(tempLine);
			}
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	//发送post请求
	 public static String doPost(String posturl,String params) {
		 try{
			 //通过在URL上调用openConnection创建连接对象
			  URL localURL = new URL(posturl);
		      URLConnection connection = localURL.openConnection();
		      
		      //设置参数
		      connection.setDoInput(true);
		      connection.setDoOutput(true);
		      connection.setUseCaches(false);
		      connection.setConnectTimeout(5000);
		      //请求属性
		      //设置通用的请求属性 设置头字段
		      connection.setRequestProperty("accept", "*/*");
		      connection.setRequestProperty("connection", "Keep-Alive");
		      connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
		      
		      //设置请求正文
		      PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
		      pw.print(params);
		      pw.flush();
		      pw.close();
		      
		      //使用connection方法建立到远程对象的实际连接
		      connection.connect();
		      //远程对象变为可用，远程对象的头字段和内容变成可访问
		      //获取响应的头字段
//		      Map<String ,List<String>> headers = connection.getHeaderFields();
		      //遍历所有的响应头，输出头字段
//		      for(String key:headers.keySet()){
//		    	  	
//		      }
		      //获取响应正文
		      BufferedReader reader = null;
		      StringBuffer resultBuffer = new StringBuffer();
		      String tempLine = null;
		      
		      reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
		      while((tempLine = reader.readLine()) != null){
		    	  resultBuffer.append(tempLine);
		      }
		      
		      reader.close();
		      return resultBuffer.toString();
		 	}catch(IOException e){
		 		e.printStackTrace();
		 	}
		 return null;
	 }
}
