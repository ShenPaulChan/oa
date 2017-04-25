package cn.com.bizunited.cp.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.vo.AdminVo;
import cn.com.bizunited.cp.oa.domain.AdminDto;
import cn.com.bizunited.cp.oa.domain.base.Admin;

public interface AdminBeanMapper {

	/**
	 * @Title: searchDeptUsers 
	 * @param deptId
	 * @param key
	 * @author: Paul Chan
	 * @date: 2017年3月19日 上午11:38:51
	 */
	public List<Admin> searchDeptUsers(@Param("deptId")Integer deptId, @Param("key")String key);

	/**
	 * @Title: findMemberUser 
	 * @param memberId
	 * @return
	 * @author: Paul Chan
	 * @date: 2017年3月20日 下午1:48:16
	 */
	public Admin findMemberUser(@Param("memberId")Integer memberId);

	public List<AdminVo> findVisitUserPage(Page<AdminVo> page);
	


	/**
	 * 根据用户名获取用户信息
	 * @Title: getByUserName
	 * @param username
	 * @return
	 * @author: Omar(OmarZhang)
	 * @date: 2016年4月28日 下午4:54:26
	 */
	public AdminDto getByUserName(@Param("username")String username);

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

}