/**
 * 
 */
package com.hhz.tms.admin.service.sys;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.hhz.tms.admin.dao.sys.DeptDao;
import com.hhz.tms.admin.dao.sys.UserDao;
import com.hhz.tms.entity.sys.Dept;
import com.hhz.tms.entity.sys.Role;
import com.hhz.tms.entity.sys.User;

/**
 * 用户管理
 * 
 * @author huangjian
 * 
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class UserService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private DeptDao deptDao;

	public List<User> findAll() {
		Sort sort = new Sort(Direction.ASC, "dispOrderNo");
		List<User> users = (List<User>) userDao.findAll(sort);
		return users;
	}

	public User getEntity(Long id) {
		return userDao.findOne(id);
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	/** 验证用户是否匹配 */
	public boolean validate(String loginName, String password) {
		boolean flag = false;
		User user = userDao.findByLoginName(loginName);
		if (user != null) {
			flag = isPwdEqual(password, user);
		}
		return flag;
	}

	public boolean existDictTypeCd(String loginName, String loginNameOld) {
		long cnt = userDao.existLoginName(loginName, loginNameOld);
		if (cnt == 0) {
			return false;
		}
		return true;
	}

	@Transactional(readOnly = false)
	public void saveRoles(Long id, String roleids) {
		String[] ids = roleids.split(",");
		User user = getEntity(id);
		// 删除不存在的记录
		List<Role> roles = user.getRoles();
		for (Iterator<Role> it = roles.iterator(); it.hasNext();) {
			Role role = it.next();
			if (!ArrayUtils.contains(ids, String.valueOf(role.getId()))) {
				// 删除不存在的记录
				it.remove();
			} else {
				// 已有的记录不动
				ids = ArrayUtils.removeElement(ids, String.valueOf(role.getId()));
			}
		}
		// 新增新记录
		for (String strId : ids) {
			if (StringUtils.isNotBlank(strId)) {
				Long id_new = Long.valueOf(strId);
				if (id_new > 0) {
					Role role = new Role();
					role.setId(id_new);
					user.getRoles().add(role);
				}
			}
		}
		userDao.save(user);
	}

	@Transactional(readOnly = false)
	public User save(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		if (user.getDept() != null) {
			Dept dept = deptDao.findOne(user.getDept().getId());
			user.setDept(dept);
		}
		return userDao.save(user);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		userDao.delete(id);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		user.setPassword(getEncodePwd(user.getPlainPassword(), salt));
	}

	private String getEncodePwd(String plainPwd, byte[] salt) {
		byte[] hashPassword = Digests.sha1(plainPwd.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}

	/** 判断2个密码是否一致 */
	public boolean isPwdEqual(String plainPwd, User user) {
		byte[] salt = Encodes.decodeHex(user.getSalt());
		String encodePwd = getEncodePwd(plainPwd, salt);
		return encodePwd.equals(user.getPassword());
	}
}
