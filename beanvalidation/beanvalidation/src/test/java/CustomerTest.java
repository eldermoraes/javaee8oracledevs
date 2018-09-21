/*
 * Copyright 2018 eldermoraes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.eldermoraes.beanvalidation.Customer;
import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author eldermoraes
 */
public class CustomerTest {
    
    private static Validator VALIDATOR;
    
    public CustomerTest() {
    }
    
    @Before
    public void setUp() {
        VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @Test
    public void validCustomer(){
        Customer c = new Customer(1, "Customer", "customer@test.com", Arrays.asList(new String[] {"address1", "address2"}));
        Set<ConstraintViolation<Customer>> cons = VALIDATOR.validate(c);
        Assert.assertTrue(cons.isEmpty());
    }
    
    @Test
    public void negativeId(){
        Customer c = new Customer(-11, "Customer", "customer@test.com", Arrays.asList(new String[] {"address1", "address2"}));
        Set<ConstraintViolation<Customer>> cons = VALIDATOR.validate(c);
        Assert.assertTrue(cons.size() == 1);
    }

    @Test
    public void blankName(){
        Customer c = new Customer(1, " ", "customer@test.com", Arrays.asList(new String[] {"address1", "address2"}));
        Set<ConstraintViolation<Customer>> cons = VALIDATOR.validate(c);
        Assert.assertTrue(cons.size() == 1);
    }
    
    @Test
    public void longName(){
        Customer c = new Customer(1, "NameWithMoreThan45CharactersThatsReallyABigNameDontYouThink", "customer@test.com", Arrays.asList(new String[] {"address1", "address2"}));
        Set<ConstraintViolation<Customer>> cons = VALIDATOR.validate(c);
        Assert.assertTrue(cons.size() == 1);
    }    
    
    @Test
    public void invalidEmail(){
        Customer c = new Customer(1, "Customer", "customer&test.com", Arrays.asList(new String[] {"address1", "address2"}));
        Set<ConstraintViolation<Customer>> cons = VALIDATOR.validate(c);
        Assert.assertTrue(cons.size() == 1);
    }  
    
    @Test
    public void emptyAddress(){
        Customer c = new Customer(1, "Customer", "customer@test.com", Arrays.asList(new String[] {}));
        Set<ConstraintViolation<Customer>> cons = VALIDATOR.validate(c);
        Assert.assertTrue(cons.size() == 1);
    }    
}
