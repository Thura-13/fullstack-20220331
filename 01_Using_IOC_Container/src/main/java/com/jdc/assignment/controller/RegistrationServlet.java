package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;
import com.jdc.assignment.model.RegistrationModel;

@WebServlet({
	"/registrations",
	"/registration-edit",
	"/registration-save"
})
public class RegistrationServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		get openClassId from parameter
		var classId = req.getParameter("classId");
		
//		get openClassModel
		var classModel = getBean("openClassModel", OpenClassModel.class);
		
//		get openClass Object from openClassModel by openClass id
		var openClass = classModel.findById(Integer.parseInt(classId));
		
		
//		set data to request scope
		req.setAttribute("openClass", openClass);
		
		
		var page = switch(req.getServletPath()) {
		case "/registration-edit" -> "registration-edit";
		default -> {

//			get model
			var registrationModel = getBean("registrationModel", RegistrationModel.class);
			
//			get registration object from model
			var registration = registrationModel.findRegistrationByClass(Integer.parseInt(classId));
			
//			set data to request scope
			req.setAttribute("registration", registration);
			
			yield "registrations";
		}
			
		};
		
//		forward to registration.jsp
		getServletContext().getRequestDispatcher("/%s.jsp".formatted(page)).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		get data from parameter 
		
		var classId = req.getParameter("classId");
		var studentName = req.getParameter("studentName");
		var phone = req.getParameter("phone");
		var email = req.getParameter("email");
		
//		get openClass model
		
		var openClassModel = getBean("openClassModel", OpenClassModel.class);
		
//		get openClass object from openClass model by openClassId
		
		var openClass = openClassModel.findById(Integer.parseInt(classId));
		
//		create registration object
		
		var r = new Registration();
		r.setStudent(studentName);
		r.setPhone(phone);
		r.setEmail(email);
		r.setOpenClass(openClass);
		
//		get registration model 
		
		var registrationModel = getBean("registrationModel", RegistrationModel.class);
		
//		save to database
		
		registrationModel.create(r);
		
//		redirect to registration.jsp
		
		resp.sendRedirect("/registrations?classId="+classId);
	}
}
