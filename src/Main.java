/**
 * @author Joakim Wirén jowi1137
 */

public class Main {
	private Program program = new Program();

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.start();
	}

	public void start() {
		startUp();
		runCommandLoop();
		closeDown();
	}

	private void startUp() {
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
					System.out.println(
							"Available commands:\nregister new dog\nlist dogs\nremove dog\nincrease age\nregister new owner\nlist owners\nremove owner\nassign dog\nstart auction\nmake bid\nlist bids\nlist auctions\nclose auction\nexit");
					break;
				case "exit":
					return;
				default:
					System.out.println("Error: No such command.");
			}
		}
	}

	// fixa scanner
	private String readCommand() {
		System.out.print("Command?> ");
		return program.getScanner().nextLine().toLowerCase().trim();
	}

	private void closeDown() {
		System.out.println("Exiting program. Goodbye!");
	}

}
