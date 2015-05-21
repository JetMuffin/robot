package com.dag.robot.db.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.dag.robot.db.dao.FieldDao;
import com.dag.robot.entities.Field;
import com.dag.robot.entities.RelExpertField;
import com.dag.robot.entities.RelFieldTopic;

@Repository("fieldDao")
public class FieldDaoImpl extends BaseDao implements FieldDao {

	@Override
	public void addField(Field field) {
		save(field);
	}

	@Override
	public void updateField(Field field) {
		update(field);
	}

	@Override
	public Field getById(int fieldId) {
		return get(Field.class,fieldId);
	}

	@Override
	public List<Field> getAllFields() {
		return getAll("Field");
	}

	@Override
	public void deleteField(Field field) {
		delete(field);
	}

	@Override
	public Field getByName(String name) {
		String hql = "from Field as field where field.name = ?";
		List<Field> fields = query(hql).setString(0, name).list();
		if(fields.size() == 0 || fields == null)
			return null;
		return fields.get(0);
	}
	
}
