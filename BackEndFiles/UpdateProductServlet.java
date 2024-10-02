package com.sathya.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;




@WebServlet("/UpdateProductServlet")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
//    	Read From Data
		String Pid= request.getParameter("proId");
		String PName=request.getParameter("Proname");
		double PPrice=Double.parseDouble(request.getParameter("ProPrice"));
		String PBrand=request.getParameter("ProBrand");
		String PMadeIn=request.getParameter("ProMadeIN");
		Date PMfgDate=Date.valueOf(request.getParameter("ProMFDdate"));
		Date PExpDate=Date.valueOf(request.getParameter("ProExpDate"));
		Part part=request.getPart("oldProImage");
		Part part1 = request.getPart("proImage");
		InputStream PImage=part.getInputStream();
		byte[] imageWork = IOUtils.toByteArray(PImage);		
		
product product = new product();
		
		product.setProid(Pid);
		product.setProname(PName);
		product.setProPrice(PPrice);
		product.setProBrand(PBrand);
		product.setProMadein(PMadeIn);
		product.setProMfgDate(PMfgDate);
		product.setProExpDate(PExpDate);
		product.setImageWork(imageWork);
		
Part newPart1=request.getPart("newProImage"); 
		
		if(newPart1 !=null && newPart1.getSize()>0) {
			InputStream inputStream1=newPart1.getInputStream();
			byte[] newProImage=IOUtils.toByteArray(inputStream1);
			product.setImageWork(newProImage);
		}
		else {
			String str1=request.getParameter("oldProImage");
			byte[] existingProImage=Base64.getDecoder().decode(str1);
			product.setImageWork(existingProImage);
		}

Part newPart2=request.getPart("newProAudio"); 
		
		if(newPart1 !=null && newPart1.getSize()>0) {
			InputStream inputStream1=newPart1.getInputStream();
			byte[] newProAudio=IOUtils.toByteArray(inputStream1);
			product.setImageWork(newProAudio);
		}
		else {
			String str1=request.getParameter("oldProAudio");
			byte[] existingProAudio=Base64.getDecoder().decode(str1);
			product.setImageWork(existingProAudio);
		}

Part newPart3=request.getPart("newProVideo"); 
		
		if(newPart1 !=null && newPart1.getSize()>0) {
			InputStream inputStream1=newPart1.getInputStream();
			byte[] newProVideo=IOUtils.toByteArray(inputStream1);
			product.setImageWork(newProVideo);
		}
		else {
			String str1=request.getParameter("oldProVideo");
			byte[] existingProVideo=Base64.getDecoder().decode(str1);
			product.setImageWork(existingProVideo);
		}

		
		
		//"newProImage is the name of your file input field
		
		
		ProductDAO productDAO = new ProductDAO();
		int result = productDAO.updateById(product);

		if (result == 1) {
			request.setAttribute("saveResult", result);

			RequestDispatcher dispatcher = request.getRequestDispatcher("ListOfProducts.jsp");
			dispatcher.forward(request, response);
		} else {
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.println("<h2 class='font-italic font-weight-bold text-danger text-center'>Product Saving Failed..."
					+ result + "</h2>");
			writer.println("</body>");
			writer.println("</html>");
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("ProApp.html");
			dispatcher1.include(request, response);
		}
		
		
	}
}
