package io.delivery.service.impl;

import io.delivery.config.AppConfig;
import io.delivery.config.application.WebConfig;
import io.delivery.entity.Customer;
import io.delivery.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, CustomerServiceImpl.class})
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;
    private Customer customer_1;
    private Customer customer_2;
    private Customer customer_3;

    @Before
    public void CreateAndInitializeCustomers(){
        customer_1 = new Customer();
        customer_1.setName("заказчик_1");
        customer_1.setPhoneNumber("+375(11)123-45-67");
        customer_1.setAddress("адрес_заказчика_1");
        customer_1.seteMail("customer_1@mail.mail");

        customer_2 = new Customer();
        customer_2.setName("заказчик_2");
        customer_2.setPhoneNumber("+375(22)123-45-67");
        customer_2.setAddress("адрес_заказчика_2");
        customer_2.seteMail("customer_2@mail.mail");

        customer_3 = new Customer();
        customer_3.setName("заказчик_3");
        customer_3.setPhoneNumber("+375(33)123-45-67");
        customer_3.setAddress("адрес_заказчика_3");
        customer_3.seteMail("customer_3@mail.mail");

        customerService.createCustomer(customer_1);
        customerService.createCustomer(customer_2);
        customerService.createCustomer(customer_3);
    }

    @Test
    public void testGetCustomerByName(){
        List<Customer> returnedCustomers = customerService.getCustomerByName(customer_2.getName());
        assertNotNull("List of customers is null",returnedCustomers);
        Customer firstCustomerInList = returnedCustomers.get(0);
        assertEquals("id's not equal", firstCustomerInList.getId(), customer_2.getId());
        assertEquals("names not equal", firstCustomerInList.getName(), customer_2.getName());
        assertEquals("e-mails not equal", firstCustomerInList.geteMail(), customer_2.geteMail());
        assertEquals("addresses not equal", firstCustomerInList.getAddress(), customer_2.getAddress());
    }

    @Test
    public void testGetCustomerByAddress(){
        List<Customer> returnedCustomers = customerService.getCustomerByAddress(customer_3.getAddress());
        assertNotNull("List of customers is null",returnedCustomers);
        Customer firstCustomerInList = returnedCustomers.get(0);
        assertEquals("id's not equal", firstCustomerInList.getId(), customer_3.getId());
        assertEquals("names not equal", firstCustomerInList.getName(), customer_3.getName());
        assertEquals("e-mails not equal", firstCustomerInList.geteMail(), customer_3.geteMail());
        assertEquals("addresses not equal", firstCustomerInList.getAddress(), customer_3.getAddress());
    }

    @Test
    // Должен свалиться если в базе номера телефонов не уникальны
    public void testGetCustomerByPhoneNumber(){
        Customer returnedCustomer = customerService.getCustomerByPhoneNumber(customer_1.getPhoneNumber());
        assertNotNull("returnedCustomer is null", returnedCustomer);
        assertEquals("IDs not equal", returnedCustomer.getId(), customer_1.getId());
        assertEquals("names not equal", returnedCustomer.getName(), customer_1.getName());
        assertEquals("e-mails not equal", returnedCustomer.geteMail(), customer_1.geteMail());
        assertEquals("addresses not equal", returnedCustomer.getAddress(), customer_1.getAddress());
    }

    @Test
    // Должен свалиться если в базе e-mail не уникальны
    public void testGetCustomerByEmail(){
        Customer returnedCustomer = customerService.getCustomerByEmail(customer_2.geteMail());
        assertNotNull("returnedCustomer is null", returnedCustomer);
        assertEquals("IDs not equal", returnedCustomer.getId(), customer_2.getId());
        assertEquals("names not equal", returnedCustomer.getName(), customer_2.getName());
        assertEquals("e-mails not equal", returnedCustomer.geteMail(), customer_2.geteMail());
        assertEquals("addresses not equal", returnedCustomer.getAddress(), customer_2.getAddress());
    }

    @Test
    public void testGetCustomerById(){
        Customer returnedCustomer = customerService.getCustomerById(customer_3.getId());
        assertNotNull("returnedCustomer is null", returnedCustomer);
        assertEquals("IDs not equal", returnedCustomer.getId(), customer_3.getId());
        assertEquals("names not equal", returnedCustomer.getName(), customer_3.getName());
        assertEquals("e-mails not equal", returnedCustomer.geteMail(), customer_3.geteMail());
        assertEquals("addresses not equal", returnedCustomer.getAddress(), customer_3.getAddress());
    }

    @Test
    public void testGetAllCustomers(){
        List<Customer> returnedCustomers = customerService.getAllCustomers();
        assertNotNull("List of customers is null",returnedCustomers);
        Customer lastCustomerInList = returnedCustomers.get(returnedCustomers.size()-1);
        assertNotNull(lastCustomerInList);
        assertEquals("IDs not equal", lastCustomerInList.getId(), customer_3.getId());
        assertEquals("names not equal", lastCustomerInList.getName(), customer_3.getName());
        assertEquals("e-mails not equal", lastCustomerInList.geteMail(), customer_3.geteMail());
        assertEquals("addresses not equal", lastCustomerInList.getAddress(), customer_3.getAddress());
    }

    @Test
    public void testUpdateCustomer(){
        Customer beforeUpdateCustomer = customer_1;
        beforeUpdateCustomer.setName("обновлённый_заказчик");
        beforeUpdateCustomer.setAddress("новый_адрес_обновлённого_заказчика");
        beforeUpdateCustomer.seteMail("updated_customer@mail.mail");
        Customer afterUpdateCustomer = customerService.updateCustomer(beforeUpdateCustomer);
        assertNotNull(afterUpdateCustomer);
        assertEquals("IDs not equal", beforeUpdateCustomer.getId(), afterUpdateCustomer.getId());
        assertEquals("names not equal", beforeUpdateCustomer.getName(), afterUpdateCustomer.getName());
        assertEquals("e-mails not equal", beforeUpdateCustomer.geteMail(), afterUpdateCustomer.geteMail());
        assertEquals("addresses not equal", beforeUpdateCustomer.getAddress(), afterUpdateCustomer.getAddress());

    }

    @After
    public void deleteCreatedCustomers(){
        customerService.deleteCustomer(customer_1.getId());
        customerService.deleteCustomer(customer_2.getId());
        customerService.deleteCustomer(customer_3.getId());
    }

}
