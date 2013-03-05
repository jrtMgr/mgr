package com.ruyicai.mgr.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tgiftaudit;

@RequestMapping("/batchgiftAudit")
@Controller
public class BatchGiftAuditController {
	private Logger logger = Logger.getLogger(BatchGiftController.class);
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view){
		logger.info("batchgiftAudit/list");
		view.addObject("list", Tgiftaudit.findAllTgiftauditsByFlag(0));
		view.setViewName("batchgiftAudit/list");
		return view;
	}
	
	@RequestMapping("/doAudit")
	public ModelAndView doAudit(@RequestParam(value = "id") String id,
			@RequestParam("flag") int flag,
			ModelAndView view){
		logger.info("batchgift/doAudit");
		String errormsg = "操作成功";
		try {
			Tgiftaudit tgiftaudit = Tgiftaudit.findTgiftaudit(id);
			if (tgiftaudit==null || tgiftaudit.getFlag() != 0) {
				view.addObject("errormsg", "不是待审核");
				return this.list(view);
			}
			tgiftaudit.setFlag(flag);
			tgiftaudit.setAftertime(new Date());
			tgiftaudit.merge();
		} catch (Exception e) {
			logger.error("batchgiftAudit/doAudit error:", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
}
