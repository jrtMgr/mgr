package com.ruyicai.mgr.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ruyicai.mgr.domain.Nineteenpay;
import com.ruyicai.mgr.util.Page;

public class ChargecenterDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void findNineteenpayList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from nineteenpay o " + where, params.toArray()));
		params.add(page.getMaxResult() * page.getPageIndex());
		params.add(page.getMaxResult());
		String sql = "select * from nineteenpay o " + where + " order by chargetime desc limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}
	
	public void findDnapayList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from dnapay o " + where, params.toArray()));
		params.add(page.getMaxResult() * page.getPageIndex());
		params.add(page.getMaxResult());
		String sql = "select * from dnapay o " + where + " order by chargetime desc limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}
	
	public void findTalibatchpayList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from talibatchpay o " + where, params.toArray()));
		params.add(page.getMaxResult() * page.getPageIndex());
		params.add(page.getMaxResult());
		String sql = "select * from talibatchpay o " + where + " order by paydate desc limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}
	
	
	public void findChargeconfigList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from chargeconfig o " + where, params.toArray()));
		params.add(page.getMaxResult() * page.getPageIndex());
		params.add(page.getMaxResult());
		String sql = "select * from chargeconfig o " + where + " order by o.memo limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}
	
	public Map<String, Object> findChargeconfig(String id) {
		return jdbcTemplate.queryForMap("select * from chargeconfig o where id = ?", new Object[]{id});
	}
}
