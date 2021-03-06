package service.dateMine.frequentItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.imagemap.ImageMapUtilities;

import util.PathUtil;

public class FrequentItem {
	private int frequent;
	private List<String> items;

	public FrequentItem(int frequent, List<String> items) {
		super();
		this.frequent = frequent;
		this.items = items;
	}

	public FrequentItem() {
		super();
	}

	public static void main(String[] args) {
		try {
			FrequentItem frequentItem = new FrequentItem().getFrequentItem("Gov", 4);
			List<String> list = frequentItem.getItems();
			System.out.println(list.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrequentItem getFrequentItem(String system, int frequent) throws IOException {
	
		 InputStreamReader read = new InputStreamReader(
					new FileInputStream(PathUtil.getWebRealBasePath() + "data/" + system + ".txt"),"utf-8");
			System.out.println(PathUtil.getWebRealBasePath()+"data/"+ system + ".txt");
			
			@SuppressWarnings("resource")
			BufferedReader reader=new BufferedReader(read);
			
			String line = null;
			FrequentItem fItem = new FrequentItem();
			while ((line = reader.readLine()) != null) {
				
				String str = "";
				str += line;
				if (str.contains("频繁"+frequent + "项集")) {
					System.out.println("memda");
					fItem.setFrequent(frequent);
					fItem.setItems(getList(reader.readLine()));
					return fItem;
				}
			}
			read.close();
			return null;

	}

	public static List<String> getList(String string) {
		List<String> frqItems = new ArrayList<>();
		String[] strs = string.split("  ");
		for (int i = 1; i < strs.length; i += 2) {
			frqItems.add(strs[i]);
		}
		return frqItems;
	}

	public static String getSupport(String string) {
		@SuppressWarnings("unused")
		List<String> frqItems = new ArrayList<>();
		String[] strs = string.split("  ");
		return strs[0].split(" ")[1];
	}

	public int getFrequent() {
		return frequent;
	}

	public void setFrequent(int frequent) {
		this.frequent = frequent;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	
}
