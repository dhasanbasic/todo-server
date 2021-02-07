package com.ista.isp.assessment.todo;

import com.ista.isp.assessment.todo.business.Reminder;
import com.ista.isp.assessment.todo.business.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(createdReminder.getId());
        return ResponseEntity.created(uri).body(createdReminder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Long id, @RequestBody Reminder reminder) {
        Reminder updatedReminder = reminderService.updateReminder(reminder, id);
        if (updatedReminder == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reminder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reminder> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}
