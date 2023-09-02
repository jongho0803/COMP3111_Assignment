package ATU_project.Output;

import ATU_project.App;
import ATU_project.Team;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The class for sorting the generated team list.
 * @author Yoanna Lo
 * @see Team
 */
public class Output implements Serializable {
	/**
	 * The unsorted team list.
	 */
	private Team[] unsortedTeamList;
	/**
	 * The sorted team list based on each team's average K1 energy, secondly based on average K2 energy, in descending order.
	 */
	private Team[] sortedTeamList;

	/**
	 * The only constructor for Output. Sort the given team list and stored in {@link Output#sortedTeamList}.
	 * @param teamList The generated team list from process.
	 */
	public Output(Team[] teamList){
		this.unsortedTeamList = teamList;
		this.sortedTeamList = new Team[teamList.length];
		for (int i = 0; i < teamList.length; i++){
			sortedTeamList[i] = teamList[i];
		}
		this.sortByK1Avg();
	}

	/**
	 * Sort team list by each team's K1 average energy in descending order.
	 * If both team have the same K1 average energy, then sort by K2 average energy.
	 */
	private void sortByK1Avg() {
		for (int i = sortedTeamList.length -1; i >= 0; i--) {
	        for (int j = sortedTeamList.length -1; j > sortedTeamList.length -1 - i; j--) {
	            if (sortedTeamList[j].getK1_avg() > sortedTeamList[j - 1].getK1_avg())
	            {
	                Team tempSwap = sortedTeamList[j];
	                sortedTeamList[j] = sortedTeamList[j-1];
	                sortedTeamList[j-1]= tempSwap;
	            } else if (sortedTeamList[j].getK1_avg() == sortedTeamList[j - 1].getK1_avg()){
					if (sortedTeamList[j].getK2_avg() > sortedTeamList[j - 1].getK2_avg()) {
						Team tempSwap = sortedTeamList[j];
						sortedTeamList[j] = sortedTeamList[j - 1];
						sortedTeamList[j - 1] = tempSwap;
					}
				}
	        }
		}
	}

	/**
	 * Getter for {@link Output#sortedTeamList}.
	 * @return The sorted team list.
	 */
	public Team[] getSortedTeamList(){
		return sortedTeamList;
	}

	/**
	 * Getter for {@link Output#unsortedTeamList}.
	 * @return The unsorted team list.
	 */
	public Team[] getUnsortedTeamList() {
		return unsortedTeamList;
	}

	/**
	 * Serialize the output instance to "export/output.ser".
	 * @throws IOException when fails to serialize.
	 */
	public void serialize() throws IOException {
		//File file = new File("src/main/resources/output.ser");
		FileOutputStream fileOut = new FileOutputStream(App.PATH_TO_OUTPUT_SER, false);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		out.close();
		fileOut.close();
		System.out.println("Serialized data is saved in " + App.PATH_TO_OUTPUT_SER);
	}
}
