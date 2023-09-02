package ATU_project;

import java.io.Serializable;

/**
 * The class for containing student data.
 * @author Yoanna Lo, Winnie Lam, Ju Jong Ho
 */
public class Student implements Serializable {
	/**
	 * Student name.
	 */
	private String name;
	/**
	 * Student SID.
	 */
	private long sid;
	/**
	 * Student email.
	 */
	private String email;
	/**
	 * Student K1 energy.
	 */
	private int k1;
	/**
	 * Student K2 energy.
	 */
	private int k2;
	/**
	 * Student K3 tick 1.
	 */
	private boolean k3_tick1;
	/**
	 * Student K3 tick 2.
	 */
	private boolean k3_tick2;
	/**
	 * Student leader preference.
	 */
	private boolean leaderPref;
	/**
	 * Student concerns.
	 */
	private String concerns;

	/**
	 * The method used to construct an instance of Student.
	 * @param name represent the name of the student.
	 * @param sid represent the student id of the student.
	 * @param email represent the email of the student.
	 * @param k1 represent the k1 energy of the student.
	 * @param k2 represent the k2 energy of the student.
	 * @param k3_tick1 represent whether the student have the corresponding ability.
	 * @param k3_tick2 represent whether the student have the corresponding ability.
	 * @param leaderPref represent whether the student wants to be the leader.
	 * @param concerns represent the student's concern towards the project.
	 */
	public Student(String name, long sid, String email, int k1, int k2, boolean k3_tick1, boolean k3_tick2, boolean leaderPref, String concerns){
		this.name = name;
		this.sid = sid;
		this.email = email;
		this.k1 = k1;
		this.k2 = k2;
		this.k3_tick1 = k3_tick1;
		this.k3_tick2 = k3_tick2;
		this.leaderPref = leaderPref;
		this.concerns = concerns;
	}

	/**
	 * The methods used to get the name of student.
	 * @return string instance of {@link Student#name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The method used to get the name of student without comma separated.
	 * @return string instance of {@link Student#name}, without comma.
	 */
	public String getNameWithNoComma() {
		return name.replace(",","");
	}

	/**
	 * The method used to get the student id of student.
	 * @return long instance of {@link Student#sid}.
	 */
	public long getSid() {
		return sid;
	}

	/**
	 * The method used to get the email of student.
	 * @return string instance of {@link Student#email}.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The method used to get the K1 energy of the student.
	 * @return int instance of {@link Student#k1}.
	 */
	public int getK1() {
		return k1;
	}

	/**
	 * The method used to get the K2 energy of the student.
	 * @return int instance of {@link Student#k2}.
	 */
	public int getK2() {
		return k2;
	}

	/**
	 * The method used to get whether a student have the first ability.
	 * @return boolean instance of {@link Student#k3_tick1}.
	 */
	public boolean getK3_tick1() {
		return k3_tick1;
	}

	/**
	 * The method used to get whether a student have the first ability in string format.
	 * @return string instance of {@link Student#k3_tick1}.
	 */
	public String getK3_tick1_inString() {
		if (k3_tick1) {
			return "1";
		}
		else {
			return "0";
		}
	}

	/**
	 * The method used to get whether a student have the second ability.
	 * @return boolean instance of {@link Student#k3_tick2}.
	 */
	public boolean getK3_tick2() {
		return k3_tick2;
	}

	/**
	 * The method used to get whether a student have the second ability in string format.
	 * @return string instance of {@link Student#k3_tick2}.
	 */
	public String getK3_tick2_inString() {
		if (k3_tick2) {
			return "1";
		}
		else {
			return "0";
		}
	}

	/**
	 * The method used to get whether a student want to be the leader.
	 * @return boolean instance of {@link Student#leaderPref}.
	 */
	public boolean getLeaderPref() {
		return leaderPref;
	}

	/**
	 * The method used to get whether a student want to be the leader in string format.
	 * @return string instance of {@link Student#leaderPref}.
	 */
	public String getLeaderPref_inString() {
		if (leaderPref) {
			return "1";
		}
		else {
			return "0";
		}
	}

	/**
	 * The method used to get the concerns of student.
	 * @return string instance of {@link Student#leaderPref}.
	 */
	public String getConcerns() {
		if (concerns.equals("\"\""))
			return "N/A";
		else return concerns;
	}

}
