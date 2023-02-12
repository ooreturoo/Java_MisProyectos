package com.sergio.main.controller.menu;


import java.io.IOException;

import com.sergio.main.controller.windows.items.ItemsCatalogueRootController;
import com.sergio.main.model.datasources.enums.ItemsType;
import com.sergio.main.model.datasources.user.UserState;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class MenuController{

	
	@FXML
	private Pane menuRoot;
	@FXML
	private HBox rootBtnMenu;
	@FXML
	private Button btnMenu;
	@FXML
	private Button btnAnimeMenu;
	@FXML
	private Button btnMangaMenu;
	@FXML
	private Button btnUserConfig;
	
	private boolean menuOpened;
	
	
	@FXML
	private void openMenu() {
		
		if(menuOpened) {
			
			menuOpened = false;
			
			menuRoot.setPrefWidth(Region.USE_COMPUTED_SIZE);
			btnMenu.setMaxWidth(Double.MAX_VALUE);
			btnAnimeMenu.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			btnMangaMenu.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			btnUserConfig.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			
			
			
		}else {
			
			menuOpened = true;
			
			menuRoot.setPrefWidth(200);
			btnMenu.setMaxWidth(Region.USE_COMPUTED_SIZE);
			btnAnimeMenu.setContentDisplay(ContentDisplay.LEFT);
			btnMangaMenu.setContentDisplay(ContentDisplay.LEFT);
			btnUserConfig.setContentDisplay(ContentDisplay.LEFT);
			
		}
		
		
	}
	
	@FXML
	public void goToAnime() throws IOException {
		
		goToElementView();
		ItemsCatalogueRootController controller = ItemsCatalogueRootController.getInstance();
		controller.setShownItemType(ItemsType.ANIME);
		controller.resetButtons();
		controller.loadData();
		
	}
	
	@FXML
	private void goToManga() throws IOException {
		
		goToElementView();
		ItemsCatalogueRootController controller = ItemsCatalogueRootController.getInstance();
		controller.setShownItemType(ItemsType.MANGA);
		controller.resetButtons();
		controller.getInstance().loadData();


	}
	
	@FXML
	private void goToUserConfig() {
		
		if (UserState.isUserLogged()) {
			
			try {
				
				Pane userPane = FXMLLoader.load(getClass().getResource("/com/sergio/main/view/windows/user/data/userView.fxml"));
				
				Pane parent = (Pane) menuRoot.getParent();
				HBox.setHgrow(userPane, Priority.ALWAYS);
				parent.getChildren().set(1, userPane);
				
				
			}catch(IOException e) {
				
				e.printStackTrace();
				
			}
			
			
		}else {
			
			try {
				
				Pane loginPane = FXMLLoader.load(getClass().getResource("/com/sergio/main/view/windows/user/actions/loginView.fxml"));
				
				Pane parent = (Pane) menuRoot.getParent();
				HBox.setHgrow(loginPane, Priority.ALWAYS);
				parent.getChildren().set(1, loginPane);
				
				
			}catch(IOException e) {
			
				e.printStackTrace();
				
			}
			
			
		}
		
		
	}
	
	private void goToElementView() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sergio/main/view/windows/items/itemsCatalogueRootView.fxml"));
		loader.setController(ItemsCatalogueRootController.getInstance());
		ScrollPane itemsPane = loader.load();
		Pane parent = (Pane) menuRoot.getParent();
		HBox.setHgrow(itemsPane, Priority.ALWAYS);
		parent.getChildren().set(1, itemsPane);
		
		
	}
	
	

}
