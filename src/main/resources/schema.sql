
CREATE TABLE reminders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  done BOOLEAN DEFAULT FALSE
);