package org.example;


import io.javalin.Javalin;


import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        list.add("Denmark");
        list.add("england");
        list.add("holland");
        list.add("Marokko");

        Javalin app = Javalin.create().start(7777);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/countries", ctx -> ctx.json(list, String.class));
        app.post("/countries", ctx -> {
            list.add(ctx.body());
            ctx.status(201);

        });
        app.delete("/countries/{country}", ctx -> {
            list.remove(ctx.pathParam("country"));
            ctx.status(204);
        });

        app.put("/countries/{country}", ctx -> {
            list.remove(ctx.pathParam("country"));
            list.add(ctx.body());
            ctx.status(204);
        });
    }
}