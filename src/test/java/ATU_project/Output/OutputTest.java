package ATU_project.Output;

import static org.junit.Assert.*;

import ATU_project.Student;
import ATU_project.Team;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Output. Test cases written using JUnit4.
 * @author Yoanna Lo
 */
public class OutputTest {
    Team team1;
    Team team2;
    Team team3;
    Team team4;
    Team team5;
    Team[] teamList;

    /**
     * Fixture method that's called before every test case. Set up the team list for creating the Output instance.
     * @throws Exception when failing to create the team list.
     */
    @Before
    public void setUp() throws Exception {
        // team1: avg_k1 = 50, avg_k2 = 30
        team1 = new Team(new Student("Apple", 12345678, "A@email.hk", 20, 30, true, false, true, ""),
                new Student("Ben", 23456789, "B@email.hk", 80, 30, true, false, false, ""),
                new Student("Cat", 34567891, "C@email.hk", 50, 30, false, true, false, ""), 1);
        // team2: avg_k1 = 90, avg_k2 = 50
        team2 = new Team(new Student("Dog", 45678912, "D@email.hk", 90, 50, true, false, true, ""),
                new Student("Egg", 56789123, "E@email.hk", 90, 50, true, false, false, ""),
                new Student("Fish", 67891234, "F@email.hk", 90, 50, false, true, false, ""), 2);
        // team3: avg_k1 = 20, avg_k2 = 30
        team3 = new Team(new Student("Google", 78912345, "G@email.hk", 20, 30, true, false, true, ""),
                new Student("Hon", 89123456, "H@email.hk", 20, 30, true, false, false, ""),
                new Student("Ian", 91234567, "I@email.hk", 20, 30, false, true, false, ""), 3);
        // team4: avg_k1 = 50, avg_k2 = 40
        team4 = new Team(new Student("Joe", 11001234, "J@email.hk", 50, 50, true, false, true, ""),
                new Student("Ken", 12001234, "K@email.hk", 50, 40, true, false, false, ""),
                new Student("Mal", 15001243, "M@email.hk", 50, 30, false, true, false, ""), 4);
        // team5: avg_k1 = 50, avg_k2 = 30
        team5 = new Team(new Student("Nat", 24894840, "N@email.hk", 70, 20, true, false, true, ""),
                new Student("Omen", 23587692, "O@email.hk", 50, 40, true, false, false, ""),
                new Student("Papyrus", 27884900, "P@email.hk", 30, 30, false, true, false, ""),
                new Student("Queen", 24802890, "Q@email.hk", 50, 30, false, true, false, ""), 5);
        teamList = new Team[] {team1, team2, team3, team4, team5};
    }

    /**
     * Test whether the sorted team list obtained from output is sorted according to the following rules:
     * <ol>
     * <li> Sort according to avg_k1 in descending order. </li>
     * <li> If both avg_k1 is the same, then sort according to avg_k2.</li>
     * </ol>
     */
    @Test
    public void getSortedTeamList() {
        Team[] expected = new Team[]{team2, team4, team1, team5, team3};
        assertArrayEquals(expected, new Output(teamList).getSortedTeamList());
    }

    /**
     * Test whether team can return true when the target student, who is team leader, exists on that team.
     */
    @Test
    public void containWithExistLeaderStudent(){
        assertTrue(teamList[0].contain("Apple", 12345678));
    }

    /**
     * Test whether team can return true when the target student, who is team member B, exists on that team.
     */
    @Test
    public void containWithExistMemBStudent(){
        assertTrue(teamList[0].contain("Ben", 23456789));
    }

    /**
     * Test whether team can return true when the target student, who is team member C, exists on that team.
     */
    @Test
    public void containWithExistMemCStudent(){
        assertTrue(teamList[0].contain("Cat", 34567891));
    }

    /**
     * Test whether team can return true when the target student, who is team member D, exists on that team.
     */
    @Test
    public void containWithExistMemDStudent(){
        assertTrue(teamList[4].contain("Queen", 24802890));
    }

    /**
     * Test whether team can return false when the target student doesn't exist on that team.
     */
    @Test
    public void containWithNonExistStudent(){
        assertFalse(teamList[0].contain("App", 12345678));
    }
}