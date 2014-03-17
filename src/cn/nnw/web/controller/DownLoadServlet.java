package cn.nnw.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//得到要下载的文件名  uuid
		String filename = "chaoniu.mp4";
		/*filename = new String(filename.getBytes("iso8859-1"),"UTF-8");*/
		
		//找出这个文件  url    c:\\
		String path = this.getServletContext().getRealPath("/video") ;//+ File.separator + getpath(filename)
		File file = new File(path + File.separator + filename);
/*		if(!file.exists()){
			request.setAttribute("message", "对不起，您要下载的资源已被删除");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}*/
		//得到文件的原始文件名
		String oldname = file.getName().substring(file.getName().indexOf("_")+1);  
		
		//通知浏览器以下载方式打开下面发送的数据
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(oldname,"UTF-8"));
		
		FileInputStream in = new FileInputStream(file);
		int len = 0;
		byte buffer[] = new byte[1024];
		OutputStream out = response.getOutputStream();
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public String getpath(String filename){
		int hashcode = filename.hashCode();  //121221
		int dir1 = hashcode&15;
		int dir2 = (hashcode>>4)&0xf;

		return dir1 + File.separator + dir2;  //   3/5
	}

}
