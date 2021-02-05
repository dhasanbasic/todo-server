package com.ista.isp.assessment.todo.business;

import com.ista.isp.assessment.todo.persistence.ReminderEntity;
import com.ista.isp.assessment.todo.persistence.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;

    ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public List<Reminder> getReminders() {
        return reminderRepository.findAll()
                .stream().map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    public Reminder createReminder(String description) {
        ReminderEntity reminder = new ReminderEntity();
        reminder.setDescription(description);
        reminder.setDone(Boolean.FALSE);

        reminderRepository.save(reminder);
        return mapToDomain(reminder);
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    private Reminder mapToDomain(ReminderEntity reminderEntity) {
        Reminder reminder = new Reminder();
        reminder.setId(reminderEntity.getId());
        reminder.setDescription(reminderEntity.getDescription());

        if (reminderEntity.getDone() != null) {
            reminder.setDone(reminderEntity.getDone());
        }

        return reminder;
    }
}
