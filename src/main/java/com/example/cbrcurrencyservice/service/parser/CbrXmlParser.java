package com.example.cbrcurrencyservice.service.parser;

import com.example.cbrcurrencyservice.domain.CacheCurrency;
import com.example.cbrcurrencyservice.domain.Currency;
import com.example.cbrcurrencyservice.domain.MoneyValue;
import org.springframework.stereotype.Component;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CbrXmlParser implements Parser {
    @Override
    public CacheCurrency parse(String ratesAsString) {
        List<Currency> currencies = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Document doc = buildXMLDoc(ratesAsString);
        Node root = doc.getFirstChild();
        String d = root.getAttributes().getNamedItem("Date").getNodeValue();
        LocalDate date = LocalDate.parse(d, formatter);
        NodeList currenciesNodeList = root.getChildNodes();
        for (int i = 0; i < currenciesNodeList.getLength(); i++) {
            Node currencyNode = currenciesNodeList.item(i);
            if (currencyNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element element = (Element) currencyNode;
            Currency currency = Currency.builder()
                    .numCode(Integer.parseInt(element.getElementsByTagName("NumCode").item(0).getTextContent()))
                    .charCode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                    .nominal(Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent()))
                    .name(element.getElementsByTagName("Name").item(0).getTextContent())
                    .rate(new MoneyValue("RUB", new BigDecimal(element.getElementsByTagName("Value").item(0).getTextContent().replace(",", "."))))
                    .unitRate(new BigDecimal(element.getElementsByTagName("VunitRate").item(0).getTextContent().replace(",", ".")))
                    .build();
            currencies.add(currency);
        }
        return new CacheCurrency(date, currencies);
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
