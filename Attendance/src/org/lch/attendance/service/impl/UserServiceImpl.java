package org.lch.attendance.service.impl;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.ClasDao;
import org.lch.attendance.dao.RoleDao;
import org.lch.attendance.dao.UserDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.util.EncryptUtil;
import org.lch.attendance.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("userService") @Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
	
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private ClasDao classesDao;
	
//	@Override
//	public void doAddRolesToUser(Integer userId, Collection<Role> roles) {
//		User user = userDao.findById(userId);
//		if (user == null) {
//			return;
//		}
//		Set<Role> r = user.getUserRoles();
//		roles.addAll(roles);
//		user.setUserRoles(r);
//		doUpdate(user);
//	}
	@Override
	public void doChangePassword(Integer userId, String newPassword) {
		String hql = "update User set userPwd=:password where userId=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", newPassword);
		params.put("id", userId);
		userDao.bulkUpdate(hql, params);
	}
	@SuppressWarnings("unchecked")
	@Override @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public User doFindByUsername(String username) {
		List<User> result = (List<User>) userDao.findByName(username);
		if (result != null && result.size() > 0)
			return result.get(0);
		return null;
	}
//	@Override 
//	public void doRemoveRolesFromUser(Integer userId, Collection<Role> roles) {
////		User user = doFindById(userId);
//		if (user == null) {
//			return;
//		}
//		Set<Role> temp = new HashSet<Role>();
//		temp.addAll(roles);
//		Set<Role> users = user.getUserRoles();
//		users.removeAll(temp);
//		user.setUserRoles(users);
//		doUpdate(user);
//	}
	@Override @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public User doFindByUserName(String userName) {
		return userDao.findByName(userName);
	}
	@Override
	public void deleteUserByIds(Integer[] ids) {
		if(ids==null||ids.length<1){
			return;
		}
		String delete_hql="delete User u where u.userId in (:ids)";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("ids", ids);
		this.userDao.bulkUpdate(delete_hql, params);
		
	}
	@Override
	public void doUpdateUser(User user) {
		User u =this.userDao.findById(user.getUserId());
		if(u!=null){
			u.setClas(user.getClas());
			u.setDetails(user.getDetails());
			u.setUserBirthday(user.getUserBirthday());
//			String password = EncryptUtil.encodePassword(userVo.getPassword());
			u.setUserEmail(user.getUserEmail());
			u.setUserGender(user.getUserGender());
			u.setUserHobby(user.getUserHobby());
			u.setUserIntro(user.getUserIntro());
			u.setUserName(user.getUserName());
			u.setUserNum(user.getUserNum());
			u.setUserPortrait(user.getUserPortrait());
			u.setUserPwd(user.getUserPwd());
			u.setUserQq(user.getUserQq());
			u.setUserTel(user.getUserTel());
			this.userDao.update(u);
		}
		
	}
	@Override
	public void addRoleToUser(Integer userId, List<Integer> roleIds) {
//		if(userId==null||roleIds==null||roleIds.isEmpty())
//			return;
//		User user=this.userDao.findById(userId);
//		if(user==null)
//			return;
//		Set<Role> roles=user.getUserRoles();
//		if(roles==null){
//			roles=new HashSet<Role>();
//		}
//		for(Integer roleId:roleIds){
//			Role role=roleDao.findById(roleId);
//			roles.add(role);
//		}
//		this.userDao.update(user);
	}
	@Override
	public void delRoleFromUser(Integer userId, List<Integer> roleIds) {
//		if(userId==null||roleIds==null||roleIds.isEmpty())
//			return;
//		User user=this.userDao.findById(userId);
//		if(user==null)
//			return;
//		Set<Role> roles=new HashSet<Role>();
//
//		for(Integer roleId:roleIds){
//			Role role=roleDao.findById(roleId);
//			roles.add(role);
//		}
//		user.getUserRoles().removeAll(roles);
//		this.userDao.update(user);
	}
	/*
	@Override
	public PageResult<Map<String, Object>> queryRoleRelatedUsers(
			QueryCriteria criteria) {String queryEntry = "select new Map(user.id as id,user.username as userName,user.realnameZh as nameZh,user.realnameEn as nameEn,user.telephone as phone,user.fax as fax,user.email as email)";
			String[] whereBodies = new String[] {"role.id = :roleId" };
			String fromJoinSubClause = "from TsUser user";
			if(criteria.getQueryCondition().get("roleId")!=null){
				fromJoinSubClause += " left join user.tsRoles role";
			}
			
			if (!StringUtils.isEmpty(criteria.getOrderField()) && !criteria.getOrderField().startsWith("user.")) {
				criteria.setOrderField("user."+criteria.getOrderField());
			}
			
			Map<String, ?> params = criteria.getQueryCondition();
			String orderField = criteria.getOrderField();
			String orderDirection = criteria.getOrderDirection();
			String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
					whereBodies, orderField, orderDirection, params);
			int pageSize = criteria.getPageSize();
			int startIndex = criteria.getStartIndex();
			PageResult result=(PageResult) this.userDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);
			return result;
	}
	@Override
	public PageResult<Map<String, Object>> doFindUsersForRole(
			QueryCriteria criteria) {
		String queryEntry = "select new Map(user.id as id,user.username as userName,user.realnameZh as nameZh,user.realnameEn as nameEn,user.telephone as phone,user.fax as fax,user.email as email)";
		String[] whereBodies = new String[] { "lower(user.username) like lower(:username)",
				"user.tsDepartment.deptNo like lower(:deptNo)", "lower(user.realnameEn) like lower(:realnameEn)",
				"lower(user.realnameZh) like lower(:realnameZh)", "lower(user.telephone) like lower(:telephone)",
				"lower(user.email) like lower(:email)", "user.id not in (:userIds)" };
		String fromJoinSubClause = "from TsUser user";
		if(criteria.getQueryCondition().get("roleId")!=null){
			String roleId=criteria.getQueryCondition().get("roleId").toString();
			TsSysRole role=this.roleDao.findById(Long.valueOf(roleId));
			Set<TsUser> userSet=role.getTsUsers();
			if(userSet!=null&&!userSet.isEmpty()){
				List<Long> userIds=new ArrayList<Long>(); 
				for(TsUser user:userSet){
					userIds.add(user.getId());
				}
				criteria.getQueryCondition().put("userIds", userIds);
			}
		}
		
		if (!StringUtils.isEmpty(criteria.getOrderField()) && !criteria.getOrderField().startsWith("user.")) {
			criteria.setOrderField("user."+criteria.getOrderField());
		}
		
		Map<String, ?> params = criteria.getQueryCondition();
		String orderField = criteria.getOrderField();
		String orderDirection = criteria.getOrderDirection();
		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
				whereBodies, orderField, orderDirection, params);
		int pageSize = criteria.getPageSize();
		int startIndex = criteria.getStartIndex();
		PageResult result=(PageResult) this.userDao.query(hql,HqlUtils.generateCountHql(hql, null), params, startIndex,pageSize);
		return result;
	}*/
	@Override
	public void doAddRolesToUser(Integer userId, Collection<Role> roles) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doRemoveRolesFromUser(Integer userId, Collection<Role> roles) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public QueryResult<User> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
			
			QueryResult<User> qr=userDao.getScrollData(User.class, firstindex, maxresult, wherejpql, queryParams, orderby);
			return qr;	
	}
	@Override
	public List<Clas> getAllClasses() {
		return classesDao.findAll();
	}
	@Override
	public void doSaveUserByVo(UserVo userVo) {
		// 创建用户
		User user = new User();
		Clas c = null;
		String classId = userVo.getUserClass();
		if(classId!=null)
			c = classesDao.findById(Integer.parseInt(classId));
		if(c!=null)
			user.setClas(c);
		user.setUserName(userVo.getUserName());
		String password = EncryptUtil.encodePassword(userVo.getUserPwd());
		System.out.println("user-password:"+password);
		user.setUserPwd(password);
		user.setUserEnabled(true);
		user.setUserEmail(userVo.getUserEmail());
		String gender = userVo.getUserGender();
		if(gender!=null)
			user.setUserGender(gender.equals("1")? true : false);
		user.setUserHobby(userVo.getUserHobby());
		user.setUserIntro(userVo.getUserIntro());
		user.setUserNum(userVo.getUserNum());
		user.setUserTel(userVo.getUserTel());
		user.setUserQq(userVo.getUserQq());
		user.setUserBirthday(DateUtil.string2Date(userVo.getUserBirthday()));
		
		
		this.doCreate(user);
		
	}
	@Override
	public Clas doFindClass(Integer tmp) {
		return classesDao.findById(tmp);
	}
	@Override
	public List<Role> getRolesByUser(User u) {
		List<Role> roles = userDao.getRolesByUser(u);
		return roles;
	}
//	@Override
//	public User doFindById(Integer id) {
//		User u = super.doFindById(id);
//		Hibernate.initialize(u);
//		return u;
//	}
	
	
//	@Override
//	public User doFindById(Integer id) {
//		User u = super.doFindById(id);
//		Hibernate.initialize(u.getRoles());
//		return u;
//	}
	
//	public PageResult<User> doFindByCriteria(QueryCriteria criteria) {
//		String queryEntry = "select user";
//		String[] whereBodies = new String[] { "lower(user.username) like lower(:username)",
//				"user.tsDepartmentId = :departmentId", "lower(user.realnameEn) like lower(:realnameEn)",
//				"lower(user.realnameZh) like lower(:realnameZh)", "lower(user.telephone) like lower(:telephone)",
//				"lower(user.birthday) like lower(:birthday)", "lower(user.gender) like lower(:gender)",
//				"lower(user.interest) like lower(:interest)", 
//				"lower(user.email) like lower(:email)", "role.id = :roleId" };
//		String fromJoinSubClause = "from TsUser user";
//		if(criteria.getQueryCondition().get("roleId")!=null){
//			fromJoinSubClause += " left join user.tsRoles role";
//		}
//		
//		if (!StringUtils.isEmpty(criteria.getOrderField()) && !criteria.getOrderField().startsWith("user.")) {
//			criteria.setOrderField("user."+criteria.getOrderField());
//		}
//		
//		Map<String, ?> params = criteria.getQueryCondition();
//		
//		String orderField = criteria.getOrderField();
//		String orderDirection = criteria.getOrderDirection();
//		
//		String hql = HqlUtils.generateHql(queryEntry, fromJoinSubClause,
//				whereBodies, orderField, orderDirection, params);
//		
//		int pageSize = criteria.getPageSize();
//		int startIndex = criteria.getStartIndex();
//		
//		PageResult result=(PageResult) userDao.query(hql,
//				HqlUtils.generateCountHql(hql, null), params, startIndex,
//				pageSize);
//		
//		return result;
//	}
	public boolean doImport(String filepath){
		boolean flag = false;
		try{
			Workbook book  =  Workbook.getWorkbook( new  File( filepath  ));
             //  获得第一个工作表对象
            Sheet sheet  =  book.getSheet(0);
            for(int i=0;i<sheet.getRows();i++){
            	
                String userName  =  sheet.getCell(0,i).getContents();
                String userNum = sheet.getCell(1,i).getContents();
                String userPwd = sheet.getCell(2,i).getContents();
                String userEmail = sheet.getCell(3,i).getContents();
                String userTel = sheet.getCell(4,i).getContents();
                
                
                
                User u = new User();
                u.setUserName(userName);
                u.setUserNum(userNum);
                u.setUserPwd(userPwd);
                u.setUserEmail(userEmail);
                u.setUserTel(userTel);
                u.setUserEnabled(true);
                
                
                
                if(sheet.getColumns()>5){
                	String userClas = sheet.getCell(5,i).getContents();
                    String userGender = sheet.getCell(6,i).getContents();
                    String userBirthday = sheet.getCell(7,i).getContents();
                    String userQq = sheet.getCell(8,i).getContents();
                    String userHobby = sheet.getCell(9,i).getContents();
                    String userIntro = sheet.getCell(10,i).getContents();
                    

                    Clas c = null;
                    if(userClas.trim().length()>0 && userClas!=null){
                    	c = classesDao.findById(Integer.parseInt(userClas));
                    	u.setClas(c);
                    }
                    
                    if(userGender.trim().length()>0 && userGender!=null){
                    	u.setUserGender(userGender.equals("男")?true:false);
                    }
                    if(userBirthday.trim().length()>0 && userBirthday!=null){
                    	u.setUserBirthday(DateUtil.string2Date(userBirthday));
                    }
                    if(userQq.trim().length()>0 && userQq!=null){
                    	u.setUserQq(userQq);
                    }
                    if(userHobby.trim().length()>0 && userHobby!=null){
                    	u.setUserHobby(userHobby);
                    }
                    if(userIntro.trim().length()>0 && userIntro!=null){
                    	u.setUserIntro(userIntro);
                    }
                    
                }
                
                userDao.create(u);
                flag = true;
            }
            book.close();
        }   catch  (Exception e)  {
            System.out.println(e);
        }
		return flag;
	}
	@Override
	public List<User> doFindClassmates(Integer clasId) {
		return userDao.findClassmates(clasId);
		
	}
	@Override
	public List<User> doFindTeachers(Integer deptId) {
		return userDao.findTeachers(deptId);
	}
}
