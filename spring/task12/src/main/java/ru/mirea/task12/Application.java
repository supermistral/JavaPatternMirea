package ru.mirea.task12;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private BufferedReader reader;

	public Application() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String line;
		while (true) {
			System.out.print("> ");
			line = reader.readLine().trim();

			if (line.isEmpty()) {
				break;
			}
		}
	}
}
