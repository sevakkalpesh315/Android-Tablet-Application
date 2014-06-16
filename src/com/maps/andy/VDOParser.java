package com.maps.andy;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class VDOParser extends DefaultHandler 
{
 boolean boFetchAd;
 boolean boFoundAd;
 
 String adData;
 
 private AdParseEventListener adParseEventListener;
 
 interface AdParseEventListener 
 {
 public void adParseStatus(Boolean status, String adData);
 }
 
 public void initParser(URL sourceURL, AdParseEventListener listener )
 {
 this.adParseEventListener = listener;
 try
 {
 SAXParserFactory spf = SAXParserFactory.newInstance();
 SAXParser sp = spf.newSAXParser();
 XMLReader xr = sp.getXMLReader();
 
 xr.setContentHandler(this);
 xr.parse(new InputSource(sourceURL.openStream()));
 }catch (Exception e) {
 System.out.println("XML Parsing Exception in  initParser = " + e);
 }
 }
 
 public void startDocument()
 {
  
 }
 
 /** Called when tag starts */
 @Override
 public void startElement(String uri, String localName, String qName,
 Attributes attributes) throws SAXException {

 if(localName.equals("xhtml"))
 {
 boFetchAd = true;
 adData = new String();
 }
 }

 /** Called when tag closing */
 @Override
 public void endElement(String uri, String localName, String qName)
 throws SAXException {

 if(localName.equals("xhtml"))
 {
 boFetchAd = false;
 boFoundAd = true;
  }
 }

 /** Called to get tag characters */
 @Override
 public void characters(char[] ch, int start, int length)
 throws SAXException {
 
 if(boFetchAd == true)
 {
 adData = adData.concat(new String(ch, start, length));
 }
 }
 
 public void endDocument() 
 {
 adParseEventListener.adParseStatus(boFoundAd, adData);
 }
}
