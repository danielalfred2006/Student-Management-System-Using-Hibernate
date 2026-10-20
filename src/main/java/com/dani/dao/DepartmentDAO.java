package com.dani.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dani.Department;
import com.dani.util.HibernateUtil;

public class DepartmentDAO {

	public void addDepartment(Department department) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(department);
			tx.commit();
			System.out.println("Department added successfully");
		}catch(Exception e){
			if(tx != null) {
				tx.rollback();
			}
			System.out.println("Error while adding department");
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	public Department getDepartment(int id) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		Department department = session.get(Department.class, id);
		session.close();
		return department;
	}
	public List<Department> getAllDepartments(){
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		List<Department> department = session
				                        .createQuery("from Department", Department.class)
				                        .list();
		session.close();
		return department;
	}
	public boolean departmentExists(String name) {

	    Session session = HibernateUtil.getSessionFactory().openSession();

	    try {
	        Long count = session.createQuery(
	                "SELECT COUNT(d) FROM Department d WHERE LOWER(d.name) = LOWER(:name)",
	                Long.class)
	                .setParameter("name", name.trim())
	                .uniqueResult();

	        return count > 0;

	    } finally {
	        session.close();
	    }
	}
}
