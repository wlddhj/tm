package com.hhz.tms.service.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.dao.TaskDao;
import com.hhz.tms.entity.Task;
import com.hhz.tms.util.WebUtil;

//Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class TaskService {

	private TaskDao taskDao;

	public Task getTask(Long id) {
		return taskDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public void saveTask(Task entity) {
		taskDao.save(entity);
	}

	@Transactional(readOnly = false)
	public void deleteTask(Long id) {
		taskDao.delete(id);
	}

	public List<Task> getAllTask() {
		return (List<Task>) taskDao.findAll();
	}

	public Page<Task> getUserTask(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		searchParams.put("EQL_user.id", userId);
		Specification<Task> spec = WebUtil.buildSpecification(searchParams,Task.class);

		return taskDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
}
