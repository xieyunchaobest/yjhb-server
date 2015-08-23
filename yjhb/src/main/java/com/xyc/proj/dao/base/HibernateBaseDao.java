package com.xyc.proj.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xyc.proj.utility.GenericsUtils;

public class HibernateBaseDao<T> extends HibernateDaoSupport{
		
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public HibernateBaseDao(SessionFactory sessionFactory){
		entityClass = (Class<T>)GenericsUtils.getActualReflectArgumentClass(this.getClass());
		this.setSessionFactory(sessionFactory);
	}
	
	public Serializable save(T entity){
		return (String)this.getHibernateTemplate().save(entity);
	}
	
	public void update(T entity){
		this.getHibernateTemplate().update(entity);
	}
	
	public void saveOrUpdate(T entity){
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void delete(T entity){
		this.getHibernateTemplate().delete(entity);
	}
	
	public T findById(Serializable id){
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}
	
	public List<T> findByHsql(final String hsql){
		List<T> list = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>(){  
            public List<T> doInHibernate(Session session) throws HibernateException {  
                Query query = session.createQuery(hsql);  
                @SuppressWarnings("unchecked")
				List<T> list = query.list();  
                return list;  
            }  
        });  
		return list;
	}
	
	public int executeHsql(final String hsql){
		int cnt = this.getHibernateTemplate().execute(new HibernateCallback<Integer>(){  
            public Integer doInHibernate(Session session) throws HibernateException {  
                Query query = session.createQuery(hsql);  
                return query.executeUpdate();  
            }  
        });
		
		return cnt;
	}
	
	public List<T> getListForPage(final String hql, final int offset, final int length){  
        List<T> list = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>(){  
            public List<T> doInHibernate(Session session) throws HibernateException {  
                Query query = session.createQuery(hql);  
                query.setFirstResult(offset);  
                query.setMaxResults(length);  
                @SuppressWarnings("unchecked")
				List<T> list = query.list();  
                return list;  
            }  
        });  
        return list;  
    }
}
