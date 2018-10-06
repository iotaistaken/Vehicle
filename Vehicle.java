package lab_1;

import java.util.ArrayList;


public class Vehicle implements Vecv
{
	int n_wl; 		// Number of wheels
	boolean engine; // whether has a engine or not
	String name; 	// Name of the owner
	String model; 	// Name of the model
	boolean Tpp_policy; // whether the vehicle holds TPP or not
	boolean pol_valid; // Checks whether policy is valid no not
	int damage;		   // Amount of damage to the object;
	
	public Vehicle(int wls, boolean eng, String nm, String mod, boolean tpp, boolean pl_vald)
	{
		this.n_wl = wls;
		this.engine = eng;
		this.name = nm;
		this.model = mod;
		this.Tpp_policy = tpp;
		this.pol_valid = pl_vald;
	}
	public Vehicle(int wls, boolean eng, String nm, String mod)
	{
		this.n_wl = wls;
		this.engine = eng;
		this.name = nm;
		this.model = mod;
	}
	
	
	public int[] settle(Vehicle x) 
	{
		this.damage = (int) Math.ceil(Math.random()*100); // Assigning damage to self
		x.damage = (int) Math.ceil(Math.random()*100); // Assigning damage to incoming
		
		if (!this.engine) // Non engine vehicle.
		{
			int[] arr = {0,-1,this.damage,1,-1,x.damage}; //No settlement is done.
			return arr;
		}
		else
		{   // All here are engine enabled initiating vehicle.
			if (this.n_wl==2 && this.Tpp_policy && this.pol_valid) // Third party policy
			{
				int[] arr = {0,0,this.damage,1,50,x.damage};
				return arr;
			}
			else if (this.n_wl==4 && !this.Tpp_policy && this.pol_valid) // Comprehensive Policy
			{
				int[] arr = {0,50,this.damage,1,80,x.damage};
				return arr;
			}
			else
			{
				int[] arr = {0,10,this.damage,1,0,x.damage};
				return arr;
			}
		}
		
	}
	public static void main(String[] args) 
	{
		ArrayList<Vehicle> ins = new ArrayList<Vehicle>();
		Vehicle car = new Vehicle(4,true,"Lambo","Aventador",false,true); // Car of valid policy
		Vehicle car2 = new Vehicle(4,true,"Koenisseg", "Agera",false,false); // Car of invalid policy
		Vehicle bike = new Vehicle(2,true,"Kawa","Ninja",true,true); // Bike of valid policy
		Vehicle bike2 = new Vehicle(2,true,"Hero", "Honda",true,false); // Bike of invalid policy
		Vehicle bycle = new Vehicle(2,false,"Atlas","GoldLine");		// Bicycle
		Vehicle Rick = new Vehicle(3,false,"Jag","uar");				// Rickshaw equivalent to Bicycle
		ins.add(car);
		ins.add(car2);
		ins.add(bike);
		ins.add(bike2);
		ins.add(bycle);
		ins.add(Rick);
		ArrayList<Integer> op = new ArrayList<Integer>();
		for (Vehicle io:ins)
		{
			if (io.engine)
			{
				System.out.println("Wheels- " + io.n_wl + ", Engine- " + io.engine+ ", Name- " + io.name + ", TPP Policy- " + io.Tpp_policy + ", Policy is Valid or not, " + io.pol_valid);						
			}
			else
			{
				System.out.println("Wheels- " + io.n_wl + ", Engine- " + io.engine+ ", Name- " + io.name + ", TPP Policy- Null"   + ", Policy is Valid or not- Not Valid");						
			}
		}
		System.out.println();
		System.out.println("Here we Start the RoadRage");
		System.out.println();
		for (int i=0; i<ins.size(); i++)
		{
			ArrayList<Vehicle> iop = ins;
			Vehicle v1 = iop.remove(i);
			for (Vehicle v2:iop)
			{
				System.out.println("Vehicle1- " + "Model_Name- " + v1.model + " Owner- " + v1.name + " collided with vehicle2 " + "Model- "+ v2.model + " Owner- " + v2.name);
				int[] arr = v1.settle(v2);
				System.out.println("Damages awarded to vehicle1: " + arr[2]);
				System.out.println("Damages awarded to vehicle2: " + arr[5]);
				System.out.println("Settlement details. ");
				if (arr[1]==0)
				{
					System.out.println("          " + "vehicle1 damage status, after settlement: " + arr[2]);
				}
				else if (arr[1]==-1)
				{
					System.out.println("          Non-Engine Vehicle Generated the accident it. Hence No Claim");
				}
				else
				{
					System.out.println("          " + "vehicle1 damage status, after settlement: " + arr[2]*(1.0-(arr[1]/100.0)));
				}
				// For Vehicle2
				if (arr[4]==0)
				{
					System.out.println("          " + "vehicle2 damage status, after settlement: " + arr[5]);
				}
				else if (arr[3]==-1)
				{
					System.out.println("          Non-Engine Vehicle Generated the accident it. Hence No Claim");
				}	
				else
				{
					System.out.println("          " + "vehicle2 damage status, after settlement: " + arr[5]*(1.0-(arr[4]/100.0)));
				}
				System.out.println();
				System.out.println("Next Collision");
				System.out.println();
			}
			iop.add(i, v1);
		}
		System.out.println();
		System.out.println("End of the show RoadRage");
		System.out.println();
	}

}
