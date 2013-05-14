/**
 * 
 */
package com.hhz.tms.admin.service.app;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.admin.dao.app.DictDataDao;
import com.hhz.tms.admin.dao.app.DictTypeDao;
import com.hhz.tms.entity.app.DictData;
import com.hhz.tms.entity.app.DictType;
import com.hhz.tms.util.dao.BaseDao;
import com.hhz.tms.util.service.BaseService;

/**
 * 数据字典
 * 
 * @author huangjian
 * 
 */
@Component
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class DictService extends BaseService<DictType> {
	@Autowired
	private DictTypeDao dictTypeDao;
	@Autowired
	private DictDataDao dictDataDao;
	public DictType save(DictType entity) {
		entity.setCreatedDate(new Date());
		entity.setUpdatedDate(new Date());
		return dictTypeDao.save(entity);
	}
	@Override
	public BaseDao<DictType> getBaseDao() {
		return dictTypeDao;
	}

	@Transactional(readOnly = false)
	public void deleteData(Long id) {
		dictDataDao.delete(id);
	}

	private void saveData(DictData data, DictType dictType) {
		data.setDictType(dictType);
		data.setUpdatedDate(new Date());
		data.setCreatedDate(new Date());
		dictDataDao.save(data);
	}
	public boolean existDictTypeCd(String dictTypeCd, String dictTypeOld) {
		long cnt = dictTypeDao.existDictTypeCd(dictTypeCd, dictTypeOld);
		if (cnt == 0) {
			return false;
		}
		return true;
	}
	@Transactional(readOnly = false)
	public void saveDatas(List<DictData> insertedRecords, List<DictData> updatedRecords, List<DictData> deletedRecords,
			Long dictTypeId) {
		DictType dictType = getEntity(dictTypeId);
		for (DictData dictData : insertedRecords) {
			saveData(dictData, dictType);
		}
		for (DictData dictData : updatedRecords) {
			saveData(dictData, dictType);
		}
		dictDataDao.delete(deletedRecords);
	}

	@Override
	public Class<DictType> getEntityClazz() {
		return DictType.class;
	}

}
