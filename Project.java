import java.awt.*;
import java.util.Scanner;

public class Project {

    // Necessary to enable input from keyboard
    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String name;
        System.out.println("Hello, and welcome to Campus Explorer. What's your name?");

        name = keyboard.nextLine();

        System.out.println("Hello, " + name + ". Let's design you an avatar!");

        // Asks the user if they want to customize their avatar or not
        boolean custom = custom();

        // Avatar creation
        Avatar you;
        if (custom) {
            System.out.println("Welcome to the customization menu.");
            // Opens customization menu
            you = create(name);
        } else {
            System.out.println("No problem!");
            // Creates the Avatar with the provided name and randomly-generated values for other instance variables
            you = new Avatar(name);
        }

        System.out.println("Here you are! Check out your avatar in the StdDraw window.");

        you.draw(0.5, 0.7, 0.5);

        // Pauses for five seconds
        StdDraw.pause(5000);

        // Gameplay begins

        // Variables to track if each location has been visited
        boolean[] visited = new boolean[6];
        // Location key:
        // visited[0] = if Frost has been visited
        // visited[1] = if Val has been visited
        // visited[2] = if Keefe has been visited
        // visited[3] = if the Science Center has been visited
        // visited[4] = if Memorial Hill has been visited
        // visited[5] = if J-Chap has been visited

        // Determines which location to visited first
        int start = location();

        System.out.print("Welcome to Amherst College, " + name + ". Behind you is ");

        if (start == 0) {
            System.out.println("Frost Library, or Frost for short.");
            System.out.println("Fun fact! Frost is home to Amherst's Archives & Special Collections,\nwhich holds many of Emily Dickinson's original poems, manuscripts, and letters.");
            visited[0] = true;
            StdDraw.clear();
            frost(0.5);
            you.draw(0.2, 0.22, 0.25);

            // Pauses for two seconds
            StdDraw.pause(2000);

            // Enables animation
            StdDraw.enableDoubleBuffering();

            frostChoice(you, visited);
        } else if (start == 1) {
            System.out.println("Valentine Dining Hall, also known as Val.");
            System.out.println("Fun fact! In addition to the regular dining options, Val sells birthday cakes.");
            visited[1] = true;
            StdDraw.clear();
            val(0.5);
            you.draw(0.2, 0.22, 0.25);

            // Pauses for two seconds
            StdDraw.pause(2000);

            // Enables animation
            StdDraw.enableDoubleBuffering();

            valChoice(you, visited);
        } else if (start == 2) {
            System.out.println("Keefe Campus Center, also called Keefe\n--not to be confused with Keefe Health Center or the Keefe Science Library.");
            System.out.println("Fun fact! There's a movie theater in the Keefe basement.");
            visited[2] = true;
            StdDraw.clear();
            keefe(0.5);
            you.draw(0.2, 0.22, 0.25);

            // Pauses for two seconds
            StdDraw.pause(2000);

            // Enables animation
            StdDraw.enableDoubleBuffering();

            keefeChoice(you, visited);
        } else if (start == 3) {
            System.out.println("the Science Center.");
            System.out.println("Fun fact! There's a single-stall shower in the Science Center to make it easier\nfor faculty and staff to commute by bike.");
            visited[3] = true;
            StdDraw.clear();
            scce(0.5);
            you.draw(0.2, 0.22, 0.25);

            // Pauses for two seconds
            StdDraw.pause(2000);

            // Enables animation
            StdDraw.enableDoubleBuffering();

            scceChoice(you, visited);
        } else if (start == 4) {
            System.out.println("Memorial Hill, named for the\nWar Memorial built at its top.");
            System.out.println("Fun fact! At the base of Memorial Hill is the aptly named Memorial Field.");
            visited[4] = true;
            StdDraw.clear();
            mem(0.5);
            you.draw(0.2, 0.22, 0.25);

            // Pauses for two seconds
            StdDraw.pause(2000);

            // Enables animation
            StdDraw.enableDoubleBuffering();

            memChoice(you, visited);
        } else { // if start == 5
            System.out.println("Johnson Chapel, or J-Chap for short.");
            System.out.println("Fun fact! The entrance to J-Chap that most people use (on the First Year Quad)\nleads into the basement.");
            visited[5] = true;
            StdDraw.clear();
            jChap(0.5);
            you.draw(0.2, 0.22, 0.25);

            // Pauses for two seconds
            StdDraw.pause(2000);

            // Enables animation
            StdDraw.enableDoubleBuffering();

            jChapChoice(you, visited);
        }

        // The game will return here once all locations have been visited
        endGame(you);
    }

    // Allows the user to choose if they want to have control over their Avatar customization or not
    public static boolean custom() {
        int customInt;
        System.out.println("Would you like to customize your avatar?");
        System.out.println("0) Yes, of course!");
        System.out.println("1) No, do it for me.");
        customInt = keyboard.nextInt();

        if (customInt == 0) return true;

        if (customInt == 1) return false;

        System.err.println("I'm sorry, that's not a valid input.");
        return custom();
    }

    // Customization menu
    // User chooses various aspects of their Avatar's appearance and the Avatar is returned to main
    public static Avatar create(String name) {
        // Head shape
        int headType = head();

        // Head color
        int[] headColor = part("head");

        // Body shape
        int bodyType = body();

        // Body color
        int[] bodyColor = part("body");

        // Hat (yes/no)
        boolean hat;
        int hatInt;
        System.out.println("Should your avatar be wearing a hat?");
        System.out.println("0) Yes");
        System.out.println("1) No");
        hatInt = keyboard.nextInt();
        if (hatInt == 0) {
            hat = true;
        } else if (hatInt == 1) {
            hat = false;
        } else {
            // Invalid input
            // Sets hat to an arbitrary value to allow program to compile
            hat = false;

            // Prints error message, keeps giving chances
            while (hatInt != 0 && hatInt != 1) {
                System.err.println("I'm sorry, that's not a valid input.");

                System.out.println("Should your avatar be wearing a hat?");
                System.out.println("0) Yes");
                System.out.println("1) No");
                hatInt = keyboard.nextInt();

                if (hatInt == 0) {
                    hat = true;
                    break;
                } else if (hatInt == 1) {
                    hat = false;
                    break;
                } else {
                    // Another invalid input has been given; keeps giving chances
                    continue;
                }
            }
        }

        // Hat color
        int[] hatColor = new int[3];

        // If a hat has been requested, asks for and sets the appropriate color
        if (hat) hatColor = part("hat");

        // Creates and returns avatar
        Avatar you = new Avatar(name, headType, headColor, bodyType, bodyColor, hat, hatColor);
        return you;
    }

    // Prompts and sets head shape (using recursion if given an invalid input!)
    public static int head() {
        int headType;

        System.out.println("What type of head should your avatar have?");
        System.out.println("0) Circular");
        System.out.println("1) Square");
        System.out.println("2) Triangular");

        headType = keyboard.nextInt();

        // Returns if valid
        if (headType == 0 || headType == 1 || headType == 2) {
            return headType;
        }

        // If not valid, prints error statement and repeats (recursion!)
        System.err.println("I'm sorry, that's not a valid input.");
        return head();
    }

    // Obtains an integer indicating the shape of the avatar's body (using recursion!)
    public static int body() {
        int bodyType;

        System.out.println("What type of body should your avatar have?");
        System.out.println("0) Circular");
        System.out.println("1) Rectangular");
        System.out.println("2) Triangular");

        bodyType = keyboard.nextInt();

        // If bodyType is valid, returns
        if (bodyType == 0 || bodyType == 1 || bodyType == 2) return bodyType;

        // Validity check + another chance
        System.err.println("I'm sorry, that's not a valid input.");
        return body();
    }

    // Determines what color a part (head, body, hat) should be
    public static int[] part(String part) {
        int[] partColor = new int[3];

        System.out.println("What color should the avatar's " + part + " be?");
        System.out.println("0) Yellow");
        System.out.println("1) Purple");
        System.out.println("2) Brown");
        System.out.println("3) Other");
        System.out.println("4) Surprise me!");

        int partChoice = keyboard.nextInt();

        // Returns if valid
        if (partChoice == 0) {
            // Fills partChoice with RGB values for yellow
            partColor[0] = 255;
            partColor[1] = 255;
            partColor[2] = 100;
            return partColor;
        } else if (partChoice == 1) {
            // Fills partChoice with RGB values for purple
            partColor[0] = 180;
            partColor[1] = 52;
            partColor[2] = 209;
            return partColor;
        } else if (partChoice == 2) {
            // Fills partChoice with RGB values for brown
            partColor[0] = 161;
            partColor[1] = 98;
            partColor[2] = 0;
            return partColor;
        } else if (partChoice == 3) {
            // Creates a custom color
            System.out.println("Let's create a custom color for you!");
            // Red
            partColor[0] = color("red", part);

            // Green
            partColor[1] = color("green", part);

            // Blue
            partColor[2] = color("blue", part);

            return partColor;
        } else if (partChoice == 4) {
            // Creates a randomly-generated color
            System.out.println("Mixing up something special for you now!");

            // Randomizes rgb values
            partColor[0] = (int) (Math.random() * 256);
            partColor[1] = (int) (Math.random() * 256);
            partColor[2] = (int) (Math.random() * 256);

            return partColor;
        }

        // If we reach this part of the code, an invalid value for partChoice was given (neither 0, 1, 2, nor 3)
        System.err.println("I'm sorry, that wasn't a valid input.");
        return part(part);
    }

    // Secures an int in [0, 255] for use in an array of three ints that specifies a color in rgb (using recursion!)
    public static int color(String rgb, String part) {
        // An int to hold the value to be returned
        int ans;

        System.out.println("What should the " + rgb + " value (on an RGB display) of the " + part + " be?");
        System.out.println("This is an integer between 0 and 255, inclusive.");

        ans = keyboard.nextInt();

        // Returns if valid
        if (ans >= 0 && ans <= 255) return ans;

        // Error message, recur
        System.err.println("I'm sorry, that's not a valid input.");
        return color(rgb, part);
    }

    // Allows the user to pick a starting location (or have one randomly generated) and returns an integer indicating the location
    // Location key:
    // 0 = Frost
    // 1 = Val
    // 2 = Keefe
    // 3 = Science Center
    // 4 = Memorial Hill
    // 5 = J-Chap
    public static int location() {
        // An int to hold the value to be returned
        int ans;

        System.out.println("Where would you like to start exploring?");
        System.out.println("0) Frost Library\n1) Valentine Dining Hall\n2) Keefe Campus Center\n3) Science Center\n4) Memorial Hill\n5) Johnson Chapel\n6) Surprise me!");

        ans = keyboard.nextInt();

        // Returns if valid
        if (ans >= 0 && ans <= 5) return ans;
        // Returns random int in [0, 5] if requested
        if (ans == 6) return (int) (Math.random() * 6);

        // Error message, recur
        System.err.println("I'm sorry, that's not a valid input.");
        return location();
    }

    // Draws J-Chap
    // Takes one input, a double indicating the x-coordinate at which the drawing is centered
    public static void jChap(double x) {
        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.5, 0.2);

        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.5, 0.3);

        // Sets pen to brick red
        StdDraw.setPenColor(163, 22, 20);

        // Draws main section of J-Chap
        StdDraw.filledRectangle(x, 0.3, 0.3, 0.2);

        // Sets pen color to white
        StdDraw.setPenColor(StdDraw.WHITE);
        // Draws left-most column
        StdDraw.filledRectangle(x - 0.25, 0.3, 0.04, 0.2);

        // Column to the right of that
        StdDraw.filledRectangle(x - 0.09, 0.3, 0.04, 0.2);

        // Column to the right of that
        StdDraw.filledRectangle(x + 0.09, 0.3, 0.04, 0.2);

        // Draws right-most column
        StdDraw.filledRectangle(x + 0.25, 0.3, 0.04, 0.2);

        // Sets pen color to light grey (white but further away/in shadow)
        StdDraw.setPenColor(224, 224, 224);

        // Draws steeple
        StdDraw.filledRectangle(x, 0.8, 0.1, 0.15);

        // Draws the roof (bottom rectangle)
        StdDraw.filledRectangle(x, 0.55, 0.32, 0.05);

        // Sets pen color back to white
        StdDraw.setPenColor(StdDraw.WHITE);

        // Draws the roof (triangle)
        double[] roofX = new double[]{x - 0.4, x + 0.4, x};
        double[] roofY = new double[]{0.55, 0.55, 0.8};
        StdDraw.filledPolygon(roofX, roofY);

        // Sets pen color to concrete grey
        StdDraw.setPenColor(125, 125, 125);

        // Draws bottom of J-Chap
        StdDraw.filledRectangle(x, 0.1, 0.32, 0.02);

        // Sets pen color to dark grey
        StdDraw.setPenColor(80, 80, 80);

        // Draws clock on steeple
        StdDraw.filledCircle(x, 0.87, 0.05);

        // Location name
        StdDraw.setPenColor(0, 0, 0);
        Font font = new Font("Arial", Font.BOLD, 43);
        StdDraw.setFont(font);
        StdDraw.text(x - 0.3, 0.82, "Johnson");
        StdDraw.text(x + 0.28, 0.82, "Chapel");
    }

    // Draws Frost Library
    // Takes one input, a double x indicating the x-coordinate around which the drawing should be centered
    // Reference photo: https://midcenturymundane.files.wordpress.com/2014/06/img_7251.jpg
    public static void frost(double x) {
        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.5, 0.2);

        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.5, 0.3);

        // Light grey
        StdDraw.setPenColor(220, 220, 220);

        // Upper portion of roof
        StdDraw.filledRectangle(x, 0.65, 0.3, 0.08);

        // Lower portion of roof
        StdDraw.filledRectangle(x, 0.6, 0.4, 0.04);

        // Sets pen to dull brick red
        StdDraw.setPenColor(185, 57, 57);

        // Draws the main section of Frost
        StdDraw.filledRectangle(x, 0.4, 0.4, 0.2);

        // Draws south-side staircase
        StdDraw.filledRectangle(x + 0.15, 0.45, 0.07, 0.25);

        // Sets to same light grey as roof
        StdDraw.setPenColor(220, 220, 220);

        // Left (western) edge of Frost
        StdDraw.filledRectangle(x - 0.38, 0.42, 0.02, 0.22);

        // Right (eastern) edge of Frost
        StdDraw.filledRectangle(x + 0.38, 0.42, 0.02, 0.22);

        // Left (western) edge of staircase
        StdDraw.filledRectangle(x + 0.088, 0.46, 0.01, 0.26);

        // Right (eastern) edge of staircase
        StdDraw.filledRectangle(x + 0.212, 0.46, 0.01, 0.26);

        // Doors
        StdDraw.setPenColor(80, 80, 80);
        StdDraw.filledRectangle(x - 0.02, 0.25, 0.1, 0.05);

        // Window up the staircase
        StdDraw.filledRectangle(x + 0.15, 0.39, 0.012, 0.19);

        // Where the vertical window stops
        StdDraw.setPenColor(220, 220, 220);
        StdDraw.filledRectangle(x + 0.15, 0.58, 0.06, 0.005);

        // Awning over entryway
        StdDraw.filledRectangle(x - 0.03, 0.3, 0.12, 0.01);

        // Location name
        StdDraw.setPenColor(0, 0, 0);
        Font font = new Font("Arial", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(x, 0.82, "Frost Library");
    }

    // Draws Val centered at the provided x-coordinate
    public static void val(double x) {
        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.5, 0.2);

        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.5, 0.3);

        // Sets pen to dark roof/path grey
        StdDraw.setPenColor(80, 80, 80);

        // Path from the left
        double[] path1X = new double[]{x - 0.5, x - 0.4, x - 0.03, x - 0.13};
        double[] path1Y = new double[]{0, 0, 0.25, 0.25};
        StdDraw.filledPolygon(path1X, path1Y);

        // Path down the middle
        StdDraw.filledRectangle(x, 0.125, 0.03, 0.125);

        // Path from the right
        double[] path2X = new double[]{x + 0.5, x + 0.4, x + 0.03, x + 0.13};
        double[] path2Y = new double[]{0, 0, 0.25, 0.25};
        StdDraw.filledPolygon(path2X, path2Y);

        // Middle section of roof
        StdDraw.filledRectangle(x, 0.5, 0.2, 0.05);

        // Sets pen to cobblestone grey (where the paths meet)
        StdDraw.setPenColor(110, 110, 110);

        // Ellipse for the area at the door to Val (where the paths meet)
        StdDraw.filledEllipse(x, 0.27, 0.13, 0.07);

        // Sets pen to brick red
        StdDraw.setPenColor(163, 22, 20);

        // Draws main section of Val
        StdDraw.filledRectangle(x, 0.35, 0.35, 0.1);

        // Sets pen to lighter brick red
        StdDraw.setPenColor(173, 23, 21);

        // Draws left wing of Val as seen from the back
        StdDraw.filledRectangle(x - 0.25, 0.375, 0.125, 0.125);

        // Draws right wing of Val as seen from the back
        StdDraw.filledRectangle(x + 0.25, 0.375, 0.125, 0.125);

        // Draws balcony in white
        StdDraw.setPenColor(230, 230, 230);
        StdDraw.filledRectangle(x, 0.42, 0.125, 0.02);

        // Draws door
        StdDraw.filledRectangle(x, 0.33, 0.05, 0.08);

        // Draws window on door
        StdDraw.setPenColor(30, 30, 30);
        StdDraw.filledRectangle(x, 0.35, 0.02, 0.03);

        // Draws window panes
        StdDraw.setPenColor(230, 230, 230);
        StdDraw.setPenRadius(0.003);
        // Vertical
        StdDraw.line(x, 0.38, x, 0.32);
        // Horizontal
        StdDraw.line(x - 0.02, 0.35, x + 0.02, 0.35);

        // Sets pen to lighter roof grey
        StdDraw.setPenColor(100, 100, 100);

        // Left triangle section of roof
        double[] leftX = new double[]{x - 0.4, x - 0.1, x - 0.25};
        double[] roofY = new double[]{0.5, 0.5, 0.6};
        StdDraw.filledPolygon(leftX, roofY);

        // Right triangle section of roof
        double[] rightX = new double[]{x + 0.1, x + 0.4, x + 0.25};
        StdDraw.filledPolygon(rightX, roofY);

        // Window into the Front Room
        StdDraw.setPenColor(201, 196, 52);
        StdDraw.filledRectangle(x - 0.25, 0.35, 0.08, 0.05);
        // Window frame and panes
        StdDraw.setPenColor(230, 230, 230);
        StdDraw.rectangle(x - 0.25, 0.35, 0.08, 0.05);
        // Top line, leftmost line, and rightmost line are thickest
        StdDraw.setPenRadius(0.005);
        StdDraw.line(x - 0.3033, 0.4, x - 0.3033, 0.3);
        StdDraw.line(x - 0.33, 0.375, x - 0.17, 0.375);
        StdDraw.line(x - 0.1967, 0.4, x - 0.1967, 0.3);
        // Others are thinner
        // Horizontal
        StdDraw.setPenRadius(0.0025);
        StdDraw.line(x - 0.33, 0.35, x - 0.17, 0.35);
        StdDraw.line(x - 0.33, 0.325, x - 0.17, 0.325);
        // Vertical
        StdDraw.line(x - 0.25, 0.4, x - 0.25, 0.3);
        StdDraw.line(x - 0.2233, 0.4, x - 0.2233, 0.3);
        StdDraw.line(x - 0.2767, 0.4, x - 0.2767, 0.3);


        // Window into Lewis Sebring
        StdDraw.setPenColor(50, 50, 50);
        StdDraw.filledRectangle(x + 0.25, 0.35, 0.08, 0.05);
        // Window frame and panes
        StdDraw.setPenColor(230, 230, 230);
        StdDraw.rectangle(x + 0.25, 0.35, 0.08, 0.05);
        // Top line, leftmost line, and rightmost line are thickest
        StdDraw.setPenRadius(0.005);
        StdDraw.line(x + 0.3033, 0.4, x + 0.3033, 0.3);
        StdDraw.line(x + 0.1967, 0.4, x + 0.1967, 0.3);
        StdDraw.line(x + 0.33, 0.375, x + 0.17, 0.375);
        // Others are thinner
        // Horizontal
        StdDraw.setPenRadius(0.0025);
        StdDraw.line(x + 0.33, 0.35, x + 0.17, 0.35);
        StdDraw.line(x + 0.33, 0.325, x + 0.17, 0.325);
        // Vertical
        StdDraw.line(x + 0.25, 0.4, x + 0.25, 0.3);
        StdDraw.line(x + 0.2233, 0.4, x + 0.2233, 0.3);
        StdDraw.line(x + 0.2767, 0.4, x + 0.2767, 0.3);

        // Location name
        StdDraw.setPenColor(0, 0, 0);
        Font font = new Font("Arial", Font.BOLD, 45);
        StdDraw.setFont(font);
        StdDraw.text(x, 0.75, "Valentine Dining Hall");
    }

    // Draws Keefe Campus Center centered around a given x-coordinate
    public static void keefe(double x) {
        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.5, 0.2);

        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.5, 0.3);

        // Sets pen color to pale/desaturated yellow
        StdDraw.setPenColor(245, 241, 144);

        // Draws back section of Keefe
        StdDraw.filledRectangle(x, 0.4, 0.35, 0.2);

        // Sets pen color to slightly lighter yellow
        StdDraw.setPenColor(250, 247, 167);

        // Draws front section of Keefe
        StdDraw.filledRectangle(x, 0.38, 0.39, 0.18);

        // Sets pen color to grey for the roof
        StdDraw.setPenColor(145, 145, 145);

        // Draws the roof
        double[] roof1X = new double[]{x - 0.37, x + 0.37, x + 0.35, x - 0.35};
        double[] roof1Y = new double[]{0.6, 0.6, 0.7, 0.7};
        StdDraw.filledPolygon(roof1X, roof1Y);

        // Draws the edging on top of front section
        // Left grey
        StdDraw.filledRectangle(x - 0.24, 0.56, 0.16, 0.005);
        // Right grey
        StdDraw.filledRectangle(x + 0.24, 0.56, 0.16, 0.005);
        StdDraw.setPenColor(235, 235, 235);
        // Left white
        StdDraw.filledRectangle(x - 0.24, 0.55, 0.16, 0.005);
        // Right white
        StdDraw.filledRectangle(x + 0.24, 0.55, 0.16, 0.005);

        // Sets pen color to pale yellow
        StdDraw.setPenColor(250, 247, 167);

        // Draws vertical front section of Keefe
        StdDraw.filledRectangle(x, 0.42, 0.1, 0.22);

        // Sets pen color to lighter roof grey
        StdDraw.setPenColor(155, 155, 155);

        // Draws small front roof triangle
        double[] roof2X = new double[]{x - 0.1, x + 0.1, x};
        double[] roof2Y = new double[]{0.64, 0.64, 0.69};
        StdDraw.filledPolygon(roof2X, roof2Y);

        // Draws circle window
        StdDraw.setPenColor(100, 100, 100);
        StdDraw.filledCircle(x, 0.55, 0.035);

        // Draws light blue rectangle window below circle window
        StdDraw.setPenColor(213, 230, 229);
        StdDraw.filledRectangle(x, 0.43, 0.035, 0.06);

        // Outlines window panes on light blue window
        StdDraw.setPenColor(10, 10, 10);
        StdDraw.setPenRadius(0.0035);
        StdDraw.rectangle(x, 0.43, 0.035, 0.06);
        // Vertical
        StdDraw.line(x, 0.49, x, 0.37);
        // Top horizontal
        StdDraw.line(x - 0.035, 0.46, x + 0.035, 0.46);
        // Middle horizontal
        StdDraw.line(x - 0.035, 0.43, x + 0.035, 0.43);
        // Bottom horizontal
        StdDraw.line(x - 0.035, 0.4, x + 0.035, 0.4);

        // Draws entryway
        StdDraw.setPenColor(80, 80, 80);
        StdDraw.filledRectangle(x, 0.25, 0.037, 0.051);

        // Location name
        StdDraw.setPenColor(0, 0, 0);
        Font font = new Font("Arial", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text(x, 0.8, "Keefe Campus Center");
    }

    // Draws the Science Center centered around a given x-coordinate
    // Reference photo: https://www.amherst.edu/system/files/styles/original/private/media/6-67-1-New-Science-Center_850px.jpg?itok=E-q5aHHQ&__=1635951290
    public static void scce(double x) {
        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.5, 0.2);

        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.5, 0.3);

        // Draws bulk of the building in dusty grey-green (windows you can't see through)
        StdDraw.setPenColor(137, 163, 146);
        StdDraw.filledRectangle(x, 0.425, 0.4, 0.125);

        // Sets pen to rusty red-brown
        StdDraw.setPenColor(150, 59, 14);

        // Draws C wing (on the left)
        StdDraw.filledRectangle(x - 0.32, 0.375, 0.1, 0.075);

        // Draws D wing (in the middle)
        StdDraw.filledRectangle(x - 0.07, 0.4, 0.1, 0.1);

        // Draws E wing (on the right)
        StdDraw.filledRectangle(x + 0.32, 0.4, 0.1, 0.1);
        // With greenhouse
        StdDraw.setPenColor(131, 168, 168);
        StdDraw.filledRectangle(x + 0.36, 0.47, 0.06, 0.03);

        // Draws a bit of the garden on top of C wing
        StdDraw.setPenColor(44, 120, 31);
        StdDraw.filledRectangle(x - 0.32, 0.454, 0.09, 0.004);

        // Draws solar panels on top of the building
        for (double i = x - 0.402; i < x + 0.39; i += 0.03) {
            // White part (supports)
            StdDraw.setPenColor(230, 230, 230);
            StdDraw.setPenRadius(0.004);
            StdDraw.line(i, 0.585, i + 0.02, 0.57);

            // Dark blue part (panels)
            StdDraw.setPenColor(2, 9, 82);
            StdDraw.setPenRadius(0.002);
            StdDraw.line(i + 0.004, 0.585, i + 0.024, 0.57);
        }

        // Draws roof
        StdDraw.setPenColor(230, 230, 230);
        StdDraw.filledRectangle(x, 0.56, 0.43, 0.01);

        // Draws main entrance
        // Doors in the shade under the awning
        StdDraw.setPenColor(87, 87, 87);
        StdDraw.filledRectangle(x + 0.125, 0.335, 0.07, 0.035);
        // Awning
        StdDraw.setPenColor(230, 230, 230);
        StdDraw.filledRectangle(x + 0.125, 0.37, 0.08, 0.004);

        // Location name
        StdDraw.setPenColor(0, 0, 0);
        Font font = new Font("Arial", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text(x, 0.8, "Science Center");
    }

    // Draws Memorial Hill
    // Takes one input, a double x indicating the x-coordinate around which the drawing should be centered
    public static void mem(double x) {
        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.5, 0.3);

        // Sets pen to dark forest green
        StdDraw.setPenColor(7, 48, 13);

        // Fills the horizon with large trees
        for (double i = x - 0.61; i < x + 0.6; i += 0.03) {
            double[] treeX = new double[]{i - 0.01, i + 0.05, i + 0.02};
            double[] treeY = new double[]{0.4, 0.4, 0.55};
            StdDraw.filledPolygon(treeX, treeY);
            i += 0.01;
        }

        // Sets pen to medium forest green
        StdDraw.setPenColor(21, 77, 29);

        // Fills the horizon with medium-sized trees
        for (double i = x - 0.62; i < x + 0.62; i += 0.03) {
            double[] treeX = new double[]{i, i + 0.03, i + 0.015};
            double[] treeY = new double[]{0.4, 0.4, 0.5};
            StdDraw.filledPolygon(treeX, treeY);
            i += 0.02;
        }

        // Sets pen to lighter forest green
        StdDraw.setPenColor(38, 102, 47);

        // Fills the horizon with small trees
        for (double i = x - 0.642; i < x + 0.65; i += 0.01) {
            double[] treeX = new double[]{i, i + 0.01, i + 0.005};
            double[] treeY = new double[]{0.4, 0.4, 0.45};
            StdDraw.filledPolygon(treeX, treeY);
            i += 0.008;
        }

        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.5, 0.2);

        // Light grey (bottom and edges of memorial)
        StdDraw.setPenColor(180, 180, 180);

        // Ellipse for the bottom of the War Memorial
        StdDraw.filledEllipse(x, 0.25, 0.35, 0.1);

        // Left edge of memorial
        StdDraw.filledRectangle(x - 0.3, 0.275, 0.05, 0.03);

        // Right edge of memorial
        StdDraw.filledRectangle(x + 0.3, 0.275, 0.05, 0.03);

        // Even lighter grey (top of memorial)
        StdDraw.setPenColor(220, 220, 220);

        // Ellipse for the top of the War Memorial
        StdDraw.filledEllipse(x, 0.3, 0.35, 0.1);

        // Location name
        StdDraw.setPenColor(0, 0, 0);
        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(x, 0.7, "Memorial Hill");
    }

    // Draws the sky and ground with no background (for transitioning between locations)
    // Takes one input, a double x indicating around which x-coordinate the drawing should be centered
    public static void transition(double x) {
        // Sets pen to grass green
        StdDraw.setPenColor(44, 138, 58);

        // Draws the ground
        StdDraw.filledRectangle(x, 0.2, 0.55, 0.2);

        // Sets pen to sky blue
        StdDraw.setPenColor(66, 154, 201);

        // Draws the sky
        StdDraw.filledRectangle(x, 0.7, 0.55, 0.3);
    }

    // At J-Chap, the user chooses between going to Frost or Memorial Hill
    public static void jChapChoice(Avatar you, boolean[] visited) {
        // Checks if game should end now; returns if it should
        if (done(visited)) return;

        int frostMem;
        System.out.println("Would you like to go:");
        // Choose to go right or left
        System.out.println("0) northwards towards Frost Library or");
        System.out.println("1) southwards towards Memorial Hill?");
        frostMem = keyboard.nextInt();

        // If invalid input, prints an error message and repeats the prompt until a valid input is given
        while (frostMem != 0 && frostMem != 1) {
            System.err.println("I'm sorry, that's not a valid input.");

            System.out.println("Would you like to go:");
            System.out.println("0) northwards towards Frost Library; or");
            System.out.println("1) southwards towards Memorial Hill?");
            frostMem = keyboard.nextInt();
        }

        if (frostMem == 0) {
            // Goes to Frost
            move(you, 5, 0);

            // Gives a fun fact if Frost has not yet been visited
            if (visited[0] == false)
                System.out.println("Fun fact! Frost is home to Amherst's Archives & Special Collections,\nwhich holds many of Emily Dickinson's original poems, manuscripts, and letters.");

            // Frost has been visited
            visited[0] = true;

            // Once at Frost, the user chooses a new direction to go in
            frostChoice(you, visited);
        } else {
            // if (frostMem == 1)
            // Goes to Memorial Hill
            move(you, 5, 4);

            // Gives a fun fact if Memorial Hill has not yet been visited
            if (visited[4] == false)
                System.out.println("Fun fact! At the base of Memorial Hill is the aptly named Memorial Field.");

            // Memorial Hill has been visited
            visited[4] = true;

            // Once at Memorial Hill, the user chooses a new direction to go in
            memChoice(you, visited);
        }
    }

    // At Frost, the user chooses between going to JChap or Val
    public static void frostChoice(Avatar you, boolean[] visited) {
        // Checks if game should end now; returns if it should
        if (done(visited)) return;

        int valJChap;
        System.out.println("Would you like to go:");
        // Choose to go right or left
        System.out.println("0) northeast towards Valentine Dining Hall or");
        System.out.println("1) southwards towards Johnson Chapel?");
        valJChap = keyboard.nextInt();

        // If invalid input, prints an error message and repeats the prompt until a valid input is given
        while (valJChap != 0 && valJChap != 1) {
            System.err.println("I'm sorry, that's not a valid input.");

            System.out.println("Would you like to go:");
            // Choose to go right or left
            System.out.println("0) northeast towards Valentine Dining Hall or");
            System.out.println("1) southwards towards Johnson Chapel?");
            valJChap = keyboard.nextInt();
        }

        if (valJChap == 0) {
            // Goes to Val
            move(you, 0, 1);

            // Gives a fun fact if Val has not yet been visited
            if (visited[1] == false)
                System.out.println("Fun fact! In addition to the regular dining options, Val sells birthday cakes.");

            // Val has been visited
            visited[1] = true;

            valChoice(you, visited);
        } else {
            //if (valJChap == 1)

            // Goes to J-Chap
            move(you, 0, 5);

            // Gives a fun fact if J-Chap has not yet been visited
            if (visited[5] == false)
                System.out.println("Fun fact! The entrance to J-Chap that most people use (on the First Year Quad)\nleads into the basement.");

            // J-Chap has been visited
            visited[5] = true;

            jChapChoice(you, visited);
        }
    }

    // At Val, the user chooses between going to Frost or Keefe
    public static void valChoice(Avatar you, boolean[] visited) {
        // Checks if game should end now; returns if it should
        if (done(visited)) return;

        int frostKeefe;
        System.out.println("Would you like to go:");
        // Choose to go right or left
        System.out.println("0) southwards towards Keefe Campus Center or");
        System.out.println("1) southwest towards Frost Library?");
        frostKeefe = keyboard.nextInt();

        // If invalid input, prints an error message and repeats the prompt until a valid input is given
        while (frostKeefe != 0 && frostKeefe != 1) {
            System.err.println("I'm sorry, that's not a valid input.");

            System.out.println("Would you like to go:");
            // Choose to go right or left
            System.out.println("0) southwards towards Keefe Campus Center or");
            System.out.println("1) southwest towards Frost Library?");
            frostKeefe = keyboard.nextInt();
        }

        if (frostKeefe == 0) {
            // Goes to Keefe
            move(you, 1, 2);

            // Gives a fun fact if Keefe has not yet been visited
            if (visited[2] == false) System.out.println("Fun fact! There's a movie theater in the Keefe basement.");

            // Keefe has been visited
            visited[2] = true;

            // Once at Keefe, the user chooses a new direction to go in
            keefeChoice(you, visited);
        } else {
            // if (frostKeefe == 1)
            // Goes to Frost
            move(you, 1, 0);

            // Gives a fun fact if Frost has not yet been visited
            if (visited[0] == false)
                System.out.println("Fun fact! Frost is home to Amherst's Archives & Special Collections,\nwhich holds many of Emily Dickinson's original poems, manuscripts, and letters.");

            // Frost has been visited
            visited[0] = true;

            frostChoice(you, visited);
        }
    }

    // At Keefe, the user chooses between going to Val or the Science Center
    public static void keefeChoice(Avatar you, boolean[] visited) {
        // Checks if game should end now; returns if it should
        if (done(visited)) return;

        int scceVal;
        System.out.println("Would you like to go:");
        // Choose to go right or left
        System.out.println("0) southeast towards the Science Center or");
        System.out.println("1) northwards towards Valentine Dining Hall?");
        scceVal = keyboard.nextInt();

        // If invalid input, prints an error message and repeats the prompt until a valid input is given
        while (scceVal != 0 && scceVal != 1) {
            System.err.println("I'm sorry, that's not a valid input.");

            System.out.println("Would you like to go:");
            // Choose to go right or left
            System.out.println("0) southeast towards the Science Center or");
            System.out.println("1) northwards towards Valentine Dining Hall?");
            scceVal = keyboard.nextInt();
        }

        if (scceVal == 0) {
            // Goes to the Science Center
            move(you, 2, 3);

            // Gives a fun fact if the Science Center has not yet been visited
            if (visited[3] == false)
                System.out.println("Fun fact! There's a single-stall shower in the Science Center to make it easier\nfor faculty and staff to commute by bike.");

            // The Science Center has been visited
            visited[3] = true;

            // Once at the Science Center, the user chooses a new direction to go in
            scceChoice(you, visited);
        } else {
            // if (scceVal == 1)
            // Goes to Val
            move(you, 2, 1);

            // Gives a fun fact if Val has not yet been visited
            if (visited[1] == false)
                System.out.println("Fun fact! In addition to the regular dining options, Val sells birthday cakes.");

            // Val has been visited
            visited[1] = true;

            // Once at Val, the user chooses a new direction to go in
            valChoice(you, visited);
        }
    }

    // At the Science Center, the user chooses between going to Memorial Hill or Keefe
    public static void scceChoice(Avatar you, boolean[] visited) {
        // Checks if game should end now; returns if it should
        if (done(visited)) return;

        int memKeefe;
        System.out.println("Would you like to go:");
        // Choose to go right or left
        System.out.println("0) west towards Memorial Hill or");
        System.out.println("1) northwest towards Keefe Campus Center?");
        memKeefe = keyboard.nextInt();

        // If invalid input, prints an error message and repeats the prompt until a valid input is given
        while (memKeefe != 0 && memKeefe != 1) {
            System.err.println("I'm sorry, that's not a valid input.");

            System.out.println("Would you like to go:");
            // Choose to go right or left
            System.out.println("0) west towards Memorial Hill or");
            System.out.println("1) northwest towards Keefe Campus Center?");
            memKeefe = keyboard.nextInt();
        }

        if (memKeefe == 0) {
            // Goes to Memorial Hill
            move(you, 3, 4);

            // Gives a fun fact if Memorial Hill has not yet been visited
            if (visited[4] == false)
                System.out.println("Fun fact! At the base of Memorial Hill is the aptly named Memorial Field.");

            // Memorial Hill has been visited
            visited[4] = true;

            // Once at Memorial Hill, the user chooses a new direction to go in
            memChoice(you, visited);
        } else {
            // if (memKeefe == 1)
            // Goes to Keefe
            move(you, 3, 2);

            // Gives a fun fact if Keefe has not yet been visited
            if (visited[2] == false) System.out.println("Fun fact! There's a movie theater in the Keefe basement.");

            // Keefe has been visited
            visited[2] = true;

            // Once at Keefe, the user chooses a new direction to go in
            keefeChoice(you, visited);
        }
    }

    // At Memorial Hill, the user chooses between going to J-Chap or the Science Center
    public static void memChoice(Avatar you, boolean[] visited) {
        // Checks if game should end now; returns if it should
        if (done(visited)) return;

        int jChapScce;
        System.out.println("Would you like to go:");
        // Choose to go right or left
        System.out.println("0) northwards towards Johnson Chapel or");
        System.out.println("1) east towards the Science Center?");
        jChapScce = keyboard.nextInt();

        // If invalid input, gives an error message and repeats the prompt until a valid input is given
        while (jChapScce != 0 && jChapScce != 1) {
            System.err.println("I'm sorry, that's not a valid input.");

            System.out.println("Would you like to go:");
            System.out.println("0) northwards towards Johnson Chapel or");
            System.out.println("1) east towards the Science Center?");
            jChapScce = keyboard.nextInt();
        }

        if (jChapScce == 0) {
            // Goes to J-Chap
            move(you, 4, 5);

            // Gives a fun fact if J-Chap has not yet been visited
            if (visited[5] == false)
                System.out.println("Fun fact! The entrance to J-Chap that most people use (on the First Year Quad)\nleads into the basement.");

            // J-Chap has been visited
            visited[5] = true;

            // Once at J-Chap, the user chooses a new direction to go in
            jChapChoice(you, visited);
        } else {
            // if (jChapScce == 1)
            // Goes to the Science Center
            move(you, 4, 3);

            // Gives a fun fact if the Science Center has not yet been visited
            if (visited[3] == false)
                System.out.println("Fun fact! There's a single-stall shower in the Science Center to make it easier\nfor faculty and staff to commute by bike.");

            // The Science Center has been visited
            visited[3] = true;

            // Once at the Science Center, the user chooses a new direction to go in
            scceChoice(you, visited);
        }
    }

    // Moves the user from one location (start) to an adjacent one (end)
    // Location key:
    // 0 = Frost
    // 1 = Val
    // 2 = Keefe
    // 3 = Science Center
    // 4 = Memorial Hill
    // 5 = J-Chap
    public static void move(Avatar you, int start, int end) {
        // If either start or end location is not in key, prints an error message and exits
        if (start > 5 || start < 0) {
            System.err.println("Error: Location " + start + " not in key.");
            System.exit(1);
        }
        if (end > 5 || end < 0) {
            System.err.println("Error: Location " + end + " not in key.");
            System.exit(1);
        }

        // If the start and end locations are not next to one another, prints an error message and exits
        if (start - end != 1 && start - end != -1 && !(start == 0 && end == 5) && !(start == 5 && end == 0)) {
            System.err.println("Error: Locations " + start + " and " + end + " are not next to one another.");
            System.exit(1);
        }

        // Array of doubles to store center coordinate of various backgrounds
        double[] x;
        if (start - end == -1 || (start == 5 && end == 0)) {
            // If moving "forwards" through the loop (ex. from Frost to Val, from J-Chap to Frost)
            x = new double[]{0.5, 1, 2};

            while (x[2] >= 0.5) {
                // Clears canvas and redraws all background elements
                StdDraw.clear();
                transition(x[1]);

                // Draws "start" location
                if (start == 0) {
                    frost(x[0]);
                } else if (start == 1) {
                    val(x[0]);
                } else if (start == 2) {
                    keefe(x[0]);
                } else if (start == 3) {
                    scce(x[0]);
                } else if (start == 4) {
                    mem(x[0]);
                } else {
                    // if (start == 5)
                    jChap(x[0]);
                }

                // Draws "end" location
                if (end == 0) {
                    frost(x[2]);
                } else if (end == 1) {
                    val(x[2]);
                } else if (end == 2) {
                    keefe(x[2]);
                } else if (end == 3) {
                    scce(x[2]);
                } else if (end == 4) {
                    mem(x[2]);
                } else {
                    // if (end == 5)
                    jChap(x[2]);
                }

                // Redraws avatar
                you.draw(0.2, 0.22, 0.25);

                // Pauses
                StdDraw.pause(35);

                // Shows drawing
                StdDraw.show();

                // Iterates x-values
                for (int i = 0; i < x.length; i++) {
                    x[i] -= 0.006;
                }
            }
        } else {
            // If moving "backwards" through the loop (ex. from Val to Frost, from Frost to J-Chap)
            x = new double[]{0.5, 0, -1};

            while (x[2] <= 0.5) {
                // Clears canvas and redraws all background elements
                StdDraw.clear();
                transition(x[1]);

                // Draws "start" location
                if (start == 0) {
                    frost(x[0]);
                } else if (start == 1) {
                    val(x[0]);
                } else if (start == 2) {
                    keefe(x[0]);
                } else if (start == 3) {
                    scce(x[0]);
                } else if (start == 4) {
                    mem(x[0]);
                } else {
                    // if (start == 5)
                    jChap(x[0]);
                }

                // Draws "end" location
                if (end == 0) {
                    frost(x[2]);
                } else if (end == 1) {
                    val(x[2]);
                } else if (end == 2) {
                    keefe(x[2]);
                } else if (end == 3) {
                    scce(x[2]);
                } else if (end == 4) {
                    mem(x[2]);
                } else {
                    // if (end == 5)
                    jChap(x[2]);
                }

                // Redraws avatar
                you.draw(0.2, 0.22, 0.25);

                // Pauses
                StdDraw.pause(35);

                // Shows drawing
                StdDraw.show();

                // Iterates x-values
                for (int i = 0; i < x.length; i++) {
                    x[i] += 0.006;
                }
            }
        }
    }

    // Checks if all locations have been visited (if all booleans in the array visited are true)
    // Returns true if all locations have been visited and false if any location has not been visited
    public static boolean done(boolean[] visited) {
        boolean done = true;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) done = false;
        }
        return done;
    }

    // End game screen
    public static void endGame(Avatar you) {
        // Pauses for 7 seconds with drawing as-is
        StdDraw.pause(7000);

        StdDraw.clear();

        // White square filling whole screen
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledSquare(0.5, 0.5, 0.5);

        // Sets color to official mammoth purple
        StdDraw.setPenColor(71, 10, 119);

        // Makes pen thicker
        StdDraw.setPenRadius(0.03);

        // Rectangle in which text will be displayed
        StdDraw.rectangle(0.5, 0.6, 0.4, 0.2);

        // Text in rectangle
        Font bigFont = new Font("Times New Roman", Font.PLAIN, 45);
        StdDraw.setFont(bigFont);
        StdDraw.text(0.5, 0.65, "Game Over");

        // More text in rectangle
        Font smallFont = new Font("Times New Roman", Font.PLAIN, 20);
        StdDraw.setFont(smallFont);
        StdDraw.text(0.5, 0.55, "Congratulations! You have visited all of");
        StdDraw.text(0.5, 0.5, "Amherst's most important sights.");

        // Same text printed in text interface through which the game has been played
        System.out.println();
        System.out.println("Congratulations! You have visited all of Amherst's most important sights.");

        System.out.println("Thanks for playing!");

        // Draws avatar in the corner
        you.draw(0.2, 0.22, 0.25);

        // Draws mammoth in the opposite corner
        mammoth(0.5, -0.1, 0.5);

        StdDraw.show();
    }

    // Draws a mammoth on the endGame screen
    // Code taken from my Game of Life project
    public static void mammoth(double x, double y, double scale) {

        // x and y are the coordinates of the bottom left corner of the mammoth's box.
        // scale is the width/height of the mammoth's box.

        // Sets color to official mammoth purple
        StdDraw.setPenColor(71, 10, 119);

        // upper back
        double[] tri1X = new double[]{x + 0.28 * scale, x + 0.46 * scale, x + 0.65 * scale};
        double[] tri1Y = new double[]{y + 0.59 * scale, y + 0.68 * scale, y + 0.59 * scale};
        StdDraw.filledPolygon(tri1X, tri1Y);

        // lower back
        double[] tri2X = new double[]{x + 0.13 * scale, x + 0.33 * scale, x + 0.33 * scale};
        double[] tri2Y = new double[]{y + 0.49 * scale, y + 0.49 * scale, y + 0.59 * scale};
        StdDraw.filledPolygon(tri2X, tri2Y);

        // left-most (rear) leg
        double[] leg1X = new double[]{x + 0.18 * scale, x + 0.26 * scale, x + 0.23 * scale, x + 0.15 * scale};
        double[] leg1Y = new double[]{y + 0.49 * scale, y + 0.49 * scale, y + 0.30 * scale, y + 0.30 * scale};
        StdDraw.filledPolygon(leg1X, leg1Y);

        // left-most foot
        double[] foot1X = new double[]{x + 0.23 * scale, x + 0.23 * scale, x + 0.25 * scale};
        double[] foot1Y = new double[]{y + 0.3 * scale, y + 0.32 * scale, y + 0.3 * scale};
        StdDraw.filledPolygon(foot1X, foot1Y);

        // leg to the right of that
        double[] leg2X = new double[]{x + 0.275 * scale, x + 0.355 * scale, x + 0.34 * scale, x + 0.26 * scale};
        double[] leg2Y = new double[]{y + 0.38 * scale, y + 0.4 * scale, y + 0.30 * scale, y + 0.30 * scale};
        StdDraw.filledPolygon(leg2X, leg2Y);

        // that leg's foot
        double[] foot2X = new double[]{x + 0.36 * scale, x + 0.34 * scale, x + 0.34 * scale};
        double[] foot2Y = foot1Y;
        StdDraw.filledPolygon(foot2X, foot2Y);

        // leg to the right of that
        double[] leg3X = new double[]{x + 0.4 * scale, x + 0.48 * scale, x + 0.45 * scale, x + 0.37 * scale};
        double[] leg3Y = new double[]{y + 0.49 * scale, y + 0.49 * scale, y + 0.3 * scale, y + 0.3 * scale};
        StdDraw.filledPolygon(leg3X, leg3Y);

        // that leg's foot
        double[] foot3X = new double[]{x + 0.45 * scale, x + 0.45 * scale, x + 0.47 * scale};
        double[] foot3Y = foot1Y;
        StdDraw.filledPolygon(foot3X, foot3Y);

        // right-most leg (upper part)
        double[] thighX = new double[]{x + 0.48 * scale, x + 0.56 * scale, x + 0.58 * scale, x + 0.505 * scale};
        double[] thighY = new double[]{y + 0.437 * scale, y + 0.457 * scale, y + 0.42 * scale, y + 0.405 * scale};
        StdDraw.filledPolygon(thighX, thighY);

        // right-most leg (lower part)
        double[] calfX = new double[]{x + 0.58 * scale, x + 0.505 * scale, x + 0.48 * scale, x + 0.54 * scale};
        double[] calfY = new double[]{y + 0.42 * scale, y + 0.41 * scale, y + 0.35 * scale, y + 0.32 * scale};
        StdDraw.filledPolygon(calfX, calfY);

        // right-most foot
        double[] foot4X = new double[]{x + 0.54 * scale, x + 0.55 * scale, x + 0.535 * scale};
        double[] foot4Y = new double[]{y + 0.32 * scale, y + 0.315 * scale, y + 0.355 * scale};
        StdDraw.filledPolygon(foot4X, foot4Y);

        // bottom of the belly
        double[] bellyX = new double[]{x + 0.24 * scale, x + 0.24 * scale, x + 0.65 * scale};
        double[] bellyY = new double[]{y + 0.38 * scale, y + 0.5 * scale, y + 0.5 * scale};
        StdDraw.filledPolygon(bellyX, bellyY);

        // center of body
        double[] bodyX = new double[]{x + 0.33 * scale, x + 0.33 * scale, x + 0.6 * scale, x + 0.6 * scale};
        double[] bodyY = new double[]{y + 0.49 * scale, y + 0.6 * scale, y + 0.6 * scale, y + 0.49 * scale};
        StdDraw.filledPolygon(bodyX, bodyY);

        // head
        double[] headX = new double[]{x + 0.58 * scale, x + 0.675 * scale, x + 0.654 * scale, x + 0.5 * scale};
        double[] headY = new double[]{y + 0.65 * scale, y + 0.59 * scale, y + 0.5 * scale, y + 0.5 * scale};
        StdDraw.filledPolygon(headX, headY);

        // trunk straight down
        double[] trunkX = new double[]{x + 0.654 * scale, x + 0.609 * scale, x + 0.589 * scale, x + 0.63 * scale};
        double[] trunkY = new double[]{y + 0.5 * scale, y + 0.5 * scale, y + 0.42 * scale, y + 0.4 * scale};
        StdDraw.filledPolygon(trunkX, trunkY);

        // trunk curve
        StdDraw.setPenRadius(0.047 * scale);
        StdDraw.arc(x + 0.674 * scale, y + 0.42 * scale, 0.06 * scale, 180, 275);

        // the larger, right-most tusk
        StdDraw.setPenRadius(0.03 * scale);
        StdDraw.arc(x + 0.7 * scale, y + 0.63 * scale, 0.15 * scale, 250, 440);

        // the smaller tusk to the left
        StdDraw.setPenRadius(0.025 * scale);
        StdDraw.arc(x + 0.7 * scale, y + 0.623 * scale, 0.12 * scale, 250, 440);

    }
}
