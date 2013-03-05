package com.ruyicai.mgr.mysql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ruyicai.mgr.util.Page;

public class ClientDao {
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> getAllcoop(){
		String sql = "select * from tbl_coop";
		return jdbcTemplate.queryForList(sql);
	}
	
	public void findList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from tbl_coop "+where, params.toArray()));
		params.add(page.getMaxResult()*page.getPageIndex());
		params.add(page.getMaxResult());
		String sql = "select * from tbl_coop "+where+" order by coopid desc limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}
	
	public Map<String, Object> getCoop(int id){
		String sql = "select * from tbl_coop where id=?";
		return jdbcTemplate.queryForMap(sql, new Object[]{id});
	}
	
	public int updateCoop(int id, String coopname, int coopid, int rate,
			String productno) {
		return jdbcTemplate.update("update tbl_coop set coopname = ?, coopid=?, rate=?,productno = ? where id = ?", 
				new Object[]{coopname, coopid, rate, productno, id});
	}
	
	public int deleteCoop(int id) {
		return jdbcTemplate.update("delete from tbl_coop where id = ?", new Object[]{id});
	}
	
	
	public int addCoop(int id, String coopname, int coopid, int rate,
			String productno) {
		return jdbcTemplate.update("insert into tbl_coop(id,coopname,coopid,rate,productno) values(?,?,?,?,?)", 
				new Object[]{id, coopname, coopid, rate, productno});
	}
	
	public int getId(){
		return jdbcTemplate.queryForInt("select max(id) from tbl_coop ");
	}
	public void findCDList(String where, List<Object> params, Page<Map<String, Object>> page) {
		String sql = "SELECT p.coopid, p.coopname, COUNT(*) AS num FROM tbl_userinf u,tbl_coop p WHERE u.channel=p.coopid  "+where
		+" AND u.createtime>? GROUP BY p.coopname ORDER BY COUNT(*) desc";
		
		String sql1 = "SELECT COUNT(1) FROM ("+sql+") t1";
		params.add(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		int i = jdbcTemplate.queryForInt(sql1,params.toArray());
		page.setTotalResult(i);
		
		String sql2 = sql+" limit ?,?";
		params.add(page.getMaxResult()*page.getPageIndex());
		params.add(page.getMaxResult());
		List<Map<String, Object>> cds = jdbcTemplate.queryForList(sql2, params.toArray());
		//SELECT p.coopid, p.coopname, COUNT(*) FROM tbl_userinf u,tbl_coop p 
		//WHERE u.channel=p.coopid AND u.createtime>'20120201' GROUP BY p.coopname ORDER BY COUNT(*) desc
		page.setList(cds);
	}
	
	public List<Map<String, Object>> findTproducttypeList() {
		String sql = "SELECT * FROM tproducttype";
		List<Map<String, Object>> cds = jdbcTemplate.queryForList(sql);
		return cds;
		
	}
}
