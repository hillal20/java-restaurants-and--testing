package com.lambdaschool.restaurants.controller;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.restaurants.model.Menu;
import com.lambdaschool.restaurants.model.Payment;
import com.lambdaschool.restaurants.model.Restaurant;
import com.lambdaschool.restaurants.model.RestaurantPayments;
import com.lambdaschool.restaurants.service.RestaurantService;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class, secure = false)
public class RestaurantControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private RestaurantService mockRestaurantService;


    private List<Restaurant> restaurantList;

    @Before
    public void  setUp(){
      restaurantList = new ArrayList<>();

        Payment payType1 = new Payment("Credit card");
        payType1.setPaymentid(1);
        Payment payType2 = new Payment("Cash");
        payType1.setPaymentid(2);
        Payment payType3 = new Payment("Mobile pay");
        payType1.setPaymentid(3);

        ArrayList<RestaurantPayments> allPay = new ArrayList<>();
        allPay.add( new RestaurantPayments(new Restaurant(), payType1));
        allPay.add( new RestaurantPayments(new Restaurant(), payType2));
        allPay.add( new RestaurantPayments(new Restaurant(), payType3));

        ArrayList<RestaurantPayments> cashPay = new ArrayList<>();
        cashPay.add(new RestaurantPayments(new Restaurant(), payType2));

        ArrayList<RestaurantPayments> noCashPay = new ArrayList<>();
        noCashPay.add(new RestaurantPayments(new Restaurant(), payType3));
        noCashPay.add(new RestaurantPayments(new Restaurant(), payType1));

        String rest1Name = "Apple";
        Restaurant r1 = new Restaurant(rest1Name,"123 main st", "city", "state", "9999999", allPay);
        r1.setRestaurantid(11);
        r1.getMenus().add(new Menu("Mack && cheese",6.96, r1 ));
        r1.getMenus().get(0).setMenuId(20);
        r1.getMenus().add(new Menu("Lasagna",6.0, r1 ));
        r1.getMenus().get(1).setMenuId(21);
        r1.getMenus().add(new Menu("MeatLoaf",6.90, r1 ));
        r1.getMenus().get(2).setMenuId(22);
        r1.getMenus().add(new Menu("Tacos",7, r1 ));
        r1.getMenus().get(3).setMenuId(23);
        r1.getMenus().add(new Menu("Chef salad",9, r1 ));
        r1.getMenus().get(4).setMenuId(23);

        restaurantList.add(r1);

        String rest2Name = "Peach";
        Restaurant r2 = new Restaurant(rest2Name,"200 main st", "city", "state", "000000", cashPay);
        r2.setRestaurantid(12);
        r2.getMenus().add(new Menu("pancake",6.96, r2 ));
        r2.getMenus().get(0).setMenuId(30);
        r2.getMenus().add(new Menu("spaghetti",6.0, r2 ));
        r2.getMenus().get(1).setMenuId(31);
        r2.getMenus().add(new Menu("chickenLoaf",6.90, r2 ));
        r2.getMenus().get(2).setMenuId(32);
        r2.getMenus().add(new Menu("mexican",7, r2 ));
        r2.getMenus().get(3).setMenuId(33);
        r2.getMenus().add(new Menu("guest salad",9, r2 ));
        r2.getMenus().get(4).setMenuId(34);

        restaurantList.add(r2);

        String rest3Name = "Banana";
        Restaurant r3 = new Restaurant(rest3Name,"300 main st", "city", "state", "000000", noCashPay);
        r3.setRestaurantid(13);
        r3.getMenus().add(new Menu("couscous",6.96, r3 ));
        r3.getMenus().get(0).setMenuId(40);
        r3.getMenus().add(new Menu("french Toast",6.0, r3 ));
        r3.getMenus().get(1).setMenuId(41);
        r3.getMenus().add(new Menu("chickenLoaf",6.90, r3 ));
        r3.getMenus().get(2).setMenuId(42);


        restaurantList.add(r3);

    }


    @After
    public void  tearDown(){

    }

    @Test
    public  void listAllRestaurants () throws Exception {
        String endPoint = "restaurants/restaurants";
        // i dont need to call the real service but i need to call the mock  one
        Mockito.when(mockRestaurantService.findAll()).thenReturn(restaurantList);

        // we build the request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON);
        // mockRequest
        MvcResult requestResult = mockMvc.perform(requestBuilder).andReturn();

        String neededResult = requestResult.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String stringedRestaurantList = mapper.writeValueAsString(restaurantList);
        assertEquals(" === rest Api result ===  ", neededResult,  stringedRestaurantList);
    }


    @Test
    public  void updateRestaurant () throws Exception {
        String endPoint = "restaurants/restaurants/{restaurantid}";
        ArrayList<RestaurantPayments> thisPay = new ArrayList<>();
        Restaurant r1 = new Restaurant(null, null, null, "ZZ", null, thisPay);
        r1.setRestaurantid(10);

        Mockito.when(mockRestaurantService.update(r1, 10)).thenReturn(r1);

        ObjectMapper mapper = new ObjectMapper();
         String stringedResult = mapper.writeValueAsString(r1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(endPoint,10).accept(MediaType.APPLICATION_JSON).content(stringedResult);

        MvcResult requestResult = (MvcResult) mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());


    }



}
