// Importing to access necessary libraries

import java.awt.*;

public class Avatar {
    public String name;
    public int head;
    public int[] headColor;
    public int body;
    public int[] bodyColor;
    public boolean hat;
    public int[] hatColor;

    // Creates an Avatar using the given values (constructor 1)
    public Avatar(String n, int h, int[] heC, int b, int[] bC, boolean ht, int[] hC) {
        name = n;
        head = h;
        headColor = heC;
        body = b;
        bodyColor = bC;
        hat = ht;
        hatColor = hC;
    }

    // Creates an Avatar using randomly-assigned values (constructor 2)
    public Avatar(String n) {
        name = n;

        // head is an int in [0,2]
        head = (int) (Math.random() * 3);

        // headColor is an int[] of rgb values
        headColor = new int[]{(int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)};

        // body is an int in [0,2]
        body = (int) (Math.random() * 3);

        // bodyColor is an int[] of rgb values
        bodyColor = new int[]{(int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)};

        // hat is either true or false
        hat = (Math.random() < 0.5);

        // hatColor is an int[] of rgb values
        hatColor = new int[]{(int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)};
    }

    public void draw(double x, double y, double scale) {

        // Draws head with designated color
        StdDraw.setPenColor(headColor[0], headColor[1], headColor[2]);
        if (head == 0) {
            // circular head
            StdDraw.filledCircle(x, y, 0.2 * scale);
        } else if (head == 1) {
            // square head
            StdDraw.filledSquare(x, y, 0.2 * scale);
        } else if (head == 2) {
            // triangular head
            double[] triX = {x - 0.28 * scale, x + 0.28 * scale, x};
            double[] triY = {y - 0.2 * scale, y - 0.2 * scale, y + 0.25 * scale};
            StdDraw.filledPolygon(triX, triY);
        }

        // Calculates luminance of the head color according to the formula 0.299r + 0.587g + 0.114b
        double lum = 0.299 * headColor[0] + 0.587 * headColor[1] + 0.114 * headColor[2];

        // Sets face color (mouth and eyes) based on luminance of head color
        if (lum > 128) {
            StdDraw.setPenColor(StdDraw.BLACK);
        } else {
            StdDraw.setPenColor(StdDraw.WHITE);
        }

        // Draws face
        // Left eye
        StdDraw.filledRectangle(x - 0.07 * scale, y + 0.035 * scale, 0.025 * scale, 0.025 * scale);
        // Right eye
        StdDraw.filledRectangle(x + 0.07 * scale, y + 0.035 * scale, 0.025 * scale, 0.025 * scale);
        // Smile
        StdDraw.setPenRadius(0.025 * scale);
        StdDraw.arc(x, y, 0.15 * scale, 220, 320);

        // Sets pen to requested color for the body
        StdDraw.setPenColor(bodyColor[0], bodyColor[1], bodyColor[2]);
        // Draws body
        if (body == 0) {
            // circular body
            StdDraw.filledCircle(x, y - 0.5 * scale, 0.3 * scale);
        } else if (body == 1) {
            // rectangular body
            StdDraw.filledRectangle(x, y - 0.5 * scale, 0.25 * scale, 0.3 * scale);
        } else if (body == 2) {
            // triangular body
            double[] triX = {x - 0.32 * scale, x + 0.32 * scale, x};
            double[] triY = {y - 0.2 * scale, y - 0.2 * scale, y - 0.9 * scale};
            StdDraw.filledPolygon(triX, triY);
        }

        // Draws a name tag on the body
        // Names must be six characters or shorter to fit on the name tag
        if (name.length() <= 6) {
            // Nametage is white
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(x - 0.08 * scale, y - 0.35 * scale, 0.08 * scale, 0.05 * scale);

            // Creates appropriately sized font to write the name
            Font nameFont = new Font("Arial", Font.BOLD, (int) (20 * scale + 1));
            StdDraw.setFont(nameFont);
            StdDraw.setPenColor(0, 0, 0);

            // Writes the name on the name tag
            StdDraw.text(x - 0.08 * scale, y - 0.35 * scale, name);
        }

        // If a hat has been requested, draws a hat
        if (hat) {
            // Sets hat color
            StdDraw.setPenColor(hatColor[0], hatColor[1], hatColor[2]);
            // Draws the top section of the hat
            StdDraw.filledRectangle(x, y + 0.18 * scale, 0.22 * scale, 0.1 * scale);
            // Hat brim
            StdDraw.filledRectangle(x, y + 0.1 * scale, 0.3 * scale, 0.02 * scale);
        }
    }
}
