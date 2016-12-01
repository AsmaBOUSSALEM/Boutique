package test.main.controllers;

import main.controllers.ProductControler;

import org.j.*;

import static junit.framework.*;

/**
 * Created by sara on 01/12/16.
 */
public class ProductControllerTest {
    @Test
    public void testApp(){
        ProductController PC=new ProductController();
        String result = PC.init();
        assertEquals(result, "welcome to boutique");}
}

