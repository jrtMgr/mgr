package com.ruyicai.mgr.domain.statis;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "CHANNEL", schema = "JRTSTATIS", identifierField = "id")
public class Channel {
	@Id
	@Column(name = "ID")
	private Integer id;
	@Column(name = "YWID")
	private Integer ywid;
	@Column(name = "CODE")
	private String code;
	@Column(name = "NAME")
	private String name;	
	@Column(name = "BDUSERID")
	private Integer bduserid;
	@Column(name = "CJDATE")
	private Date cjdate;
	@Column(name = "STATUS")
	private Integer status;
	@Column(name = "BZ")
	private String bz;
	@Column(name = "TEL")
	private String tel;
	@Column(name = "URL")
	private String url;
	@Column(name = "ISOPEN")
	private Integer isopen;
	@Column(name = "REGIST",nullable=true)
	private Double regist;
	@Column(name = "CHARGE",nullable=true)
	private Double charge;
	
	
	public static List<Channel> findList() {
		return Channel.entityManager().createQuery("SELECT o FROM Channel o where o.status = 1 order by id", Channel.class).getResultList();
	}
	
	public static Map<String, String> getMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Channel> list = findList();
		for (Channel channel : list) {
			map.put(channel.id.toString(), channel.name);
		}
		return map;         
	}
	public static int findId() {
		return Channel.entityManager().createQuery("SELECT max(o.id) FROM Channel o ", Integer.class).getSingleResult();
	}
	
	public static void findList(String where, String orderby, List<Object> params, Page<Channel> page) {
		TypedQuery<Channel> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Channel o " + where + orderby, Channel.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Tlot.entityManager().createQuery(
				"select count(o) from Channel o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
	
	/**
	 * 根据业务id查询channel
	 * @param ywid
	 * @return
	 */
	public static List<Channel> findChannelByYwid(int ywid){
		return entityManager().createQuery("select o from Channel o where o.status = 1 and o.ywid = ?", Channel.class).setParameter(1, ywid).getResultList();
	}
}
