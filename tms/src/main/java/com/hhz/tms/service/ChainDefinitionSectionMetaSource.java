/**
 * 
 */
package com.hhz.tms.service;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.tms.service.account.ShiroService;

/**
 * @author huangjian
 * 
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {
	@Autowired
	private ShiroService shiroService;
	private String filterChainDefinitions;

	/**
	 * 通过filterChainDefinitions对默认的url过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws Exception {
		// 获取所有Resource

		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		shiroService.initSection(section);
		// 其他地址都需要登录
		section.put("/**", "user");
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
