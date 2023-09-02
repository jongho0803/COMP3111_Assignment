package ATU_project.Input;

import ATU_project.App;
import ATU_project.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

/** The InputUI set up the student list and statistic list and
 * show the two corresponding table in window (GUI).
 *
 * @author  Winnie Lam
 *
 */
public class InputUI{

	private final static ObservableList<Student> student_list = FXCollections.observableArrayList();
	private final static ObservableList<Statistics> stat_list = FXCollections.observableArrayList();

	/**
	 * Width of the scene that contain the two table.
	 */
	static double tableViewWidth;

	/**
	 * An array list by converting {@link InputUI#student_list}.
	 */
	private static ArrayList<Student> studentList = new ArrayList<>();

	private static File inputFile;

	/**
	 * A List of {@link Student} instance representing the student list
	 * @return The student list.
	 */
	public static ObservableList<Student> get_student_list(){
		return student_list;
	}
	/**
	 * Clear and set {@link InputUI#student_list}.
	 * @param input_list The student list.
	 */
	public static void set_student_list (ObservableList<Student> input_list) {
		student_list.clear();
		student_list.addAll(input_list);
	}

	/**
	 * Getter for {@link InputUI#stat_list}.
	 * @return a list of {@link Statistics} instance representing the statistic of
	 * the students in source file.
	 */
	public static ObservableList<Statistics> get_stat_list(){
		return stat_list;
	}

	private static final String delimiter = ",";

	/**
	 * This method is used to read the provided csv file and add students into
	 * the {@link InputUI#student_list}.
	 * @param csvFile The CSV file to be read.
	 */
	private static void read(File csvFile) {
		student_list.clear();
		stat_list.clear();
		studentList.clear();
		System.out.print("\n");
		try {
			//File file = new File(csvFile);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(csvFile), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = " ";
			String[] tempArr;
			br.readLine(); // skip the first line
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				
				boolean leaderPref;
				if (tempArr[8].equals("\"\"")) {
					leaderPref = false;
				}
				else {
					leaderPref = Integer.parseInt(tempArr[8]) == 1;
				}
				Student thisStudent = new Student( tempArr[1].replace("\"", "") + "," + tempArr[2].replace("\"", "") ,
						Long.parseLong(tempArr[0]), tempArr[3].replace("\"", ""), Integer.parseInt(tempArr[4]), Integer.parseInt(tempArr[5]),
						Integer.parseInt(tempArr[6])==1, Integer.parseInt(tempArr[7])==1, leaderPref, tempArr[9]);
				student_list.add(thisStudent);
				studentList.add(thisStudent);
			}
			br.close();
		} catch (IOException ioe) {

			ioe.printStackTrace();
		}
	}

	/**
	 * This is the main method which make use the {@link InputUI#read} and {@link InputUI#build} method.
	 * A scene with student list and statistic for GUI is created and shown.
	 * @throws IOException when there's error in constructing the scene.
	 */
	public static void run () throws IOException {
		///String csvFile = "src/main/resources/StuPi.csv";
		//try {
			read(inputFile);
		//}catch(IOException e){
		//	System.out.println("Can't parse the file.");
		//}
		build();

		final var fxml = InputUI.class.getResource("/UI/tableViewParallel.fxml");
		final var loader = new FXMLLoader(Objects.requireNonNull(fxml));
		final var startPane = loader.<VBox>load();

		Scene scene = new Scene(startPane, App.width -10 , App.height - 50 );

		TableViewParallelController controller = loader.getController();
		try {
			Student[] studentListArray = studentList.toArray(new Student[0]);
			controller.setStudentList(studentListArray);
		} catch(ClassCastException e){
			e.printStackTrace();
		}

		App.HomeStage.setScene(scene);
		tableViewWidth = App.HomeStage.getWidth();
		App.HomeStage.setTitle("ATU Program - Student List");
		App.HomeStage.show();
		App.HomeStage.setX((App.width - App.HomeStage.getWidth()) / 2);
		App.HomeStage.setY((App.height - App.HomeStage.getHeight()) / 2);
	}

	/**
	 * This is a class for storing statistics of student skills value.
	 */
	public static class Statistics{ 
		private final SimpleStringProperty entry;
		private final SimpleStringProperty value;

		/**
		 * The method used to construct the {@link Statistics} specifying
		 * the entry and value of object to create.
		 * @param fName	The name of a statistics field, refer to the {@link Statistics#entry}.
		 * @param lName	The value of a statistics field, refer to the {@link Statistics#value}.
		 */
		public Statistics(String fName, String lName) {
			this.entry = new SimpleStringProperty(fName);
			this.value = new SimpleStringProperty(lName);
		}

		/**
		 * The method used to get the value of {@link Statistics#entry}.
		 * @return A String instance of the name of a statistics field.
		 */
		public String getEntry() {
			return entry.get();
		}

		/**
		 * The method used to get the value of {@link Statistics#value}.
		 * @return A String instance of the value of a statistics field.
		 */
		public String getValue() {
			return value.get();
		}
	}

	/**
	 * The method used to get the total number of student in the student list.
	 * @return A total number of elements of the {@link InputUI#student_list}.
	 */
	public static int getNumberOfElement() {
		return student_list.size();
	}

	/**
	 * The method used to get the minimum K1 energy in the student list.
	 * @return The minimum K1 value in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getK1_Min () {
		int min = student_list.get(0).getK1();
		for (int i = 1; i < student_list.size(); i++) {
			if (student_list.get(i).getK1() < min) {
				min = student_list.get(i).getK1();
			}
		}
		return min;
	}

	/**
	 * The method used to get the minimum K2 energy in the student list.
	 * @return The minimum K2 value in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getK2_Min () {
		int min = student_list.get(0).getK2();
		for (int i = 1; i < student_list.size(); i++) {
			if (student_list.get(i).getK2() < min) {
				min = student_list.get(i).getK2();
			}
		}
		return min;
	}

	/**
	 * The method used to get the maximum K1 energy in the student list.
	 * @return The maximum K1 value in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getK1_Max () {
		int max = student_list.get(0).getK1();
		for (int i = 1; i < student_list.size(); i++) {
			if (student_list.get(i).getK1() > max) {
				max = student_list.get(i).getK1();
			}
		}
		return max;
	}

	/**
	 * The method used to get the maximum K2 energy in the student list.
	 * @return The maximum K2 value in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getK2_Max () {
		int max = student_list.get(0).getK2();
		for (int i = 1; i < student_list.size(); i++) {
			if (student_list.get(i).getK2() > max) {
				max = student_list.get(i).getK2();
			}
		}
		return max;
	}

	/**
	 * The method used to get the average K1 energy in the student list.
	 * @return The total K1 energy / the number of elements in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static String getK1_Average () {
		double total = 0;
		for (int i = 0; i < getNumberOfElement() ; i++) {
			total += student_list.get(i).getK1();
		}
		return new DecimalFormat("#.##").format(total/ getNumberOfElement());
	}

	/**
	 * The method used to get the average K2 energy in the student list.
	 * @return The total K2 energy / the number of elements in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static String getK2_Average () {
		double total = 0;
		for (int i = 0; i < getNumberOfElement(); i++) {
			total += student_list.get(i).getK2();
		}
		return new DecimalFormat("#.##").format(total/ getNumberOfElement());
	}

	/**
	 * The method used to get the number of student who have corresponding K3_tick1 ability.
	 * @return The number of K3_tick1 equal to true in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getK3_Tick1 () {
		int acc = 0;
		for (int i = 0; i < student_list.size(); i++) {
			if (student_list.get(i).getK3_tick1()) {
				acc++;
			}
		}
		return acc;
	}

	/**
	 * The method used to get the number of student who have corresponding K3_tick2 ability.
	 * @return The number of K3_tick2 equal to true in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getK3_Tick2 () {
		int acc = 0;
		for (int i = 0; i < student_list.size(); i++) {
			if (student_list.get(i).getK3_tick2()) {
				acc++;
			}
		}
		return acc;
	}

	/**
	 * The method used to get the number of student with preference to be leader.
	 * @return The number of MyPreference equal to true in the {@link InputUI#student_list}.
	 * @see Student
	 */
	public static int getLeadPref () {
		int acc = 0;
		for (int i = 0; i < student_list.size(); i++) {
			if (student_list.get(i).getLeaderPref()) {
				acc++;
			}
		}
		return acc;
	}

	/**
	 * The method used to build the statistic list with statistics data of
	 * the {@link InputUI#student_list}.
	 */
	public static void build() {
		stat_list.add(new Statistics("Total Number of Students", Integer.toString(getNumberOfElement())));
		
		stat_list.add(new Statistics("K1_Energy(Average, Min, Max)", 
				"(" + getK1_Average() + ", " +
				Integer.toString(getK1_Min()) + ", " +
				Integer.toString(getK1_Max()) + ")"));
		
		stat_list.add(new Statistics("K2_Energy(Average, Min, Max)", 
				"(" + getK2_Average() + ", " +
				Integer.toString(getK2_Min()) + ", " +
				Integer.toString(getK2_Max()) + ")"));
		
		stat_list.add(new Statistics("K3_Tick1 = 1", Integer.toString(getK3_Tick1())));
		
		stat_list.add(new Statistics("K3_Tick2 = 1", Integer.toString(getK3_Tick2())));
		
		stat_list.add(new Statistics("My_Preference = 1", Integer.toString(getLeadPref ())));
	}

	/**
	 * Setter for {@link InputUI#inputFile}.
	 * @param file The student csv file to view in table.
	 */
	public static void setInputFile(File file){
		inputFile = file;
	}
}
