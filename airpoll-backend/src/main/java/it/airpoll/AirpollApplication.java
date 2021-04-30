package it.airpoll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.airpoll.service.importer.AirpollImporterTask;

/**
 * @author Giacomo
 * 
 * Main class with DB importerTask call at each load
 * 
 */


@SpringBootApplication
public class AirpollApplication implements CommandLineRunner {
	
	@Autowired private AirpollImporterTask airpollImporterTask;

	public static void main(String[] args) {
		SpringApplication.run(AirpollApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		airpollImporterTask.execute();
	}

}
