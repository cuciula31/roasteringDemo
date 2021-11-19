package ui;

import Motion.FadeTransition;
import algorithm.Roster;
import data.Shift;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private Pane selectionPane;

    private final SelectionPane selectionPaneController = new SelectionPane();
    private final FadeTransition fadeTransition = new FadeTransition();
    private final Roster roster = new Roster();
    @FXML
    private FlowPane e1fp;
    @FXML
    private FlowPane e2fp;
    @FXML
    private FlowPane e3fp;
    @FXML
    private FlowPane e4fp;

    private List<Shift> localSSH = new ArrayList<>();

    @FXML
    private void initialize() {
        selectionPaneController.initialize(selectionPane);
        roster.testCalculate(4, 30, 2);

        localSSH = roster.getSsh(4, 30, 2);

        getColorized(0, 1, 4, 30, 2);
        getColorized(1, 1, 4, 30, 2);
        getColorized(2, 1, 4, 30, 2);
        getColorized(3, 1, 4, 30, 2);


    }

    @FXML
    private void electricianReleased() {
        selectionPaneController.electricianSelected(selectionPane);
    }

    @FXML
    private void mechanicReleased() {
        selectionPaneController.mechanicSelected(selectionPane);
    }

    @FXML
    private void hoverIn(MouseEvent event) {
        Node toHover = (Node) event.getSource();
        fadeTransition.fade(toHover, 1, 0.7, 50, 0);

    }

    @FXML
    private void hoverOut(MouseEvent event) {
        Node toHover = (Node) event.getSource();
        fadeTransition.fade(toHover, 0.7, 1, 150, 50);

    }

    private void getColorized(int employee, int solution, int employees, int wDays, int shifts) {
        List<Shift> localList = new ArrayList<>();

        for (Shift s : localSSH) {
            if (s.getWorker() == employee) {

                localList.add(s);
            }
        }

        FlowPane employeeFp;

        switch (employee) {
            case 0:
                employeeFp = e1fp;
                break;
            case 1:
                employeeFp = e2fp;
                break;
            case 2:
                employeeFp = e3fp;
                break;
            case 3:
                employeeFp = e4fp;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + employee);
        }

        switch (solution) {
            case 1:
                for (int i = 0; i < 30; i++) {
                    Pane shiftPane = (Pane) employeeFp.getChildren().get(i);
                    FlowPane fp = (FlowPane) shiftPane.getChildren().get(0);
                    Text t = new Text();
                    if(fp.getChildren().size() > 0){
                        fp.getChildren().remove(0);
                    }
                    fp.getChildren().add(t);

                    if (localList.get(i).getShift() != -1) {
                        if (localList.get(i).getShift() == 0) {
                            shiftPane.setStyle("-fx-background-color: #e74c3c; -fx-border-color: WHITE;");
                            t.setText("S1");
                            t.setStyle("-fx-font-family: impact; -fx-fill: WHITE; fx-font-size: 25;");

                        } else if (localList.get(i).getShift() == 1) {
                            t.setText("S2");
                            t.setStyle("-fx-font-family: impact; -fx-fill: WHITE; fx-font-size: 31;");

                            shiftPane.setStyle("-fx-background-color: #9b59b6;-fx-border-color: WHITE;");

                        }
                    } else {
                        t.setText("L");
                        t.setStyle("-fx-font-family: impact; -fx-fill: WHITE; fx-font-size: 25;");
                        shiftPane.setStyle("-fx-background-color: #27ae60;-fx-border-color: WHITE;");

                    }

                }
                break;

            case 2:
                for (int i = 29; i < 60; i++) {
                    Pane shiftPane = (Pane) employeeFp.getChildren().get(i - 29);
                    FlowPane fp = (FlowPane) shiftPane.getChildren().get(0);
                    Text t = new Text();
                    if (localList.get(i).getShift() != -1) {
                        if (localList.get(i).getShift() == 0) {
                            shiftPane.setStyle("-fx-background-color: #e74c3c; -fx-border-color: WHITE;");
                            t.setText("S1");
                            t.setStyle("-fx-font-family: impact; -fx-fill: WHITE; fx-font-size: 25;");
                            if (!fp.getChildren().contains(t)) {
                                fp.getChildren().add(t);
                            }
                        } else if (localList.get(i).getShift() == 1) {
                            t.setText("S2");
                            t.setStyle("-fx-font-family: impact; -fx-fill: WHITE; fx-font-size: 31;");
                            if (!fp.getChildren().contains(t)) {
                                fp.getChildren().add(t);
                            }
                            shiftPane.setStyle("-fx-background-color: #9b59b6;-fx-border-color: WHITE;");

                        }
                    } else {
                        t.setText("L");
                        t.setStyle("-fx-font-family: impact; -fx-fill: WHITE; fx-font-size: 25;");
                        if (!fp.getChildren().contains(t)) {
                            fp.getChildren().add(t);
                        }
                        shiftPane.setStyle("-fx-background-color: #27ae60;-fx-border-color: WHITE;");

                    }

                }
                break;
            case 3:
                for (int i = 59; i < 90; i++) {
                    Pane shiftPane = (Pane) employeeFp.getChildren().get(i - 59);
                    if (localList.get(i).getShift() != -1) {
                        if (localList.get(i).getShift() == 0) {
                            shiftPane.setStyle("-fx-background-color: #e74c3c; -fx-border-color: WHITE;");
                        } else if (localList.get(i).getShift() == 1) {
                            shiftPane.setStyle("-fx-background-color: #9b59b6;-fx-border-color: WHITE;");

                        }
                    } else {
                        shiftPane.setStyle("-fx-background-color: #27ae60;-fx-border-color: WHITE;");

                    }

                }
                break;
            case 4:
                for (int i = 89; i < 120; i++) {
                    Pane shiftPane = (Pane) employeeFp.getChildren().get(i - 89);
                    if (localList.get(i).getShift() != -1) {
                        if (localList.get(i).getShift() == 0) {
                            shiftPane.setStyle("-fx-background-color: #e74c3c; -fx-border-color: WHITE;");
                        } else if (localList.get(i).getShift() == 1) {
                            shiftPane.setStyle("-fx-background-color: #9b59b6;-fx-border-color: WHITE;");

                        }
                    } else {
                        shiftPane.setStyle("-fx-background-color: #27ae60;-fx-border-color: WHITE;");

                    }

                }
                break;

            case 5:
                for (int i = 119; i < 130; i++) {
                    Pane shiftPane = (Pane) employeeFp.getChildren().get(i - 119);
                    if (localList.get(i).getShift() != -1) {
                        if (localList.get(i).getShift() == 0) {
                            shiftPane.setStyle("-fx-background-color: #e74c3c; -fx-border-color: WHITE;");
                        } else if (localList.get(i).getShift() == 1) {
                            shiftPane.setStyle("-fx-background-color: #9b59b6;-fx-border-color: WHITE;");

                        }
                    } else {
                        shiftPane.setStyle("-fx-background-color: #27ae60;-fx-border-color: WHITE;");

                    }

                }
                break;

        }

    }

    @FXML
    private void firstReleased() {
        getColorized(0, 1, 4, 30, 2);
        getColorized(1, 1, 4, 30, 2);
        getColorized(2, 1, 4, 30, 2);
        getColorized(3, 1, 4, 30, 2);
    }

    @FXML
    private void secondReleased() {
        getColorized(0, 2, 4, 30, 2);
        getColorized(1, 2, 4, 30, 2);
        getColorized(2, 2, 4, 30, 2);
        getColorized(3, 2, 4, 30, 2);
    }

    @FXML
    private void thirdReleased() {
        getColorized(0, 3, 4, 30, 2);
        getColorized(1, 3, 4, 30, 2);
        getColorized(2, 3, 4, 30, 2);
        getColorized(3, 3, 4, 30, 2);
    }

    @FXML
    private void forthReleased() {
        getColorized(0, 4, 4, 30, 2);
        getColorized(1, 4, 4, 30, 2);
        getColorized(2, 4, 4, 30, 2);
        getColorized(3, 4, 4, 30, 2);
    }

    @FXML
    private void fifthReleased() {
        getColorized(0, 5, 4, 30, 2);
        getColorized(1, 5, 4, 30, 2);
        getColorized(2, 5, 4, 30, 2);
        getColorized(3, 5, 4, 30, 2);
    }

    @FXML
    private void regen() {
        localSSH.clear();
        localSSH = roster.getSsh(4,20,2);

        getColorized(0, 1, 4, 30, 2);
        getColorized(1, 1, 4, 30, 2);
        getColorized(2, 1, 4, 30, 2);
        getColorized(3, 1, 4, 30, 2);
    }
}
