package com.thegutuproject.pizzashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:resources/config/application.properties")
@ImportResource("classpath:resources/applicationContext.xml")
public class PizzashopApplication {

	
	public static void main(String[] args) {

//		if (args.length == 2 && !("").equals(args[0]) && !("").equals(args[1])) {
//
//			String fileInput = args[0];
//			String fileOutput = args[1];
//
//			OrderLog orderLog = orderSorterHelper.createOrderLog(fileInput);
//			orderSorterHelper.sortOrderLog(orderLog);
//			orderSorterHelper.writeOrderLogToFile(orderLog, fileOutput);
//
//		}
		
		
//		OrderEntryDao orderEntryDao = new OrderEntryDao();
		

		/**
		 * @TODO
		 * expand structure for possible web use/db use
		 * stub out resource, service, data provider, etc
		 */
		
		


		SpringApplication.run(PizzashopApplication.class, args);
	}
}
