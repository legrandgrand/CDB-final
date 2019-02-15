import controller.Controller;
import service.Service;
import view.View;

public class CDB {

	public static void main(String[] args) {
		Service s = new Service(); 
		Controller c = new Controller(s);
		View view = new View(c);
	}

}
