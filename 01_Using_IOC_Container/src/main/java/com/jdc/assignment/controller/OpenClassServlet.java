package com.jdc.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;

@WebServlet({
	"/classes",
	"/class-edit",
	"/class-save"
})
public class OpenClassServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var courseId = req.getParameter("courseId");
		
		var courseModel = getBean("courseModel", CourseModel.class);
		var course = courseModel.findByCourseId(Integer.parseInt(courseId));
		req.setAttribute("course", course);
		
		var page = switch(req.getServletPath()) {
		
		case "/class-edit" -> "/class-edit";
		
		default -> {
			var model = getBean("openClassModel", OpenClassModel.class);
			var classes = model.findByCourseId(Integer.parseInt(courseId));
			req.setAttribute("classes", classes);
			yield "/classes";
		}
		};
		
		getServletContext().getRequestDispatcher("%s.jsp".formatted(page)).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
//		get data from request parameter

		var courseId = req.getParameter("courseId");
		var teacher = req.getParameter("teacherName");
		var startDate = req.getParameter("startDate");
		
//		get courseModel
		
		var model = getBean("courseModel", CourseModel.class);
		
//		get course object from courseModel by courseId
		
		var course = model.findByCourseId(Integer.parseInt(courseId));
		

		
//		create open class object
		
		var op = new OpenClass();
		op.setCourse(course);
		op.setTeacher(teacher);
		op.setStartDate(LocalDate.parse(startDate));
		
//		save to database
		
		var classModel = getBean("openClassModel", OpenClassModel.class);
		classModel.create(op);
		
//		redirect to classes.jsp
		resp.sendRedirect("/classes?courseId="+course.getId());
	}
	
	

}
