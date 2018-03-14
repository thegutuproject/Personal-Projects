package com.thegutuproject.pizzashop;

import com.thegutuproject.pizzashop.domain.OrderLog;
import com.thegutuproject.pizzashop.helper.OrderSorterHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:resources/config/application.properties")
@ImportResource("classpath:resources/applicationContext.xml")
public class PizzaShopApplication {
	
	public static void main(String[] args) {
		
		if (args.length == 2 && !("").equals(args[0]) && !("").equals(args[1])) {

			OrderSorterHelper orderSorterHelper = new OrderSorterHelper();

			String fileInput = args[0];
			String fileOutput = args[1];

			System.setProperty("fileInput", fileInput);
			System.setProperty("fileOutput", fileOutput);

			OrderLog orderLog = orderSorterHelper.createOrderLog(fileInput);
			orderSorterHelper.sortOrderLog(orderLog);
			orderSorterHelper.writeOrderLogToFile(orderLog, fileOutput);

		} else {
			
			System.out.println("Either the input, output, or both file locations are missing. Please try again.");
			
		}

		/**
		 * In order to onable the rest of this application,
		 * such as the REST API and DB usage, please uncomment
		 * this line
		 */
//		SpringApplication.run(PizzaShopApplication.class, args);

	}
}
