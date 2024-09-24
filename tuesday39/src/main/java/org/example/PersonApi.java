package org.example;

import io.javalin.Javalin;
import org.example.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonApi {

    // List to store Person objects
    static List<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        // Pre-populate the list with some persons using the constructor
        persons.add(new Person("John"));
        persons.add(new Person("Emily"));
        persons.add(new Person("Michael"));

        // Initialize Javalin app
        Javalin app = Javalin.create().start(7777);

        // Default route
        app.get("/", ctx -> ctx.result("Hello World"));

        // Get all persons
        app.get("/persons", ctx -> ctx.json(persons));

        // Add a new person
        app.post("/persons", ctx -> {
            // Deserialize JSON body into a Person object
            Person newPerson = ctx.bodyAsClass(Person.class);
            persons.add(newPerson);
            ctx.status(201).json(newPerson);
        });

        // Delete a person by name
        app.delete("/persons/{name}", ctx -> {
            String nameToRemove = ctx.pathParam("name");
            persons.removeIf(person -> person.getName().equalsIgnoreCase(nameToRemove));
            ctx.status(204); // No content after deletion
        });

        // Update a person by name
        app.put("/persons/{name}", ctx -> {
            String nameToUpdate = ctx.pathParam("name");
            Person updatedPerson = ctx.bodyAsClass(Person.class);

            // Find and update the person
            persons.removeIf(person -> person.getName().equalsIgnoreCase(nameToUpdate));
            persons.add(updatedPerson);
            ctx.status(204); // No content after update
        });
    }
}

