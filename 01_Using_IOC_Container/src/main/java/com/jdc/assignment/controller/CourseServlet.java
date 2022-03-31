package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.listener.SpringContainerManager;
import com.jdc.assignment.model.CourseModel;

@WebServlet({
	"/",
	"/courses",
	"/course-edit",
	"/course-save"
})
public class CourseServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var page = switch(req.getServletPath()) {
		case "/course-edit" -> "/course-edit.jsp";
		default -> {
			var model = getBean("courseModel", CourseModel.class);
			req.setAttribute("data", model.getAll());
			yield "/index.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		get data from request parameter
		
		var name = req.getParameter("courseName");
		var duration = req.getParameter("duration");
		var fees = req.getParameter("fees");
		var description = req.getParameter("description");
		
//		create  course

		var course = new Course();
		course.setName(name);
		course.setDuration(Integer.parseInt(duration));
		course.setFees(Integer.parseInt(fees));
		course.setDescription(description);
		
//		save to database
		
		var courseModel = getBean("courseModel", CourseModel.class);
		courseModel.save(course);
		
//		redirect to index.jsp
		
		resp.sendRedirect("/");
		
	}
}
