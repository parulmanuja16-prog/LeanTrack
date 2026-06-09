# com.airtribe.learntrack.entity

This package holds the domain model classes for LearnTrack.

## Contents

- `Person.java` — base model for people in the system.
- `Student.java` — student-specific model extending `Person`.
- `Trainer.java` — trainer-specific model extending `Person`.
- `Course.java` — course model describing offerings and duration.
- `Enrollment.java` — links students with courses and captures enrollment status.

## Purpose

Use this package to define the core data structures that represent application state. These classes are typically manipulated by the service layer and displayed by the UI.
