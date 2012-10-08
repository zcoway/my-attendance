package org.lch.attendance.common;


import org.lch.attendance.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUserHolder {
	
	/**
	 * Returns the current user
	 * 
	 * @return
	 */
	public static User getCurrentUser() {
		//取得登录用户      
		User user = null;
        SecurityContext   ctx   =   SecurityContextHolder.getContext();              
        Authentication   auth   =   ctx.getAuthentication();                    
        System.out.println(auth);
        if(auth.getPrincipal()   instanceof   UserDetails){      
            user = (User)auth.getPrincipal();          
        }              
        return user;     
	}

}
