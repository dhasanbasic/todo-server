package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.persistence.ReminderEntity;
import com.ista.isp.assessment.todo.persistence.ReminderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.util.AssertionErrors.assertEquals;
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
	private ReminderRepository reminderRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldGetAllReminders() throws Exception {
		this.mockMvc.perform(get("/api/reminders")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void shouldCreateReminder() throws Exception {
		this.mockMvc.perform(
				post("/api/reminders")
						.content("{\"description\": \"Call dentist\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", matchesPattern(".*/\\d+")))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void shouldUpdateReminder() throws Exception {
		this.mockMvc.perform(
				put("/api/reminders/{id}", "1")
						.content("{\"description\": \"Updated reminder\", \"done\": true}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		Optional<ReminderEntity> searchedReminder = reminderRepository.findById(1L);
		assertTrue("Expected reminder status to be updated", searchedReminder.isPresent());

		ReminderEntity updatedReminder = searchedReminder.get();
		assertEquals("Expected reminder description to be updated", "Updated reminder",
				updatedReminder.getDescription());
		assertTrue("Expected reminder status to be updated", updatedReminder.getDone());
	}

	@Test
	public void shouldDeleteReminder() throws Exception {
		List<ReminderEntity> reminders = reminderRepository.findAll();
		this.mockMvc.perform(delete("/api/reminders/{id}", "1"))
				.andExpect(status().isNoContent());
		List<ReminderEntity> reducedReminders = reminderRepository.findAll();
		assertTrue("Expected a reminder to be deleted", reducedReminders.size() < reminders.size());
	}

}
