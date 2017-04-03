package com.rockies.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockies.mapper.EvaluationMapper;
import com.rockies.model.Evaluation;
import com.rockies.services.IEvaluationService;

@Service
public class EvaluationServiceImpl implements IEvaluationService {

	@Autowired
	private EvaluationMapper evaluationMapper;

	@Override
	public int insert(Object o) {
		evaluationMapper.insert(o);
		return 0;
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		evaluationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Object> getAllList() {
		return evaluationMapper.getAllList();
	}

	@Override
	public List<Object> getAllListByMap(Map map) {
		return evaluationMapper.getAllListByMap(map);
	}

	@Override
	public List<Map<String, Object>> getAllListMap(Map map) {
		return evaluationMapper.getAllListMap(map);
	}

	@Override
	public Object getOneByPrimaryKey(int id) {
		return evaluationMapper.getOneByPrimaryKey(id);
	}

	@Override
	public long getCount(Map map) {
		return evaluationMapper.getCount(map);
	}

	@Override
	public void updateObject(Object o) {
		evaluationMapper.updateObject(o);
	}

	@Override
	public List<Evaluation> getEvaluationListByPid(Map params) {
		return evaluationMapper.getEvaluationListByPid(params);
	}

	@Override
	public int getEvaluationCountByPid(String pid) {
		int i = 0;
		i = evaluationMapper.getEvaluationCountByPid(pid);
		return i;
	}

}
