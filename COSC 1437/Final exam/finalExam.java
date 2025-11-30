import java.util.ArrayList; //to use dynamic arrays
import java.util.InputMismatchException; //handle input errors
import java.util.Scanner;

public class finalExam {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numCourses;

        System.out.print("Enter number of courses: "); //asking qty of courses for the loop
        numCourses = input.nextInt();
        input.nextLine();

        // Arrays of course info
        String[] courseNames = new String[numCourses];
        String[] courseSections = new String[numCourses];
        double[] courseAverages = new double[numCourses];

        // No infinite loop, just asking for the qty of courses that the user wants to put
        for (int i = 0; i < numCourses; i++) {
            String courseName;
            String courseSection;
            int numStudents;

            System.out.print("Enter course name: "); // Name of the course
            courseName = input.nextLine();
            System.out.print("Enter course section: "); //section number of the course
            courseSection = input.nextLine();

            courseNames[i] = courseName;
            courseSections[i] = courseSection;

            // ArrayLists of students' info
            ArrayList<String> studentNames = new ArrayList<>();
            ArrayList<String> studentIDs = new ArrayList<>();
            ArrayList<Double> studentAverages = new ArrayList<>();
            ArrayList<String> letterGrades = new ArrayList<>();
            ArrayList<Integer> extraCredits = new ArrayList<>();
            ArrayList<Double> scholarships = new ArrayList<>();

            //header for the course input
            System.out.println("\n------------ Entering data for " + courseNames[i] + " Section " + courseSections[i] + " ------------");
            System.out.print("Enter number of students: "); //asking ONLY for the qty of students for the loop
            numStudents = input.nextInt();
            input.nextLine();

            /*
            * .equalIgnoreCase no considered as hard break
            * break at not in infinite loop
            * loop of the qty of the students
            */
            for (int j = 0; j < numStudents; j++) {
                String studentName, studentID;
                int numQuizzes,  numExams,  numPrograms, numSLOs, extraCredit;
                double average;

                System.out.print("Enter student's full name (or type 'done' to finish): "); //asking for a name and last name
                studentName = input.nextLine();

                if (studentName.equalsIgnoreCase("done")) { //option to finished with the word "done"
                    break;
                }

                System.out.print("Enter student ID: "); //asking for the ID student
                studentID = input.nextLine();

                double quizAverage, examAverage, programAverage, sloAverage;

                System.out.print("How many quiz scores? "); //asking qty of quizzes to input the scores in it
                numQuizzes = input.nextInt();
                input.nextLine();
                quizAverage = scoresAndAverage(input, numQuizzes, "Quiz");

                System.out.print("How many exam scores? "); //asking qty of exams to input the scores in it
                numExams = input.nextInt();
                input.nextLine();
                examAverage = scoresAndAverage(input, numExams, "Exam");

                System.out.print("How many program scores? "); //asking qty of programs to input the scores in it
                numPrograms = input.nextInt();
                input.nextLine();
                programAverage = scoresAndAverage(input, numPrograms, "Program");

                System.out.print("How many SLO scores? "); //asking qty of SLO to input the scores in it
                numSLOs = input.nextInt();
                input.nextLine();
                sloAverage = scoresAndAverage(input, numSLOs, "SLO");

                /*
                 * calculating the average of the scores
                 * all the scores are getting their own avg
                 */
                average = quizAverage * 0.15 + examAverage * 0.20 + programAverage * 0.30 + sloAverage * 0.35;

                //add extra credit and cap the final average at 100 if neccessary
                extraCredit = extraCredit(input);
                average += extraCredit;
                if (average > 100) {
                    average = 100;
                }

                //Adding to arrays
                //sotring all student information in corresponding lists
                studentNames.add(studentName);
                studentIDs.add(studentID);
                studentAverages.add(average);
                letterGrades.add(letterGrade(average));
                extraCredits.add(extraCredit);
                scholarships.add(scholarship(average));
            }

            // print header for student results
            System.out.println("\n===================== Results for " + courseName + " Section " + courseSection + " =====================");
            System.out.printf("%-20s %-10s %-10s %-10s %-10s %-10s\n", "Name", "ID", "Average", "Grade", "Extra", "Scholarship");

            double total = 0, highest = -1, lowest = 101;
            int above80 = 0, below70 = 0;
            double studentAverage;

            //loop over students to display and compute stats
            //name, id, average, grade, extra credits, scholarship
            for (int s = 0; s < studentNames.size(); s++) {
                studentAverage = studentAverages.get(s);
                total += studentAverage;

                if (studentAverage >= 80) {
                    above80++; //count of people that got more than 80
                }
                if (studentAverage < 70) {
                    below70++; //count of people that got less than 70
                }
                if (studentAverage > highest) {
                    highest = studentAverage; //update the highest score if current is greater
                }
                if (studentAverage < lowest) {
                    lowest = studentAverage; //update the lowest score if current is lower
                }

                // Print individual student data
                System.out.printf("%-20s %-10s %-10.2f %-10s %-10d $%-10.2f\n",
                        studentNames.get(s), //student's name
                        studentIDs.get(s), //student's ID
                        studentAverage, //average with 2 decimals !!!
                        letterGrades.get(s), //letter grade
                        extraCredits.get(s), //extra credit
                        scholarships.get(s)); //scholarship amount based on their grade
            }

            //Calculate course average only if there are students entered
            double courseAverage;
            courseAverage = studentAverages.size() > 0 ? total / studentAverages.size() : 0;
            courseAverages[i] = courseAverage;

            // Displaying summary stats for this course
            System.out.printf("\nCourse Average: %.2f\n", courseAverage); //COURSE AVERAGE !!!
            System.out.println("Students above 80: " + above80); //STUDENTS ABOVE 80
            System.out.println("Students below 70: " + below70); //STUDENTS BELOW 70
            System.out.printf("Highest Average: %.2f\n", highest); //HIGHEST AVERAGE OF THE CLASS
            System.out.printf("Lowest Average: %.2f\n", lowest); //LOWES AVERAGE OF THE CLASS

            // Displaying scholarship recipients
            System.out.println("\nScholarship Recipients:");
            for (int s = 0; s < studentNames.size(); s++) {
                if (scholarships.get(s) > 0) {
                    System.out.printf("%s - Average: %.2f - Scholarship: $%.2f\n",
                            studentNames.get(s),
                            studentAverages.get(s),
                            scholarships.get(s));
                }
            }

            System.out.println("============================================================\n");
        }

        sortCourses(courseNames, courseAverages);
        System.out.println("============== Sorted Course Averages ==============");
        for (int i = 0; i < courseNames.length; i++) {
            System.out.printf("%s: %.2f\n", courseNames[i], courseAverages[i]);
        }

        // Close scanner
        input.close();
    }

    // METHODS!!!

    /*
     * asking and getting scores and average for each section
     * no while loop
     * getting second chance to put it again and make it a valid input
     */
    public static double scoresAndAverage(Scanner input, int count, String category) {
        double total = 0;
        double score;
        int validScores = 0;

        for (int i = 0; i < count; i++) {
            score = -1;
            boolean validInput = false;

            //first chance
            System.out.print("Enter " + category + " score #" + (i + 1) + " (0-100): ");
            try {
                score = input.nextDouble();
                input.nextLine();  // Clear the buffer
                if (score >= 0 && score <= 100) { //veryfing if is valid
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Skipping this score.");
                input.nextLine();  // Clear the invalid input
            }

            //second chance
            if (!validInput) {
                System.out.println("Invalid score range. You have 1 attempt left.");
                System.out.print("Enter " + category + " score #" + (i + 1) + " again (0-100): ");
                try {
                    score = input.nextDouble();
                    input.nextLine();  // Clear the buffer
                    if (score >= 0 && score <= 100) {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input again. Skipping this score.");
                    input.nextLine();  // Clear the invalid input
                }
            }

            if (validInput) {
                total += score;
                validScores++;
            } else {
                System.out.println("Invalid score. Skipping this score.");
            }
        }

        //only add score if it is within valid range and input is correct
        if (validScores > 0) {
            return total / validScores;
        } else {
            return 0;
        }
    }

    //method extra credits
    //now without while
    public static int extraCredit(Scanner input) {
        int credit = 0;

        try {
            System.out.print("Enter extra credit (0-5): "); //asking to give extra credits
            credit = input.nextInt();
            input.nextLine();

            if (credit < 0 || credit > 5) { //verifying if it is only between 0 or 5
                System.out.println("Invalid range. Extra credit set to 0.");
                credit = 0;
            }
        } catch (InputMismatchException e) {  //********** */
            System.out.println("Invalid input. Extra credit set to 0.");
            input.nextLine();
        }

        return credit;
    }

    // method letterGrade
    // method to get letter grade by using switch method
    public static String letterGrade(double average) {
        int gradeRange;

        gradeRange = (int) average / 10;

        switch (gradeRange) {
            case 10:
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            case 6:
                return "D";
            default:
                return "F";
        }
    }

    // method scholarship
    // should be the grade more than 85
    // gives the amount of money according to the grade
    public static double scholarship(double average) {
        if (average >= 95) {
            return 750.00;
        } else if (average >= 90) {
            return 500.00;
        } else if (average >= 85) {
            return 250.00;
        }
        return 0.00;
    }

    // Mehtod sortCourses
    // sort courses and their averages in descending order
    public static void sortCourses(String[] names, double[] averages) {
        String tempName;
        double tempAvg;

        for (int i = 0; i < averages.length - 1; i++) {
            for (int j = i + 1; j < averages.length; j++) {
                if (averages[i] < averages[j]) {

                    //swap averages
                    tempAvg = averages[i];
                    averages[i] = averages[j];
                    averages[j] = tempAvg;

                    //swap course name accordingly
                    tempName = names[i]; 
                    names[i] = names[j];
                    names[j] = tempName;
                }
            }
        }
    }
}