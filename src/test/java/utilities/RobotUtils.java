package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotUtils
{

    public static void mouseMove() throws AWTException
    {
        Robot robot = new Robot();

        // Delay for a specified time (milliseconds)
        robot.delay(2000); // Pauses execution for 2000 milliseconds (2 seconds)

        // Press a given key
        robot.keyPress(KeyEvent.VK_A); // Presses the 'A' key

        // Release a given key
        robot.keyRelease(KeyEvent.VK_A); // Releases the 'A' key

        // Press one or more mouse buttons
        // Example: Press the left mouse button
        robot.mousePress(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);

        // Release one or more mouse buttons
        // Example: Release the left mouse button
        robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);

        // Move mouse pointer to given screen coordinates
        robot.mouseMove(100, 200); // Moves the mouse to coordinates (100, 200)

        // Rotates the scroll wheel on wheel-equipped mice
        robot.mouseWheel(5); // Scrolls down by 5 notches (positive value)
        robot.mouseWheel(-3); // Scrolls up by 3 notches (negative value)

        // Creates an image containing pixels read from the screen
        // Rectangle screenRect = new
        // Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        // BufferedImage screenCapture = robot.createScreenCapture(screenRect);
        // // You would typically save this BufferedImage to a file

        // Returns the color of a pixel at the given screen coordinates
        // Color pixelColor = robot.getPixelColor(50, 50);
        // System.out.println("Color at (50, 50): " + pixelColor);

        // Sets the number of milliseconds this Robot sleeps after generating an event
        robot.setAutoDelay(100); // Sets a delay of 100 milliseconds after each action

        // Returns the number of milliseconds this Robot sleeps after generating an
        // event
        int autoDelay = robot.getAutoDelay();
        System.out.println("Current auto delay: " + autoDelay);

        // Sets whether this Robot automatically invokes waitForIdle after generating an
        // event
        robot.setAutoWaitForIdle(true); // Enable automatic waiting

        // Returns whether this Robot automatically invokes waitForIdle after generating
        // an event
        boolean isAutoWaitForIdle = robot.isAutoWaitForIdle();
        System.out.println("Is auto wait for idle enabled: " + isAutoWaitForIdle);

        // Waits until all events currently on the event queue have been processed
        robot.waitForIdle();

    }

}