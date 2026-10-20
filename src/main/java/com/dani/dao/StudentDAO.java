package com.dani.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

import com.dani.Student;
import com.dani.util.HibernateUtil;

public class StudentDAO {

	public void addStudent(Student stu) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
	try {
		tx = session.beginTransaction();
		
		session.save(stu);
		tx.commit();
		System.out.println("student added successfully");

	}catch(Exception e) {
		if(tx != null) {
			tx.rollback();
		}
		System.out.println("Error while adding student");
		e.printStackTrace();
	}finally {
		session.close();
	}
	}
	public Student getStudent(int id) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Student stu = session.get(Student.class, id);
		session.close();
		
		return stu;
	}
	public List<Student> getAllStudents(){
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		List<Student> students = session
				                 .createQuery("from Student", Student.class)
				                 .list();
		session.close();
		return students;
		                           
	}
	public void updateStudent(Student stu) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(stu);
			tx.commit();
			System.out.println("student update successfully");
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			System.out.println("Error when student updating");
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	public void deleteStudent(int id) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Student stu = session.get(Student.class, id);
			if(stu != null) {
				session.delete(stu);
				System.out.println("student deleted successfully");
			}else {
				System.out.println("student not found");
			}
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			System.out.println("Error when student deleting");
			e.printStackTrace();
		}finally {
			session.close();	
		}
	}
	public List<Student>  getStudentsByDepartment (int departmentId){
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		List<Student> students = session.createQuery(
				"from Student where department.id = :deptId",
				Student.class)
				.setParameter("deptId", departmentId)
				.list();
		session.close();
		return students;		
	}
	public List<Student> getStudentsByName(String name){
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		List<Student> students = session.createQuery(
				"from Student where name like :name",
				Student.class)
				.setParameter("name", "%" + name + "%")
				.list();
		session.close();
		return students;
	}
}
