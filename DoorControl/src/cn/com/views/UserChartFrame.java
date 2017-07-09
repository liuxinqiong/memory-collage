package cn.com.views;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import cn.com.dao.RecordDao;
import cn.com.entity.User;
import cn.com.global.Global;

public class UserChartFrame extends JDialog {
	User user;
	String startTime;
	String endTime;
	String title;
	List<Date> list;

	RecordDao recordDao;

	public UserChartFrame(RecordDialog rd,String title,boolean model, User user, String startTime,
			String endTime) {
		super(rd,title,model);
		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title + "(" + user.getUserName() + ")";
		recordDao = new RecordDao();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		list = Global.getDates(startTime, endTime);
		Global.setCenterByWindow(this);
		this.setContentPane(createPanel());
		this.setVisible(true);
	}

	// ������״ͼ���ݼ�
	public CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 1; i <= list.size(); i++) {
			dataset.setValue(
					recordDao.getOpenCount(user, sdf.format(list.get(i - 1))),
					i + "", sdf.format(list.get(i - 1)));
		}
		return dataset;
	}

	// �����ݼ�����һ��ͼ��
	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(this.title, "����", "���Ŵ���",
				dataset, PlotOrientation.VERTICAL, true, true, false); // ����һ��JFreeChart
		chart.setTitle(new TextTitle(this.title, new Font("����", Font.BOLD
				+ Font.ITALIC, 20)));// �����������ñ���

		CategoryPlot plot = (CategoryPlot) chart.getPlot();// ���ͼ���м䲿�֣���plot
		CategoryAxis categoryAxis = plot.getDomainAxis();// ��ú�����
		categoryAxis.setLabelFont(new Font("΢���ź�", Font.BOLD, 12));// ���ú���������
		categoryAxis.setTickLabelFont(new Font("����", Font.BOLD, 10));// x������������
		ValueAxis numberaxis = plot.getRangeAxis(); // y��
		numberaxis.setLabelFont(new Font("΢���ź�", Font.BOLD, 12));// ���ú���������
		numberaxis.setTickLabelFont(new Font("����", Font.BOLD, 10));// y������������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));// ͼ������
		return chart;
	}

	public JPanel createPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart); // ��chart�������Panel�����ȥ��ChartPanel���Ѽ̳�Jpanel
	}
}
