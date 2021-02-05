package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.business.Reminder;
import com.ista.isp.assessment.todo.business.ReminderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ReminderService reminderService;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldGetAllReminders() throws Exception {
		this.mockMvc.perform(get("/api/reminders")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void shouldCreateReminder_validRequest() throws Exception {
		this.mockMvc.perform(
				post("/api/reminders")
						.param("description", "Totally new reminder")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", matchesPattern(".*/\\d+")))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void shouldCreateReminder_missingDescriptionParam() throws Exception {
		this.mockMvc.perform(
				post("/api/reminders")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldDeleteReminder() throws Exception {
		List<Reminder> reminders = reminderService.getReminders();
		this.mockMvc.perform(delete("/api/reminders/{id}", "1"))
				.andExpect(status().isNoContent());
		List<Reminder> reducedReminders = reminderService.getReminders();
		assertTrue("Expected a reminder to be deleted", reducedReminders.size() < reminders.size());
	}

}
