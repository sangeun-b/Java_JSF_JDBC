/*****************************************************************c******************o*******v******id********
 * File: CustomerDao.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers.dao;

import java.util.List;

import com.algonquincollege.cst8277.customers.model.CustomerPojo;

/**
 * File: CustomerDao.java
 * Course materials (20F) CST 8277
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 * 
 * Description: API for the database C-R-U-D operations
 */
public interface CustomerDao {

    // C
    /**
     * created new customer
     * @param customer - will be created
     * @return created customer
     */
    public CustomerPojo createCustomer(CustomerPojo customer);
    // R
    /**
     * select customer using id
     * @param customerId - will be selected
     * @return selected customer
     */
    public CustomerPojo readCustomerById(int customerId);
    /**
     * read all customer in the database
     * @return list of customers
     */
    public List<CustomerPojo> readAllCustomers();
    // U
    /**
     * update existing cusotmer information
     * @param customer - will be updated
     */
    public void updateCustomer(CustomerPojo customer);
    // D
    /**
     * delte existing customer
     * @param customerId - will be deleted
     */
    public void deleteCustomerById(int customerId);

}