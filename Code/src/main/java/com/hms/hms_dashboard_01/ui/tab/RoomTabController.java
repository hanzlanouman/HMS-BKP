package com.hms.hms_dashboard_01.ui.tab;

import com.hms.hms_dashboard_01.Factory.HMSFactory;
import com.hms.hms_dashboard_01.controllers.RoomController;
import com.hms.hms_dashboard_01.dal.DALRoomManager;
import com.hms.hms_dashboard_01.model.entities.Room;
import com.hms.hms_dashboard_01.utility.path;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoomTabController implements Initializable  {

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, String> roomAllocatedTo;

    @FXML
    private TableColumn<Room, Integer> roomFee;

    @FXML
    private TableColumn<Room, String> roomLocation;

    @FXML
    private TableColumn<Room, Integer> roomNo;

    @FXML
    private TableColumn<Room, String> roomStatus;



//    Holds the data for the table view in the room tab of the admin/warden
ObservableList<Room> roomList = FXCollections.observableArrayList();
// Assign the values to the columns of the table view in the room tab of the admin/warden
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RoomController roomController = HMSFactory.getInstanceOfRoomController();
    roomList = FXCollections.observableArrayList(roomController.getAllRooms());
        loadTable(roomList);
    }
    public void loadTable(ObservableList<Room> roomList){
        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
//        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomFee.setCellValueFactory(new PropertyValueFactory<>("roomFee"));
        roomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        roomAllocatedTo.setCellValueFactory(new PropertyValueFactory<>("roomAssignedTo"));
//        roomLocation.setCellValueFactory(new PropertyValueFactory<>("roomLocation"));

        roomTable.setItems(roomList);
    }

    public void addRoom(ActionEvent e) throws IOException {
//       set the stage scene to add room
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path.getPath("tab", "add_room_form")));
        Parent root1 = loader.load();
        RoomFormController roomFormController = loader.getController();
        roomFormController.setRoomController(this);
        stage.setTitle("Add Room");
        stage.setScene(new Scene(root1, 1054, 650));
        stage.show();
    }
    public void updateRoomTable() {
        roomList.clear();
        RoomController roomController = HMSFactory.getInstanceOfRoomController();
        roomList.addAll(roomController.getAllRooms());
    }

    public void deleteRoom(ActionEvent e) {
//        Get selected object from the table view
        Room room = roomTable.getSelectionModel().getSelectedItem();
        RoomController roomController = HMSFactory.getInstanceOfRoomController();
        if(room == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No room selected");
            alert.setContentText("Please select a room to delete");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete room");
            alert.setContentText("Are you sure you want to delete this room?");
            if(alert.showAndWait().get().getText().equals("OK")){
                roomController.deleteRoom(room.getRoomNo());
                updateRoomTable();
            }
        }

    }

}
