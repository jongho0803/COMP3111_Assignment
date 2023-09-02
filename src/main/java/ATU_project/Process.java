package ATU_project;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for generating team list based on K1, K2 energy of the students.
 * @author Ju Jong Ho
 * @see Student
 * @see Team
 */
public class Process {
	private Student[] K1_List;
	private Student[] K2_List;
	private Student[] K3_List;
	
	/**
	 * Public constructor for Process. Process instance is constructed with the following mechanisms:
	 * <ol>
     * <li> Make 3 separate studentList from the given studentList. </li>
     * <li> Sort K1_List according to the students' energy. </li>
     * <li> Sort K2_List according to the students' energy without one third students in K1_List. </li>
     * <li> Sort K3_List according to the students' energy without two third students in K1_List and K2_List. </li>
	 * </ol>
	 * @param student_List represents the imported student list from Input task.
	 */
	public Process(Student[] student_List){
		this.K1_List = student_List;
		sortK1();
		
		int K1_Pick = K1_List.length / 3;
		K2_List = new Student[K1_List.length - K1_Pick];
		for(int i = 0; i < K2_List.length; i ++) {
			this.K2_List[i] = K1_List[i + K1_Pick]; 
		}
		sortK2();
		
		int K2_Pick = K1_List.length / 3;
		K3_List = new Student[K2_List.length - K2_Pick];
		for(int i = 0; i < K3_List.length; i ++) {
			this.K3_List[i] = K2_List[i + K2_Pick]; 
		}
		sortK3();
	}
	
	/**
	 * Sort K1_List with following mechanism:
	 * <ol>
     * <li> Sort the list by each students' K1 energy in descending order. </li>
     * <li> If students have the same K1 energy, then sort by K2 energy in descending order. </li>
	 * </ol>
	 */
	public void sortK1() {
		for (int i = 0; i < K1_List.length; i++) {
	        for (int j = K1_List.length - 1; j > i; j--) {
	            if (K1_List[j].getK1() > K1_List[j - 1].getK1())
	            {
	                Student swap = K1_List[j];
	                K1_List[j] = K1_List[j-1];
	                K1_List[j-1]= swap;
	            }
	            else if (K1_List[j].getK1() == K1_List[j - 1].getK1()) {
	            	if (K1_List[j].getK2() > K1_List[j - 1].getK2())
		            {
		                Student swap = K1_List[j];
		                K1_List[j] = K1_List[j-1];
		                K1_List[j-1]= swap;
		            }
	            }
	        }
		}
	}
	
	/**
	 * Sort K2_List with following mechanism:
	 * <ol>
     * <li> Sort the list by each students' K2 energy in ascending order. </li>
     * <li> If students have the same K2 energy, then sort by K1 energy in ascending order. </li>
	 * </ol>
	 */
	public void sortK2() {
		for (int i = 0; i < K2_List.length; i++) {
			for (int j = K2_List.length - 1; j > i; j--) {
	            if (K2_List[j].getK2() < K2_List[j - 1].getK2())
	            {
	                Student swap = K2_List[j];
	                K2_List[j] = K2_List[j-1];
	                K2_List[j-1]= swap;
	            }
	            else if (K2_List[j].getK2() == K2_List[j - 1].getK2()) {
	            	if (K2_List[j].getK1() < K2_List[j - 1].getK1())
		            {
		                Student swap = K2_List[j];
		                K2_List[j] = K2_List[j-1];
		                K2_List[j-1]= swap;
		            }
	            }
	        }
		}
	}
	
	/**
	 * Sort K3_List with following mechanism:
	 * <ol>
     * <li> Sort the list by each students' K2 energy in descending order. </li>
     * <li> If students have the same K2 energy, then sort by K1 energy in ascending order. </li>
	 * </ol>
	 */
	public void sortK3() {
		for (int i = 0; i < K3_List.length; i++) {
			for (int j = K3_List.length - 1; j > i; j--) {
	            if (K3_List[j].getK2() > K3_List[j - 1].getK2())
	            {
	                Student swap = K3_List[j];
	                K3_List[j] = K3_List[j-1];
	                K3_List[j-1]= swap;
	            }
	        }
		}
	}
	
	/**
	 * Get K1_List of the Process class.
	 * @return	studentList instances of {@link Process#K1_List}.
	 */
	public Student[] getK1List(){
		return K1_List;
	}
	
	/**
	 * Get K2_List of the Process class.
	 * @return	studentList instances of {@link Process#K2_List}.
	 */
	public Student[] getK2List(){
		return K2_List;
	}
	
	/**
	 * Get K3_List of the Process class.
	 * @return	studentList instances of {@link Process#K3_List}.
	 */
	public Student[] getK3List(){
		return K3_List;
	}
	
	/**
	 * Form teams of 3 or 4 students with minimum deviation of the average K1 and K2 energy with following methods:
	 * <ol>
     * <li> From generated K1_list, K2_List and K3_List, assign each students from the lists. </li>
     * <li> Choose the leader from the students' preference. </li>
     * <li> If no one prefer being leader, choose student with highest K2 energy, the managerial skills. </li>
     * <li> Assign extra students to the last 1 or 2 team(s). </li>
	 * </ol>
	 * @return teamList	The formed teamList with consideration of minimizing the deviation of the teams' average K1 and K2 energy.
	 */
	public Team [] formTeam() {
		Team [] ATU_Team = new Team[K1_List.length / 3];
		
		for(int i = 0; i < ATU_Team.length; i++) {
			int sz_team = 3;
			Student[] team = new Student[sz_team];
			
			for(int j = 0; j < sz_team; j++) {
				if(j == 0) team[j] = K1_List[i];
				if(j == 1) team[j] = K2_List[i];
				if(j == 2) team[j] = K3_List[i];
			}
			
			for(int j = 0; j < sz_team; j ++) {
				if(team[j].getLeaderPref()) {
					ATU_Team[i] = new Team(team[j], team[(j+1)%sz_team], team[(j+2)%sz_team], i + 1);
					break;
				}
			}
			if(ATU_Team[i] == null) {
				int max_K2 = 0;
				
				for(int j = 0; j < sz_team; j++) {
					if(team[j].getK2() > max_K2) {
						max_K2 = team[j].getK2();
					}
				}
				
				for(int j = 0; j < sz_team; j++) {
					if(team[j].getK2() == max_K2) {
						ATU_Team[i] = new Team(team[j], team[(j+1)%sz_team], team[(j+2)%sz_team], i + 1);
						break;
					}
				}
			}
		}
		int remainder = K1_List.length - 3 * ATU_Team.length;
		
		if (remainder == 1) {
			ATU_Team[ATU_Team.length - 1].addiStudent(K3_List[ATU_Team.length]);
		}
		
		if (remainder == 2) {
			ATU_Team[ATU_Team.length - 2].addiStudent(K3_List[ATU_Team.length]);
			ATU_Team[ATU_Team.length - 1].addiStudent(K3_List[ATU_Team.length + 1]);
		}
		return ATU_Team;
	}
	
	/**
	 * Create CSV File named "Team.csv", which contains the formed team data.
	 * It will save CSV file under "export/" for instructors to see.
	 * @param ATU_Team represent the formed teamList from the formTeam() methods.
	 */
	public void createTeamFile(Team[] ATU_Team){
		File file = new File(App.PATH_TO_TEAM_CSV);
		try {
			FileWriter outputfile = new FileWriter(file);
  
			CSVWriter writer = new CSVWriter(outputfile);
  
			// create a List which contains String array
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] {"Team ID", "Student ID", "Name", "Email", 
								   "K1_Energy", "K2_Energy", "K3_Tick1", "K3_Tick2",
								   "Leader Preference", "Concern", "Leader" });
  
			for(int i = 0; i < ATU_Team.length; i++) {
				for (int j = 0; j < ATU_Team[i].getSize(); j++) {
					if(j == 0) {
						data.add(new String[] { ATU_Team[i].toString(), 
        				Long.toString(ATU_Team[i].getLeader().getSid()), 
        				ATU_Team[i].getLeader().getName(),
        				ATU_Team[i].getLeader().getEmail(),
        				Integer.toString(ATU_Team[i].getLeader().getK1()),
        				Integer.toString(ATU_Team[i].getLeader().getK2()),
        				ATU_Team[i].getLeader().getK3_tick1_inString(),
        				ATU_Team[i].getLeader().getK3_tick2_inString(),
        				ATU_Team[i].getLeader().getLeaderPref_inString(),
        				ATU_Team[i].getLeader().getConcerns(),"Y"});
					}
					else if(j == 1) {
						data.add(new String[] { ATU_Team[i].toString(), 
		        		Long.toString(ATU_Team[i].getTeammateB().getSid()), 
		        		ATU_Team[i].getTeammateB().getName(),
		        		ATU_Team[i].getTeammateB().getEmail(),
		        		Integer.toString(ATU_Team[i].getTeammateB().getK1()),
		        		Integer.toString(ATU_Team[i].getTeammateB().getK2()),
		        		ATU_Team[i].getTeammateB().getK3_tick1_inString(),
		        		ATU_Team[i].getTeammateB().getK3_tick2_inString(),
		        		ATU_Team[i].getTeammateB().getLeaderPref_inString(),
		        		ATU_Team[i].getTeammateB().getConcerns(),"N"});
					}
					else if(j == 2) {
						data.add(new String[] { ATU_Team[i].toString(), 
				    	Long.toString(ATU_Team[i].getTeammateC().getSid()), 
				    	ATU_Team[i].getTeammateC().getName(),
				    	ATU_Team[i].getTeammateC().getEmail(),
				    	Integer.toString(ATU_Team[i].getTeammateC().getK1()),
				       	Integer.toString(ATU_Team[i].getTeammateC().getK2()),
				       	ATU_Team[i].getTeammateC().getK3_tick1_inString(),
				       	ATU_Team[i].getTeammateC().getK3_tick2_inString(),
				      	ATU_Team[i].getTeammateC().getLeaderPref_inString(),
				    	ATU_Team[i].getTeammateC().getConcerns(),"N"});
					}
					else if(j == 3) {
						data.add(new String[] { ATU_Team[i].toString(), 
				        Long.toString(ATU_Team[i].getTeammateB().getSid()), 
				        ATU_Team[i].getTeammateD().getName(),
				        ATU_Team[i].getTeammateD().getEmail(),
				        Integer.toString(ATU_Team[i].getTeammateD().getK1()),
				        Integer.toString(ATU_Team[i].getTeammateD().getK2()),
				        ATU_Team[i].getTeammateD().getK3_tick1_inString(),
				        ATU_Team[i].getTeammateD().getK3_tick2_inString(),
				        ATU_Team[i].getTeammateD().getLeaderPref_inString(),
				      	ATU_Team[i].getTeammateD().getConcerns(),"N"});
					}
				}
			}
 
			writer.writeAll(data);
        
			// closing writer connection
			writer.close();
		}
    
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
