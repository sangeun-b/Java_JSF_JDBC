/*****************************************************************c******************o*******v******id********
 * File: CustomerPojo.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;

/**
 * File: CustomerPojo.java
 * Course materials (20F) CST 8277
 * @author (original) Mike Norman
 * @author Sangeun Baek 040953608
*
* Description: model for the Customer object
*/
@ViewScoped
public class CustomerPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
   

    // these Model fields not used in Assignment 1 (later)
    protected int version;
    protected LocalDateTime created;
    protected LocalDateTime updated;
    protected boolean editable;
    
    /**
     * iseditable - true or false
     * @return current editable
     */

    public boolean isEditable() {
        return editable;
    }
  
    /**
     * save the value of editable
     * @param editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    /**
     * id of the customer
     * @return current id
     */

    public int getId() {
        return id;
    }
    /**
     * save the value of the id 
     * @param id new value for id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * first name of the customer
     * @return the value for firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * save the value of the first name
     * @param firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // TODO additional Model getter's and setter's
    /**
     * Last name of the customer
     * @return the current value of last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * save the value of the last name
     * @param lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * email of the customer
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }
    /**
     * save the value of the email
     * @param email new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * phoneNumber of the customer
     * @return the current value of phonenumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * save the value of the phoneNumber
     * @param phoneNumber new value for phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    // these methods not used in Assignment 1 (later)
    /**
     * version of the customer
     * @return the current value of version
     */
    public int getVersion() {
        return version;
    }
    /**
     * save the value of the phoneNumber
     * @param version new value for version
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * create date of customer
     * @return the current value of created - date
     */
    public LocalDateTime getCreatedDate() {
        return created;
    }
    /**
     * save the value of the create date
     * @param created new value for created
     */
    public void setCreatedDate(LocalDateTime created) {
        this.created = created;
    }
    /**
     * update date of customer
     * @return the current value of updated - date
     */
    public LocalDateTime getUpdatedDate() {
        return updated;
    }
    /**
     * save the value of the update date
     * @param updated new value for updated
     */
    public void setUpdatedDate(LocalDateTime updated) {
        this.updated = updated;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerPojo)) {
            return false;
        }
        CustomerPojo other = (CustomerPojo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Customer [id=")
            .append(id)
            .append(", ");
        if (firstName != null) {
            builder
                .append("firstName=")
                .append(firstName)
                .append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

}