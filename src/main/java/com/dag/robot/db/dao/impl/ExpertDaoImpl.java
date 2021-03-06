package com.dag.robot.db.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.analysis.function.Exp;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dag.robot.db.dao.ExpertDao;
import com.dag.robot.entities.Expert;
import com.dag.robot.entities.Field;
import com.dag.robot.entities.Orgnization;
import com.dag.robot.entities.Paper;
import com.dag.robot.entities.Patent;
import com.dag.robot.entities.RelExpertField;
import com.dag.robot.entities.RelExpertFieldId;
import com.dag.robot.entities.RelExpertPaper;
import com.dag.robot.entities.RelExpertPaperId;
import com.dag.robot.entities.RelExpertPatent;
import com.dag.robot.entities.RelExpertPatentId;
import com.dag.robot.entities.RelExpertTopic;
import com.dag.robot.entities.RelExpertTopicId;
import com.dag.robot.entities.Topic;
import com.dag.robot.utils.EntitiesForListUtil;
import com.dag.robot.utils.EntitiesForShowUtil;
import com.dag.robot.utils.StringMergeUtil;
import com.dag.robot.web.bean.ExpertForCheck;
import com.dag.robot.web.bean.ExpertForList;
import com.dag.robot.web.bean.Page;

@Repository("expertDao")
public class ExpertDaoImpl extends BaseDao implements ExpertDao {

	@Override
	public void addExpert(Expert expert) {
		save(expert);
	}
	
	@Override
	public void updateExpert(Expert expert) {
		update(expert);
	}

	@Override
	public List<Expert> getByName(String name) {
		String hql = "from Expert as expert where expert.name = ?";
		@SuppressWarnings("unchecked")
		List<Expert> experts = query(hql).setString(0, name).list();
		return experts;
	}

	@Override
	public Expert getById(int expertId) {
		return get(Expert.class, expertId);
	}

	@Override
	public void deleteExpert(Expert expert) {
		delete(expert);
	}

	@Override
	public void addField(Expert expert, Field field, int weight) {

		saveOrUpdate(expert);
		saveOrUpdate(field);
		RelExpertFieldId relExpertFieldId = new RelExpertFieldId(expert.getExpertId(),
				field.getFieldId());
		RelExpertField relExpertField = new RelExpertField(relExpertFieldId, expert, field,
				weight);
		saveOrUpdate(relExpertField);
	}

	@Override
	public void addTopic(Expert expert, Topic topic, int weight) {

		saveOrUpdate(expert);
		saveOrUpdate(topic);
		RelExpertTopicId relExpertTopicId = new RelExpertTopicId(expert.getExpertId(),
				topic.getTopicId());
		RelExpertTopic relExpertTopic = new RelExpertTopic(relExpertTopicId, expert, topic,
				weight);
		saveOrUpdate(relExpertTopic);

	}

	@Override
	public void addPaper(Expert expert, Paper paper, int authorOrder) {
		saveOrUpdate(expert);
		saveOrUpdate(paper);
		RelExpertPaperId relExpertPaperId = new RelExpertPaperId(expert.getExpertId(),
				paper.getPaperId());
		RelExpertPaper relExpertPaper = new RelExpertPaper(relExpertPaperId, expert, paper,
				authorOrder);
		saveOrUpdate(relExpertPaper);
	}

	@Override
	public void addPatent(Expert expert, Patent patent, int authorOrder) {
		saveOrUpdate(expert);
		saveOrUpdate(patent);
		RelExpertPatentId relExpertPatentId = new RelExpertPatentId(expert.getExpertId(),
				patent.getPatentId());
		RelExpertPatent relExpertPatent = new RelExpertPatent(relExpertPatentId, expert,
				patent, authorOrder);
		saveOrUpdate(relExpertPatent);
	}

	@Override
	public void addOrgnization(Expert expert, Orgnization orgnization,
			String job) {
		expert.setOrgnization(orgnization);
		expert.setJob(job);
		saveOrUpdate(expert);
	}

	@Override
	public Field getField(int expertId) {
		Expert expert = getById(expertId);
		Field field = expert.getField();
		return field;
	}

	@Override
	public List<Topic> getTopics(int expertId) {
		Expert expert = getById(expertId);
		List<Topic> topics = new ArrayList<Topic>();
		Set<RelExpertTopic> relExpertTopics = expert.getRelExpertTopics();
		Iterator<RelExpertTopic> iterator = relExpertTopics.iterator();
		while(iterator.hasNext()){
			RelExpertTopic relExpertTopic = iterator.next();
			Topic topic = relExpertTopic.getTopic();
			topics.add(topic);
		}
		if(topics.size() == 0)
			return null;
		return topics;
	}

	@Override
	public List<Paper> getPapers(int expertId) {
		Expert expert = getById(expertId);
		List<Paper> papers = new ArrayList<Paper>();
		Set<RelExpertPaper> relExpertPapers = expert.getRelExpertPapers();
		Iterator<RelExpertPaper> iterator = relExpertPapers.iterator();
		while(iterator.hasNext()){
			RelExpertPaper relExpertPaper = iterator.next();
			Paper paper = relExpertPaper.getPaper();
			papers.add(paper);
		}
		if(papers.size() == 0)
			return null;
		return papers;
	}

	@Override
	public List<Patent> getPatents(int expertId) {
		Expert expert = getById(expertId);
		List<Patent> patents = new ArrayList<Patent>();
		Set<RelExpertPatent> relExpertPatents = expert.getRelExpertPatents();
		Iterator<RelExpertPatent> iterator = relExpertPatents.iterator();
		while(iterator.hasNext()){
			RelExpertPatent relExpertPatent = iterator.next();
			Patent patent = relExpertPatent.getPatent();
			patents.add(patent);
		}
		if(patents.size() == 0)
			return null;
		return patents;
	}

	@Override
	public Orgnization getOrgnization(int expertId) {
		Expert expert = getById(expertId);
		Orgnization orgnization = expert.getOrgnization();
		return orgnization;
	}

	@Override
	public Page<ExpertForList> page(int pageSize, int currenPage) {
		Query query = query("select count(*) from Expert");
		Long totalCount =  (Long) query.uniqueResult();
		Page<ExpertForList> page = new Page<ExpertForList>(currenPage, pageSize, totalCount);
		page.init();
		query = query("from Expert");
		query.setFirstResult((currenPage-1) * pageSize);
		query.setMaxResults(pageSize);
		List<Expert> experts = query.list();
		List<ExpertForList> expertForLists = EntitiesForListUtil.expertForLists(experts);
		page.setList(expertForLists);
		return page;
	}

	@Override
	public List<ExpertForCheck> check(String expertName) {
		List<Expert> experts = getByName(expertName);
		List<ExpertForCheck> exerptForChecks = new ArrayList<ExpertForCheck>();
		if(experts.size() == 0)
			return exerptForChecks;//表示没有重名
		
		for(int i = 0; i < experts.size(); i++){
			Expert expert = experts.get(i);
			ExpertForCheck expertForCheck = new ExpertForCheck();
			expertForCheck.setName(expert.getName());
			expertForCheck.setExpertId(expert.getExpertId());
			expertForCheck.setOrg(expert.getOrgnization().getName());
			exerptForChecks.add(expertForCheck);
		}
		return exerptForChecks;
	}

	@Override
	public void updateExperience(int expertId, String experience) {
		Expert expert = getById(expertId);
		expert.setExperience(experience);
		updateExpert(expert);
	}

	@Override
	public void updateInfo(int expertId, String info) {
		Expert expert = getById(expertId);
		expert.setInfo(info);
		updateExpert(expert);
	}

	@Override
	public void updateAchievement(int expertId, String achievement) {
		Expert expert = getById(expertId);
		expert.setAchievement(achievement);
		updateExpert(expert);
	}

	@Override
	public Expert checkSame(String name, String OrgName) {
		List<Expert> experts = getByName(name);
		if(experts.size() != 0){
			for(int i = 0; i < experts.size(); i++){
				if(experts.get(i).getOrgnization().getName().equals(OrgName))
					return experts.get(i);
			}
		}
		return null;
	}

	@Override
	public List<ExpertForList> getByField(String field, int num) {
		String hql = "from Expert as expert where expert.field.name = ?";
		Query query = query(hql);
		query.setMaxResults(num);
		query.setString(0, field);
		List<Expert> experts = query.list();
		return EntitiesForListUtil.expertForLists(experts);
	}

	
}
