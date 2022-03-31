package com.jdc.assignment.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.model.CourseModel;

public class CourseModelImpl implements CourseModel{

	private static final String SELECT_ALL = "select * from course";
	private static final String INSET = "insert into course (name,duration,fees,description) values (?,?,?,?)";
	private static final String SELECT_ONE = "select * from course where id = ?";
	private DataSource dataSource;
	
	
	
	public CourseModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Course> getAll() {
		
		var list = new ArrayList<Course>();
		
		try(var con = dataSource.getConnection();){
			var stat = con.prepareStatement(SELECT_ALL);
			
			var results = stat.executeQuery();
			
			while(results.next()){
				var course = new Course();
				course.setId(results.getInt("id"));
				course.setName(results.getString("name"));
				course.setFees(results.getInt("fees"));
				course.setDuration(results.getInt("duration"));
				course.setDescription(results.getString("description"));
				
				list.add(course);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void save(Course course) {
		
		try(var con = dataSource.getConnection();){
			var stat = con.prepareStatement(INSET);
			
			stat.setString(1, course.getName());
			stat.setInt(2, course.getDuration());
			stat.setInt(3, course.getFees());
			stat.setString(4, course.getDescription());
			
			stat.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Course findByCourseId(int id) {
		try(var con = dataSource.getConnection();){
			var stat = con.prepareStatement(SELECT_ONE);
			
			stat.setInt(1, id);
			
			var results = stat.executeQuery();
			
			while(results.next()){
				var course = new Course();
				course.setId(results.getInt("id"));
				course.setName(results.getString("name"));
				course.setFees(results.getInt("fees"));
				course.setDuration(results.getInt("duration"));
				course.setDescription(results.getString("description"));
				
				return course;
						
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
