/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jakob
 */
public class FlightServiceTest
{
    
    public FlightServiceTest()
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


    /**
     * Test of reserveFlight method, of class FlightService.
     */
    @Test
    public void testReserveFlight() throws Exception
    {
        System.out.println("reserveFlight");
        String jsonString = "{  \n" +
"  \"flightID\":\"3257-1486089000000\",\n" +
"  \"numberOfSeats\":2,\n" +
"  \"reserveeName\":\"Jan Hansen\",\n" +
"  \"reservePhone\":\"12345678\",\n" +
"  \"reserveeEmail\":\"jan@hansen.dk\",\n" +
"  \"passengers\":[  \n" +
"    {  \n" +
"      \"firstName\":\"Jan\",\n" +
"      \"lastName\":\"Hansen\"\n" +
"    },\n" +
"    {  \n" +
"      \"firstName\":\"Jane\",\n" +
"      \"lastName\":\"Hansen\"\n" +
"    }\n" +
"  ]\n" +
"}";
        FlightService instance = new FlightService();
        String expResult = "";
        String result = instance.reserveFlight(jsonString);
        
        JSONObject json2 = new JSONObject(jsonString);
        JSONObject json = new JSONObject(result);
        System.out.println(json2.getString("flightID"));
        System.out.println(json.getString("flightNumber"));
        assertEquals(json.getString("flightNumber"), "COL3257");
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
