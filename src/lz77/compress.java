package lz77;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class compress {
	StringBuilder tocomp;
	public Vector<triple> tags = new Vector<triple>();
	public compress() {
		// TODO Auto-generated constructor stub
	}
	compress(StringBuilder x) {
		tocomp = x;
	}
	
	public void proccess(String address , String name){
		StringBuilder tmp = new StringBuilder(read(address));
		tocomp = tmp;
		com();
		write(name);
		JOptionPane.showMessageDialog(null, "the file is compressed");
		
	}
	public void showtags(){
		for(int i=0;i<tags.size();i++){
			tags.get(i).show();
		}
	}
	@SuppressWarnings("resource")
	public StringBuilder read(String address){
		String content = null;
		
		try {
			content = new Scanner(new File(address)).useDelimiter("\\Z").next();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(content);
		return sb;

		
	}
	public void write(String name) {
		try{
		    FileOutputStream fout = new FileOutputStream(name, true);
		    ObjectOutputStream oos = new ObjectOutputStream(fout);
		    for(int i=0;i<tags.size();i++){
		    	oos.writeObject(tags.get(i));
		    }
		    fout.close();
		    oos.close();
		} catch (Exception ex) {
		    
		}
		

	}

	public int slide(int end, int size) {
		if (end == -1)
			return -1;
		else if (size > end + 1)
			return -1;
		else {
			if (end + size + 1 >= tocomp.length())
				return -1;
			StringBuilder wind = new StringBuilder();
			StringBuilder find = new StringBuilder(tocomp.substring(end + 1, end + size + 1));
			int i = 0;
			for (; i < size; i++) {
				wind.append(tocomp.charAt(i));
			}
			// System.out.println(end+1 + " "+(end+1 + size) + " & " + find);
			// System.out.println(wind + "&"+ find + " in slide");
			if (wind.toString().equals(find.toString()))
				return 0;
			int s = 0;
			for (; i <= end; i++) {
				wind.deleteCharAt(0);
				wind.append(tocomp.charAt(i));
				s++;
				// System.out.println(wind + "&"+ find + " in slide");
				if (wind.toString().equals(find.toString()))
					return s;
			}
			return -1;
		}
	}

	public void com() {
		int s = -1, e = tocomp.length(), mid = 0;
		while (s < e - 2) {
			int ss = 0, ee = tocomp.length() - s, ansindx = 0, thelast = -1, thelastsize = 0;
			while (ss < ee) {
				mid = ss + (ee - ss + 1) / 2;
				ansindx = slide(s, mid);
				// System.out.println("the mid :"+mid+ "ansindx " + ansindx +
				// "end " + s);
				if (ansindx >= 0) {
					ss = mid;
					thelast = ansindx;
					thelastsize = mid;
				} else {
					ee = mid - 1;
				}
			}
			// System.out.println(s + " " +thelast + " " + thelastsize);
			if (thelast == -1) {
				triple tmp = new triple(0, 0, tocomp.charAt(s + 1));
				//tmp.show();
				tags.addElement(tmp);
				s++;

			} else {
				triple tmp = new triple(s - thelast + 1, thelastsize, tocomp.charAt(s + 1 + thelastsize));
				//tmp.show();
				tags.addElement(tmp);
				s += thelastsize + 1;
			}
		}
	}
}
