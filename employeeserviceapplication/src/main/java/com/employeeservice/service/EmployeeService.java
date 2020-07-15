package com.employeeservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.employeeservice.exception.EmployeeNotFoundException;
import com.employeeservice.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * Method to process user specific requests
     * 
     * @param phone
     * @param name
     * @return
     * @throws IOException
     */
    public Employee execute(String name, Integer phone) throws IOException {

        List<Employee> employee = getEmployeeData();
        LOGGER.info("Filtering data bases on the user input");
        Employee matchingObject = employee.stream().filter(emp -> emp.getName().equals(name) && emp.getPhoneNumber().equals(phone)).findFirst().orElse(null);
        if (matchingObject == null) {

            throw new EmployeeNotFoundException("Failed to get records for the given input");
        }
        return matchingObject;

    }

    /**
     * Method to get employee data
     * 
     * @param phone
     * @param name
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private List<Employee> getEmployeeData() throws IOException {
        LOGGER.info("Reading all the employee data from JSON file");
        byte[] mapData = Files.readAllBytes(Paths.get("src/main/resources/EmployeeData.json"));
        List<Employee> employeeList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        employeeList = objectMapper.readValue(mapData, new TypeReference<List<Employee>>() {
        });

        return employeeList;

    }

}
