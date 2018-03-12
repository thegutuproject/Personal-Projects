package com.thegutuproject.pizzashop.resource;

import com.thegutuproject.pizzashop.domain.OrderLog;
import com.thegutuproject.pizzashop.service.OrderLogService;
import io.swagger.annotations.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value="Order Log", description="API to get Order Log details")
@RestController
public class OrderLogResource {
	
	@Autowired
	private DozerBeanMapper dbBeanMapper;
	
	@Autowired
	ConversionService conversionService;
	
	@Autowired
	OrderLogService orderLogService;
	
	@RequestMapping(value="/orderlog/{orderLogId}", method= RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get Order Logs by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 700, message = "Business rule failure")
	})
	public OrderLog getOrderLogById(@PathVariable("orderLogId") @ApiParam("The Id of the order log to be retrieved")  final int orderLogId) {
		// Domain object returned by backend.
		// This still needs to be null checked.
		// Ideas: static generic exception handler
		OrderLog orderLog = orderLogService.getOrderLogById(orderLogId);
		return orderLog;
	}
}
