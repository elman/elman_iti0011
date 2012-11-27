package kimp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Freehand extends JFrame{
	int imgNr = 0;
	boolean ctrlPressed = false;
	boolean newImg = true;
	int x = 0, y = 0;
//    private List<Point> pointList = new ArrayList<Point>();
    private List<Images> images = new ArrayList<Images>();
    
    public Freehand(int width, int height, String title) {
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			//	super.keyPressed(e);
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					ctrlPressed = true;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			//	super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					ctrlPressed = false;
				}
			}
		});
    }
    
	public void run() {
		this.setVisible(true);	
		MyListener alpha = new MyListener(this);
		this.addMouseMotionListener(alpha);
		this.addMouseListener(alpha);
	}
    public int getImgNr(){
    	return imgNr;
    }
    
    public void setImgNr(){
    	this.imgNr++;
    }

    private class MyListener extends MouseAdapter {

        private Freehand freehand;

        public MyListener(Freehand freehand) {
            this.freehand = freehand;
        }

        @Override
        public void mouseDragged(MouseEvent event) {
        	
        	// Checks if left mouse button is pressed. 
        	if (SwingUtilities.isLeftMouseButton(event) && !ctrlPressed){
	        	Images img = new Images();
	        	Freehand f = (Freehand)event.getComponent();
	        	f.repaint();
	        	// Adds new image to images list and saves first points.
	        	if (newImg == true){
	        		img.setPoints(new Point(event.getX(), event.getY()));
	        		images.add(img);
	        		newImg = false;
	        	}	
	        	// saves points and keeping track of the mouse moovment.
	        	images.get(getImgNr()).setPoints(new Point(event.getX(), event.getY()));
        	}
        	
        	if (SwingUtilities.isLeftMouseButton(event) && ctrlPressed){
            	int X = event.getX();
            	int Y = event.getY();
            	int dx = X-x;
            	int dy = Y-y;
            	x=X;
            	y=Y;
                for (int i = 0; i < images.size(); i++) {
                	if (images.get(i).isSelected()){
	                	for (int j = 0; j < images.get(i).getPoints().size(); j++) {
	                		images.get(i).getPoints().get(j).setLocation(images.get(i).getPoints().get(j).getX()+dx, images.get(i).getPoints().get(j).getY()+dy);
	                	}
                	}
                }
                repaint();
        		
        	}
        }
        
        @Override
        public void mousePressed(MouseEvent event) {
        	if (SwingUtilities.isRightMouseButton(event) && ctrlPressed){
                for (int i = 0; i < images.size(); i++) {
                	if (images.get(i).isSelected()){
	                	images.remove(i);
	                	imgNr--;
                	}
                }
                repaint();
        	}
        }
        
        @Override
        public void mouseReleased(MouseEvent event) {
        	if ( SwingUtilities.isLeftMouseButton(event) && !ctrlPressed){
	        	freehand.setImgNr();
	        	freehand.newImg = true;
        	}
        }
        
        @Override
        public void mouseMoved(MouseEvent event){

        	boolean b = false;
        	x = event.getX();
        	y = event.getY();
            for (int i = 0; i < images.size(); i++) {
            	for (int j = 1; j < images.get(i).getPoints().size(); j++) {
            		int xx = (int) images.get(i).getPoints().get(j).getX();
            		int yy = (int) images.get(i).getPoints().get(j).getY();
            		if ((xx-x)<8 && (yy-y)<8 && (xx-x)>(-8) && (yy-y)>(-8) && ctrlPressed
            				|| (xx+x)<8 && (yy+y)<8 && (xx+x)>(-8) && (yy+y)>(-8) && ctrlPressed){
            			b = true;
            		}
            	}
    			if (b){
    				images.get(i).setSelected(true);
    				
    			} else {
    				images.get(i).setSelected(false);
    			}
    			repaint();
    			b=false;
            }
        }
    }
    
        @Override
        public void paint(Graphics g) {
        	super.paint(g);
            for (int i = 0; i < images.size(); i++) {
            	for (int j = 1; j < images.get(i).getPoints().size(); j++) {
	                Point p1 = images.get(i).getPoints().get(j-1);
	                Point p2 = images.get(i).getPoints().get(j);
	                if(images.get(i).isSelected() == true){
	                	g.setColor(Color.RED);
	                } else {
	                	g.setColor(Color.BLACK);
	                }
	                g.drawLine(p1.x, p1.y, p2.x, p2.y);
//	                System.out.println("x: "+p1.x+" --> "+p2.x+"      y: "+p1.y+" --> "+p2.y);
	            }
            }
        }
        
        private class Images {
        	
        	boolean selected = false;
        	
        	public boolean isSelected() {
				return selected;
			}

			public void setSelected(boolean selected) {
				this.selected = selected;
			}

			private List<Point> points = new ArrayList<Point>();
        	
        	public void setPoints(Point p){
        		this.points.add(p);
        	}
        	
        	public List<Point> getPoints() {
        	      return points;
        	}
        }
        
        public static void main(String[] args) {
    		Freehand f = new Freehand(800, 600, "Kimp");
    		f.run();
        }      
 }