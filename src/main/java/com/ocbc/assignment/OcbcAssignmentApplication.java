package com.ocbc.assignment;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class OcbcAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcbcAssignmentApplication.class, args);
	}
	
	@Bean
	public PromptProvider myPromptProvider() {
		return () -> new AttributedString("shell:>",AttributedStyle.DEFAULT.foreground(AttributedStyle.WHITE));
	}

}
