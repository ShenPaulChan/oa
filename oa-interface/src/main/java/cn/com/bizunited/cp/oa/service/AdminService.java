package cn.com.bizunited.cp.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.common.web.vo.AdminVo;
import cn.com.bizunited.cp.oa.domain.base.Admin;

/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.service.AdminService
 * @author: shenp
 * @date: 2017年2月10日 下午6:37:24 
 *
 */
@Service
public interface AdminService extends BaseService<Admin> {

	/**
	 * @Title: searchDeptUsers 
	 * @param deptId
	 * @param key
	 * @return
	 * @author: Paul Chan
	 * @date: 2017年3月19日 上午11:36:10
	 */
	List<Admin> searchDeptUsers(Integer deptId, String key);

	/**
	 * @Title: getMemberUser 
	 * @param memberId
	 * @return
	 * @author: Paul Chan
	 * @date: 2017年3月20日 下午1:36:56
	 */
	Admin getMemberUser(Integer memberId);
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	Page<AdminVo> findVisitUserPage(Pageable pageable);

/******************************************************以下是远端移植的方法**********************************************************************/

	/**
	 * 根据用户名获取用户信息
	 * @Title: getByUserName
	 * @param username
	 * @return
	 * @author: Omar(OmarZhang)
	 * @date: 2016年4月28日 下午4:54:26
	 */
	public AdminVo getByUserName(String username);

	/**
	 * 根据userId查询角色名称
	 * @param userId
	 * @return
	 */
	public String getRoleNames(Integer userId);

	/**
	 * 根据用户id获取所拥有的所有的角色的唯一标识
	 * @param userId
	 * @return
	 */
	public String getRoleCodes(Integer userId);
	
/******************************************************以上是远端移植的方法**********************************************************************/
}
