package com.ftn.modul3.zavrsni.jwd.Trotineti;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;





@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TrotinetiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrotinetiApplication.class, args);
	}
	
//	 @Bean
//	    public CommandLineRunner loadData(DataSource dataSource) {
//	        return args -> {
//	            Resource resource = new ClassPathResource("data.sql");
//	            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
//	        };
//	    }

}
