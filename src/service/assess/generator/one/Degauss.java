package service.assess.generator.one;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import bll.FloatAssessment;
//灭磁开关动作总得分
public class Degauss {

public static List<Number> Degauss1(long time){
		
	List<Number> dList=new ArrayList<>();
	DecimalFormat df=new DecimalFormat("#.0");  //控制小数点位数
	//励磁电流
		FloatAssessment fa=new FloatAssessment();
		int U1=fa.FloatAssess(145,time, 3600, 0);
		//励磁电压
		int U2=fa.FloatAssess(146,time, 600, 0);
		
		double a = Double.parseDouble(df.format((U1+U2)/2));
		
		dList.add(U1);
		dList.add(U2);
		dList.add(a);
		return dList;
		// System.out.println(a);
	}
}
