package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.business.Reminder;
import com.ista.isp.assessment.todo.business.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping
    public List<Reminder> getReminders() {
        return reminderService.getReminders();
    }

    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestParam String description) {
        Reminder reminder = reminderService.createReminder(description);
        if (reminder == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(reminder.getId());
            return ResponseEntity.created(uri).body(reminder);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reminder> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}
