/**
 * @author Joakim Wirén jowi1137
 */

public class Main {
	private Program program = new Program();

	private String[] commands = { "Available commands:", "register new dog", "list dogs", "remove dog", "increase age",
			"register new owner", "list owners", "remove owner", "assign dog", "start auction", "make bid", "list bids",
			"list auctions", "close auction", "exit" };

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.start();
	}

	public void start() {
		startMsg();
		runCommandLoop();
		closeDown();
	}

	private void startMsg() {
		System.out.println("Welcome to the dog register!\n");
		System.out.println("Enter \"list commands\" to see all available commands.");

	}

	private void runCommandLoop() {
		while (true) {
			String command = readCommand();
			switch (command) {
				case "register new dog":
					program.registerNewDog();
					break;
				case "list dogs":
					program.listDogs();
					break;
				case "increase age":
					program.increaseAge();
					break;
				case "remove dog":
					program.removeDog();
					break;
				case "register new owner":
					program.registerNewOwner();
					break;
				case "give dog":
					program.assignDog();
					break;
				case "list owners":
					program.listOwners();
					break;
				case "remove owner":
					program.removeOwner();
					break;
				case "start auction":
					program.startAuction();
					break;
				case "make bid":
					program.makeBid();
					break;
				case "list bids":
					program.listBids();
					break;
				case "list auctions":
					program.listAuctions();
					break;
				case "close auction":
					program.closeAuction();
					break;
				case "list commands":
					for (String com : commands) {
						System.out.println(com);
					}
					break;
				case "exit":
					return;
				default:
					System.out.println("Error: No such command.");
			}
		}
	}

	private String readCommand() {
		System.out.print("Command?> ");
		return program.getScanner().nextLine().toLowerCase().trim();
	}

	private void closeDown() {
		System.out.println("Exiting program. Goodbye!");
	}

}
