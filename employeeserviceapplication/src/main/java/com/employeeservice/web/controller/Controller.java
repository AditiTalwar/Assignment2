
package com.employeeservice.web.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeservice.constants.Constants;
import com.employeeservice.exception.EmployeeNotFoundException;
import com.employeeservice.model.Employee;
import com.employeeservice.service.EmployeeService;
import com.employeeservice.web.response.RestResponse;

@RestController
@RequestMapping("/")
public class Controller {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method to get employee data
     * 
     * @return List of users - List
     */

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/employeeData/{name}/{phone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<Employee>> employeeData(@PathVariable String name, @PathVariable Integer phone) {
        LOGGER.info("Request to get employee data based on and phone number");
        ResponseEntity<RestResponse<Employee>> responseEntity = null;
        RestResponse<Employee> restResponse = null;

        try {
            Employee employeeList = employeeService.execute(name, phone);
            restResponse = new RestResponse<>(RestResponse.Status.SUCCESS, Constants.SERVICE_DESCRIPTION, null, (employeeList));
            responseEntity = new ResponseEntity<>(restResponse, HttpStatus.OK);
            LOGGER.info("Successfully fetched [{}] employee data ");
        }
        catch (EmployeeNotFoundException exception) {
            restResponse = new RestResponse<>(RestResponse.Status.FAILURE, null, exception.getMessage(), null);
            responseEntity = new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
            LOGGER.info("Requested employee not found ", exception);
        }
        catch (Exception exception) {
            restResponse = new RestResponse<>(RestResponse.Status.FAILURE, null, "Internal server error occurred : " + exception.getMessage(), null);
            responseEntity = new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.info("Failed to fetch employee data", exception);
        }
        return responseEntity;
    }
}