package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
	private static PersonDAL personTestDAL;	
	private static PersonDomainModel per1;
	private static PersonDomainModel per2;
	private static UUID per1UUID = UUID.randomUUID();			
	private static UUID per2UUID = UUID.randomUUID();
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		 per1 = new PersonDomainModel();
		 per2 = new PersonDomainModel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
		per1.setPersonID(per1UUID);
		per1.setFirstName("Juan");
		per1.setLastName("Pablo");
		per1.setBirthday(dateFormat.parse("1997-08-30"));
		per1.setCity("Los Angeles");
		per1.setStreet("2401 Poinsettia Ave");
		per1.setPostalCode(90266);
		
		per2.setPersonID(per2UUID);
		per2.setFirstName("Ben");
		per2.setLastName("Gillingham");
		per2.setBirthday(dateFormat.parse("1990-02-09"));
		per2.setCity("San Francisco");
		per2.setStreet("312 East Main Street");
		per2.setPostalCode(94101);
	}
	
	@Test
	public void addPersonTest() {	
		personTestDAL.addPerson(per1);
		assert(per1==personTestDAL.getPerson(per1.getPersonID()));
		
	}
	
	@Test
	public void getPersonTest(){
		assert(personTestDAL.getPerson(per1UUID)==per1);
	}
	
	@Test
	public void personUpdateTest(){
		per1.setCity("Manhattan Beach");
		per1.setFirstName("Carson");
		per1.setLastName("Boden");
		personTestDAL.updatePerson(per1);
		assert("Boden"==personTestDAL.getPerson(per1UUID).getLastName());
		assert("Carson"==personTestDAL.getPerson(per1UUID).getFirstName());
		assert("Manhattan Beach"==personTestDAL.getPerson(per1UUID).getCity());
	}
	
	@Test
	public void getPersonsTest(){
		ArrayList<PersonDomainModel> answer = new ArrayList<PersonDomainModel>();
		answer.add(per1);
		answer.add(per2);
		personTestDAL.addPerson(per2);
		assert(answer==personTestDAL.getPersons());
	}
	
//	@Test
//	public void deletePersonTest(){
//		personTestDAL.deletePerson(per2UUID);
//		personTestDAL.deletePerson(per1UUID);
//		assert(null==personTestDAL.getPersons());
//	}
//	

}
