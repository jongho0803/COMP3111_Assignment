# Comp3111 Grp48 Project
Team repository for Comp3111 Fall2022 Group 48 project

<table>
    <thead>
        <tr>
            <th rowspan=2>Group #</th>
            <th rowspan=2>Student ID.</th>
            <th rowspan=2>Student Name</th>
            <th rowspan=2>Email</th>
            <th rowspan=2>Task Assigned</th>
            <th colspan=2>Git Hub ID</th>
            <th rowspan=2>Dev Branch</th>
        </tr>
        <tr>
            <th>Individual</th>
            <th>Team</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=3>48</td>
            <td>20770314</td>
            <td>Winnie</td>
            <td>wylamav@connect.ust.hk</td>
            <td>INPUT</td>
            <td>emhummm</td>
            <td rowspan=3>COMP3111-gp48</td>
            <td>dev/input</td>
        </tr>
        <tr>
            <td>20554930</td>
            <td>Ho</td>
            <td>jjuaa@connect.ust.hk</td>
            <td>PROCESS</td>
            <td>jongho0803</td>
            <td>dev/process</td>
        </tr>
        <tr>
            <td>20584909</td>
            <td>Yoanna</td>
            <td>hcloaf@connect.ust.hk</td>
            <td>OUTPUT</td>
            <td>yoannalhc</td>
            <td>dev/output</td>
        </tr>
    </tbody>
</table>

## Class Diagram (for overall system)
![Class Diagram](https://github.com/COMP3111-gp48/ATU-project/blob/main/diagrams/ClassDiagram.png?raw=true "Grp48 Class Diagram")  
**<ins> Description:</ins>**   
The ATU system needs 2 classes: “Student” class and “Project Team” class. 
- “Student” class contains necessary attributes of the students that are used to run the algorithms to group the students with the minimum standard deviation of team K1 and K2 energy.
- “Project Team” class has the attributes of the team’s average K1 and K2 energy, which is used in the ATU system to form balanced teams.  
- For each team, there should be at least 3 students and at most 4 students. Each student belongs to 1 team only.

## Use Case Diagram (for overall system)
![Use Case Diagram](https://github.com/COMP3111-gp48/ATU-project/blob/main/diagrams/UseCaseDiagram.png?raw=true "Grp48 Use Case Diagram")  
**<ins> Description:</ins>**  
The ATU System has 2 actors: Student and Teaching Team, and 2 use cases: Form Team and Inquire about Team Information.
- “Teaching team” actor will initiate the “Form Team” use case to form the balanced team. After the ATU system forms teams, it plots a 2D-Line Chart based on team average ability and displays it on the user interface for “Teaching team” to see.
- “Student” actor will initiate the “Inquire about Team Formation” to get the information of the team they are in after the completion of the team formation by the ATU system.


## Use Case Specification: 
### <ins> Use Case: Form Team </ins>
#### Brief Description
This use case describes how the system helps to automatically teaming up students by their skill profile into project teams.

#### Use-case Diagram
![Form Team Use-case Diagram](https://github.com/COMP3111-gp48/ATU-project/blob/main/diagrams/Form_Team_Diagram.png?raw=true "Form Team Use-case Diagram")

#### Basic Flow
1. The use case begins when the Teaching Team actor chooses the project team formation service.  
**{Choose Student Data}**
2. The ATU system retrieves the student data.  
**{Display Statistics}**
3. The ATU system displays tables with statistics based on user choice.  
**{Confirm Student Data}**
4. The Teaching Team actor confirms the loaded student data.
5. The ATU system initiates the ATU Engine program.
    5.1.   The ATU Engine distributes students into different project teams.
    5.2.    The ATU Engine prompts “Team list created.” and creates a file containing the generated team list.
**{Display Chart}**
6. The ATU system plots a 2D-Line Chart based on team average ability and displays it on the user interface.
7. The use case ends.


#### Alternative Flows
**A1: Select Another Student Data**
At **{Choose Student Data}** if the Teaching Team wants to load another student data to the ATU system,
1. The Teaching Team can select another student data.
2. The flow of events is resumed at **{Choose Student Data}**.

**A2: Student Data Invalid**
At **{Display Statistics}** if the ATU system has no csv file or invalid student data,
1. The system notifies the Teaching Team that this function is currently not available due to invalid input of student data.
2. The system rejects the request.
3. The use case ends.

**A3: Wrongly Loaded Student Data**
At **{Confirm Student Data}** if the Teaching Team finds the ATU system loads the wrong student data,
1. The Teaching Team can cancel the form team process.
2. The use case ends.

**A4: Chart Not Needed**
At **{Display Chart}** if the Teaching Team does not need to view the generated chart,
1. The Teaching Team can choose to leave the process.
2. The use case ends.

### <ins> Use Case: Inquire about Team Information </ins>
#### Brief Description
This use case describes how a student inquires about an individual’s team information after the teams have been formed by the system.

#### Use-case Diagram
![Inquiry Use-case Diagram](https://github.com/COMP3111-gp48/ATU-project/blob/main/diagrams/Inquiry_Diagram.png?raw=true "Inquiry Use-case Diagram")

#### Basic Flow
1. The use case begins when the Student actor chooses the student inquiry service to look up an individual’s team information.  
**{Begin Inquiry}**  
2. The ATU system prompts the Student to enter a name or a student ID that he/she wants to inquire for.  
**{Enter Name or Student ID}**  
3. The Student enters a name or a student ID.  
**{Display Inquiry Result}**  
4. The ATU system retrieves and displays the corresponding team information for the given name or student ID on the user interface.
5. The use case ends.

#### Alternative Flows
**A1: Team Not Yet Formed**  
At **{Begin inquiry}** if the ATU system has not formed teams,  
1. The system notifies the Student that this function is currently not available.
2. The system rejects the request.
3. The use case ends.

**A2: Invalid Name or Student ID**  
At **{Display Inquiry Result}** if the entered name or student ID is invalid,  
1. The system informs the Student that the name or student ID is invalid.
2. The flow of events is resumed at  **{Enter Name or Student ID}**.

## Task Allocation
| Name       | Task ID | Task Description                                       |
| ---------- | ------- | ------------------------------------------------------ |
| Yoanna Lo  | 100     | Submit Team Registration Form                          |
| Yoanna Lo  | 110     | Set up Team Repo on GitHub                             |
| Winnie Lam | 120     | Task Allocation                                        |
| Ho         | 130     | Draw Class Diagram using [draw.io](http://draw.io/)    |
| Ho         | 140     | Draw Use Case Diagram using [draw.io](http://draw.io/) |
| Winnie Lam | 151     | Write Use case specification - table generating        |
| Yoanna Lo  | 152     | Write Use case specification - chart generating        |
| Yoanna Lo  | 200     | Scheduling meeting / Update project schedule           |
| Winnie Lam | 210     | Take Minutes                                           |
| Ho         | 220     | Illustrate Gantt Chart                                 |
| Winnie Lam | 230     | Illustrate Burndown Chart                              |
| Winnie Lam | 241     | Unit Testing Report (Input)                            |
| Ho         | 242     | Unit Testing Report (Process)                          |
| Yoanna Lo  | 243     | Unit Testing Report (Output)                           |
| Winnie Lam | 251     | Coverage Report (Input)                                |
| Ho         | 252     | Coverage Report (Process)                              |
| Yoanna Lo  | 253     | Coverage Report (Output)                               |
| Winnie Lam | 261     | Documentation with JavaDoc (Input)                     |
| Ho         | 262     | Documentation with JavaDoc (Process)                   |
| Yoanna Lo  | 263     | Documentation with JavaDoc (Output)                    |
| Winnie Lam | 271     | Software development ( Input )                         |
| Ho         | 272     | Software development ( Process )                       |
| Yoanna Lo  | 273     | Software development ( Output )                        |
