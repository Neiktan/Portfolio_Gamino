import javax.swing.*; //GUI components
import java.awt.*; //layout managers
import java.awt.event.ActionEvent; //button actions
import java.util.Collections; //for sorting and shuffling
import java.util.LinkedList; //data structure

/*
JTextField:  for user input.
JButton: for adding buttons.
JTextArea for displaying lists of numbers or results.
JFrame: main window frame.
JOptionPane: for dialog boxes used as warnings and errors.
JPanel: to organize components.
JScrollPane: to make text areas scrollable.
*/

public class Gamino_SLO5 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gamino_NumberList frame = new Gamino_NumberList(); //creating instance of our frame
            frame.setVisible(true); //to make the frame visible
        });
    }

    public static class Gamino_NumberList extends JFrame { //main frame class

        private JTextField inputField; //text field for user input
        private JButton addButton, sortButton, shuffleButton, reverseButton; //buttons for actions
        private JTextArea originalArea, sortedArea, shuffledArea, reversedArea; //text areas for displaying lists

        //Linked List (NOT array or ArrayList) and no duplicates
        private LinkedList<Integer> numbers;

        public Gamino_NumberList() {
            super("Data Structure"); //title of the frame

            numbers = new LinkedList<>(); //initialize linked list

            // ---------------------------------------------------------------------------------

            //space for input from the user
            inputField = new JTextField(12); //text field for the input
            addButton = new JButton("Add Number"); //button to add number

            //panel for input
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Enter a number: "));
            inputPanel.add(inputField); //adding text field to panel
            inputPanel.add(addButton); //adding button to panel

            // ----------------------------------------------------------------------------------

            //setting up buttons
            sortButton = new JButton("Sort");
            shuffleButton = new JButton("Shuffle");
            reverseButton = new JButton("Reverse");

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(sortButton);
            buttonPanel.add(shuffleButton);
            buttonPanel.add(reverseButton);

            // ----------------------------------------------------------------------------------

            //setting up text areas
            originalArea = new JTextArea(4, 30);
            originalArea.setEditable(false); //user cannot type in this area

            sortedArea = new JTextArea(4, 30);
            sortedArea.setEditable(false); //user cannot type in this area

            shuffledArea = new JTextArea(4, 30);
            shuffledArea.setEditable(false); //user cannot type in this area

            reversedArea = new JTextArea(4, 30);
            reversedArea.setEditable(false); //user cannot type in this area

            //display panel
            JPanel displayPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            displayPanel.add(makeLabeledPanel("Original numbers:", originalArea));
            displayPanel.add(makeLabeledPanel("Sorted numbers:", sortedArea));
            displayPanel.add(makeLabeledPanel("Shuffled numbers:", shuffledArea));
            displayPanel.add(makeLabeledPanel("Reversed numbers:", reversedArea));
            /*
            Adding all text areas to display panel with labels
            In the order of subtitle and text area

            EDIT:
            display area is now a grid layout with 4 rows and 1 column
            5 pixels of horizontal and vertical gaps
            for the white space is 4 rows and 30 columns
            */

            // ----------------------------------------------------------------------------------

            //organizing the main frame layout
            setLayout(new BorderLayout(10, 5)); //5 pixels of gaps in vertical and horizontal is 10
            add(inputPanel, BorderLayout.NORTH); //input
            add(displayPanel, BorderLayout.CENTER); //showing list
            add(buttonPanel, BorderLayout.SOUTH); //buttons
            /*
            input panel at the top of the program/display(NORTH)
            display panel at the center of the program/display(CENTER)
            button panel at the bottom of the program/display(SOUTH)
            */

            //---------------------------------------------------------------------------------------

            //Events
            //method called when addButton is clicked

            /*
            only happens when the user does not enter any number
            verify no duplicate numbers are added
                5 to 15 numbers only
            */
            addButton.addActionListener(this::handleAddNumber);
            //sortNumbers, sort numbers in ascending order
            sortButton.addActionListener(e -> sortNumbers());
            //shuffleNumbers, randomly shuffles the numbers
            shuffleButton.addActionListener(e -> shuffleNumbers());
            //reverseNumbers, reverses the original order of numbers from input
            reverseButton.addActionListener(e -> reverseNumbers());

            //---------------------------------------------------------------------------------------

            // Window settings
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit program when window is closed
            pack();
            setLocationRelativeTo(null); // center on screen
        }

        //===============================================================================================
        //!!!!!!!!!!!!!!!!!!!!! METHODS !!!!!!!!!!!!!!!!!!!

        //Method makeLabeledPanel
        //create the tile and scrollable text area (title, scrollable area)
        private JPanel makeLabeledPanel(String labelText, JTextArea area) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel(labelText), BorderLayout.NORTH); //label at the top
            panel.add(new JScrollPane(area), BorderLayout.CENTER); //making text area scrollable if the text exceeds visible area
            return panel;
        }

        //Method listToString
        //Convert list to a single string with numbers separated by spaces
        private String listToString(LinkedList<Integer> list) {
            StringBuilder sb = new StringBuilder();
            for (Integer n : list) { //for each number in the list
                sb.append(n).append(" "); //adding space between numbers
            }
            return sb.toString().trim();
        }

        //================================================================================================

        //Method handleAddNumber
        //warnings for invalid input
        private void handleAddNumber(ActionEvent e) {
            String text = inputField.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int value = Integer.parseInt(text); //no decimals

                //5–15 numbers. We stop adding after 15.
                if (numbers.size() >= 15) {
                    JOptionPane.showMessageDialog(this,
                            "You already entered 15 numbers (maximum).",
                            "Limit Reached",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //user cannot enter duplicate numbers
                if (numbers.contains(value)) {
                    JOptionPane.showMessageDialog(this,
                            "That number is already in the list. Duplicates are not allowed.",
                            "Duplicate Number",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                numbers.add(value); //store in linked list
                originalArea.setText(listToString(numbers));  //show original order
                inputField.setText(""); //clear input field after adding number
                inputField.requestFocus();

            } catch (NumberFormatException ex) { //if the user enters letters or captions (not numbers)
                JOptionPane.showMessageDialog(this,
                        "Please enter only numbers, not letters or signs.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE); 
            }
        }

        //--------------------------------------------------------------------------------------

        //Method checkEnoughNumbers
        private boolean checkEnoughNumbers() {
            if (numbers.size() < 5) { //if less than 5 numbers it will show a warning message
                JOptionPane.showMessageDialog(this,
                        "Please enter at least 5 numbers (15 max.) before using Sort, Shuffle, or Reverse.",
                        "Not Enough Numbers",
                        JOptionPane.WARNING_MESSAGE);
                return false; //making the input invalid
            }
            return true; //if 5 or more numbers, input is valid
        }

        //Method sortNumbers
        private void sortNumbers() {
            if (!checkEnoughNumbers()) return; //checking if there are enough numbers
            LinkedList<Integer> sorted = new LinkedList<>(numbers);
            Collections.sort(sorted); //sorting the list in ascending order
            sortedArea.setText(listToString(sorted));
        }

        //Method shuffleNumbers
        private void shuffleNumbers() {
            if (!checkEnoughNumbers()) return; //checking if there are enough numbers
            LinkedList<Integer> shuffled = new LinkedList<>(numbers);
            Collections.shuffle(shuffled); //randomly shuffles the list
            shuffledArea.setText(listToString(shuffled)); 
        }

        //Method reverseNumbers
        private void reverseNumbers() {
            if (!checkEnoughNumbers()) return; //checking if there are enough numbers
            LinkedList<Integer> reversed = new LinkedList<>(numbers);
            Collections.reverse(reversed); // reverses original order
            reversedArea.setText(listToString(reversed));
        }
    }
}