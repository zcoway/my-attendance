package org.lch.attendance.common;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public class GenderType implements UserType {
    /** ����HibernateҪʹ��ʲôSQL����������DDL */ 
   public int[] sqlTypes() { 
       return new int[]{Hibernate.SHORT.sqlType()}; 
   }
   
   /** ����Hibernate���UserType����ӳ����������͡�������Gender�� */ 
   @SuppressWarnings("unchecked") 
   public Class returnedClass() { 
       return Gender.class; 
   }
   
   /** ����hibernate��������ǲ��ɱ�ġ���΢С�������Ż�    */ 
   public boolean isMutable() { 
       return false; 
   }
   
   /**��������Hibernate�������ɵĿ��գ�����Gender�ǲ��ɱ�ģ�ֱ�ӷ��ؾͺ��ˡ�*/ 
   public Object deepCopy(Object arg0) throws HibernateException {
       return arg0;
   }
   
   /** hibernate��������ݷ����������ʱҪ���õķ���  */ 
   public Serializable disassemble(Object arg0) throws HibernateException {
       return (Serializable)arg0;  
   }
   
   /** �Ӷ���������ȡ�����������ʱҪ���õķ��� */ 
   public Object assemble(Serializable arg0, Object arg1) 
           throws HibernateException { 
       return arg0;  
   }
   
   /** �����ѹܶ���״̬�ĺϲ���*/ 
   public Object replace(Object original, Object target, Object owner) 
           throws HibernateException { 
       return original; 
   }
   
   public boolean equals(Object x, Object y) throws HibernateException { 
       return x == y; 
   }
    public int hashCode(Object o) throws HibernateException { 
       return o.hashCode(); 
   }
   
   /** ��JDBC��ResultSet��ȡ����ֵ������������ڴ����ݿ��ѯ����ʱ�õ��� */ 
   public Object nullSafeGet(ResultSet rs, String[] names, Object owner) 
           throws HibernateException, SQLException { 
	   int value = rs.getInt(names[0]); 
	   return Gender.getGender(value);
   }
    /** �����Ե�ֵ���õ�PreparedStatement�� */ 
   public void nullSafeSet(PreparedStatement ps, Object value, int index) 
           throws HibernateException, SQLException { 
       if (value == null) { 
           ps.setInt(index, Hibernate.SHORT.sqlType());
       } else { 
           ps.setInt(index, ((Gender) value).getValue());
       }
   }
}

