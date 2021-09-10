package ex01main;

import java.net.URL;
import java.util.ResourceBundle;

import ex01.dbcommon.DBCommon;
import ex01.service.MyService;
import ex01.service.MyServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MainController implements Initializable{

	Parent root;
	MyService ms;
	public void setRoot(Parent root) {
		this.root = root;
		ms.setRoot(root);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ms = new MyServiceImpl();
		DBCommon.setDBConnection();
	}
	public void memberShip() {
		ms.memberShip();
	}
	public void login() {
		ms.login();
	}

}
