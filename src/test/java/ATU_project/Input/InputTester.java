package ATU_project.Input;

import ATU_project.Input.InputUI;
import ATU_project.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputTester {
	ObservableList<Student> testList =  FXCollections.observableArrayList();
	@Before
	public void setUp() throws Exception {
		testList.addAll(new Student("SAFFRON, Corgipoo",20004488l,"CorgipooSAF@connect.ust.hk",26,80,false,false,false, ""),
				new Student("HYSSOP, Chamois",20023331l,"CorgipooSAF@connect.ust.hk",27,85,false,false,false, ""),
				new Student("LEEKS, Beetle",20043679,"BeetleLEE@connect.ust.hk",71,40,true,true,true, ""),
				new Student("CHRYSANTHEMUM, Abelisaurus",20067232,"AbelisaurusCHR@connect.ust.hk",57,60,false,false,false, ""));
		InputUI.set_student_list(testList);
		InputUI.build();
	}
	@Test
	public void computeTotal() {
		assertEquals(4, InputUI.get_student_list().size() );
	}
	
	@Test
	public void compute() {
		assertEquals(testList, InputUI.get_student_list() );
	}

	@Test
	public void computeK1AvgWithValidInput() {
		assertEquals("45.25", InputUI.getK1_Average() );
	}
	
	@Test
	public void computeK2AvgWithValidInput() {
		assertEquals("66.25", InputUI.getK2_Average() );
	}
	
	@Test
	public void computeK1MaxWithValidInput() {
		assertEquals(71, InputUI.getK1_Max());
	}
	
	@Test
	public void computeK2MaxWithValidInput() {
		assertEquals(85, InputUI.getK2_Max() );
	}
	
	@Test
	public void computeK1MinWithValidInput() {
		assertEquals(26, InputUI.getK1_Min() );
	}
	
	@Test
	public void computeK2MinWithValidInput() {
		assertEquals(40, InputUI.getK2_Min() );
	}
}