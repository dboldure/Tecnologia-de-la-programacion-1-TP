package tp.p3.logic;

public class PlantsVsZombies {

	public static void main(String[] args) {
		Level level = null;
		int seed = 0;
		
		if (args.length == 1 || args.length == 2) {
			level = Level.parse(args[0]);
			
			if (level != null) {
				
				try {
					if (args.length == 2) seed = Integer.parseInt(args[1]);
					
					Game game = new Game(level, seed);
					Controller controller = new Controller(game);

					controller.run();
				} catch (RuntimeException ex) {
					System.out.println(ex.getMessage());
				}				
			}
			else {
				System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
			}
		}
		else {
			System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
		}
	}
}
