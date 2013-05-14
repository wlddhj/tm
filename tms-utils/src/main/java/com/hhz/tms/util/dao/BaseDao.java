/**
 * 
 */
package com.hhz.tms.util.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author huangjian
 * @param <T>
 *
 */
@NoRepositoryBean
public  interface  BaseDao<T>  extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T>{

}
