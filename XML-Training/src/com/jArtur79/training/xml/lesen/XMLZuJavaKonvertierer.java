package com.jArtur79.training.xml.lesen;

import static com.jArtur79.training.xml.Konstanten.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jArtur79.training.xml.objekte.Termin;
import com.jArtur79.training.xml.objekte.Termine;

/**
 * 
 * @author
 *
 */
public class XMLZuJavaKonvertierer {
	public static void main(String[] args) {
		try (InputStream eingabe = new FileInputStream(new File(
				"resources/terminkalender.xml"))) {
			DocumentBuilderFactory fabrik = DocumentBuilderFactory.newInstance();
			fabrik.setNamespaceAware(true);
			DocumentBuilder documentBauer = fabrik.newDocumentBuilder();
			Document dokument = documentBauer.parse(eingabe);
			dokument.getDocumentElement().normalize();
			Termine termine = new Termine();
			Element termineElement = (Element) dokument.getElementsByTagName(ELEMENT_TERMINE).item(0);
			termine.setName(termineElement.getAttribute(ELEMENT_TERMINE_NAME));
			NodeList termineKnoten = dokument.getElementsByTagName(ELEMENT_TERMIN);
			for (int i = 0; i < termineKnoten.getLength(); i++) {
				Node knoten = termineKnoten.item(i);
				if (knoten.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) knoten;
					Element was = (Element) element.getElementsByTagName(ELEMENT_WAS).item(0);
					Element wann = (Element) element.getElementsByTagName(ELEMENT_WANN).item(0);
					Element wo = (Element) element.getElementsByTagName(ELEMENT_WO).item(0);
					Termin termin = new Termin();
					termin.setWas(was.getTextContent());
					termin.setWann(wann.getTextContent());
					termin.setWo(wo.getTextContent());
					termine.getTermine().add(termin);
				}
			}
			System.out.println(termine.getName());
			List<Termin> termineListe = termine.getTermine();
			for (Termin termin : termineListe) {
				System.out.println(termin.getWas());
				System.out.println(termin.getWann());
				System.out.println(termin.getWo());
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
