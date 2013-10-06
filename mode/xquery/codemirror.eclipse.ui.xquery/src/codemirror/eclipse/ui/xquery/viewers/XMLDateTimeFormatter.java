package codemirror.eclipse.ui.xquery.viewers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XMLDateTimeFormatter implements IDateFormatter {

	private static final IDateFormatter INSTANCE = new XMLDateTimeFormatter();

	public static IDateFormatter getInstance() {
		return INSTANCE;
	}

	/** JAXP DatatypeFactory */
	private static DatatypeFactory datatypeFactory;

	/**
	 * Gets a static instance of a JAXP DatatypeFactory.
	 * 
	 * @return the factory or null if the factory could not be created
	 */
	private static DatatypeFactory getDataTypeFactory() {
		if (datatypeFactory == null) {
			try {
				datatypeFactory = DatatypeFactory.newInstance();
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datatypeFactory;
	}

	public String format(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = getDataTypeFactory()
				.newXMLGregorianCalendar(gCalendar);
		return xmlCalendar.toXMLFormat();
	}

	public Date format(String dateTime) {
		XMLGregorianCalendar xmlCalendar = getDataTypeFactory()
				.newXMLGregorianCalendar(dateTime);
		return xmlCalendar.toGregorianCalendar().getTime();
	}
	
	

}
