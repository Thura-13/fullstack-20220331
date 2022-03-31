package com.jdc.assignment.model.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel {

	private static final String SELECT_ALL = """
						select op.id openClass_id,c.id course_id,c.name course_name,op.teacher teacher,
						op.start_date startDate,r.id registraion_id,r.student student,r.phone phone,
						r.email email from openClass op 
						join course c on op.course_id = c.id 
						join registration r on op.id = r.openClass_id where op.id = ?;
						""";

	private static final String INSET = "insert into registration (student,phone,email,openClass_id)values(?,?,?,?)";
	
	private DataSource dataSource;

	public RegistrationModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Registration> findRegistrationByClass(int id) {

		var list = new ArrayList<Registration>();

		try (var con = dataSource.getConnection()) {
			var stat = con.prepareStatement(SELECT_ALL);
			
			stat.setInt(1, id);
			
			var results = stat.executeQuery();
			while(results.next()) {
				
				var course = new Course();
				course.setId(results.getInt("course_id"));
				course.setName(results.getString("course_name"));
	
				
				var op = new OpenClass();
				op.setCourse(course);
				op.setId(results.getInt("openClass_id"));
				op.setStartDate(results.getDate("startDate").toLocalDate());
				op.setTeacher(results.getString("teacher"));
				
				var r = new Registration();
				r.setOpenClass(op);
				r.setId(results.getInt("registraion_id"));
				r.setStudent(results.getString("student"));
				r.setPhone(results.getString("phone"));
				r.setEmail(results.getString("email"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void create(Registration registration) {
		try(var con = dataSource.getConnection();){
			var stat = con.prepareStatement(INSET);
			
			stat.setString(1, registration.getStudent());
			stat.setString(2, registration.getPhone());
			stat.setString(3, registration.getEmail());
			stat.setInt(4, registration.getOpenClass().getId());
			
			stat.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
