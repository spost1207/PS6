package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel person;	
		PersonDAL.deletePerson(person1.getPersonID());
		person = PersonDAL.getPerson(person1.getPersonID());
		assertNull("He/she shouldnt have been in here",person);		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel person;		
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("He/she shouldnt have been in here",person);		
		PersonDAL.addPerson(person1);	
		
		person = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + " found");
		assertNotNull("he/she should have been added",person);
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel person;
		final String Lastname = "Post";
		
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("he/she should not have been added",person);		
		PersonDAL.addPerson(person1);	
		
		person1.setLastName(Lastname);
		PersonDAL.updatePerson(person1);
		
		person = PersonDAL.getPerson(person1.getPersonID());

		assertTrue("her name did not change",person1.getLastName() == Lastname);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel person;		
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("he/she should not be here",person);	
		
		PersonDAL.addPerson(person1);			
		person = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + " found");
		assertNotNull("he/she should have been added",person);
		
		PersonDAL.deletePerson(person1.getPersonID());
		person = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("he/she shouldnt have been added",person);	
		
	}
	
	

}
