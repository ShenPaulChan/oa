/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.service.impl.AdminServiceImpl
 * @author: shenp
 * @date: 2017年2月10日 下午6:39:21 
 */
package cn.com.bizunited.cp.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bizunited.cp.common.utils.ModelMapperUtils;
import cn.com.bizunited.cp.common.web.pagination.Page;
import cn.com.bizunited.cp.common.web.pagination.Pageable;
import cn.com.bizunited.cp.common.web.vo.AdminVo;
import cn.com.bizunited.cp.oa.domain.base.Admin;
import cn.com.bizunited.cp.oa.mapper.AdminBeanMapper;
import cn.com.bizunited.cp.oa.mapper.base.AdminMapper;
import cn.com.bizunited.cp.oa.service.AdminService;

/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.service.impl.AdminServiceImpl
 * @author: shenp
 * @date: 2017年2月10日 下午6:39:21 
 *
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
	
	@Autowired
	private AdminBeanMapper adminBeanMapper;

	@Autowired
	public void setBaseMapper(AdminMapper adminMapper) {
		super.setBaseMapper(adminMapper);
	}
	
	/**
	 * @Title: searchDeptUsers 
	 * @param deptId
	 * @param key
	 * @return
	 * @author: Paul Chan
	 * @date: 2017年3月19日 上午11:36:26
	 * @see cn.com.bizunited.cp.oa.service.AdminService#searchDeptUsers(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<Admin> searchDeptUsers(Integer deptId, String key) {
		return adminBeanMapper.searchDeptUsers(deptId, key);
	}
	
	/**
	 * @Title: getMemberUser 
	 * @param memberId
	 * @return
	 * @author: Paul Chan
	 * @date: 2017年3月20日 下午1:37:27
	 * @see cn.com.bizunited.cp.oa.service.AdminService#getMemberUser(java.lang.Integer)
	 */
	@Override
	public Admin getMemberUser(Integer memberId) {
		return adminBeanMapper.findMemberUser(memberId);
	}

	@Override
	public Page<AdminVo> findVisitUserPage(Pageable pageable) {
		Page<AdminVo> page = new Page<AdminVo>(pageable);
		List<AdminVo> rows = this.adminBeanMapper.findVisitUserPage(page);
		page.setRows(rows);
		return page;
	}

	@Override
	public Page<Admin> getUserColleaguePage(Pageable pageable) {
		Page<Admin> page = new Page<>(pageable);
		List<Admin> admins = adminBeanMapper.getUserColleaguePage(page);
		page.setRows(admins);
		return page;
	}


/******************************************************以下是远端移植的方法**********************************************************************/
	@Override
	public AdminVo getByUserName(String username) {
		return ModelMapperUtils.toParse(adminBeanMapper.getByUserName(username),AdminVo.class);
	}

	@Override
	public String getRoleNames(Integer userId) {
		return adminBeanMapper.getRoleNames(userId);
	}

	@Override
	public String getRoleCodes(Integer userId) {

		return adminBeanMapper.getRoleCodes(userId);
	}

/******************************************************以上是远端移植的方法**********************************************************************/
}
