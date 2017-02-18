package math;


public class MathDiameterFatigue {

	public static double calcDiameter(String sn,String sKf,String sMa,String sKfs,String sTa,String sSe,String sSy,String sMm,String sTm ){
		double n = Double.parseDouble(sn);
		double Kf = Double.parseDouble(sKf);
		double Ma = Double.parseDouble(sMa);
		double Kfs = Double.parseDouble(sKfs);
		double Ta = Double.parseDouble(sTa);
		double Se = Double.parseDouble(sSe);
		double Sy = Double.parseDouble(sSy);
		double Mm = Double.parseDouble(sMm);
		double Tm = Double.parseDouble(sTm);

		if(Se==0) throw new IllegalArgumentException("Illegal Argument Se cant be 0");
		if(Sy==0) throw new IllegalArgumentException("Illegal Argument Sy cant be 0");


//	this part competes the inner bracket multiples of the first two

		double KfMa = Kf*Ma;
		double KfsTa = Kfs*Ta;
		double KfMm = Kf*Mm;
		double KfsTm = Kfs*Tm;

//	this part competes the inner brace

		double first = 4*(Math.pow(((KfMa)/Se), 2));
		double second = 3 * (Math.pow(((KfsTa)/Se), 2));
		double third = 4*(Math.pow(((KfMm)/Sy), 2));
		double fourth = 3*(Math.pow(((KfsTm)/Sy), 2));

//	this part competes the inner brace computed to the power of half

		double brak1 = first+second+third+fourth;
		double brak1Ex = Math.pow(brak1, 0.5);

//	this part calculates n to the constant pi

		double consPi = ((16*n)/Math.PI);
//	this part calculates the outer bracket

		double brak2 = brak1Ex*consPi;
		double overThird = 1.0/3.0;

		double result = Math.pow(brak2, overThird);
		System.out.println(" ");
		System.out.println("For a Fatigue factor of: " + n + "DE-ASME Elliptic fac.");
		System.out.println("For stress concentration factor for bending of: " + Kf);
		System.out.println("For stress concentration factor for torsion of: " + Kfs);
		System.out.println("For alternating bending moment of: " + Ma + "lbf");
		System.out.println("For Midrange Bending Moment: " + Mm + "lbf");
		System.out.println("For Alernating torque of: " + Ta + "lbf.in");
		System.out.println("For Midrange Torque: " + Tm + "lbf.in");
		System.out.println("For Se: " + Se + "kpsi");
		System.out.println("For Yeilding Strength: " + Sy + "kpsi");

		return result;
	}
	public static double calcFatigueFactor(String sd,String sKf,String sMa,String sKfs,String sTa,String sSe,String sSy,String sMm,String sTm){

		double d = Double.parseDouble(sd);
		double Kf = Double.parseDouble(sKf);
		double Ma = Double.parseDouble(sMa);
		double Kfs = Double.parseDouble(sKfs);
		double Ta = Double.parseDouble(sTa);
		double Se = Double.parseDouble(sSe);
		double Sy = Double.parseDouble(sSy);
		double Mm = Double.parseDouble(sMm);
		double Tm = Double.parseDouble(sTm);

		if(Se==0) throw new IllegalArgumentException("Illegal Argument Se cant be 0");
		if(Sy==0) throw new IllegalArgumentException("Illegal Argument Sy cant be 0");

	//this part competes the inner bracket multiples of the first two

		double KfMa = Kf*Ma;
		double KfsTa = Kfs*Ta;
		double KfMm = Kf*Mm;
		double KfsTm = Kfs*Tm;

	//this part competes the inner brace

		double first = 4*(Math.pow(((KfMa)/Se), 2));
		double second = 3 * (Math.pow(((KfsTa)/Se), 2));
		double third = 4*(Math.pow(((KfMm)/Sy), 2));
		double fourth = 3*(Math.pow(((KfsTm)/Sy), 2));

		double brak1 = first+second+third+fourth;
		double powHalf = Math.pow(brak1, 0.5);
		double constPi = 16 / ((Math.PI)*(Math.pow(d, 3)));

		double inverse = 1/(constPi*powHalf);
		System.out.println(" ");
		System.out.println("For a diameter of: " + d + "inch");
		System.out.println("For stress concentration factor for bending of: " + Kf);
		System.out.println("For stress concentration factor for torsion of: " + Kfs);
		System.out.println("For alternating bending moment of: " + Ma + "lbf");
		System.out.println("For Midrange Bending Moment: " + Mm + "lbf");
		System.out.println("For Alernating torque of: " + Ta + "lbf.in");
		System.out.println("For Midrange Torque: " + Tm + "lbf.in");
		System.out.println("For Se: " + Se + "kpsi");
		System.out.println("For Yeilding Strength: " + Sy + "kpsi");

		return inverse;

		}


}
