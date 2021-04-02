/*****************************************************************c******************o*******v******id********
 * File: CustomerController.java
 * Course materials (20F) CST 8277
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.annotation.*;

import com.algonquincollege.cst8277.customers.dao.CustomerDao;
import com.algonquincollege.cst8277.customers.model.CustomerPojo;

/**
 * File: CustomerController.java
 * Course materials (20F) CST 8277
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 * 
 * Description: Responsible for collection of Customer Pojo's in XHTML (list) <h:dataTable> </br>
 * Delegates all C-R-U-D behaviour to DAO
 */

@Named
@SessionScoped
public class CustomerController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    @SessionMap
    protected Map<String, Object> sessionMap;

    @Inject
    protected CustomerDao customerDao;

    protected List<CustomerPojo> customers;

    //necessary methods to make controller work
    /**
     * load all customers with all information
     */
    public void loadCustomers() {
        setCustomers(customerDao.readAllCustomers());
    }
    /**
     * retrive customer list
     * @return list of all customers
     */
    public List<CustomerPojo> getCustomers() {
        return customers;
    }
    /**
     * save the value of customers list
     * @param customers
     */
    public void setCustomers(List<CustomerPojo> customers) {
        this.customers = customers;
    }
    /**
     * connect to the add page
     * @return add-customer from
     */
    public String navigateToAddForm() {
        sessionMap.put("newCustomer", new CustomerPojo());
        return "add-customer?faces-redirect=true";
    }
    /**
     * save the creating customer
     * @param newCustomer wwho will be created
     * @return retrive changed list-customers form 
     */
    public String submitCustomer(CustomerPojo newCustomer) {
        customerDao.createCustomer(newCustomer);
        return "list-customers?faces-redirect=true";
    }
    /**
     * connect to update page
     * @param customer who will be updated
     * @return update-customer form
     */
    public String navigateToUpdateForm(CustomerPojo customer) {
        sessionMap.put("updateCustomer", customer);
        return"update-customer?faces-redirect=true";
    }
    /**
     * update existing customer
     * @param updateCustomer : will be updated
     * @return retrive changed list-customers form 
     */
    public String submitUpdatedCustomer(CustomerPojo updateCustomer) {
        customerDao.updateCustomer(updateCustomer);
        return"list-customers?faces-redirect=true";
    }
    /**
     * delete existing customer
     * @param customerId of customer want to delete
     * @return retrive changed list-customers form 
     */
    public String deleteCustomer(int customerId) {
        customerDao.deleteCustomerById(customerId);
        return"list-customers?faces-redirect=true";
       
    }

}