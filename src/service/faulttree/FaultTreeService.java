package service.faulttree;

import java.util.ArrayList;
import java.util.List;

import bll.diagnosis.tree.model.Fault;
import bll.diagnosis.tree.model.FaultDiagnosis;
import bll.diagnosis.tree.model.FaultResult;
import bll.diagnosis.tree.model.FaultTreeMain;

/**
 * 创建故障树服务返回故障对应的ID值 参考仿真服务
 * 
 * @author wuyue
 */
public class FaultTreeService {
	List<FaultResult> result = new ArrayList<>();
	private String Date;

	public List<FaultResult> findfault() {
		FaultTreeMain faulttree = new FaultTreeMain();
		result = faulttree.FaultTreeDiagnosis(1, "1", "1",
				"2015-05-26 09:48:16");
		ArrayList<Fault> faults = new FaultDiagnosis().TSFaultsRead();
		return result;
	}

	public static void main(String[] args) {
		System.out.println(
				"故障列表" + new FaultTreeService().findfault().toString());
	}
}
