/*****************************************************************c******************o*******v******id********
 * File: CustomerDaoImpl.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers.dao;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.algonquincollege.cst8277.customers.model.CustomerPojo;

/**
 *File: CustomerDaoImpl.java
 * Course materials (20F) CST 8277
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
 * 
* Description: Implements the C-R-U-D API for the database
*/

public class CustomerDaoImpl implements CustomerDao, Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;

    private static final String CUSTOMER_DS_JNDI =
        "java:app/jdbc/customers";
    private static final String READ_ALL =
        "select * from customer";
    private static final String READ_CUSTOMER_BY_ID =
        /* TODO */ "select * from customer where id = ?";
    private static final String INSERT_CUSTOMER =
        /* TODO */ "INSERT INTO CUSTOMER (FNAME, LNAME, EMAIL, PHONENUMBER) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER_ALL_FIELDS =
        /* TODO */ "UPDATE CUSTOMER SET FNAME=?, LNAME=?, EMAIL=?, PHONENUMBER=? WHERE ID=?";//VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_8804523C_92BD_456D_8780_D38EF5D93C9F";

    private static final String DELETE_CUSTOMER_BY_ID =
           "DELETE FROM CUSTOMER WHERE ID=?";// VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_8804523C_92BD_456D_8780_D38EF5D93C9F";

   
    
    
    
    @Inject
    protected ExternalContext externalContext;
    private void logMsg(String msg) {
        ((ServletContext)externalContext.getContext()).log(msg);
    }

    @Resource(lookup = CUSTOMER_DS_JNDI)
    protected DataSource customerDS;

    protected Connection conn;
    protected PreparedStatement readAllPstmt;
    protected PreparedStatement readByIdPstmt;
    protected PreparedStatement createPstmt;
    protected PreparedStatement updatePstmt;
    protected PreparedStatement deleteByIdPstmt;

    @PostConstruct
    /**
     * build connection and each statement
     */
    protected void buildConnectionAndStatements() {
        try {
            logMsg("building connection and stmts");
            conn = customerDS.getConnection();
          
            /* TODO - build other stmts */
        }
        catch (Exception e) {
            logMsg("something went wrong getting connection from database: " + e.getLocalizedMessage());
        }
        if(conn!=null) {
            try {
                readAllPstmt = conn.prepareStatement(READ_ALL);
            }
            catch (Exception e) {
                logMsg("something went wrong getting preparing readAllPstmt: " + e.getLocalizedMessage());
                
            }
            try {
                createPstmt = conn.prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            }
            catch (Exception e) {
                logMsg("something went wrong getting preparing createPstmt: " + e.getLocalizedMessage());
                
            }
            try {
                readByIdPstmt = conn.prepareStatement(READ_CUSTOMER_BY_ID);
            }
            catch (Exception e) {
                logMsg("something went wrong getting preparing readByIdPstmt: " + e.getLocalizedMessage());
                
            }
            try {
                updatePstmt = conn.prepareStatement(UPDATE_CUSTOMER_ALL_FIELDS);
            }
            catch (Exception e) {
                logMsg("something went wrong getting preparing updatePstmt: " + e.getLocalizedMessage());
                
            }
            try {
                deleteByIdPstmt = conn.prepareStatement(DELETE_CUSTOMER_BY_ID);
            }
            catch (Exception e) {
                logMsg("something went wrong getting preparing deleteByIdPstmt: " + e.getLocalizedMessage());
                
            }
            
            
        }
    }

    @PreDestroy
    /**
     * close connection and statements
     */
    protected void closeConnectionAndStatements() {
        try {
            logMsg("closing stmts and connection");
            readAllPstmt.close();
            createPstmt.close();
            readByIdPstmt.close();
            updatePstmt.close();
            deleteByIdPstmt.close();
            /* TODO - close other stmts */
            conn.close();
        }
        catch (Exception e) {
            logMsg("something went wrong closing stmts or connection: " + e.getLocalizedMessage());
        }
       
    }

    @Override
    /**
     * retrives all customer's data from database
     */
    public List<CustomerPojo> readAllCustomers() {
        logMsg("reading all customers");
        List<CustomerPojo> employees = new ArrayList<>();
        try {
            ResultSet rs = readAllPstmt.executeQuery();
            while (rs.next()) {
                CustomerPojo newEmployee = new CustomerPojo();
                newEmployee.setId(rs.getInt("id"));
                newEmployee.setFirstName(rs.getString("fname"));
                newEmployee.setLastName(rs.getString("lname"));
                newEmployee.setEmail(rs.getString("email"));
                newEmployee.setPhoneNumber(rs.getString("phoneNumber"));
                //TODO additional Model fields
                employees.add(newEmployee);
            }
            try {
                rs.close();
            }
            catch (Exception e) {
                logMsg("something went wrong closing resultSet: " + e.getLocalizedMessage());
            }
        }
        catch (SQLException e) {
            logMsg("something went wrong accessing database: " + e.getLocalizedMessage());
        }
        return employees;
    }

    @Override
    /**
     * create new customer into the database
     */
    public CustomerPojo createCustomer(CustomerPojo customer) {
        logMsg("creating an customer");
        //CustomerPojo c = customer;
        try {
            createPstmt.setString(1, customer.getFirstName());
            createPstmt.setString(2, customer.getLastName());
            createPstmt.setString(3, customer.getEmail());
            createPstmt.setString(4, customer.getPhoneNumber());
           
           int numRowsCreated = createPstmt.executeUpdate();
           
           if(numRowsCreated <1) {
               throw new Exception("something went wrong creating customer");
           }
           else {
               ResultSet generatedKeysResultSet = createPstmt.getGeneratedKeys();
               if(generatedKeysResultSet.next()) {      
                  // int generatedKey = rs.getInt(1);
                       customer.setId(generatedKeysResultSet.getInt(1));
                   try {
                       generatedKeysResultSet.close();
                   }
                   catch (Exception e) {
                       logMsg("something went wrong closing generatedKeysResultSet: " + e.getLocalizedMessage());
                   }
               }
            }
        }
        catch(Exception e) {
            logMsg("something went wrong accessing database: " + e.getLocalizedMessage());
        }
        
        /* TODO */
        return customer;
        
    }
  
    @Override
    /**
     * choose customer using id
     */
    public CustomerPojo readCustomerById(int customerId) {
        logMsg("read a specific customer");
        CustomerPojo foundCustomer = null;
        try {
            readByIdPstmt .setInt(1, customerId);
            foundCustomer = (CustomerPojo) readByIdPstmt.executeQuery();
          
        }catch(SQLException e) {
            logMsg("something went wrong reading from database (" + e.getSQLState() + ", " + e.getLocalizedMessage() + ")");
        }
        /* TODO */
        return foundCustomer;
    }

    @Override
    /**
     * udpate customer's information
     */
    public void updateCustomer(CustomerPojo customer) {
        logMsg("updating a specific customer");
      
        try {
            updatePstmt.setString(1, customer.getFirstName());
            updatePstmt.setString(2, customer.getLastName());
            updatePstmt.setString(3, customer.getEmail());
            updatePstmt.setString(4, customer.getPhoneNumber());  
            updatePstmt.setInt(5, customer.getId());
          
            
            int numRows = updatePstmt.executeUpdate();
            if(numRows<1) {
                logMsg("something went wrong updating from database (numRows < 1)");
            }
            
        }catch(SQLException e) {
            logMsg("something went wrong accessing database: " + e.getSQLState() + ", " + e.getLocalizedMessage() + ")");
            
        }
    }
        /* TODO */
    

    @Override
    /**
     * delete customer from database
     */
    public void deleteCustomerById(int customerId) {
        logMsg("deleting a specific customer");
        try {
            deleteByIdPstmt.setInt(1, customerId);
            int numRows = deleteByIdPstmt.executeUpdate();
            if (numRows < 1) {
                logMsg("something went wrong deleting from database (numRows < 1)");
            }
        }
        catch (SQLException e) {
            logMsg("something went wrong deleting from database (" + e.getSQLState() + ", " + e.getLocalizedMessage() + ")");
        }
    }

}