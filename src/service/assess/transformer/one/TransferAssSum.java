package service.assess.transformer.one;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import service.assess.transformer.TransferAssResult;
import service.assess.transformer.four.histroy4;
import service.assess.transformer.four.malfunction4;
import service.assess.transformer.four.temperature4;
import service.assess.transformer.one.frist.temperature;
import service.assess.transformer.one.second.histroy;
import service.assess.transformer.one.thrid.malfunction;

/**
 * 主变action调用数据封装
 * 
 * @author tiandiwei
 *
 */
public class TransferAssSum {

	// 主变压器总得分
	public TransferAssResult output(long time) {

		List<Number> firstList = new ArrayList<>();// 一层指标输出；存放“评估主页面”饼图、柱状图、表格数据

		List<Number> tempList = new ArrayList<>();// 主变温度底层指标输出，存放“显示子评估页面”，饼图、柱状图数据

		List<Number> faultList = new ArrayList<>();// 主变故障信号底层指标输出，存放“显示子评估页面”，饼图、柱状图数据

		DecimalFormat df=new DecimalFormat("#.0");  //控制小数点位数
		// 主变压器温度
		temperature guideVane = new temperature();
		List<Number> temp= guideVane.temperatureA(time);
		double U0 = temp.get(0).doubleValue();// 主变油温
		double U1 = temp.get(1).doubleValue();// 高压侧绕组温度
		double U2 = temp.get(2).doubleValue();// 冷却器出口水温
		double sum1 = temp.get(3).doubleValue();
		// 历史和巡检状态
		histroy history = new histroy();
		double sum3 = history.histroy(time).get(0).doubleValue();
		// 主变故障信号
		malfunction mainShaftSeal = new malfunction();
		List<Number>  mList = mainShaftSeal.Malfunction(time);
		double m1 = mList.get(0).doubleValue();// 主变重瓦斯跳闸
		double m2 = mList.get(1).doubleValue();// 主变油位高报警
		double m3 = mList.get(2).doubleValue();// 机组电气停机报警
		double sum2 = mList.get(3).doubleValue();// 主变故障总得分
		
		// 主变系统总得分
		double sum = 0.5 * sum1 + 0.1 * sum2 + 0.4 * sum3;
		// 评估页面数据
		firstList.add(sum);
		firstList.add(sum1);
		firstList.add(sum2);
		firstList.add(sum3);
		firstList.add(U0);
		firstList.add(U1);
		firstList.add(U2);
		firstList.add(m1);
		firstList.add(m2);
		firstList.add(m3);

		// 主变温度底层指标数据
		tempList.add(U0);
		tempList.add(U1);
		tempList.add(U2);

		// 主变故障底层指标数据
		faultList.add(m1);
		faultList.add(m2);
		faultList.add(m3);

		TransferAssResult transferAssResult = new TransferAssResult(firstList, tempList, faultList);

		return transferAssResult;
	}
}
