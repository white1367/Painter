/*
�d�v��
105403018
���2A
 */
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PaintPanel extends JPanel 
{
	private int oldX,oldY,currentX,currentY;//�_�l�y�ЩM���I�y��
	private Graphics2D g2d;
	private BufferedImage image;
	private Color forecolor = Color.BLACK;//�w�]�����C��
	private int size,tools;
	private boolean fill;
	
	public PaintPanel()
	{
		image= new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		size = 1;
		tools = 0;
		fill = false;
		
	}
	@Override
	public void paintComponent(Graphics g)//�NGraphics cast��Graphics2D
	{
		super.paintComponent(g);
		Graphics gg=image.createGraphics();
		g2d=(Graphics2D) gg;
		
		g2d.setPaint(forecolor);		//�o�˵���~���|�O�z����
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );//�׹��u��
		g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));//�]�w���I�Ϊ��B����j�p
		
		g.drawImage(image,0,0,this);
	}
	public void setForeColor(Color forecolor)//��Painter2��]�w�����C��
	{
		this.forecolor = forecolor;
	}
	public void setSize(int size)//��Painter2�]�w����j�p
	{
		this.size = size;
	}
	public void setTool(int tools)//��Painter2�]�w�u��
	{
		this.tools = tools;
	}
	public void setFill(boolean fill)//��Painter2�]�w��
	{
		this.fill = fill;
	}
	public int getTool()//��Painter2���o�u��
	{
		return tools;
	}
	public void clear()//�M���e�����\��
	{
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		repaint();
	}
	private class MouseHandler extends MouseAdapter//�U���u�㪺�_�l��
	{
		@Override
		public void mousePressed(MouseEvent event)
		{
			switch(tools)
			{
				case 0:
				{
					oldX = event.getX();
					oldY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					break;
				}
				case 1:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				case 2:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				case 3:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				case 4:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent event)//�U���u�㪺�׭ȡA�çP�_����j�p�B�O�_�񺡵�
		{
			switch(tools)
			{
				case 0:
				{
					break;
				}
				case 1:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					if(fill)
					{
						g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
						g2d.drawLine(oldX, oldY, currentX, currentY);
					}
					else
					{
						if(size == 1)
						{
							g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f, new float[]{21f, 5f}, 0f));
							g2d.drawLine(oldX, oldY, currentX, currentY);
						}
						else if(size == 10)
						{
							g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f, new float[]{21f, 15f}, 0f));
							g2d.drawLine(oldX, oldY, currentX, currentY);
						}
						else if(size == 30)
						{
							g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f, new float[]{21f, 50f}, 0f));
							g2d.drawLine(oldX, oldY, currentX, currentY);
						}
					}
					repaint();
					break;
				}
				case 2:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					if(fill)
					{
						g2d.fill(new Ellipse2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					else
					{
						g2d.draw(new Ellipse2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					repaint();
					break;
				}
				case 3:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					if(fill)
					{
						g2d.fill(new Rectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					else
					{
						g2d.draw(new Rectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					repaint();
					break;
				}
				case 4:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					if(fill)
					{
						g2d.fill(new RoundRectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY),50,50));
					}
					else
					{
						g2d.draw(new RoundRectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY),50,50));
					}
					repaint();
					break;
				}
				
			}
		}
	}
	private class MouseMotionHandler extends MouseMotionAdapter//�즲�L�{�����y�Ч�s
	{
		@Override
		public void mouseDragged(MouseEvent event)
		{
			
			switch(tools)
			{
				case 0:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
					break;
				}
				case 1:
				{
					break;
				}
				case 2:
				{
					break;
				}
				case 3:
				{
					break;
				}
				case 4:
				{
					break;
				}
			}
		}
	}
}
