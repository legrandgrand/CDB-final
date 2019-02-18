import controller.Controller;
import service.Service;
import view.View;

public class CDB {

	public static void main(String[] args) {
		Service s = Service.getInstance();
		Controller c = new Controller(s);
		View view = new View(c);
	}

}
