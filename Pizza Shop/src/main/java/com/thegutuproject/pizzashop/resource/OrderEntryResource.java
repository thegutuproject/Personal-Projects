package com.thegutuproject.pizzashop.resource;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.service.OrderEntryService;
import io.swagger.annotations.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Api(value="Order Entry", description="API to get details on order entries submitted to the system.")
@RestController
public class OrderEntryResource {
	
	@Autowired
	private DozerBeanMapper dbBeanMapper;
	
	@Autowired
	ConversionService conversionService;
	
	@Autowired
	OrderEntryService orderEntryService;
	
	@RequestMapping(value="/orderentry/id/{orderEntryId}", method= RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Order Entries by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
	})
	public OrderEntry getOrderEntryById(
			@PathVariable("orderEntryId") @ApiParam("order entry id")  final int orderEntryId) {
		/**
		 * Domain object returned by backend.
		 * There is some rudimentary error checking done at the DAO level,
		 * but it needs to be expanded upon
		 */
		
		OrderEntry orderEntry = orderEntryService.getOrderEntryById(orderEntryId);
		return orderEntry;
	}
	
	@RequestMapping(value="/orderentry/food/{foodItem}", method= RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Order Entries based on food")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
	})
	public List<OrderEntry> getOrderEntriesByFood(
			@PathVariable("foodItem") @ApiParam("type of food")  final String foodItem) {
		/**
		 * Domain object returned by backend.
		 * There is some rudimentary error checking done at the DAO level,
		 * but it needs to be expanded upon
		 */
		
		List<OrderEntry> orderEntryList = orderEntryService.getOrderEntriesByFood(foodItem);
		return orderEntryList;
	}
	
	@RequestMapping(value="/orderentry/time/{orderTime}", method= RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Order Entries based on time submitted")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
	})
	public List<OrderEntry> getOrderEntriesByTime(
			@PathVariable("orderTime") @ApiParam("time and date of order")  final Date orderTime) {
		/**
		 * Domain object returned by backend.
		 * There is some rudimentary error checking done at the DAO level,
		 * but it needs to be expanded upon
		 */
		
		List<OrderEntry> orderEntryList = orderEntryService.getOrderEntriesByTime(orderTime);
		return orderEntryList;
	}
	
	@RequestMapping(value="/orderentry/orderlog/{orderLogId}", method= RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get Order Entries based on log id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
	})
	public List<OrderEntry> getOrderEntriesByOrderLogId(
			@PathVariable("orderLogId") @ApiParam("order log id")  final Integer orderLogId) {
		/**
		 * Domain object returned by backend.
		 * There is some rudimentary error checking done at the DAO level,
		 * but it needs to be expanded upon
		 */
		
		List<OrderEntry> orderEntryList = orderEntryService.getOrderEntriesByOrderLogId(orderLogId);
		return orderEntryList;
	}
	
	@RequestMapping(value="/orderentry/insert", method= RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Insert Order Entries")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
	})
	public Response insertOrderEntry(
			@RequestParam("foodItem") @ApiParam("food item") final String foodItem,
			@RequestParam("orderTime") @ApiParam("order time (epoch)") final String orderTime,
			@RequestParam("orderLogId") @ApiParam("order log id") final Integer orderLogId) {
		/**
		 * Please Note: This method has NOT been "fool proofed,"
		 * meaning has very minimal exception handling/data verification.
		 */
		
		Integer dbResponse = orderEntryService.insertOrderEntry(foodItem, orderTime, orderLogId);
		if (dbResponse == 1) {
			return Response.ok().build();
		} else {
			return Response.serverError().build();
		}
	}
}
