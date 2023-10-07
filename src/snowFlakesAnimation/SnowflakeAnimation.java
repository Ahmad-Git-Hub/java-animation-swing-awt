package snowFlakesAnimation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnowflakeAnimation extends JPanel implements ActionListener {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private static final int MAX_FLAKES = 100;
	private static final int MAX_SIZE = 10;
	private static final int MIN_SIZE = 3;

	private Snowflake[] flakes;
	private Random random;

	public SnowflakeAnimation() {
		// Set the preferred size of the JPanel to the dimensions of the window
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.cyan);

		// Create an array of Snowflake objects with random positions and sizes
		flakes = new Snowflake[MAX_FLAKES];
		random = new Random();

		for (int i = 0; i < MAX_FLAKES; i++) {
			flakes[i] = new Snowflake(random.nextInt(WIDTH), random.nextInt(HEIGHT),
					random.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE);
		}

		// Create a Timer object that fires an ActionEvent every 20 milliseconds and add
		// ourselves as the ActionListener
		Timer timer = new Timer(20, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Set the color of the Graphics object to white
		g.setColor(Color.white);

		// Draw each snowflake as a white oval using its x, y, and size data
		for (int i = 0; i < MAX_FLAKES; i++) {
			g.fillOval(flakes[i].x, flakes[i].y, flakes[i].size, flakes[i].size);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// Update the position of each snowflake by adding half of its size to the y
		// coordinate and a random amount to the x coordinate
		for (int i = 0; i < MAX_FLAKES; i++) {
			flakes[i].y += flakes[i].size / 2;
			flakes[i].x += random.nextInt(3) - 1;

			// If a snowflake reaches the bottom of the frame, reset its position to the top
			// with a new random x position
			if (flakes[i].y > HEIGHT) {
				flakes[i].y = 0;
				flakes[i].x = random.nextInt(WIDTH);
			}
		}

		// Repaint the display with the new snowflake positions
		repaint();
	}

	// Inner class to represent a snowflake with an x and y position and a size
	private class Snowflake {
		int x;
		int y;
		int size;

		public Snowflake(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Snowflake Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new SnowflakeAnimation());
		frame.pack();
		frame.setVisible(true);
	}
}