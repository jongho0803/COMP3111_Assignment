package ATU_project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Process. Test cases written using JUnit4.
 * @author Ju Jong Ho
 */
public class ProcessTester {

	Student A, B, C, D, E;
	Student F, G, H, I, J;
	Student[] student_List0;
	Student[] student_List1;
	Student[] student_List2;
	
	/**
     * Fixture method that's called before every test case.
     * Set up the student list for creating the Process instance.
     * @throws Exception when failing to create the student list.
     */
	@Before
	public void setUp() throws Exception {
		A = new Student("Amy", 20201010, "amy@connect.ust.hk", 4, 0, true, true, true,"");
		B = new Student("Ben", 30201010, "ben@connect.ust.hk", 1, 7, false, false, false, "");
		C = new Student("Carl", 40201010, "car@connect.ust.hk", 3, 4, true, false, false, "");
		D = new Student("Dennis", 50201010, "den@connect.ust.hk", 6, 4, true, true, false, "");
		E = new Student("Emily", 60201010, "emi@connect.ust.hk", 2, 2, false, false, true, "");
		F = new Student("Fred", 70201010, "fre@connect.ust.hk", 4, 2, true, false, false, "");
		G = new Student("George", 80201010, "geo@connect.ust.hk", 9, 1, true, false, true, "");
		H = new Student("Harry", 90201010, "har@connect.ust.hk", 5, 3, true, true, false, "");
		I = new Student("Isaac", 10201010, "isa@connect.ust.hk", 0, 5, false, false, false, "");
		J = new Student("Jerry", 11201010, "jer@connect.ust.hk", 3, 2, true, false, false, "");
		
		student_List0 = new Student[9];
		student_List0[0] = A;
		student_List0[1] = B;
		student_List0[2] = C;
		student_List0[3] = D;
		student_List0[4] = E;
		student_List0[5] = F;
		student_List0[6] = G;
		student_List0[7] = H;
		student_List0[8] = I;
		
		student_List1 = new Student[10];
		student_List1[0] = A;
		student_List1[1] = B;
		student_List1[2] = C;
		student_List1[3] = D;
		student_List1[4] = E;
		student_List1[5] = F;
		student_List1[6] = G;
		student_List1[7] = H;
		student_List1[8] = I;
		student_List1[9] = J;
		
		student_List2 = new Student[8];
		student_List2[0] = A;
		student_List2[1] = B;
		student_List2[2] = C;
		student_List2[3] = D;
		student_List2[4] = E;
		student_List2[5] = F;
		student_List2[6] = G;
		student_List2[7] = H;
	}	

	/**
     * Test whether the {@link Process#K1_List} is constructed and sorted in correct order at Process construction.
     */
	@Test
	public void GetK1List() {
		Student[] expect = new Student[] {G, D, H, F, A, C, J, E, B, I};
		assertArrayEquals(expect, new Process(student_List1).getK1List());
	}
	
	/**
     * Test whether the {@link Process#K2_List} is constructed and sorted in correct order at Process construction.
     */
	@Test
	public void GetK2List() {
		Student[] expect = new Student[] {A, E, J, F, C, I, B};
		assertArrayEquals(expect, new Process(student_List1).getK2List());
	}
	
	/**
     * Test whether the {@link Process#K3_List} is constructed and sorted in correct order at Process construction.
     */
	@Test
	public void GetK3List() {
		Student[] expect = new Student[] {B, I, C, F};
		assertArrayEquals(expect, new Process(student_List1).getK3List());
	}
	
	/**
     * Test whether team 1 is formed correctly when there are 1 remaining student.
     * This test case mainly tests the members of the team 1.
     */
	@Test
	public void FormTeam1() {
		Team[] expect = new Team[3];
		expect[0] = new Team(G, A, B, 1);
		expect[1] = new Team(E, I, D, 2);
		expect[2] = new Team(C, H, J, F, 3);
		assertEquals(expect[0].getLeader().getSid(), new Process(student_List1).formTeam()[0].getLeader().getSid());
		assertEquals(expect[0].getTeammateB().getSid(), new Process(student_List1).formTeam()[0].getTeammateB().getSid());
		assertEquals(expect[0].getTeammateC().getSid(), new Process(student_List1).formTeam()[0].getTeammateC().getSid());
	}
	
	/**
     * Test whether team 2 is formed correctly when there are 1 remaining student.
     * This test case mainly tests the members of the team 2.
     */
	@Test
	public void FormTeam2() {
		Team[] expect = new Team[3];
		expect[0] = new Team(G, A, B, 1);
		expect[1] = new Team(E, I, D, 2);
		expect[2] = new Team(C, H, J, F, 3);
		assertEquals(expect[1].getLeader().getSid(), new Process(student_List1).formTeam()[1].getLeader().getSid());
		assertEquals(expect[1].getTeammateB().getSid(), new Process(student_List1).formTeam()[1].getTeammateB().getSid());
		assertEquals(expect[1].getTeammateC().getSid(), new Process(student_List1).formTeam()[1].getTeammateC().getSid());
	}
	
	/**
     * Test whether team 3 is formed correctly when there are 1 remaining student.
     * This test case mainly tests the members of the team 3.
     */
	@Test
	public void FormTeam3() {
		Team[] expect = new Team[3];
		expect[0] = new Team(G, A, B, 1);
		expect[1] = new Team(E, I, D, 2);
		expect[2] = new Team(C, H, J, F, 3);
		assertEquals(expect[2].getLeader().getSid(), new Process(student_List1).formTeam()[2].getLeader().getSid());
		assertEquals(expect[2].getTeammateB().getSid(), new Process(student_List1).formTeam()[2].getTeammateB().getSid());
		assertEquals(expect[2].getTeammateC().getSid(), new Process(student_List1).formTeam()[2].getTeammateC().getSid());
		assertEquals(expect[2].getTeammateD().getSid(), new Process(student_List1).formTeam()[2].getTeammateD().getSid());
	}
	
	/**
     * Test whether teams are formed correctly when there is no remaining student.
     * This test case tests overall data of the team:
     * <ol>
     * <li> Leader selection. </li>
     * <li> Team size. </li>
     * <li> Calculated K1 average. </li>
     * <li> Calculated K2 average. </li>
     * </ol>
     */
	@Test
	public void FormTeam_Remainder0() {
		Team[] expect = new Team[3];
		expect[0] = new Team(G, A, B, 1);
		expect[1] = new Team(E, I, D, 2);
		expect[2] = new Team(C, H, F, 3);
		assertEquals(expect[0].getLeader().getSid(), new Process(student_List0).formTeam()[0].getLeader().getSid());
		assertEquals(expect[0].getK1_avg(), new Process(student_List0).formTeam()[0].getK1_avg(), 0.001);
		assertEquals(expect[0].getK2_avg(), new Process(student_List0).formTeam()[0].getK2_avg(), 0.001);
		assertEquals(expect[0].getSize(), new Process(student_List0).formTeam()[0].getSize());
		assertEquals(expect[1].getLeader().getSid(), new Process(student_List0).formTeam()[1].getLeader().getSid());
		assertEquals(expect[1].getK1_avg(), new Process(student_List0).formTeam()[1].getK1_avg(), 0.001);
		assertEquals(expect[1].getK2_avg(), new Process(student_List0).formTeam()[1].getK2_avg(), 0.001);
		assertEquals(expect[1].getSize(), new Process(student_List0).formTeam()[1].getSize());
		assertEquals(expect[2].getLeader().getSid(), new Process(student_List0).formTeam()[2].getLeader().getSid());
		assertEquals(expect[2].getK1_avg(), new Process(student_List0).formTeam()[2].getK1_avg(), 0.001);
		assertEquals(expect[2].getK2_avg(), new Process(student_List0).formTeam()[2].getK2_avg(), 0.001);
		assertEquals(expect[2].getSize(), new Process(student_List0).formTeam()[2].getSize());
	}
	
	/**
     * Test whether teams are formed correctly when there is one remaining student.
     * This test case tests overall data of the team:
     * <ol>
     * <li> Leader Selection. </li>
     * <li> Team size. </li>
     * <li> Calculated K1 average. </li>
     * <li> Calculated K2 average. </li>
     * <li> Proper assignment of the remaining student to the corresponding team. </li>
     * </ol>
     */
	@Test
	public void FormTeam_Remainder1() {
		Team[] expect = new Team[3];
		expect[0] = new Team(G, A, B, 1);
		expect[1] = new Team(E, I, D, 2);
		expect[2] = new Team(C, H, J, F, 3);
		assertEquals(expect[0].getLeader().getSid(), new Process(student_List1).formTeam()[0].getLeader().getSid());
		assertEquals(expect[0].getK1_avg(), new Process(student_List1).formTeam()[0].getK1_avg(), 0.001);
		assertEquals(expect[0].getK2_avg(), new Process(student_List1).formTeam()[0].getK2_avg(), 0.001);
		assertEquals(expect[0].getSize(), new Process(student_List1).formTeam()[0].getSize());
		assertEquals(expect[1].getLeader().getSid(), new Process(student_List1).formTeam()[1].getLeader().getSid());
		assertEquals(expect[1].getK1_avg(), new Process(student_List1).formTeam()[1].getK1_avg(), 0.001);
		assertEquals(expect[1].getK2_avg(), new Process(student_List1).formTeam()[1].getK2_avg(), 0.001);
		assertEquals(expect[1].getSize(), new Process(student_List1).formTeam()[1].getSize());
		assertEquals(expect[2].getLeader().getSid(), new Process(student_List1).formTeam()[2].getLeader().getSid());
		assertEquals(expect[2].getTeammateD().getSid(), new Process(student_List1).formTeam()[2].getTeammateD().getSid());
		assertEquals(expect[2].getK1_avg(), new Process(student_List1).formTeam()[2].getK1_avg(), 0.001);
		assertEquals(expect[2].getK2_avg(), new Process(student_List1).formTeam()[2].getK2_avg(), 0.001);
		assertEquals(expect[2].getSize(), new Process(student_List1).formTeam()[2].getSize());
	}
	
	/**
     * Test whether teams are formed correctly when there are two remaining students.
     * This test case tests overall data of the team:
     * <ol>
     * <li> Leader Selection. </li>
     * <li> Team size. </li>
     * <li> Calculated K1 average. </li>
     * <li> Calculated K2 average. </li>
     * <li> Proper assignment of the remaining students to the corresponding team. </li>
     * </ol>
     */
	@Test
	public void FormTeam_Remainder2() {
		Team[] expect = new Team[2];
		expect[0] = new Team(G, A, B, H, 1);
		expect[1] = new Team(E, C, D, F, 2);
		assertEquals(expect[0].getLeader().getSid(), new Process(student_List2).formTeam()[0].getLeader().getSid());
		assertEquals(expect[0].getTeammateD().getSid(), new Process(student_List2).formTeam()[0].getTeammateD().getSid());
		assertEquals(expect[0].getK1_avg(), new Process(student_List2).formTeam()[0].getK1_avg(), 0.001);
		assertEquals(expect[0].getK2_avg(), new Process(student_List2).formTeam()[0].getK2_avg(), 0.001);
		assertEquals(expect[0].getSize(), new Process(student_List2).formTeam()[0].getSize());
		assertEquals(expect[1].getLeader().getSid(), new Process(student_List2).formTeam()[1].getLeader().getSid());
		assertEquals(expect[1].getTeammateD().getSid(), new Process(student_List2).formTeam()[1].getTeammateD().getSid());
		assertEquals(expect[1].getK1_avg(), new Process(student_List2).formTeam()[1].getK1_avg(), 0.001);
		assertEquals(expect[1].getK2_avg(), new Process(student_List2).formTeam()[1].getK2_avg(), 0.001);
		assertEquals(expect[1].getSize(), new Process(student_List2).formTeam()[1].getSize());
	}
}