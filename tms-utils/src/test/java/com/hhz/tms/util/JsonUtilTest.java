/**
 * 
 */
package com.hhz.tms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;

/**
 * @author huangjian
 *
 */
public class JsonUtilTest {
	protected final Log logger = LogFactory.getLog(getClass());
	@Test
	public void obj2Json(){
		Permission permission=new Permission();
		permission.setId(1l);
		permission.setPermCd("user:create");
		permission.setPermName("用户新增");
		List<Resource> resources=new ArrayList<Resource>();
		Resource resource=new Resource();
		resources.add(resource);
		permission.setResources(resources);
		logger.info(JsonUtil.obj2Json(permission, new String[]{}));
	}
}
