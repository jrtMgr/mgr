package com.ruyicai.mgr.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ruyicai.mgr.mysql.pojo.Category;
import com.ruyicai.mgr.mysql.pojo.FLData;
import com.ruyicai.mgr.mysql.pojo.Issus;
import com.ruyicai.mgr.util.Page;

public class IssusDao {
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
	
	public void findList1(Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from Issus "));
		String sql = "select * from Issus order by addDate desc limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, 
				new Object[]{page.getMaxResult()*page.getPageIndex(),page.getMaxResult()});
		page.setList(l);
		
	}
	
	public void findList(Page<Map<String, Object>> page) {
		page.setTotalResult(jdbcTemplate.queryForInt("select count(*) from Issus "));
		String sql = "select id,vdlId,any9Id,games6Id,games4Id,addDate,d1,d2,CASE WHEN flag=0 THEN '未发布' " +
				"WHEN now()>d1 and now()<d2 THEN '赛事进行中' WHEN now()>d2 THEN '对阵已结束' ELSE '发售中' END dz,CASE WHEN con2=0 " +
				"THEN '未添加' WHEN con1=con2 and con1=flCount1 THEN '已完成' ELSE '部分添加' END sg " +
				"from(select t1.id,t1.vdlId,t1.any9Id,t1.games6Id,t1.games4Id,t1.addDate,t1.flag,t2.d1,t2.d2,IFNULL(t3.con1,0) con1," +
				"IFNULL(t4.con2,0) con2,flCount1 from Issus t1 left join (select pid,case when min(date)='' then max(date) " +
				"else min(date) end d1,max(date) d2,count(*) flCount1 from FLData group by pid)t2 on t1.id=t2.pid left join " +
				"(select pid,count(result1) con1 from FLData group by pid) t3 on t1.id=t3.pid  left join (select pid,count(result1)" +
				" con2 from FLData where result1<>'' group by pid) t4 on t1.id=t4.pid order by addDate desc) tab limit ?,?";
		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql, 
				new Object[]{page.getMaxResult()*page.getPageIndex(),page.getMaxResult()});
		page.setList(l);
		
	}
	public Map<String, Object> getIssus(String id){
		String sql = "select * from Issus where id=?";
		return jdbcTemplate.queryForMap(sql, new Object[]{id});
	}
	
	public List<Map<String, Object>> getFLDataList(String pid){
		String sql = "select * from FLData where pid=? order by num";
		return jdbcTemplate.queryForList(sql, new Object[]{pid});
	}
	
	public int updateFLDataFlag(String id, String flag ) {
		return jdbcTemplate.update("update FLData set flag = ? where id = ?", 
				new Object[]{flag, id});
		
	}
	public int deleteEvents(String id) {
		String sql = "delete from FLData where pid =?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public int deleteIssue(String id) {
		String sql = "delete from Issus where id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public Map<String, Object> getFLDataByIdAndNun(String id, String num){
		return jdbcTemplate.queryForMap("select * from FLData where pid=? and num=?", new Object[]{id,num});
	}
	public int addFLData(FLData fl) {
		return jdbcTemplate.update("insert into FLData(Pid,Num,Name,HTeam,VTeam,Date,avgOdds) values(?,?,?,?,?,?,?)", 
				new Object[]{fl.getPid(),fl.getNum(),fl.getName(),fl.getHTeam(),fl.getVTeam(),fl.getDate(),fl.getAvgOdds()});
	}
	
	public Map<String, Object> getNextIssus() {
		String sql = "select max(vdlId) vdlId,max(any9Id) any9Id,max(games6Id) games6Id,max(games4Id) games4Id from Issus";
		return jdbcTemplate.queryForMap(sql);
	}
	public int addIssus(Issus is) {
		return jdbcTemplate.update("insert into Issus(vdlId,any9Id,games6Id,games4Id,addDate,type,flag) values(?,?,?,?,?,?,?)", 
				new Object[]{is.getVdlId(),is.getAny9Id(),is.getGames6Id(),is.getGames4Id(),is.getAddDate(),is.getType(),is.getFlag()});
	}
	
	public Long getId(String vdlId,String any9Id,String games6Id,String games4Id){
		return jdbcTemplate.queryForLong("select id from Issus where vdlId=? and any9Id=? and games6Id=? and games4Id=? ", 
				new Object[]{vdlId,any9Id,games6Id, games4Id});
	}
	public int updateFLData(FLData fl) {
		return jdbcTemplate.update("update FLData set name = ?,HTeam=?,VTeam=?,date=?,avgOdds=?,leagueRank=?,HTeam8=?" +
				",VTeam8=?,ou=?,ya=?,xi=?,ce=?,bsbfzd=? ,bsbfkd=?,banchangzd=?,banchangkd=? where pid = ? and num=?", 
				new Object[]{fl.getName(),fl.getHTeam(),fl.getVTeam(),fl.getDate(),fl.getAvgOdds(),fl.getLeagueRank(),
				fl.getHTeam8(),fl.getVTeam8(),fl.getOu(),
				fl.getYa(),fl.getXi(),fl.getCe(),fl.getBsbfzd(),fl.getBsbfkd(),fl.getBanchangzd(),fl.getBanchangkd(),fl.getPid(),fl.getNum()});
		
	}
	public int savecc(String id, int i, String flag) {
		return jdbcTemplate.update("update FLData set flag=? where pid=? and num = ?",new Object[]{flag, id ,i});
	}
	public int release(String id) {
		return jdbcTemplate.update("update Issus set flag=1 where id=?",new Object[]{id});
	}
}
