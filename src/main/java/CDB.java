import controller.Controller;
import service.ServiceCompany;
import service.ServiceComputer;
import view.View;

public class CDB {

	public static void main(String[] args) {
		ServiceComputer s1 = ServiceComputer.getInstance();
		ServiceCompany s2 = ServiceCompany.getInstance();
		Controller c = new Controller(s1, s2);
		View view = new View(c);
	}

}
