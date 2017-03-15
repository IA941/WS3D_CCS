package br.com.cominotti.ws3d_ccs.gui;

import br.com.cominotti.ws3d_ccs.WS3DCreatureControlSystem;
import br.com.cominotti.ws3d_ccs.application.ApplicationState;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.EatAllFoodsInVisionUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.eating.EatSingleFoodUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.DeliverLeafletUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.GenerateLeafletUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.gaming.PrintLeafletsDetailsUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.HideAllThingsInVisionUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.HideSingleThingUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.UnhideAllThingsInVisionUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.hiding.UnhideSingleThingUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.moving.*;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing.PutAllThingsInVisionInSackUseCaseInput;
import br.com.cominotti.ws3d_ccs.application.creatures.use_cases.storing.PutSingleThingInSackUseCaseInput;
import br.com.cominotti.ws3d_ccs.domain.model.commons.FoodPredicates;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    @FXML
    private ComboBox<String> environmentComboBox;

    @FXML
    private Button eatSingleThingButton;

    @FXML
    private Button hideSingleThingButton;

    @FXML
    private Button unhideSingleThingButton;

    @FXML
    private Button putSingleThingInStackButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eatSingleThingButton.setDisable(true);
        hideSingleThingButton.setDisable(true);
        unhideSingleThingButton.setDisable(true);
        putSingleThingInStackButton.setDisable(true);
        environmentComboBox.setItems(
                WS3DCreatureControlSystem.getApplicationState().getObservableThings()
        );
        environmentComboBox.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    eatSingleThingButton.setDisable(
                            newValue == null || !FoodPredicates.isFoodName().test(newValue)
                    );
                    hideSingleThingButton.setDisable(newValue == null);
                    unhideSingleThingButton.setDisable(newValue == null);
                    putSingleThingInStackButton.setDisable(newValue == null);
                }
        );
    }


    public void handleMoveForwardButtonOnMousePressed(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new MoveForwardUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleMoveForwardButtonOnMouseReleased(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new StopUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleRotateLeftOnMousePressed(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new RotateLeftUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleRotateLeftOnMouseReleased(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new StopUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleRotateRightOnMousePressed(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new RotateRightUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleRotateRightOnMouseReleased(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new StopUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleMoveBackwardButtonOnMousePressed(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new MoveBackwardUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleMoveBackwardButtonOnMouseReleased(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new StopUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }


    public void handleEatAllThingsInVisionButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new EatAllFoodsInVisionUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handlePutAllThingsInVisionInSackButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new PutAllThingsInVisionInSackUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleHideAllThingsInVisionButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new HideAllThingsInVisionUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleUnhideAllThingsInVisionButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new UnhideAllThingsInVisionUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleEatSingleThingButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new EatSingleFoodUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName(), environmentComboBox.getValue()
        ));
    }

    public void handlePutSingleThingInSackButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new PutSingleThingInSackUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName(), environmentComboBox.getValue()
        ));
    }

    public void handleHideSingleThingButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new HideSingleThingUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName(), environmentComboBox.getValue()
        ));
    }

    public void handleUnhideSingleThingButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new UnhideSingleThingUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName(), environmentComboBox.getValue()
        ));
    }

    public void handleGenLeafletButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new GenerateLeafletUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handlePrintLeafletsButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new PrintLeafletsDetailsUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }

    public void handleDeliverLeafletsButtonOnMouseClicked(MouseEvent mouseEvent) {
        WS3DCreatureControlSystem.getApplicationState().handle(new DeliverLeafletUseCaseInput(
                WS3DCreatureControlSystem.getApplicationState().getCreatureName()
        ));
    }
}
