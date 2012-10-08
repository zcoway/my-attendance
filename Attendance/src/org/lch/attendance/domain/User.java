package org.lch.attendance.domain;

// Generated 2012-5-2 16:56:18 by Hibernate Tools 3.2.0.CR1

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.lch.attendance.model.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User generated by hbm2java
 */
@Entity
public class User extends BaseEntity implements UserDetails,java.io.Serializable {

	private Integer userId;

	private Clas clas;
	
	private String clasName;
	
	private Department dept;
	
	private String userName;

	private String userNum;

	private String userPwd;

	private Boolean userGender;
	
	private String genderText;
	
	private Date userBirthday;
	
	private String birthdayText;
	
	private String userEmail;

	private String userQq;

	private String userPortrait;

	private String userTel;

	private String userHobby;

	private String userIntro;

	private boolean userEnabled;

	private Set<Role> roles = new HashSet<Role>(0);

	private Set<Week> weeks = new HashSet<Week>(0);

	private Set<Detail> details = new HashSet<Detail>(0);

	public User() {
	}

	public User(String userName, String userNum, String userPwd,
			String userEmail, String userTel, boolean userEnabled) {
		this.userName = userName;
		this.userNum = userNum;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.userTel = userTel;
		this.userEnabled = userEnabled;
	}

	public User(Clas clas, String userName, String userNum, String userPwd,
			Boolean userGender, Date userBirthday, String userEmail,
			String userQq, String userPortrait, String userTel,
			String userHobby, String userIntro, boolean userEnabled,
			Set<Role> roles, Set<Week> weeks, Set<Detail> details, Department dept) {
		this.clas = clas;
		this.userName = userName;
		this.userNum = userNum;
		this.userPwd = userPwd;
		this.userGender = userGender;
		this.userBirthday = userBirthday;
		this.userEmail = userEmail;
		this.userQq = userQq;
		this.userPortrait = userPortrait;
		this.userTel = userTel;
		this.userHobby = userHobby;
		this.userIntro = userIntro;
		this.userEnabled = userEnabled;
		this.roles = roles;
		this.weeks = weeks;
		this.details = details;
		this.dept = dept;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Clas getClas() {
		return this.clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Boolean getUserGender() {
		return this.userGender;
	}

	public void setUserGender(Boolean userGender) {
		this.userGender = userGender;
	}

	public Date getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserQq() {
		return this.userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getUserPortrait() {
		return this.userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserHobby() {
		return this.userHobby;
	}

	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}

	public String getUserIntro() {
		return this.userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public boolean isUserEnabled() {
		return this.userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Week> getWeeks() {
		return this.weeks;
	}

	public void setWeeks(Set<Week> weeks) {
		this.weeks = weeks;
	}

	public Set<Detail> getDetails() {
		return this.details;
	}

	public void setDetails(Set<Detail> details) {
		this.details = details;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();  
		for(Role r : roles){
			list.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
        return list;
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Integer getId() {
		return this.userId;
	}

	@Override
	public void setId(Integer id) {
		this.userId = id;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getClasName() {
		if(clas!=null)
			return clas.getClasName();
		else
			return clasName;
	}

	public void setClasName(String clasName) {
		this.clasName = clasName;
	}

	public String getGenderText() {
		if(userGender!=null){
			if(userGender)
				return "男";
			else
				return "女";
		}
		return genderText;
	}

	public void setGenderText(String genderText) {
		this.genderText = genderText;
	}
	
	private String abc;

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public String getBirthdayText() {
		return birthdayText;
	}

	public void setBirthdayText(String birthdayText) {
		this.birthdayText = birthdayText;
	}
}
