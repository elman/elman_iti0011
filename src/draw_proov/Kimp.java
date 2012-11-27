package draw_proov;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Kimp extends JFrame {

	class Rectangle {
		public Point start;
		public Point end;
		public Color color;
		private Point actualStart;
		private int height;
		private int width;
		
		public Rectangle(Point start) {
			this.start = start;
		}
		
		public void setEnd(Point end) {
			this.end = end;
			Point p = this.getStartPoint();
			this.start = p;
		}
		
		public int getWidth() {
			return this.width;
		}
		
		public int getHeight() {
			return this.height;
		}
		
		public Point getStartPoint() {
			Point p = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
			this.width = Math.max(start.x, end.x) - p.x;
			this.height = Math.max(start.y, end.y) - p.y;
			if (p.x == start.x) {
				if (p.y == start.y) {
					color = Color.BLACK;
				} else {
					color = Color.BLUE;
				}
			} else {
				if (p.y == start.y) {
					// red?
					color = new Color(255, 0, 0);
				} else {
					color = Color.GREEN;
				}
			}
			return p;
		}
		
		public boolean isClose(Point p) {
			int minX = Math.min(start.x, end.x);
			int maxX = Math.max(start.x, end.x);
			int minY = Math.min(start.y, end.y);
			int maxY = Math.max(start.y, end.y);
			if (Math.abs(p.x - start.x) < 5 ||
					Math.abs(p.x - end.x) < 5) {
				/*
				 * [minY - 5, maxY + 5]
				 */
				if (minY - 5 <= p.y && maxY + 5 >= p.y) {
					return true;
				}
			}
			if (Math.abs(p.y - start.y) < 5 ||
					Math.abs(p.y - end.y) < 5) {
				/*
				 * [minX - 5, maxX + 5]
				 */
				if (minX - 5 <= p.x && maxX + 5 >= p.x) {
					return true;
				}
			}
			return false;
		}

		public int getDistance(Point p) {
			return (int)start.distance(p);
		}
	}
	
	public List<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	public boolean resize = false;
	
	public Rectangle selected;
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Kimp k = new Kimp(800, 600, "Kimp 0.1");
		k.run();
	}
	
	public Kimp(int width, int height, String title) {
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (resize == false) {
					Rectangle r = new Rectangle(e.getPoint());
					rectangles.add(r);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				
				Kimp k = (Kimp)e.getComponent();
				if (k.resize) {
					k.selected = null;
				} else {
					// last rect
					Rectangle r = rectangles.get(rectangles.size() - 1);
					r.end = e.getPoint();
				}
				k.repaint();
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				/*
				 * 101100
				 * 011000
				 * &
				 * 001000
				 */
				/*if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) > 0) {
					//System.out.println("kolmas");
					Rectangle r = rectangles.get(rectangles.size() - 1);
					r.end = e.getPoint();
					
					Kimp k = (Kimp)e.getComponent();
					k.repaint();
				} else */
				Kimp k = (Kimp)e.getComponent();
				if (k.resize) {
					// selected rect
					k.selected.end = e.getPoint();
				} else {
					// last rect
					Rectangle r = rectangles.get(rectangles.size() - 1);
					r.end = e.getPoint();
				}
				k.repaint();
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				
				Kimp k = (Kimp)e.getComponent();
				if (k.resize) {
					k.selected = null;
					for (Rectangle r : k.rectangles) {
						/*int d = r.getDistance(e.getPoint());
						if (d < 20) {
							// select
							k.selected = r;
							break;
						}*/
						if (r.isClose(e.getPoint())) {
							k.selected = r;
							break;
						}
					}
					k.repaint();
				}
				
				
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					resize = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					int selectedOption = JOptionPane.showConfirmDialog(null, 
							"Exit?", "Exit", JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					resize = false;
				}
				// kontrollite esc?
			}
		});
	}
	
	public void run() {
		this.setVisible(true);		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		for (Rectangle r : rectangles) {
			if (r.equals(selected)) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(r.color);
			}
			Point start = r.getStartPoint();
			g.drawRect(start.x, start.y, r.getWidth(), r.getHeight());
		}
	}

}
