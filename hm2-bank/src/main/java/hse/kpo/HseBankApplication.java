package hse.kpo;

import hse.kpo.enums.DataType;
import hse.kpo.enums.OperationType;
import hse.kpo.facade.HseBankFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

/**
 * Точка входа в приложение.
 */
@SpringBootApplication
public class HseBankApplication {
	private HseBankFacade hseBankFacade;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HseBankApplication.class, args);
		HseBankApplication app = context.getBean(HseBankApplication.class);
		try {
			app.run();
		} catch (Throwable e) {
            throw new RuntimeException(e);
        }
	}

	private void run() throws Throwable {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Enter command: ");
			if (!scanner.hasNextLine()) {
				break;
			}
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("exit")) {
				break;
			}
			switch (input) {
				case "export":
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after export");
					}
					String type = scanner.nextLine();
					hseBankFacade.exportAll(DataType.valueOf(type));
					break;
				case "import":
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after import");
					}
					type = scanner.nextLine();
					hseBankFacade.importAll(DataType.valueOf(type));
					break;
				case "addBankAccount":
					System.out.println("want id name balance");
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after import");
					}
					String[] params = scanner.nextLine().split(" ");
					hseBankFacade.addBankAccount(params[0], params[1], Integer.parseInt(params[2]));
					break;
				case "addCategory":
					System.out.println("want operationType(EARNING/SPENDING) id name");
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after import");
					}
					params = scanner.nextLine().split(" ");
					hseBankFacade.addCategory(OperationType.valueOf(params[0]), params[1], params[2]);
					break;
				case "addOperation":
					System.out.println("want id bankAccountId sum categoryId");
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after import");
					}
					params = scanner.nextLine().split(" ");
					hseBankFacade.addOperation(params[0], params[1], Integer.parseInt(params[3]), params[4]);
					break;
				case "getOperationByAccountId":
					System.out.println("want bankAccountId");
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after import");
					}
					params = scanner.nextLine().split(" ");
					System.out.println(hseBankFacade.getOperationsByAccountId(params[0]));
					break;
				case "getBankAccounts":
					System.out.println(hseBankFacade.getBankAccounts());
					break;
				case "getCategories":
					System.out.println(hseBankFacade.getCategories());
					break;
				default:
					break;
			}
		}
	}
}
