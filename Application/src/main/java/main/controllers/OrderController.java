package main.controllers;

import com.alma.boutique.api.repositories.OrderRepository;
import model.order.Order;
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
import java.util.List;

/**
 * Created by asmaboussalem on 25/11/2016.
 */
@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/orders")
    public List<Order> getAllproducts(Model model) {
        return orderRepository.findAll();
    }

    @RequestMapping("/orders/{id}")
    public Order getProductById(@PathVariable String id, Model model) {
        return orderRepository.findOne(id);
    }

    @RequestMapping("/validate-credit-card")
    public String validateCreditCard(@RequestParam(value="type", required=true) String type, @RequestParam(value="number", required=true) String number)
    {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://www.webservicex.net/CreditCard.asmx?WSDL";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(type, number), url);

            // Process the SOAP Response
            printSOAPResponse(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
        return "";
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

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}
