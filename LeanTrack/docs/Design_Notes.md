# Design Notes

## Why ArrayList instead of array

`ArrayList` is used when the number of elements can change during runtime. It provides easy methods for adding, removing, and iterating over items without manual resizing or index management. Using `ArrayList` makes the code more flexible and readable for collections such as students, courses, and enrollments.

## Where static members were used and why

Static members were used for shared utilities and globally accessible services.

- `InputUtil`: utility methods for reading input and printing lines are static because they do not rely on instance state and should be available from anywhere in the UI.
- `Main`: static service instances and the `main` method are used to initialize and run the console application without requiring an object instance.

Using static members in these places simplifies access and avoids unnecessary object creation for common, stateless operations.

## Where inheritance was used and what was gained from it

Inheritance is used in the entity model to represent common behavior between `Student` and `Trainer` through a shared base class `Person`.

- `Person` defines shared fields like first name, last name, and email.
- `Student` and `Trainer` inherit from `Person` and reuse the common properties.

This reduces duplicate code, enforces a consistent structure for people-related entities, and makes it easier to extend the model in the future with additional person types.
