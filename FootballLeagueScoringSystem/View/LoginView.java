package FootballLeagueScoringSystem.View;

import FootballLeagueScoringSystem.Control.ViewTrans;
import FootballLeagueScoringSystem.Module.League;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginView extends Pane {
    private League theLeague;

    public LoginView(Stage stage) {
        theLeague = new League();
        this.setMinSize(1200, 600);
        this.setMaxSize(1920, 1080);
        generate(stage,theLeague);
    }

    private void generate(Stage stage, League theLeague) {
        Label welcome = new Label("欢迎使用足球联赛评分系统！");
        welcome.setLayoutX(300);
        welcome.setLayoutY(100);
        welcome.setMinSize(600,80);
        welcome.setStyle("-fx-font-scale: 32px;-fx-background-color: blue;");
        Label accountL = new Label("输入账号：");
        accountL.setLayoutX(450);
        accountL.setLayoutY(180);
        accountL.setMinSize(100,50);
        TextField accountInput = new TextField();
        accountInput.setPromptText("输入账号");
        accountInput.setLayoutX(accountL.getLayoutX()+accountL.getMinWidth());
        accountInput.setLayoutY(accountL.getLayoutY());
        accountInput.setPrefSize(200,50);
        Label passwordL = new Label("输入密码：");
        passwordL.setLayoutX(accountL.getLayoutX());
        passwordL.setLayoutY(accountL.getLayoutY()+accountL.getMinHeight());
        passwordL.setMinSize(accountL.getMinWidth(),accountL.getMinHeight());
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("输入密码");
        passwordInput.setLayoutX(passwordL.getLayoutX()+passwordL.getMinWidth());
        passwordInput.setLayoutY(passwordL.getLayoutY());
        passwordInput.setPrefSize(accountInput.getPrefWidth(),accountInput.getPrefHeight());
        Button confirm = new Button("确认登录");
        confirm.setLayoutX(passwordL.getLayoutX());
        confirm.setLayoutY(passwordL.getLayoutY()+passwordL.getMinHeight());
        confirm.setMinSize(80,50);
        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String account = accountInput.getText();
                String password = passwordInput.getText();
                if(theLeague.checkUser(account,password)!=null){
                    ViewTrans vt = new ViewTrans();
                    theLeague.setUserStatus(theLeague.checkUser(account,password));
                    vt.toMainView(theLeague,stage);
                }
            }
        });
        Button visitor = new Button("访客登录");
        visitor.setLayoutX(confirm.getLayoutX()+confirm.getMinWidth());
        visitor.setLayoutY(confirm.getLayoutY());
        visitor.setMinSize(80,50);
        visitor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ViewTrans vt = new ViewTrans();
                theLeague.setUserStatus("游客登录");
                vt.toMainView(theLeague,stage);
            }
        });
        this.getChildren().addAll(welcome,accountL,accountInput,passwordL,passwordInput,confirm,visitor);
    }
}
