package io.delivery.dao;

import io.delivery.entity.Customer;

import java.util.List;

public interface CustomerDao extends BasicDao<Customer>{
    /**
     * Find customers by name in database
     * @param name - customer's name
     * @return - list of customers
     */
    List<Customer> findCustomerByName(String name);

    /**
     * Find customers by address in database
     * @param address - customer's address
     * @return - list of customers, if more then one customer registered at same address
     */
    List<Customer> findCustomerByAddress(String address);

    /**
     * Find customer by phone number
     * @param phoneNumber - customer's phone number
     * @return - current customer
     */
    Customer findCustomerByPhoneNumber(String phoneNumber);

    /**
     * Find customer by e-mail
     * @param eMail - customer's e-mail
     * @return - current customer
     */
    Customer findCustomerByEmail(String eMail);

}
