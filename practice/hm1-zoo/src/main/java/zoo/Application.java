package zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import zoo.domains.animals.Animal;
import zoo.domains.animals.Herbo;
import zoo.domains.animals.AnimalAttributes;
import zoo.domains.animals.HerboAttributes;
import zoo.domains.nonLThings.Table;
import zoo.domains.nonLThings.ThingAttributes;
import zoo.factories.RabbitFactory;
import zoo.factories.TableFactory;
import zoo.services.ClinicService;
import zoo.services.ZooService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Application {
	@Autowired
	private ClinicService clinicService;

	@Autowired
	private ZooService zooService;


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		Application app = context.getBean(Application.class);
		app.run();

		/*SpringApplication.run(Application.class, args);

		TableFactory tf = new TableFactory();
		tf.CreateThing(new ThingAttributes(1, "good table"));

		RabbitFactory rf = new RabbitFactory();
		var res = rf.CreateAnimal(new HerboAttributes(2, 5), new ThingAttributes(1, "good rabbit"));
		System.out.println(res);*/
	}

	private void run() {
		zooService.SetClinicService(clinicService);
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Enter command: ");
			if (!scanner.hasNextLine()) {
				break;
			}
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("exit")){
				break;
			}
			switch (input) {
				case "get need food report":
					System.out.println(zooService.GetNeedFoodAmt());
					break;
				case "get animals report":
					System.out.println(zooService.GetAnimals());
					break;
				case "get things report":
					System.out.println(zooService.GetInventory());
					break;
				case "get animals fine for contact zoo":
					System.out.println(zooService.GetContactFineAnimals());
					break;
				default:
					if (!scanner.hasNextLine()) {
						System.out.println("expected line with params after adding");
					}
					var params = scanner.nextLine();
					Optional<String> res = Optional.empty();
					switch (input) {
						case "add animal":
							res = zooService.AddAnimal(params);
							break;
						case "add thing":
							res = zooService.AddThing(params);
							break;
						default:
							System.out.println("got unknown command");
					}
					if (res.isEmpty()) {
						continue;
					}
					System.out.println(res.get());
			}
		}
	}
}
