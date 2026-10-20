package com.dani;


import com.dani.dao.DepartmentDAO;
import com.dani.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class App {
	
	private static void addDepartment() {
		System.out.println("Enter Department Name: ");
        String departmentName = sc.nextLine();

        Department department = new Department(departmentName);
        departmentDAO.addDepartment(department);
	}
	private static void viewDepartment() {
		System.out.println("\n------ DEPARTMENTS ------");

        for (Department d : departmentDAO.getAllDepartments()) {
            System.out.println(d.getId() + " | " + d.getName());
        }
	}
	private static void addStudent() {
		 System.out.println("Enter Name: ");
		    String name = sc.nextLine();

		    System.out.println("Enter Age: ");

		    int age;

		    try {
		        age = sc.nextInt();
		        sc.nextLine();

		    } catch (Exception e) {
		        System.out.println("Please enter a valid number");
		        sc.nextLine();
		        return;
		    }

		    System.out.println("Enter Gender: ");
		    String gender = sc.nextLine();

		    System.out.println("Enter Email: ");
		    String email = sc.nextLine();
		    
		    System.out.println("Enter Marks: ");
		    int marks  = sc.nextInt();
		    sc.nextLine();

		    System.out.println("Enter Department ID: ");
		    int deptId = sc.nextInt();
		    sc.nextLine();

		    Department studentDepartment =
		            departmentDAO.getDepartment(deptId);

		    if (studentDepartment != null) {

		        Student stu = new Student(
		                0,
		                name,
		                age,
		                gender,
		                email,
		                marks,
		                studentDepartment
		        );

		        service.addStudent(stu);

		    } else {

		        System.out.println("Department not found");
		    }
	}
	private static void viewStudent() {
		System.out.println("Enter student Id: ");
		int searchId = sc.nextInt();
			Student student = service.getStudent(searchId);
			
			if(student != null) {
				
				System.out.println("Id: " + student.getId());
				System.out.println("Name: " + student.getName());
				System.out.println("Age: " + student.getAge());
				System.out.println("Gender: " + student.getGender());
				System.out.println("Email: " + student.getEmail());
				System.out.println("Marks: " + student.getMarks());
				String grade = service.calculateGrade(student.getMarks());
				String placementStatus = service.getPlacementStatus(student.getMarks());
				String placementCategory = service.getPlacementCategory(student.getMarks());
				System.out.println("Grade: " + grade);
				System.out.println("Placement Status: " + placementStatus);
				System.out.println("Placement Category: " + placementCategory);
				System.out.println("Department: " + student.getDepartment());

			} else {
				System.out.println("student not found");
			}
	}
	private static void viewAllStudent() {
		for(Student s : service.getAllStudents()) {
			String grade = service.calculateGrade(s.getMarks());
			String PlacementStatus = service.getPlacementStatus(s.getMarks());
			String PlacementCategory = service.getPlacementCategory(s.getMarks());
			System.out.println(
			s.getId() + " | " +
			s.getName() + " | " +
			s.getAge() + " | " +
			s.getGender()+ " | " +
			s.getEmail() + " | " +
			s.getMarks() + " | " +
			grade + " | " +
			PlacementStatus + " | " +
			PlacementCategory + " | " +
			s.getDepartment());
		}
	}
	private static void updateStudent() {
		System.out.println("Enter Student Id: ");
		int updateId = sc.nextInt();
		sc.nextLine();
		
		Student updateStudent = service.getStudent(updateId);
		 if(updateStudent != null) {
			 System.out.print("Enter New Name: ");
			 updateStudent.setName(sc.nextLine());
			 
			 System.out.print("Enter New Age: ");
			 updateStudent.setAge(sc.nextInt());
			 sc.nextLine();
			 
			 System.out.print("Enter New Gender: ");
			 updateStudent.setGender(sc.nextLine());
			 
			 System.out.print("Enter New Email: ");
			 updateStudent.setEmail(sc.nextLine());
			 
			 System.out.println("Enter New Marks: ");
			 updateStudent.setMarks(sc.nextInt());
			 sc.nextLine();
			 
			 System.out.print("Enter New Department ID: ");
			 int newDeptId = sc.nextInt();
			 sc.nextLine();
			 
			 Department newDepartment = departmentDAO.getDepartment(newDeptId);
			 
			 if(newDepartment != null) {
				 updateStudent.setDepartment(newDepartment);
				 service.updateStudent(updateStudent);
			 }else {
				 System.out.println("Department not found");
			 }
			 }else {
				 System.out.println("Student not found");
			 }
	}
	private static void deleteStudent() {
		System.out.println("Enter Student Id: ");
		int deleteId = sc.nextInt();
		sc.nextLine();
		
		service.deleteStudent(deleteId);
	}
	private static void searchStudentByDepartment() {
		System.out.println("Enter Department Id: ");
		int departmentId = sc.nextInt();
		sc.nextLine();
		
		List<Student> students = service.getStudentsByDepartment(departmentId);
		if(students.isEmpty()) {
			System.out.println("student not found");
		}else {
			for(Student s : students) {
				String grade = service.calculateGrade(s.getMarks());
				String PlacementStatus = service.getPlacementStatus(s.getMarks());
				String PlacementCategory = service.getPlacementCategory(s.getMarks());
				System.out.println(
						s.getId() + "|" +
						s.getName() + "|" +
				        s.getAge() + "|" +
						s.getGender() + "|" +
				        s.getEmail() + "|" +
						s.getMarks() + " | " +
				        grade + " | " +
						PlacementStatus + " | " +
				        PlacementCategory + " | " +
						s.getDepartment()
						);
			}
		}
	}
	private static void searchStudentByName() {
		System.out.println("Enter Student Name: ");
		String searchname = sc.nextLine();
		
		List<Student> searchResult = service.getStudentsByName(searchname);
		if(searchResult.isEmpty()) {
			System.out.println("Student not found");
		}else {
			for(Student s : searchResult) {
				String grade = service.calculateGrade(s.getMarks());
				String PlacementStatus = service.getPlacementStatus(s.getMarks());
				String PlacementCategory = service.getPlacementCategory(s.getMarks());
				System.out.println(
					s.getId() + " | " +
				    s.getName() + " | " +
					s.getAge() + " | " +
				    s.getGender() + " | " +
					s.getEmail() + " | " +
				    s.getMarks() + " | " +
					grade + " | " +
					PlacementStatus + " | " +
			        PlacementCategory + " | " +
				    s.getDepartment()
						);
			}
		}
	}
	
	private static void placementReport() {
		System.out.println();
		System.out.println("----Placement Report----");
		for(Student s : service.getAllStudents()) {
			String grade = service.calculateGrade(s.getMarks());
			String PlacementStatus = service.getPlacementStatus(s.getMarks());
			String PlacementCategory = service.getPlacementCategory(s.getMarks());
			
			System.out.println(
					s.getId() + " | " +
			        s.getName() + " | " +
					s.getMarks()	 + " | " +
			        grade + " | " +
					PlacementStatus + " | " +
			        PlacementCategory 
					);
		}
	}
	
	private static void placementCategoryReport() {

	    System.out.println();
	    System.out.println("----- PLACEMENT CATEGORY REPORT -----");

	    System.out.println("1. Foundation Placement");
	    System.out.println("2. Standard Placement");
	    System.out.println("3. Advanced Placement");
	    System.out.println("4. Premier Placement");
	    System.out.println("5. Not Eligible");

	    System.out.print("Enter your choice: ");
	    int choice = sc.nextInt();
	    sc.nextLine();

	    String requiredCategory;

	    switch (choice) {

	        case 1:
	            requiredCategory = "Foundation Placement";
	            break;

	        case 2:
	            requiredCategory = "Standard Placement";
	            break;

	        case 3:
	            requiredCategory = "Advanced Placement";
	            break;

	        case 4:
	            requiredCategory = "Premier Placement";
	            break;

	        case 5:
	            requiredCategory = "No Placement";
	            break;

	        default:
	            System.out.println("Invalid choice");
	            return;
	    }

	    System.out.println();
	    System.out.println("Students: " + requiredCategory);
	    System.out.println("");

	    for (Student s : service.getAllStudents()) {

	        String category =
	                service.getPlacementCategory(s.getMarks());

	        if (category.equals(requiredCategory)) {

	            System.out.println(
	                    s.getId() + " | " +
	                    s.getName() + " | " +
	                    s.getMarks() + " | " +
	                    service.calculateGrade(s.getMarks()) + " | " +
	                    service.getPlacementStatus(s.getMarks()) + " | " +
	                    category + " | " +
	                    s.getDepartment()
	            );
	        }
	    }

	    System.out.println("");
	}
	private static void exit() {
		System.out.println("Thank You!");
	}
	
	static Scanner sc = new Scanner(System.in);
	static StudentService service = new StudentService();
	static DepartmentDAO departmentDAO = new DepartmentDAO();
	
    
	public static void main( String[] args )
    {
    	
    	while(true) {
    		
    		System.out.println("\n-------STUDENT MANAGEMENT SYSTEM-------");
    		System.out.println("1. Add Department");
    		System.out.println("2. View Departments");
    		System.out.println("3. Add Student");
    		System.out.println("4. View Student");
    		System.out.println("5. View All Students");
    		System.out.println("6. Update Student");
    		System.out.println("7. Delete Student");
    		System.out.println("8. Search Student by Department");
    		System.out.println("9. Search Student By Name");
    		System.out.println("10. Placement Report");
    		System.out.println("11. Placement Category Report");
    		System.out.println("12. Exit");
    		
    		System.out.println("Enter your choice: ");
    		int choice = sc.nextInt();
    		sc.nextLine();
    		
    		switch(choice) {
    		
    		case 1:
                 addDepartment();
    	        break;
    	        
    		case 2:
    	        viewDepartment();

    	        break;

    		case 3:
    			
    			addStudent();
    			break;
    			
    			case 4:
    				viewStudent();
      				break;
      				
    			case 5:
    		         viewAllStudent();
    		         break;
    		
    			case 6:
    				updateStudent();
    					 break;
    		
    			case 7:
    				deleteStudent();
    				break;
    		
    			case 8:
    				searchStudentByDepartment();
       				break;
    				
    			case 9:
    				searchStudentByName();
    				break;
    				
    			case 10:
    				placementReport();
    				break;
    				
    			case 11:
    				placementCategoryReport();
    				break;
    				
    			case 12:
    				exit();
    				sc.close();
    				return;
    				default:
    					System.out.println("Invalid choice");
    		}	
    		
    	}
    	}
}
