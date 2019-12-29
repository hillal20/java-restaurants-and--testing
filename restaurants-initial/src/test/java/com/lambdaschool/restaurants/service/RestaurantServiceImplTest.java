package com.lambdaschool.restaurants.service;

import com.lambdaschool.restaurants.RestaurantsApplication;
import com.lambdaschool.restaurants.model.Restaurant;
import com.lambdaschool.restaurants.model.RestaurantPayments;
import com.lambdaschool.restaurants.repos.RestaurantRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantsApplication.class) // the main class that we using
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService  restaurantService; // we have to bring the interface


   // stubs when we fake a return of a method
    // mock is the seed data that we came out with



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void A_findRestaurantByNameLike() {
    }

    @Test
    public void B_findNameCity() {
    }

    @Test
    public void C_findAll() {

        assertEquals(3, restaurantService.findAll().size());
    }

    @Test
    public void D_findRestaurantById() {
       assertEquals("Eagle Cafe Test", restaurantService.findRestaurantById(10).getName()); ;
    }

    @Test
    public void E_findRestaurantByName() {
    }

    @Test
    public void Z_delete() {
        restaurantService.delete(13);
        assertEquals(2, restaurantService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void Y_deleteNotFound(){
       restaurantService.delete(800);
      assertEquals(2, restaurantService.findAll().size());

    }

    @Test
    public void F_save() {
    }

    @Test
    public void J_update() {


        ArrayList<RestaurantPayments > restaurantPayments = new ArrayList<>();
        Restaurant r1 = new Restaurant("Settara", "37 st rod", "Il", "chicago","234837373", restaurantPayments);
        r1.setRestaurantid(10); // we just assigning an id

        Restaurant r;
        r = restaurantService.update(r1, 10);
        assertEquals("Il", r.getCity());
    }
}