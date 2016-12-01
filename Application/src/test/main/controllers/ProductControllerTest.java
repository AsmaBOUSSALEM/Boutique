package main.controllers;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by sara on 01/12/16.
 */
public class ProductControllerTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void init() throws Exception {

        ProductController PC=new ProductController();
        String result = PC.init();
        assertEquals(result, "welcome to boutique");

    }

}