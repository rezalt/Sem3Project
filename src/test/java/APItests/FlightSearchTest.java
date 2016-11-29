/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APItests;

import static com.jayway.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rezalt
 */
public class FlightSearchTest
{

    public FlightSearchTest()
    {
    }

//    @BeforeClass
//    public static void setUpClass()
//    {
//    }
//    
//    @AfterClass
//    public static void tearDownClass()
//    {
//    }
//    
//    @Before
//    public void setUp()
//    {
//    }
//    
//    @After
//    public void tearDown()
//    {
//    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void LarsFlightSearch()
    {
        
        assertNotNull(this);
        String url = "http://airline-plaul.rhcloud.com/api/flightinfo/";
        
        
            List<String> destinationList = new ArrayList();
            destinationList.add("SXF");
            destinationList.add("STN");
            destinationList.add("STN");
            destinationList.add("SXF");
        
        given()
                .contentType("application/json")
                .when()
                .get( url )
                .then()
                .body("flights.destination", equalTo(destinationList));
    }
}
