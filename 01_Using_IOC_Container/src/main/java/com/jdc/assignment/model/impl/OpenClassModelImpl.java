package com.jdc.assignment.model.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.OpenClassModel;

public class OpenClassModelImpl implements OpenClassModel{
	
	private static final String SELECT_ALL = """
			select c.id id,c.name name,c.fees fees,
			c.duration duration,c.description description,
			oc.id class_id,oc.start_date startDate,
			oc.teacher teacher  from course c join openClass 
			oc on c.id = oc.course_id where c.id = ?
			""";
	private static final String SELECT_ONE = """
			select c.id id,c.name name,c.fees fees,
			c.duration duration,c.description description,
			oc.id class_id,oc.start_date startDate,
			oc.teacher teacher  from course c join openClass 
			oc on c.id = oc.course_id where oc.id = ?
			""";	
	private static final String INSET = "insert into openClass (start_date,teacher,course_id) values(?,?,?)";
	private DataSource dataSource;
	
	

	public OpenClassModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<OpenClass> findByCourseId(int id) {
		
		var list =new ArrayList<OpenClass>();
		
		try(var con = dataSource.getConnection()){
			var stat = con.prepareStatement(SELECT_ALL);
			
			stat.setInt(1, id);
			
			var results = stat.executeQuery();
			
			while(results.next()) {
				
				var c = new Course();
				c.setId(results.getInt("id"));
				c.setName(results.getString("name"));
				c.setDuration(results.getInt("duration"));
				c.setFees(results.getInt("fees"));
				c.setDescription(results.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setCourse(c);
				openClass.setId(results.getInt("class_id"));
				openClass.setStartDate(results.getDate("startDate").toLocalDate());
				openClass.setTeacher(results.getString("teacher"));
				
				list.add(openClass);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void create(OpenClass openClass) {

		try(var con = dataSource.getConnection();){
			var stat = con.prepareStatement(INSET);
			
			stat.setDate(1,Date.valueOf(openClass.getStartDate()));
			stat.setString(2, openClass.getTeacher());
			stat.setInt(3, openClass.getCourse().getId());
			
			stat.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OpenClass findById(int id) {
		try(var con = dataSource.getConnection();){
			var stat = con.prepareStatement(SELECT_ONE);
			
			stat.setInt(1, id);
			
			var results = stat.executeQuery();
			
			while(results.next()) {
			
				var c = new Course();
				c.setId(results.getInt("id"));
				c.setName(results.getString("name"));
				c.setDuration(results.getInt("duration"));
				c.setFees(results.getInt("fees"));
				c.setDescription(results.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setCourse(c);
				openClass.setId(results.getInt("class_id"));
				openClass.setStartDate(results.getDate("startDate").toLocalDate());
				openClass.setTeacher(results.getString("teacher"));
				
				return openClass;
			}
			

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return null;
	}
	}


