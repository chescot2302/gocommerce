/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.server.servlet;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SISTEMAS
 */
public class SrvGestionSeguridad  extends HttpServlet {
	private static final long serialVersionUID = 2556188587429050683L;
	private static final Logger log = Logger.getLogger(SrvGestionSeguridad.class
			.getName());

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=ISO-8859-1");
		String url = request.getServletPath();
		if (url.equals("/sesion.html")) {
			redirectGoSuperAdmin(request, response);
		}
	}

	private void redirectGoSuperAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("indexsesion.jsp").forward(
					request, response);			
		} catch (Exception ex) {
			log.warning(ex.getMessage());
			ex.printStackTrace();
		}
	}
	

	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
