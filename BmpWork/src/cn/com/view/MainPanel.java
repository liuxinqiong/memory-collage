package cn.com.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cn.com.listeners.MainPanel_MouseListener;
import cn.com.listeners.MainPanel_MouseMotionListener;

public class MainPanel extends JPanel{
	List<Polygon>  polygons=new ArrayList<Polygon>();
	Image image;
	public MainPanel(){
		
	}
	public MainPanel(Image image,LayoutManager loyout){
		this.setLayout(loyout);
		this.image=image;
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		this.addMouseListener(new MainPanel_MouseListener(this));
		this.addMouseMotionListener(new MainPanel_MouseMotionListener(this));
	}
	/**
	 * 覆盖paint方法  在此完成自己的需求  用repaint()方法手动调用
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//得到画笔
		Graphics2D g_2d=(Graphics2D)g;
		//绘制图片
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		//绘制线条
		g_2d.setColor(Color.RED);
		BasicStroke bs_1=new BasicStroke(8,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
		g_2d.setStroke(bs_1);
		for (Polygon polygon : polygons) {
			g_2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		}	
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Polygon polygon=new Polygon();
		int beginX = e.getX();
		int beginY = e.getY();
		polygon.addPoint(beginX, beginY);
		polygons.add(polygon);
		repaint(); 
	}
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int beginX = e.getX();
		int beginY = e.getY();
		polygons.get(polygons.size()-1).addPoint(beginX, beginY);
		repaint(); 
	}
}
