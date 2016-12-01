package com.alma.boutique.infra.services;

import javax.xml.soap.*;

/**
 * Created by asmaboussalem on 01/12/2016.
 */
public class ValidateCreditCardSOAP {
    public boolean validateCreditCardSOAP(String type, String number)
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
