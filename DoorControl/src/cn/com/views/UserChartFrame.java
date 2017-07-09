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

	// 创建柱状图数据集
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

	// 用数据集创建一个图表
	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(this.title, "日期", "开门次数",
				dataset, PlotOrientation.VERTICAL, true, true, false); // 创建一个JFreeChart
		chart.setTitle(new TextTitle(this.title, new Font("宋体", Font.BOLD
				+ Font.ITALIC, 20)));// 可以重新设置标题

		CategoryPlot plot = (CategoryPlot) chart.getPlot();// 获得图标中间部分，即plot
		CategoryAxis categoryAxis = plot.getDomainAxis();// 获得横坐标
		categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));// 设置横坐标字体
		categoryAxis.setTickLabelFont(new Font("黑体", Font.BOLD, 10));// x轴坐标上文字
		ValueAxis numberaxis = plot.getRangeAxis(); // y轴
		numberaxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));// 设置横坐标字体
		numberaxis.setTickLabelFont(new Font("黑体", Font.BOLD, 10));// y轴坐标上文字
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));// 图例文字
		return chart;
	}

	public JPanel createPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}
}
