package com.ruyicai.mgr.filter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Tmenu;
import com.ruyicai.mgr.domain.Tpermission;
import com.ruyicai.mgr.util.PaySign;
import com.ruyicai.mgr.util.URLExcludeUtil;

public class PermissionFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(PermissionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		if(url.endsWith(request.getContextPath()+"/")) {
			chain.doFilter(request, response);
			return;
		}
		List<String> excludeUrls = URLExcludeUtil.getExcludeUrls();
		for(String excludeUrl : excludeUrls) {
			if(url.contains(excludeUrl) && !url.endsWith(".jsp")) {
				//jumu edit
				if(url.endsWith("/logout")){
					logout(session);
					response.sendRedirect(request.getContextPath()+"/j_spring_security_logout");
					return;
				}
				//jumu edit end
				chain.doFilter(request, response);
				return;
			}
		}
		
		
		Tloguser tloguser = (Tloguser)session.getAttribute("user");
		if(null == tloguser) {
			//jumu edit
			List<Tloguser> tlogusers = Tloguser.findTlogusersByNickname(
					request.getRemoteUser()).getResultList();
			if (tlogusers.isEmpty()) {
				throw new RuntimeException();
			}
			tloguser = tlogusers.get(0);
			if (!tloguser.getState().equals(BigDecimal.ONE)) {
				throw new RuntimeException();
			}
			logger.info(tloguser.getNickname() + "登录");
			session.setAttribute("user", tloguser);
			session.setAttribute("permission", Tpermission
					.findByUseridAndState(tloguser.getId(), BigDecimal.ONE));
			session.setAttribute("usermenu", Tmenu.findByUserid(tloguser.getId()));
			
			//request.setAttribute("errormsg", "请先登录");
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
			//return;
			//jumu edit end
		}
		if(url.endsWith(request.getContextPath()+"/index.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		List<Tmenu> tmenus = (List<Tmenu>)session.getAttribute("usermenu");
		if(ispermission(url, tmenus)) {
			chain.doFilter(request, response);
		} else {
			//jumu edit
			logout(session);
			//request.setAttribute("errormsg", "没有权限操作此功能");
			response.sendRedirect(request.getContextPath()+"/j_spring_security_logout");
			
			//jumu edit end
			return;
		}
	}
	
	private boolean ispermission(String url, List<Tmenu> tmenus) {
		for(Tmenu tmenu : tmenus) {
			if(url.contains(tmenu.getUrl())) {
				return true;
			}
		}
		return false;
	}
	
	private void logout(HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("permission");
		session.removeAttribute("menu");
		session.invalidate();
	}

	@Override
	public void destroy() {
	}
}
