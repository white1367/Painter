 /*
�d�v��
105403018
���2A
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Painter2 extends JFrame
{	
	private final JPanel tools;//�u��C��panel
	private final JLabel status;//���A�C��Label
	private final PaintPanel canvas;//�e����PaintPanel
	private final BorderLayout blLayout;//BorderLayout���ŧi
	//1.ø�Ϥu��
	private final JLabel jlPaintTool;
	//2.����
	private final JComboBox<PaintTool> jcbBrush;//PaintTools��enum�A�bJComboBox���x��
	//3.����j�p
	private final JLabel jlBrushSize;
	private final String[] strBrushSize={"�p","��","�j"};
	private final JRadioButton[] jrdBrushSize; 
	private final ButtonGroup bgBrushSize;
	//4.��
	private final JCheckBox jcbFill;
	//5���s
	private final JButton[] jbToolGroup;
	
	private Color color;
	
	public Painter2()	
	{
		super("�p�e�a");
		setLayout(new BorderLayout());//�]�wFrame��Layout
		
		blLayout=new BorderLayout(); //�����BorderLayout
		tools=new JPanel();//����Ƥu��C��panel
		tools.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tools.setLayout(new GridLayout(10,1));//�]�w�u��C��layout�覡
		//1
		jlPaintTool=new JLabel("[ø�Ϥu��]");//�����[ø�Ϥu��]��label
		tools.add(jlPaintTool);//�N[���Z�u��]�[�J�u��C��panel
		
		//2
		jcbBrush=new JComboBox<PaintTool>(PaintTool.values());//�NPaintTools�����Ȩ��X
		jcbBrush.addItemListener
		(
				new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent event)
					{
						
						if(event.getStateChange() == ItemEvent.SELECTED)
						{
							if(jcbBrush.getSelectedIndex() == 0 && jcbFill.isSelected() == true)
							{
								jcbFill.doClick();
							}
							canvas.setTool(jcbBrush.getSelectedIndex());
							//System.out.println(jcbBrush.getSelectedIndex());
							JOptionPane.showMessageDialog(null,String.format("�A�I��F:%s",jcbBrush.getSelectedObjects()));
						}
					}
				}
		);//�NjcbBrush�[�WListener
		tools.add(jcbBrush);//�NjcbBrush�[�J�u��C
		
		//3
		jlBrushSize=new JLabel("[����j�p]");//�N[����j�p]��label�����
		tools.add(jlBrushSize);//�N[����j�p]�[�J�u��C
		
		jrdBrushSize=new JRadioButton[3];//����Ƶ���j�pJRadioButton������}�C
		
		for(int counter=0;counter<jrdBrushSize.length;counter++)//�N����j�pJRadioButton�v�@����ƴI������j�p���r��}�C�åB�[�WListener
		{
			if(counter==0)
			{
				jrdBrushSize[counter]=new JRadioButton(strBrushSize[counter],true);
				jrdBrushSize[counter].addItemListener(new JRadioButtonListener(counter));
			}
			else
			{
				jrdBrushSize[counter]=new JRadioButton(strBrushSize[counter],false);
				jrdBrushSize[counter].addItemListener(new JRadioButtonListener(counter));
			}
		}
		
		bgBrushSize=new ButtonGroup();//�NButtonGroup�����
		//�N����j�p��JRadioButton�[�JButtonGroup
		bgBrushSize.add(jrdBrushSize[0]);
		bgBrushSize.add(jrdBrushSize[1]);
		bgBrushSize.add(jrdBrushSize[2]);
		//�N����j�pJRadioButton�[�J�u��C
		tools.add(jrdBrushSize[0]);
		tools.add(jrdBrushSize[1]);
		tools.add(jrdBrushSize[2]);
		
		//4
		jcbFill=new JCheckBox("��");//�N�񺡪�JCheckBox�����
		jcbFill.addItemListener//�N�񺡥[�WListener
		(
				new ItemListener()
				{

					@Override
					public void itemStateChanged(ItemEvent event)
					{
						if(canvas.getTool() == 0)
						{
							canvas.setFill(false);
							jcbFill.setSelected(false);
						}
						else
						{
							if(event.getStateChange()  == ItemEvent.SELECTED)
							{
								canvas.setFill(true);
								System.out.println("�񺡤F");
							}
							else
							{
								canvas.setFill(false);
								System.out.println("�A�����F��");
							}
						}
					}
					
				}
		);
		tools.add(jcbFill);//�N�񺡥[�J�u��C
		
		//5
		jbToolGroup=new JButton[3];//�N���s�}�C�����
		//�N�U�ӫ��s�����
		jbToolGroup[0]=new JButton("�e����");
		
		jbToolGroup[1]=new JButton("�ᴺ��");
		jbToolGroup[1].setBackground(Color.BLACK);//�N"�ᴺ��"�[�W�I���C��
		jbToolGroup[2]=new JButton("�M���e��");
		
		
		//�N�U�ӫ��s�[�Wlistener
		jbToolGroup[0].addActionListener(new ButtonHandler(0));
		jbToolGroup[1].addActionListener(new ButtonHandler(1));
		jbToolGroup[2].addActionListener(new ButtonHandler(2));
		//�N�U�ӫ��s�[�J�u��C
		tools.add(jbToolGroup[0]);
		tools.add(jbToolGroup[1]);
		tools.add(jbToolGroup[2]);
		add(tools,blLayout.WEST);//�]�w�u��C����m
		
		//�e��
		canvas=new PaintPanel();//�bclass PaintPanel �������e���A�ҥH�b�o�̪��������
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		canvas.setBackground(Color.WHITE);
		canvas.addMouseListener(new MouseHandler());//�N�e���[�Wlistener
		canvas.addMouseMotionListener(new MouseMotionHandler());//�N�e���[�WmotionListener
		add(canvas,blLayout.CENTER);//�]�w�e������m
		
		//status bar
		status=new JLabel("��Ц�m:(,)");//����ƪ��A�C
		add(status,blLayout.SOUTH);//�]�w���A�C����m
		
	}
	private class MouseHandler extends MouseAdapter
	{
		@Override
		 public void mouseExited(MouseEvent event)
		 {
			status.setText("�e���ϥ~");
		 }
	}
	private class MouseMotionHandler extends MouseMotionAdapter//MouseHandler��@MouseListener�MMouseMotionListener
	{
		
		@Override
		public void mouseDragged(MouseEvent event) //�ƹ��즲�ɡA���A�C�y���H������
		{
			
			status.setText(String.format("��Ц�m:(%d,%d)",event.getX(),event.getY()));
		}

		@Override
		public void mouseMoved(MouseEvent event)//�ƹ����ʮɡA���A�C�y���H������
		{
			
			status.setText(String.format("��Ц�m:(%d,%d)",event.getX(),event.getY()));
		}
		
	}
	private class JRadioButtonListener implements ItemListener//JRadioButton��@ItemListener
	{
		private final int index;
		public JRadioButtonListener(int index)//�]�wJRadioButton���غc�l�A�]�w�ǤJ�Ȭ�JRadioButton���}�Cindex
		{
			this.index=index;
		}
		@Override
		public void itemStateChanged(ItemEvent event)//�Q��index�ӧ��۹������r��}�C�ȩ�JJOptionPane
		{
			if(event.getStateChange() == ItemEvent.SELECTED)
			{
				JOptionPane.showMessageDialog(null,String.format("�A�I��F:%s",strBrushSize[index]));
				switch(index)
				{
					case 0:
					{
						canvas.setSize(1);
						break;
					}
					case 1:
					{
						canvas.setSize(10);
						break;
					}
					case 2:
					{
						canvas.setSize(30);
						break;
					}
				}
			}
		}
		
	}
	private class ButtonHandler implements ActionListener//ButtonHandler��@ActionListner
	{
		private final int index;
		public ButtonHandler(int index)
		{
			this.index=index;
		}
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			JOptionPane.showMessageDialog(null,String.format("�A�I��F:%s",event.getActionCommand()));
			if(index==0)//�]�w�����C��
			{
				color=JColorChooser.showDialog(jbToolGroup[index],"Choose a color",color);
				jbToolGroup[index].setBackground(color);
				canvas.setForeColor(color);
			}
			else if(index==1)//�]�w�I�v��
			{
				color=JColorChooser.showDialog(jbToolGroup[index],"Choose a color",color);
				jbToolGroup[index].setBackground(color);
				canvas.setBackground(color);
			}
			else if(index==2)//�M�ŵe���A�^�_���զ�e��
			{
				canvas.clear();
				jbToolGroup[1].setBackground(Color.BLACK);
				canvas.setBackground(Color.WHITE);
			}
		}
		
	}
}