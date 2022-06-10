package com.jdc.project.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {

	@Autowired
	private ProjectHelper projectHelper;

	@Autowired
	private SimpleJdbcInsert projectInsert;

	@Autowired
	private NamedParameterJdbcOperations template;

	private RowMapper<Project> rowMapper;

	public ProjectService() {
		rowMapper = new BeanPropertyRowMapper<Project>(Project.class);
	}

	public int create(Project project) {

//		Validate Input Value
		projectHelper.validate(project);

//		Get Insert Parameters
		var params = projectHelper.insertParams(project);

//		Insert Into DB & Return Id
		return projectInsert.executeAndReturnKey(params).intValue();
	}

	public Project findById(int id) {
		
		var sql = "select m.id managerId,m.name managerName,"
				+ "m.login_id memberLogin,p.id,p.name,p.description,"
				+ "p.manager managerId ,p.start startDate,p.months"
				+ " from member m inner join project p on m.id = p.manager"
				+ " where p.id =:id";
		
		var params = new HashMap<String, Object>();
		params.put("id", id);
		
		return template.queryForObject(sql, params, rowMapper);
	}

	public List<Project> search(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {

		var sb = new StringBuilder(
				"select m.id managerId,m.name managerName,"
				+ "m.login_id memberLogin,p.id,p.name,"
				+ "p.description,p.manager managerId ,"
				+ "p.start startDate,p.months from member m "
				+ "inner join project p on m.id = p.manager"
				+ " where 1 = 1");
		
		var params = new HashMap<String, Object>();

		if (StringUtils.hasLength(project)) {
			sb.append(" and lower(p.name) like :name");
			params.put("name", project.toLowerCase().concat("%"));
		}

		if (StringUtils.hasLength(manager)) {
			sb.append(" and lower(m.name) like :name");
			params.put("name", manager.toLowerCase().concat("%"));
		}

		if (dateFrom != null) {
			sb.append(" and start >= :dateFrom");
			params.put("dateFrom", dateFrom);
		}

		if (dateTo != null) {
			sb.append(" and start <= :dateTo");
			params.put("dateTo", dateTo);
		}

		return template.queryForStream(sb.toString(), params, rowMapper)
				.map(pro -> (Project) pro).toList();
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		
		var sql = "update project set name = :name,"
				+ "description = :description,start = :startDate,"
				+ "months = :month where id = :id";
		
		var params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("name", name);
		params.put("description", description);
		params.put("startDate", Date.valueOf(startDate));
		params.put("month", month);
		
		return template.update(sql, params);
		
	}

	public int deleteById(int id) {
		
		var sql = "delete from project where id = :id";
		var params = new HashMap<String, Object>();
		params.put("id", id);
		
		return template.update(sql, params);
	}

}
