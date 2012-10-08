package org.lch.attendance.service.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.dao.ResourceDao;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.security.CustomInvocationSecurityMetadataSource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
  
@Service("customInvocationSecurityMetadataSource")  
public class CustomInvocationSecurityMetadataSourceImpl implements CustomInvocationSecurityMetadataSource {  
  
    @javax.annotation.Resource  
    private ResourceDao resourceDao; 
    @javax.annotation.Resource  
    private GenericDao genericDao;
  
    private AntPathRequestMatcher pathMatcher;  
  
    private HashMap<String, Collection<ConfigAttribute>> resourceMap = null;  
  
    /** 
     * 自定义方法，这个类放入到Spring容器后， 
     * 指定init为初始化方法，从数据库中读取资源 
     *  
     */  
    @PostConstruct  
    public void init(){  
          
        this.resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        List<Resource> resources = resourceDao.findAll();
        for (Resource item : resources) {
//        	System.out.println("item"+item);
//        	System.out.println("item.getRoles().getClass()"+item.getRoles().getClass());
//        	genericDao.getSuperHibernateTemplate().initialize(item);
//        	Hibernate.initialize(item.getRoles());
        	Set<Role> roles = item.getRoles();
//        	Hibernate.initialize(roles);
            resourceMap.put(item.getResourceValue(), formatCollection(roles));  
        }  
    }  
  
    @Override  
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
          
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {  
            allAttributes.addAll(entry.getValue());  
        }  
          
        return allAttributes;  
    }  
  
    @Override  
    public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException {  
          
        HttpServletRequest request = ((FilterInvocation) object).getRequest();  
        System.out.println("requestUrl is " + request.getRequestURI());  
          
        if (resourceMap == null) {  
            this.loadAllResourcesAndAuthorities();  
        }  
//        Iterator<String> it = resourceMap.keySet().iterator();  
//        System.out.println("resourceMap.keySet():"+resourceMap.keySet());
//        System.out.println("it:"+it);
//        while (it.hasNext()) {  
//            String resURL = it.next();
//            System.out.println("*******************************************************");
//            System.out.println("resURL"+resURL);
//            System.out.println("*******************************************************");
//            pathMatcher = new AntPathRequestMatcher(resURL);  
//            if (pathMatcher.matches(request)) {  
//                Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);  
//                  
//                return returnCollection;  
//            }  
//        }
        Set<String> keys=resourceMap.keySet();
        for(String url : keys){
        	if(url.trim().length() > 0){
        		pathMatcher = new AntPathRequestMatcher(url);
            	if (pathMatcher.matches(request)) { 
            		Collection<ConfigAttribute> returnCollection = resourceMap.get(url);
            		return returnCollection; 
            	}
        	}else{
        		continue;
        	}
        	
        }
          
        return null;  
    }  
  
    @Override  
    public boolean supports(Class<?> arg0) {  
        // TODO Auto-generated method stub  
        return true;  
    }  
  
      
    /** 
     * 自定义方法，将Set<Role>集合转换为框架需要的Collection<ConfigAttribute>集合 
     *  
     * @param roles 
     * @return 
     */ 
    private Collection<ConfigAttribute> formatCollection(Set<Role> roles) {  
          
        List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();  
        for (Role role : roles) {  
//        	genericDao.getSuperHibernateTemplate().initialize(role);
        	list.add(new SecurityConfig(role.getRoleName()));  
        }
          
        return list;  
    }  
      
    /** 
     * 加载所有资源与权限的关系 
     */ 
    private void loadAllResourcesAndAuthorities() {  
        System.out.println("CustomInvocationSecurityMetadataSourceImpl-loadAllResourcesAndAuthorities:加载所有资源与权限的关系 ");
        if (resourceMap == null) {  
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
        }  
          
        List<Resource> resources = this.resourceDao.findAll();  
        for (Resource resource : resources) {  
            resourceMap.put(resource.getResourceValue(),formatCollection(resource.getRoles()));  
        }  
    }
}  