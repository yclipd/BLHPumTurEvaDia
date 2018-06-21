package algorithms.DNN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blh.action.datamine.history.SystemConvert;
import service.FaultInfoService;
import util.DataUtils;
import util.FaultUtils;
import util.TimeUtils;


/**
 * 多分类任务调用
 * @author lh
 * */
public class MultiClass {
	MLP mlp = new MLP();
	double[][] inputdata;
	double[][] outputdata;
    int itrs=5000;
    double lr=0.8;
    double mom=0.3;
    /**
     * 初始化，默认只有三层
     * */
	public MultiClass(double[][] inputArray,double[][] desiredOutputArray){
		int[] layers = new int[]{0,100,0};
		layers[0] = inputArray[0].length;
		layers[2] = desiredOutputArray[0].length;
		inputdata = inputArray;
		outputdata = desiredOutputArray;
		mlp.initNeuralNet(layers); 
	}
	/**
	 * 用默认参数训练数据
	 * */
	public void train(){
		mlp.train(inputdata,outputdata,itrs,lr,mom);
	}
	/**
	 * 重载，自定义训练参数
	 * */
	public void train(int itrs,double lr,double mom){
		mlp.train(inputdata,outputdata,itrs,lr,mom);
	}
	/**
	 * 根据输入数据预测分类
	 * */
	public double[][] predict(double[][] testdata){
		double[][] pred = mlp.interrogate(testdata);
		return pred;
	}
	
	public static void main(String args[]) throws NumberFormatException, ClassNotFoundException, SQLException{
        //input  
        double[][] inputArray = new double[][]  
        {  
        { 0.0, 0.0 },  
        { 0.0, 1.0 },  
        { 1.0, 0.0 },  
        { 1.0, 1.0 } };  
        // desired output  
        
    	String system;
    	Long startTime;
    	Long endTime;
		system = "aa";
		startTime = 1323404214L;
		endTime=1323404215L;
		
		FaultInfoService faultInfoService = new FaultInfoService();
		List<FaultUtils> data = faultInfoService.getFaultInfos(system,startTime,endTime);
        
        double[][] desiredOutputArray = new double[][]  
        {
        { 0.0,1.0},  
        { 0.0,1.0 },  
        { 1.0,0.0 },  
        { 1.0,0.0} }; 
		MultiClass mc =new  MultiClass(inputArray,desiredOutputArray);
		mc.train();
		double[][] pred = mc.predict(inputArray);//获得预测值
		
	}
}
