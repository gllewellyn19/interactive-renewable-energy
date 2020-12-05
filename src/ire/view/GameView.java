package ire.view;

import ire.Main;
import ire.view.energyTypes.ExampleGameView;
import ire.view.energyTypes.HydroEnergyTypeView;
import ire.view.energyTypes.RenewableEnergyType;
import ire.view.energyTypes.SolarEnergyTypeView;
import ire.view.energyTypes.WindEnergyTypeView;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameView implements LanguageControls, StartEnergyTypeable {

  public static final String LANGUAGE_FOLDER = "languages/";
  public static final String DEFAULT_STARTING_LANGUAGE = "English";
  public static final String STARTING_STYLESHEET = "Default.css";
  public static final String STYLESHEETS_FOLDER = "stylesheets/";
  public static final Font DEFAULT_FONT_TITLE = new Font(26);
  public static final Font DEFAULT_FONT_DESCRIPTION = new Font(14);
  public static final Paint DEFAULT_BACKGROUND = Color.AQUA;

  private ErrorPrinting errorPrinting;
  private ResourceBundle languageResources;
  private ButtonsMaintainer buttonsMaintainer;
  private String language = DEFAULT_STARTING_LANGUAGE;
  private boolean setupSuccessful = true;
  private RenewableEnergyType currentRenewableEnergyType;
  private SceneControls sceneControls;

  public GameView(SceneControls sceneControls) {
    this.sceneControls = sceneControls;
    errorPrinting = new ErrorPrinting(this);
    try {
      languageResources = ResourceBundle.getBundle(
          Main.DEFAULT_RESOURCES_PACKAGE + LANGUAGE_FOLDER + language);
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
      setupSuccessful = false;
    }
    if (setupSuccessful) {
      buttonsMaintainer = new ButtonsMaintainer(languageResources, this,
          sceneControls, errorPrinting);
    }
  }

  /**
   * @param width  - width of new scene
   * @param height -  height of new scene
   * @return - starting scene with the default css that appears in the center of the screen (creates
   * the splash screen)
   */
  public Optional<Scene> makeAnInitialScene(int width, int height) {
    try {
      String topTextContent = languageResources.getString("startingMessage");
      Text topText = new Text(topTextContent);
      topText.setFont(DEFAULT_FONT_TITLE);
      topText.setFill(Color.CORAL);
      //BorderPane.setAlignment(topText, Pos.TOP_CENTER);
      BorderPane displayLayout = new BorderPane(getGameDescription(), topText,
          null, null, buttonsMaintainer.createEnergyOptions());
      return Optional.of(uploadCSSFile(width, height, STARTING_STYLESHEET,
          displayLayout));
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
    }
    return Optional.empty();
  }

  /**
   * @param width  - width of new scene
   * @param height -  height of new scene
   * @return - starting scene for the chosen type of renewable energy
   */
  public Optional<Scene> createGeneralEnergyTypeScene(double width, double height) {
    try {
      String topTextContent = languageResources.getString("startingMessage"+
          currentRenewableEnergyType.getEnergyType());
      Text topText = new Text(topTextContent);
      topText.setFont(DEFAULT_FONT_TITLE);
      topText.setFill(Color.CORAL);
      BorderPane displayLayout = new BorderPane(currentRenewableEnergyType.getEnergyTypePicture(),
          topText, null, buttonsMaintainer.createOptionsGeneralEnergyType(), null);
      return Optional.of(uploadCSSFile(width, height, STARTING_STYLESHEET,
          displayLayout));
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
    }
    return Optional.empty();
  }

  /**
   * @param width  - width of new scene
   * @param height -  height of new scene
   * @return - scene for the game portion of the selected renewable energy type
   */
  public Optional<Scene> createGameScreen(double width, double height) {
    try {
      //FIXME: Cams If you want a description for your game, modify the value of the key startingMessageGame+energyType
      // (i.e. startingMessageGamesolar) in the english resource bundle
      String topTextContent = languageResources.getString("startingMessageGame"+
          currentRenewableEnergyType.getEnergyType());
      Text topText = new Text(topTextContent);
      topText.setFont(DEFAULT_FONT_TITLE);
      topText.setFill(Color.CORAL);
      BorderPane displayLayout = new BorderPane(currentRenewableEnergyType.getGamePicture(),
          topText, null, null, buttonsMaintainer.createOptionsEnergyTypeGame(
              currentRenewableEnergyType.getEnergyType()));
      return Optional.of(uploadCSSFile(width, height, STARTING_STYLESHEET,
          displayLayout));
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
    }
    return Optional.empty();
  }

  /**
   * @param width  - width of new scene
   * @param height -  height of new scene
   * @return - scene for the animation portion of the selected renewable energy type
   */
  public Optional<Scene> createAnimationScreen(double width, double height) {
    try {
      String topTextContent = languageResources.getString("startingMessageAnimation"+
          currentRenewableEnergyType.getEnergyType());
      Text topText = new Text(topTextContent);
      topText.setFont(DEFAULT_FONT_TITLE);
      topText.setFill(Color.CORAL);
      BorderPane displayLayout = new BorderPane(currentRenewableEnergyType.getAnimationPicture(),
          topText, null, null, buttonsMaintainer.createOptionsEnergyTypeAnimation());
      return Optional.of(uploadCSSFile(width, height, STARTING_STYLESHEET,
          displayLayout));
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
    }
    return Optional.empty();
  }

  /*
   * Returns a description of the game to make the center of the starting screen
   */
  private Text getGameDescription() {
    //initialInputBoxes.setAlignment(Pos.TOP_CENTER);
    String textContent = languageResources.getString("gameExplanation");
    Text description = new Text(textContent);
    description.setFont(DEFAULT_FONT_DESCRIPTION);
    description.setFill(Color.CORAL);
    return description;

  }

  /**
   * @param width   - width of returned scene
   * @param height  - height of returned scene
   * @param cssFile - string with name of wanted css file
   * @return - scene with a new style sheet
   */
  public Scene uploadCSSFile(double width, double height, String cssFile, BorderPane displayLayout) {
    Scene scene = new Scene(displayLayout, width, height, DEFAULT_BACKGROUND);
    try {
      scene.getStylesheets()
          .add(getClass().getResource(Main.DEFAULT_RESOURCE_FOLDER +
              STYLESHEETS_FOLDER + cssFile).toExternalForm());
    } catch (NullPointerException e) {
      errorPrinting.printErrorMessageAlert("CSSNotFound", cssFile);
    }
    return scene;
  }

  /**
   * @param width   - width of returned scene
   * @param height  - height of returned scene
   * @param cssFile - string with name of wanted css file
   * @return - scene with a new style sheet- uses a root not a BorderPane
   */
  public Scene uploadCSSFileGame(double width, double height, String cssFile, Group root) {
    Scene scene = new Scene(root, width, height, DEFAULT_BACKGROUND);
    try {
      scene.getStylesheets()
          .add(getClass().getResource(Main.DEFAULT_RESOURCE_FOLDER +
              STYLESHEETS_FOLDER + cssFile).toExternalForm());
    } catch (NullPointerException e) {
      errorPrinting.printErrorMessageAlert("CSSNotFound", cssFile);
    }
    return scene;
  }

  @Override
  public String getLanguage() {
    return language;
  }

  @Override
  public void startNewEnergyType(String energyType) {
    energyType = energyType.toLowerCase();
    if (energyType.equals("solar")) {
      currentRenewableEnergyType = new SolarEnergyTypeView(languageResources, sceneControls);
      currentRenewableEnergyType.initializeEnergyType();
    } else if (energyType.equals("wind")) {
      currentRenewableEnergyType = new WindEnergyTypeView(languageResources, sceneControls);
      currentRenewableEnergyType.initializeEnergyType();
    } else if (energyType.equals("hydro")) {
      currentRenewableEnergyType = new HydroEnergyTypeView(languageResources, sceneControls);
      currentRenewableEnergyType.initializeEnergyType();
    }
    //FIXME: delete when this example is no longer needed cams
    else if (energyType.equals("example")){
      currentRenewableEnergyType = new ExampleGameView(languageResources, sceneControls);
      currentRenewableEnergyType.initializeEnergyType();
    }
    else {
      errorPrinting.printErrorMessageAlert("energyTypeNotFound", energyType);
    }
  }

  public void stepCurrentGame(double elapsedTime) {
    currentRenewableEnergyType.stepGame(elapsedTime);
  }

  public RenewableEnergyType getCurrentRenewableEnergyType() {
    return currentRenewableEnergyType;
  }
}
