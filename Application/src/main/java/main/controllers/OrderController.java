package main.controllers;

import com.alma.boutique.api.repositories.OrderRepository;
import com.alma.boutique.api.repositories.ProductRepository;
import model.order.Order;
import model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asmaboussalem on 25/11/2016.
 */
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/orders")
    public List<Order> getAllproducts(Model model) {
        return orderRepository.findAll();
    }

    @RequestMapping("/orders/{id}")
    public Order getProductById(@PathVariable String id, Model model) {
        return orderRepository.findOne(id);
    }

    @RequestMapping("/submit-order")
    public String submitOrder(@RequestParam(value="products", required = true) String[] products)
    {
        Order order = new Order();
        List<Product> productsList = new ArrayList<>();
        double totalPrice = 0;
        for (int i = 0; i < products.length; i++) {
            productsList.add(productRepository.findOne(products[i]));
            totalPrice += productsList.get(i).getPrice();
        }
        order.setProducts(productsList);
        order.setTotalPrice(totalPrice);
        return "order Created successfully";
    }
    @RequestMapping("/validate-credit-card")
    public boolean validateCreditCard(@RequestParam(value="type", required=true) String type,
                                      @RequestParam(value="number", required=true) String number)
    {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://www.webservicex.net/CreditCard.asmx?WSDL";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(type, number), url);

            soapConnection.close();
            if (soapResponse == null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private SOAPMessage createSOAPRequest(String type, String number) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://www.webservicex.net";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
        /*
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <ValidateCardNumber xmlns="http://www.webservicex.net/">
                    <cardType>visa</cardType>
                    <cardNumber>1232454541456787</cardNumber>
                </ValidateCardNumber>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement validateCardNumber = soapBody.addChildElement("ValidateCardNumber", "", serverURI);
        SOAPElement cardType = validateCardNumber.addChildElement("cardType");
        SOAPElement cardNumber = validateCardNumber.addChildElement("cardNumber");
        cardType.addTextNode(type);
        cardNumber.addTextNode(number);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "http://www.webservicex.net/ValidateCardNumber");

        soapMessage.saveChanges();

        return soapMessage;
    }

}
