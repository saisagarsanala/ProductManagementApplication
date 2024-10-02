package com.sathya.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public DeleteProductServlet() {
        super();
    }
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Proid = request.getParameter("proid");
		int result = new ProductDAO().DeletebyId(Proid);
		
		if(result==1) {
			request.setAttribute("PResult", result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("ListOfProducts.jsp");
			dispatcher.forward(request, response); 			
		}
		else {
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("ListOfProducts.jsp");
			dispatcher.include(request, response);
		}		
	}
}
