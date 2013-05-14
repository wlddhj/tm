/**
 * 
 */
package com.hhz.tms.admin.dao.app;

import org.springframework.data.jpa.repository.Query;

import com.hhz.tms.entity.app.DictType;
import com.hhz.tms.util.dao.BaseDao;

/**
 * @author huangjian
 * 
 */
public interface DictTypeDao extends BaseDao<DictType> {
	@Query(value="select count(*) from DictType m where dictTypeCd=?1 and dictTypeCd <> ?2")
	public Long existDictTypeCd(String dictTypeCd, String dictTypeOld);
}
