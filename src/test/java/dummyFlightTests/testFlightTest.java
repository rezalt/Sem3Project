/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dummyFlightTests;

import static com.jayway.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olls
 */
public class testFlightTest
{
    
    public testFlightTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testFlight() 
    {
        List<String> destinationList = new ArrayList();
        destinationList.add("SXF");
        destinationList.add("STN");
        destinationList.add("STN");
        destinationList.add("SXF");
        
        given()
                .contentType("application/json")
                .when()
                .get("http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-01T00:00:00.000Z/1")
                .then()
                .statusCode(200)
                .body("flights.destination",equalTo(destinationList));
        
    }
}
