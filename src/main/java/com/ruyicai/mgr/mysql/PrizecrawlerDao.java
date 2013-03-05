package com.ruyicai.mgr.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class PrizecrawlerDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Map<String, Object>> findAllLotcheckswitchs() {
		return jdbcTemplate.queryForList("select * from lotcheckswitch");
	}
	public List<Map<String, Object>> findAllTagencys() {
		return jdbcTemplate.queryForList("select * from tagency");
	}
	public List<Map<String, Object>> findAllTthresholds() {
		return jdbcTemplate.queryForList("select * from tthreshold");
	}
	
	public void updatethreshold(int id, double threshold){
		jdbcTemplate.update("update tthreshold set threshold = ? where id = ?", new Object[]{threshold,id});
	}
	
	public void updateLotcheckswitch(String lotno, int state){
		jdbcTemplate.update("update lotcheckswitch set state = ? where lotno = ?", new Object[]{state,lotno});
	}

	public void updatetagency(int id, double weight){
		jdbcTemplate.update("update tagency set weight = ? where id = ?", new Object[]{weight,id});
	}
	
	public List<Map<String, Object>> findAllTagencyprizecode(String batchcode, String lotno) {
		return jdbcTemplate.queryForList("select * from tagencyprizecode where batchcode = ? and lotno = ?", new Object[]{batchcode, lotno});
	}
	public List<Map<String, Object>> findAllTagencyprizecode2(String where, Object[] o) {
		return jdbcTemplate.queryForList("select * from tagencyprizecode o "+ where, o);
	}
}
