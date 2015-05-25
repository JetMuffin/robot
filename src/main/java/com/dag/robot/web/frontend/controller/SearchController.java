package com.dag.robot.web.frontend.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dag.robot.data.add.AddService;
import com.dag.robot.db.dao.ExpertDao;
import com.dag.robot.db.dao.OrgnizationDao;
import com.dag.robot.db.dao.RelExpertTopicDao;
import com.dag.robot.db.dao.TopicDao;
import com.dag.robot.entities.Expert;
import com.dag.robot.entities.Topic;
import com.dag.robot.utils.EntitiesForShowUtil;
import com.dag.robot.web.bean.ExpertForShow;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	@Qualifier("expertDao")
	private ExpertDao expertDao;

	@Autowired
	@Qualifier("orgnizationDao")
	private OrgnizationDao orgnizationDao;

	@Autowired
	@Qualifier("topicDao")
	private TopicDao topicDao;

	@Autowired
	@Qualifier("relExpertTopicDao")
	private RelExpertTopicDao relExpertTopicDao;

	@Autowired
	private AddService addService;
	/**
	 * 搜索页面
	 * @param model
	 * @param searchType 搜索类型 expert or topic or field
	 * @param searchKey 搜索key
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String search(Model model,String searchType,String searchKey) throws UnsupportedEncodingException{
		searchKey = new String(searchKey.getBytes("ISO-8859-1"),"utf-8");
		if(searchType.equals("expert")){
			List<ExpertForShow> experts = expertDao.getByFuzzyName(searchKey);
			model.addAttribute("experts", experts);
			model.addAttribute("searchKey", searchKey);
			System.out.println(searchKey);
			System.out.println(experts.size());
			return "search/expert";
		}
		else if(searchType.equals("field"))
			return "search/field";
		else 
			return "search/topic";
	}
	
	@RequestMapping(value = "/fuzzyTopic/{topic}", method = RequestMethod.GET)
	public String paper(@PathVariable String topic, Model model) {
		List<Topic> topics = topicDao.getTopicByFuzzyName(topic);
		model.addAttribute("topics", topics);
		//返回需要修改！！！
		return "expert/paper";
	}
}
