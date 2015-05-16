package com.dag.robot.db.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.dag.robot.db.dao.OrgnizationDao;
import com.dag.robot.entities.Orgnization;
import com.dag.robot.entities.RelExpertOrg;

@Repository("orgnizationDao")
public class OrgnizationDaoImpl extends BaseDao implements OrgnizationDao {

	@Override
	public void addOrgnization(Orgnization orgnization) {
		save(orgnization);
	}

	@Override
	public void updateOrgnization(Orgnization orgnization) {
		update(orgnization);
	}

	@Override
	public Orgnization getById(int orgnizationId) {
		return get(Orgnization.class,orgnizationId);
	}

	@Override
	public List<Orgnization> getAllOrgnizations() {
		return getAll("Orgnization");
	}

	@Override
	public void deleteOrgnization(Orgnization orgnization) {
		
		Set<RelExpertOrg> relExpertOrgs = orgnization.getRelExpertOrgs();
		Iterator<RelExpertOrg> iterator = relExpertOrgs.iterator();
		while(iterator.hasNext()){
			RelExpertOrg relExpertOrg = iterator.next();
			delete(relExpertOrg);
		}
		delete(orgnization);
	}
	
}