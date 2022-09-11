package com.ocbc.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.shell.Shell;

import com.ocbc.assignment.constants.AppConstants;



@SpringBootTest(properties = { "spring.shell.interactive.enabled=false" })
class OcbcAssignmentApplicationTests {

    @Autowired
    private Shell shell;

    @Autowired
    ResourceLoader resourceLoader;
    

	String LINE_SEPARATOR= System.getProperty("line.separator");
	
	@Test
    public void testConstants() {
    	AppConstants appConstants = new AppConstants();
    	assertNotNull(appConstants);
    	
    }
	
    @Test
	public void testLogin() throws Exception {
    	
        File dataFile = resourceLoader.getResource("classpath:testInput.txt").getFile();

       
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {

			String line;
			Object result = null;
			String output = "";
			
			while ((line = br.readLine()) != null) {
				if(line.contains(">")) {
					final String input= line.substring(2);
					 result = shell.evaluate(()->input);
				}else if(line.length()!=0){
					if(output.length()>0)
					output+=LINE_SEPARATOR+line;
					else
						output+=line;
					
				}else {
					System.out.println("Actual Result: "+result);
					System.out.println("Expected Result "+output);
					assertEquals((String)result, output);
					result= null;
					output="";
					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        
     
    }

}
