package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
	    Scanner sc =new Scanner(System.in);
	    
	    File list = new File("C:\\projeto-java\\play-with-stream\\ExcerciseStream\\ListEmployee.txt");
	    List<Employee> emp = new ArrayList<>();
	    
	    try(BufferedReader br = new BufferedReader(new FileReader(list))){
	    	String line = br.readLine();
	    	
	    	while(line != null) {
	    		String[] vect = line.split(",");
	    		String name = vect[0];
	    		String email = vect[1];
	    		Double price = Double.parseDouble(vect[2]);
	    		emp.add(new Employee(name, email, price));
	    		line = br.readLine();
	    	}
	    }
	    catch(IOException e ) {
	    	System.out.println("Error: " + e.getMessage());
	    }
	    
	    
	    System.out.print("starting what salary do you want search th emails? ");
	    double n = sc.nextDouble();
	    
	    List<String> emails = emp.stream().filter(x -> x.getSalary() > n).sorted(Comparator.comparing(Employee::getEmail)).map(Employee::getEmail)
	    		.collect(Collectors.toList());
	    Double max = emp.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(1.0, (x, y) -> x + y);
	    emails.forEach(System.out::println);
	    System.out.printf("Sum of salary of people whose name starts with 'M': %.2f ", max);
	    
	    
	    
	    sc.close();
	

	}

}
