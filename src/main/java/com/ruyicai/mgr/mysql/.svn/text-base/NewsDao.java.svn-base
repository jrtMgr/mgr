package com.ruyicai.mgr.mysql;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ruyicai.mgr.mysql.pojo.Category;
import com.ruyicai.mgr.mysql.pojo.News;
import com.ruyicai.mgr.util.Page;

public class NewsDao {
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Category> getCategory(){
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from category where type ='wap' order by id");
		List<Category> relist = new ArrayList<Category>();
		Category c = null;
		for (Map<String, Object> map : list) {
			c = new Category();
			c.setId(map.get("id").toString());
			c.setCategoryName((String)map.get("categoryName"));
			c.setType((String)map.get("type"));
			relist.add(c);
		}
		return relist;
	}
	
	public void findList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from news where type = 'wap'"+where, params.toArray()));
		params.add(page.getMaxResult()*page.getPageIndex());
		params.add(page.getMaxResult());
		String sql = "select * from news where type = 'wap'"+where+" order by pubDate desc limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}

	public int delete(String id) {
		String sql = "delete from news where id =?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}
	public Map<String, Object> findNewsMap(String id) {
		String sql = "select * from news where id = ?";
		return jdbcTemplate.queryForMap(sql, new Object[]{id});
	}
	public int updateNews(String id, String title, String categoryId,
			String orderId, String content) {
		return jdbcTemplate.update("update news set title = ?, categoryId=?, orderId=?,content = ? where id = ?", 
				new Object[]{title, categoryId, orderId, content, id});
		
	}
	public int deleteNews(String id) {
		return jdbcTemplate.update("delete from news where id = ?", new Object[]{id});
	}
	public int addNews(String title, String categoryId, String orderId,
			String content) {
		return jdbcTemplate.update("insert into news(title,categoryId,orderId,content,pubDate,checked,type) values(?,?,?,?,?,?,?)", 
				new Object[]{title,categoryId,orderId,content,new Date(),1,"wap"});
	}
	public int approNews(String id, String checked) {
		return jdbcTemplate.update("update news set checked=?,pubDate=? where id=?",new Object[]{checked,new Date(),id});
	}
	public void findjingcaiResultList(String where, List<Object> params, Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("SELECT count(r.id) FROM tjingcairesult r , tjingcaimatches t WHERE CONCAT(t.type, '_', t.day, '_', t.weekid, '_', t.teamid) = r.id "+where, params.toArray()));
		params.add(page.getMaxResult()*page.getPageIndex());
		params.add(page.getMaxResult());
		
		String sql = "SELECT r.*,t.type,t.day,t.weekid,t.teamid,t.league,t.team,t.state FROM tjingcairesult r , tjingcaimatches t WHERE CONCAT(t.type, '_', t.day, '_', t.weekid, '_', t.teamid) = r.id "
			+where+" order by t.day desc,t.teamid desc  limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, params.toArray());
		page.setList(l);
	}
}
