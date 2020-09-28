public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CellWorld cellWorld1 = new CellWorld(20, 10);
		
		for (int i = 0; i < 50; i++) {
			cellWorld1.tick();
			//world1.tick();
			//world2.tick();
			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			
		}
	}

}