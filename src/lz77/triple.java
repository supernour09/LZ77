package lz77;

import java.io.Serializable;

public class triple implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int f , s;
private Character next;
	public triple(){
		
	}
	public triple(int x , int y ,Character q) {
		f=x;
		s=y;
		next = q;
}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public Character getNext() {
		return next;
	}

	public void setNext(Character next) {
		this.next = next;
	}
	public void show(){
		System.out.println(this.f +" "+ this.s+" " + (char)this.next);
	}

}
