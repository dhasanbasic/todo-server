package com.ista.isp.assessment.todo.business;

import com.ista.isp.assessment.todo.persistence.ReminderEntity;
import com.ista.isp.assessment.todo.persistence.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Reminder createReminder(Reminder reminder) {
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setDescription(reminder.getDescription());
        reminderEntity.setDone(Boolean.FALSE);

        return mapToDomain(reminderRepository.save(reminderEntity));
    }

    public Reminder updateReminder(Reminder reminder, Long id) {
        Optional<ReminderEntity> searchedReminder = reminderRepository.findById(id);
        if (searchedReminder.isEmpty()) {
            return null;
        }

        ReminderEntity reminderFromDb = searchedReminder.get();
        reminderFromDb.setDescription(reminder.getDescription());
        reminderFromDb.setDone(reminder.isDone());

        return mapToDomain(reminderRepository.save(reminderFromDb));
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
