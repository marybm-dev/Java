
package b;


class A {
    public void op1() {
        System.out.println("A.op1()");
    }
}

public class B extends A {
	public static void main(String[] args) {
        new B();
    }
    private C c;
    private D d;
    public B() {
        System.out.println("B()");
        c = new C(this);    // 1
        d = new D(this);    // 2
        c.op3(d);           // 3
        d.op4(c);           // 4
    }
    public void op2() {
        System.out.println("B.op2() ");
        op1();              // 3.1.1.1: and 4.1.1.1:
    }
}

class C {

    private B b;
    private D d;
        
    public C(B b) {
    	System.out.println("C()");
    	this.b = b;
    }
    public void op3(D d) {
    	System.out.println("C.op3()");
    	this.d = d;
    	d.op5();			// 3.1
    }
    public void op5() {
    	System.out.println("C.op5()");
    	b.op2();			// 4.1.1
    }
}

class D {

    private B b;
    private C c;

    public D(B b) {
    	System.out.println("D()");
    	this.b = b;
    }
    public void op4(C c) {
    	System.out.println("D.op4()");
    	this.c = c;
    	c.op5();			// 4.1
    }
    public void op5() {
    	System.out.println("D.op5()");
    	b.op2();			// 3.1.1
    }
}