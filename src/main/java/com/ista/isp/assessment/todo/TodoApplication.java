package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.business.ReminderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	CommandLineRunner preloadReminders(ReminderService reminderService) {
		return arguments -> {
			reminderService.createReminder("Take garbage out");
			reminderService.createReminder("Change tires");
			reminderService.createReminder("Bezos' birthday");
		};
	}
}
