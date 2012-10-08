package org.lch.attendance.service.security.impl;

import javax.annotation.Resource;

import org.lch.attendance.dao.UserDao;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.security.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
  
@Service("customUserDetailsService")  
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {  
      
    @Resource  
    private UserDao userDao;  
  
    @Override  
    public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException {  
          
        User user = userDao.findByName(userName);  
          
        if (user == null) {  
            throw new UsernameNotFoundException("用户名" + userName + "不存在");  
        }  
  
        // 因为User已经实现了UserDetails接口，所以直接返回user即可  
        return user;  
    }  
}  