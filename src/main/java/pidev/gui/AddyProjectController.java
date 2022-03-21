package pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class AddyProjectController implements Initializable {
	
	
	@FXML
	private AnchorPane fieldBudget;
	
	@FXML
    private TextField fieldEstimation;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//		fieldEstimation.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent t) {
//				// TODO Auto-generated method stub
//				char ar[] = t.getCharacter().toCharArray();
//	            char ch = ar[t.getCharacter().toCharArray().length - 1];
//	            if (!(ch >= '0' && ch <= '9')) {
//	               System.out.println("The char you entered is not a number");
//	               t.consume();
//	            }
//			}
//			
//	});
		
	}
	
	@FXML
    void tapKey(KeyEvent event) {
		fieldEstimation.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
			// TODO Auto-generated method stub
			char ar[] = t.getCharacter().toCharArray();
            char ch = ar[t.getCharacter().toCharArray().length - 1];
            if (!(ch >= '0' && ch <= '9')) {
              System.out.println(ch);
               t.consume();
            }
            
		}
		
		});
		event.consume();

}
	
	
	
}
