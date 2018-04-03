import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	private static void menu()
	{
		String fileName = "";
		
		
		GenerateLisp.generateLisp(fsaInput(fileName));
	}
	
	private static FSA fsaInput(String fileName)
	{
		FSA fsa = new FSA(fileName);
		
		if(fsa.isSuccess())
		{
			return fsa;
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("FSA");
		Button loadBtn = new Button();
		loadBtn.setText("Load FSA'");
		
		StackPane root = new StackPane();
		root.getChildren().add(loadBtn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}