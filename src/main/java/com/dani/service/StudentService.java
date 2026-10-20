package com.dani.service;

import java.util.List;
import com.dani.Student;
import com.dani.dao.StudentDAO;

public class StudentService {

	private StudentDAO  dao= new StudentDAO();
	
	public void addStudent (Student stu) {
		
		if(stu.getName() ==null || stu.getName().trim().isEmpty()) {
			System.out.println("Name cannot be empty");
			return;
		}
		if(stu.getAge() <=16) {
			System.out.println("Age must be greater than 16");
			return;
		}
		if(stu.getMarks() < 0 || stu.getMarks() > 100) {
			System.out.println("Marks Must be Between 0 and 100");
			return;
		}
		if(stu.getEmail() ==null || !stu.getEmail().contains("@")) {
			System.out.println("Invalid Email");
			return;
		}
		if(stu.getDepartment() ==null) {
			System.out.println("Department cannot be Empty");
			return;
		}
		dao.addStudent(stu);
	}
	public Student getStudent(int id) {
		return dao.getStudent(id);
	}
	public List<Student> getAllStudents(){
		return dao.getAllStudents();
	}
	public void updateStudent(Student stu) {
		
		if (stu.getName() == null || stu.getName().trim().isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }
		if(stu.getAge() <=16) {
			System.out.println("Age must be greater than 16");
			return;
		}
		if(stu.getMarks() < 0 || stu.getMarks() > 100) {
			System.out.println("Marks Must be Between 0 and 100");
			return;
		}
		if(stu.getDepartment() ==null) {
			System.out.println("Department cannot be empty");
			return;
		}
		dao.updateStudent(stu);
	}
	public void deleteStudent(int id) {
		dao.deleteStudent(id);
	}
	public List<Student> getStudentsByDepartment(int dapartmentId){
		return dao.getStudentsByDepartment(dapartmentId);
	}
	public List<Student> getStudentsByName(String name){
		return dao.getStudentsByName(name);
	}
	public String calculateGrade(int marks) {
		if (marks < 35) {
			return "Fail";
		}else if (marks <= 50) {
			return "D";
		}else if(marks <= 70) {
			return "C";
		}else if(marks <= 90) {
			return "B";
		}else if(marks <=100) {
			return "A";
		}else {
			return "Invalid";
		}
	}
	public String getPlacementStatus(int marks) {
		if(marks < 35) {
			return "Not Eligible";
		}else {
			return "Eligible";
		}
	}
	
	public String getPlacementCategory(int marks) {
		if(marks < 35) {
			return "No Placement";
		}else if(marks <= 50) {
			return "Foundation Placement";
		}else if(marks <= 70) {
			return "Standard Placement";
		}else if(marks <= 90) {
			return "Advanced Placement";
		}else if(marks <= 100) {
			return "Premier Placement";
		}else {
			return "Invalid Marks";
		}
	}
}
