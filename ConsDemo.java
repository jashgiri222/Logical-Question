
public class ConsDemo {
	
	public void m1(String s) {
		System.out.println("String version");
		}
		public void m1(StringBuffer s1)
		{
		System.out.println("StringBuffer version");
		}
		public static void main(String[] args) {
		ConsDemo cd=new ConsDemo();
		cd.m1("Govind");
		cd.m1(new StringBuffer("Ballabh"));
		
		//cd.m1(null);

		 

		}

}
