package com.example.cbrcurrencyservice.service.parser;

import com.example.cbrcurrencyservice.adapter.web.error.CurrencyXmlParsingException;
import com.example.cbrcurrencyservice.domain.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CbrXmlParser implements Parser {
    @Override
    public List<Currency> parse(String ratesAsString) {
        List<Currency> currencies = new ArrayList<>();
        Document doc = buildXMLDoc(ratesAsString);
        Node root = doc.getFirstChild();
        NodeList currenciesNodeList = root.getChildNodes();
        for (int i = 0; i < currenciesNodeList.getLength(); i++) {
            Node currencyNode = currenciesNodeList.item(i);
            if (currencyNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element element = (Element) currencyNode;
            Currency currency = Currency.builder()
                    .charCode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                    .nominal(Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent()))
                    .name(element.getElementsByTagName("Name").item(0).getTextContent())
                    .rate(new BigDecimal(element.getElementsByTagName("Value").item(0).getTextContent().replace(",", ".")))
                    .build();
            currencies.add(currency);
        }
        return currencies;
    }

    private Document buildXMLDoc(String ratesAsString) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        try (var reader = new StringReader(ratesAsString)) {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(new InputSource(reader));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
