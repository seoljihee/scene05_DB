package ex01.service;

import java.io.IOException;

import ex01.dbservice.DBService;
import ex01.dbservice.DBServiceImpl;
import ex01.dto.MemDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class MyServiceImpl implements MyService{
	Parent root;
	DBService db;
	//생성자 생성
	public MyServiceImpl() {db = new DBServiceImpl();}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}

	@Override
	public void memberShip() {
		System.out.println("회원가입 버튼 클릭");
		// 해당하는 객체 얻어오기
		TextField id = (TextField)root.lookup("#memId");
		TextField pwd = (TextField)root.lookup("#memPwd");
		TextField name = (TextField)root.lookup("#memName");
		//잘 나오는지 확인해보기
		System.out.println(id.getText());
		System.out.println(pwd.getText());
		System.out.println(name.getText());
		
		MemDTO dto = new MemDTO();
		dto.setId(id.getText());
		dto.setPwd(pwd.getText());
		dto.setName(name.getText());
		
		//DB쪽에 만들어줘야하는 메소드
		int result = db.insertMember(dto);
		if(result == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("회원가입에 성공했다.");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("동일한 아이디가 존재합니다.");
			alert.show();
			}
		}
	

	@Override
	public void login() {
		System.out.println("로그인 버튼 클릭");
		TextField id = (TextField)root.lookup("#fxid");
		TextField pwd = (TextField)root.lookup("#fxpwd");
		System.out.println(id.getText());
		System.out.println(pwd.getText());
		MemDTO dto = db.loginCheck(id.getText());
		
		if(dto != null) {
			if(dto.getPwd().equals(pwd.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("인증성공!");
				alert.show();
				
				//Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/ex01/dbcommon/memberview.fxml"));
				Parent viewRoot = null;
					try {
						viewRoot = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				Scene scene = new Scene(viewRoot);
				Stage s = (Stage)root.getScene().getWindow();
				s.setScene(scene);
				s.show();
				
				Label fxId = (Label)root.lookup("#fxid");
				Label fxPwd = (Label)root.lookup("#fxpwd");
				Label fxName = (Label)root.lookup("#fxname");
				
				fxId.setText(dto.getId());
				fxPwd.setText(dto.getPwd());
				fxName.setText(dto.getName());
				
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("비밀번호가 틀렸습니다.");
				alert.show();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("존재하지 않는 아이디 입니다.");
			alert.show();
		}
		
	}

}
















