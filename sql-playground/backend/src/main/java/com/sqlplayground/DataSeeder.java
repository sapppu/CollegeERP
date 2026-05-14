package com.sqlplayground;

import com.sqlplayground.storage.InMemoryDatabase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationRunner {
    private final InMemoryDatabase db;
    public DataSeeder(InMemoryDatabase db) { this.db = db; }

    @Override
    public void run(ApplicationArguments args) {
        db.seedSampleData();
    }
}
