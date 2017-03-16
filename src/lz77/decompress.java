package lz77;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.JOptionPane;

public class decompress {

	public decompress() {

	}

	public void proccess(String adress, String name) {
		Vector<triple> tmp = this.read(adress);
		StringBuilder tmp2 = this.decomp(tmp);
		this.write(tmp2, name);
		JOptionPane.showMessageDialog(null, "the file is decompressed");

	}

	public void write(StringBuilder x, String name) {
		try {
			  BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(name)));
              bwr.write(x.toString());
              bwr.flush();
              bwr.close();
            } catch (Exception ex) {

		}

	}

	public StringBuilder decomp(Vector<triple> tmp) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getF() == 0 && tmp.get(i).getS() == 0) {
				ret.append(tmp.get(i).getNext());
			} else {
				StringBuilder t = new StringBuilder(ret.substring(ret.length() - tmp.get(i).getF(),
						((ret.length() - tmp.get(i).getF()) + tmp.get(i).getS())));
				t.append(tmp.get(i).getNext());
				ret.append(t);
			}
		}
		return ret;

	}

	public Vector<triple> read(String x) {
		Vector<triple> tmp = new Vector<triple>();
		triple t = new triple();
		try {
			@SuppressWarnings({ "resource" })
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(x));
			for(;;){
				t = (triple) input.readObject();
				tmp.addElement(t);
			}

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return tmp;
	}

}