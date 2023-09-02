package ATU_project;


import java.io.Serializable;

/**
 * The class for team with 3 or 4 members of students for the project.
 * @author Yoanna Lo, Ju Jong Ho
 * @see Student
 */
public class Team implements Serializable {
	/**
	 * Team leader.
	 */
	private Student leader;
	/**
	 * Team member B.
	 */
	private Student teammateB;
	/**
	 * Team member C.
	 */
	private Student teammateC;
	/**
	 * Team member D.
	 */
	private Student teammateD;
	/**
	 * Team number.
	 */
	private int id;
	/**
	 * Number of students in the team.
	 */
	private int size;
	/**
	 * Team's average K1 energy.
	 */
	private double k1_avg;
	/**
	 * Team's average K2 energy.
	 */
	private double k2_avg;
	
	/**
	 * Constructor for Team class consisting of 3 students.
	 * Assign students to the team and calculate the team size, average K1 and K2 energy.
	 * @param	leader represents leader student.
	 * @param	teammateB represents second student.
	 * @param	teammateC represents third student.
	 * @param	id represent ID of the team.
	 */
	public Team(Student leader, Student teammateB, Student teammateC, int id){
		this.leader = leader;
		this.teammateB= teammateB;
		this.teammateC = teammateC;
		this.teammateD = null;
		this.id = id;
		this.size = 3;
		
		this.k1_avg = (leader.getK1() + teammateB.getK1() + teammateC.getK1())/3.0;
		this.k2_avg = (leader.getK2() + teammateB.getK2() + teammateC.getK2())/3.0;
	}
	
	/**
	 * Constructor for Team class consisting of 4 students.
	 * Assign students to the team and calculate the team size, average K1 and K2 energy.
	 * @param	leader represents leader student.
	 * @param	teammateB represents second student.
	 * @param	teammateC represents third student.
	 * @param	teammateD represents fourth student.
	 * @param	id represent ID of the team.
	 */
	public Team(Student leader, Student teammateB, Student teammateC, Student teammateD, int id){
		this.leader = leader;
		this.teammateB= teammateB;
		this.teammateC = teammateC;
		this.teammateD = teammateD;
		this.id = id;
		this.size = 4;
		
		this.k1_avg = (leader.getK1() + teammateB.getK1() + teammateC.getK1() + teammateD.getK1())/4.0;
		this.k2_avg = (leader.getK2() + teammateB.getK2() + teammateC.getK2() + teammateD.getK2())/4.0;
	}
	
	/**
	 * Methods to assign remaining students to the team.
	 * Recalculate the team size, average K1 and K2 energy.
	 * @param stu represent the remaining student to be added to the team.
	 */
	public void addiStudent(Student stu) {
		this.teammateD = stu;
		this.size += 1;
		this.k1_avg = (leader.getK1() + teammateB.getK1() + teammateC.getK1() + teammateD.getK1())/4.0;
		this.k2_avg = (leader.getK2() + teammateB.getK2() + teammateC.getK2() + teammateD.getK2())/4.0;
	}
	
	/**
	 * Methods to get the leader of the team.
	 * @return	Student instances of {@link Team#leader}.
	 */
	public Student getLeader() {
		return leader;
	}
	
	/**
	 * Methods to get the second member of the team.
	 * @return	Student instances of {@link Team#teammateB}.
	 */
	public Student getTeammateB() {
		return teammateB;
	}

	/**
	 * Methods to get the third member of the team.
	 * @return	Student instances of {@link Team#teammateC}.
	 */
	public Student getTeammateC() {
		return teammateC;
	}
	

	/**
	 * Methods to get the fourth member of the team if present.
	 * @return	Student instances of {@link Team#teammateD}.
	 */
	public Student getTeammateD() {
		return teammateD;
	}
	

	/**
	 * Methods to get the ID of the team.
	 * @return	integer instances of {@link Team#id}.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Methods to get the size of the team.
	 * @return	integer instances of {@link Team#size}.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Methods to get the calculated average K1 energy of the team.
	 * @return	double instances of {@link Team#k1_avg}.
	 */
	public double getK1_avg() {
		return k1_avg;
	}
	
	/**
	 * Methods to get the calculated average K2 energy of the team.
	 * @return	double instances of {@link Team#k2_avg}.
	 */
	public double getK2_avg() {
		return k2_avg;
	}
	
	/**
	 * Methods to get name from the ID of the team.
	 * @return string instance of "Team" plus {@link Team#id}.
	 */
	@Override
	public String toString(){
		return "Team "+ id;
	}

	/**
	 * Methods to check whether there is particular student in the team.
	 * @param	name represents the name of the student to be checked.
	 * @param	sid represents the ID of the student to be checked.
	 * @return	boolean	True if there is a student in a team. Otherwise, false.
	 */
	public boolean contain(String name, long sid){
		boolean isLeader = leader.getNameWithNoComma().equalsIgnoreCase(name) && leader.getSid() == sid;
		boolean isTeammateB = teammateB.getNameWithNoComma().equalsIgnoreCase(name) && teammateB.getSid() == sid;
		boolean isTeammateC = teammateC.getNameWithNoComma().equalsIgnoreCase(name) && teammateC.getSid() == sid;
		boolean searchResult = isLeader || isTeammateB || isTeammateC;
		if (size == 4){
			boolean isTeammateD = teammateD.getNameWithNoComma().equalsIgnoreCase(name) && teammateD.getSid() == sid;
			searchResult = searchResult || isTeammateD;
		}
		if (searchResult){
			return true;
		}
		return false;
	}
}
