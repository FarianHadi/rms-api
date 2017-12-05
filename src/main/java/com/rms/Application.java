package com.rms;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan(basePackageClasses = EmployeeController.class)
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*public void run(String... args) throws Exception {

		personRepo.deleteAll();

		// save a couple of customers
		personRepo.save(new Person("Alice", "Smith"));
		personRepo.save(new Person("Bob", "Smith"));

		// fetch all Persons
		System.out.println("Persons found with findAll():");
		System.out.println("-------------------------------");
		for (Person person : personRepo.findAll()) {
			System.out.println(person);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Person found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(personRepo.findByFirstName("Alice"));

		System.out.println("Persons found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Person person : personRepo.findByLastName("Smith")) {
			System.out.println(person);
		}

	}*/
	
}

